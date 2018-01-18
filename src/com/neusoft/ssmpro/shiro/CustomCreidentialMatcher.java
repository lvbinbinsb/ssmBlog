package com.neusoft.ssmpro.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * @ClassName: CustomCreidentialMatcher
 * @Description: 自定义密码比较器
 * @author: LBB
 * @date: 2018年1月18日 上午10:17:20
 */
public class CustomCreidentialMatcher extends HashedCredentialsMatcher implements InitializingBean {

	private static final Logger logger = LogManager.getLogger(CustomCreidentialMatcher.class);

	// 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
	private Cache<String, AtomicInteger> passwordRetryCache;

	public CustomCreidentialMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	private PasswordHash passwordHash;

	public PasswordHash getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(PasswordHash passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		/*
		 * KaptchaUsernamePasswordToken kupt=(KaptchaUsernamePasswordToken) token;
		 * String username=(String) kupt.getPrincipal();
		 * logger.info("CustomCreidentialMatcher 认证  doCredentialsMatch  "+username);
		 * boolean match=false; try { match = super.doCredentialsMatch(token, info); }
		 * catch (Exception e) { e.printStackTrace(); }
		 * logger.info("自定义认证器  doCredentialsMatch 结果:"+match); return match;
		 */
		KaptchaUsernamePasswordToken kupt = (KaptchaUsernamePasswordToken) token;
		String username = (String) kupt.getUsername();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (null == retryCount) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		logger.info(username+" 认证次数:"+retryCount.get());
		if (retryCount.incrementAndGet() > 5) {
			logger.warn("username: " + username + " tried to login more than 5 times in period");
			throw new ExcessiveAttemptsException(
					"username: " + username + " tried to login more than 5 times in period");
		}else {
			passwordRetryCache.put(username, retryCount);
		}
		
		boolean matches = false;
		try {
			matches=super.doCredentialsMatch(token, info);
		}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
		}
		if (matches) {
			// clear retry data
			logger.info(username+"认证成功");
			passwordRetryCache.remove(username);
		}
		return matches;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(passwordHash, "you must set passwordHash!");
		super.setHashAlgorithmName(passwordHash.getAlgorithmName());
		super.setHashIterations(passwordHash.getHashIterations());
	}

}
