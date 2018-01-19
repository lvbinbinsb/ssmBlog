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


	@Override
	public boolean checkOldPwd(tblUser user) {
		user.setUserPassword(passwordHash.toHex(user.getUserPassword(), user.getUserName()));
		int result=userMapper.checkOldPwd(user);
		return result>0?true:false;
	}


	@Override
	public boolean changePwd(tblUser user) {
		user.setUserPassword(passwordHash.toHex(user.getUserPassword(),user.getUserName() ));
		return userMapper.changePwd(user)>0?true:false;
	}
	
	
}
