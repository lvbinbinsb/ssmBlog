package com.neusoft.ssmpro.mapperTest;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;

public class TestPassword {

	public static void main(String[] args) {
		System.out.println(new SimpleHash("MD5", "liqian123", "1021167471@qq.com", 2).toString());
	}
}
