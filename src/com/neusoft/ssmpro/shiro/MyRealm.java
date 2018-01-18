package com.neusoft.ssmpro.shiro;

import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.neusoft.ssmpro.entity.tblUser;
import com.neusoft.ssmpro.service.RoleService;
import com.neusoft.ssmpro.service.UserService;
public class MyRealm extends AuthorizingRealm{
	
	private static final Logger LOG=LogManager.getLogger(MyRealm.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 用户简单权限增加
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		Set<String> roleNames = roleService.getRoleSet(userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		LOG.info(userName+"拥有权限:"+info.getRoles());
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOG.info("自定义realm  doGetAuthenticationInfo开始认证"+token);
		KaptchaUsernamePasswordToken kupt=(KaptchaUsernamePasswordToken) token;
		String username = kupt.getUsername();
		if(username==null) {
			throw new AccountException("用户名不能为空");
		}
		//根据用户名查找
		tblUser curUser = userService.getUserByName(username);
		if(curUser==null) {
			throw new UnknownAccountException("用户不存在！");
		}
		Object principal=username;
		Object hashedCredentials=curUser.getUserPassword();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, ByteSource.Util.bytes(curUser.getUserSalt()), getName());
		return info;
	}
	

}
