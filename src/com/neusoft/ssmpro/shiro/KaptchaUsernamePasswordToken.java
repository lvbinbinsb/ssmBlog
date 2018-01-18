package com.neusoft.ssmpro.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
@SuppressWarnings("serial")
public class KaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private String kaptcha;
	
	public KaptchaUsernamePasswordToken(String username, char[] password,  
            boolean rememberMe, String host, String kaptcha) {  
        super(username, password, rememberMe, host);  
        this.kaptcha = kaptcha;  
    }

	public String getKaptcha() {
		return kaptcha;
	}

	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}  
	
	
}
