package com.neusoft.ssmpro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.entity.tblUser;
import com.neusoft.ssmpro.service.UserService;
import com.neusoft.ssmpro.shiro.IncorrectKaptchaException;
import com.neusoft.ssmpro.shiro.KaptchaUsernamePasswordToken;
import com.neusoft.ssmpro.shiro.PasswordHash;
import com.neusoft.ssmpro.util.StringUtils;
import com.neusoft.ssmpro.vo.MSG;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	private static final Logger LOG = LogManager.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public MSG test(tblUser user, @RequestParam("verify") String verify, HttpSession session, String remember_me,
			HttpServletRequest request) {
		LOG.info(user);
		LOG.info("验证码为:" + verify);
		LOG.info("存储验证码为:" + session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY));
		String sessionVerifyCode = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (sessionVerifyCode != null && StringUtils.isNotBlank(verify)) {
			if (sessionVerifyCode.equalsIgnoreCase(verify)) {
				KaptchaUsernamePasswordToken token = new KaptchaUsernamePasswordToken(user.getUserName(),
						user.getUserPassword().toCharArray(), "1".equals(remember_me) ? true : false,
						request.getRemoteHost(), verify);
				try {
					SecurityUtils.getSubject().login(token);
					LOG.info("当前Subject对象为:" + SecurityUtils.getSubject());
					LOG.info("当前对象是否认证:" + SecurityUtils.getSubject().isAuthenticated());
					return MSG.Success();
				} catch (IncorrectKaptchaException e) {
					return MSG.fail().add("erroInfo", "验证码错误");
				} catch (IncorrectCredentialsException ice) {
					LOG.error("IncorrectCredentialsException" + user.getUserName());
					return MSG.fail().add("erroInfo", "密码输入错误");
				} catch (ExcessiveAttemptsException e) {
					return MSG.fail().add("erroInfo", "用户尝试太频繁,请半小时后重试");
				} catch (UnknownAccountException e) {
					return MSG.fail().add("erroInfo", "用户名不存在");
				} catch (AuthenticationException e) {
					return MSG.fail().add("erroInfo", "用户名或密码错误");
				}
			} else {
				return MSG.fail().add("erroInfo", "验证码输入有误");
			}
		} else {
			return MSG.fail().add("erroInfo", "验证码不能为空");
		}
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST, RequestMethod.GET })
	public MSG register(tblUser registerUser, String verify, HttpSession session) {
		String sessionVerifyCode = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (sessionVerifyCode != null && StringUtils.isNotBlank(verify)) {
			if (sessionVerifyCode.equalsIgnoreCase(verify)) {
				// 验证码成功
				// 注册用户并密码加密存储
				boolean result = userService.addUser(registerUser);
				if (result) {
					return MSG.Success();
				} else {
					return MSG.fail().add("erroInfo", "对不起,注册失败");
				}
			}
		}
		return MSG.fail().add("erroInfo", "验证码错误");
	}

	
	@RequestMapping(value="/getCurUserName",method=RequestMethod.POST)
	public MSG  getCurUserName() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return MSG.Success().add("curUserName", subject.getPrincipal());
		}
		return MSG.fail();
	}
}
