package com.neusoft.ssmpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.service.UserService;
import com.neusoft.ssmpro.vo.MSG;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/checkUserName",method= {RequestMethod.POST})
	public MSG checkUserName(String userName) {
		boolean ifExist=userService.checkUserName(userName);
		return MSG.Success().add("result", ifExist);
	}
	
}
