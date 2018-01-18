package com.neusoft.ssmpro.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Authenticator.Success;

public class MSG implements Serializable{
	//200为OK   100代表程序有误
	private Integer code;
	
	private String message;
	
	private Map<String,Object> result=new HashMap<String,Object>();	
	
	public MSG(Integer code) {
		super();
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public MSG() {
		super();
	}

	
	
	public static MSG Success() {
		return new MSG(200);
	}
	
	public static MSG fail() {
		return new MSG(100);
	}
	
	public  MSG add(String key,Object value) {
		result.put(key, value);
		return this;
	}
}
