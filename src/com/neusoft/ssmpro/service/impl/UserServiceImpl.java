package com.neusoft.ssmpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.tblUser;
import com.neusoft.ssmpro.mapper.tblUserMapper;
import com.neusoft.ssmpro.service.UserService;
import com.neusoft.ssmpro.shiro.PasswordHash;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordHash passwordHash;
	
	@Autowired
	private tblUserMapper userMapper;

	public tblUser getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

	
	public boolean addUser(tblUser user) {
		user.setUserPassword(passwordHash.toHex(user.getUserPassword(), user.getUserName()));
		user.setUserSalt(user.getUserName());
		return userMapper.addUser(user)>0?true:false;
	}


	@Override
	public boolean checkUserName(String userName) {
		return userMapper.checkByUserName(userName)>0?false:true;
	}
	
	
}
