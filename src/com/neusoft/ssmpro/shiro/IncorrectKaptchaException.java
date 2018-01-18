package com.neusoft.ssmpro.shiro;

import org.apache.shiro.authc.AuthenticationException;
@SuppressWarnings("serial")
public class IncorrectKaptchaException extends AuthenticationException {

	public IncorrectKaptchaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncorrectKaptchaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IncorrectKaptchaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IncorrectKaptchaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
