package com.neusoft.ssmpro.service;

import com.neusoft.ssmpro.entity.tblUser;

public interface UserService {

	tblUser getUserByName(String username);
	
	boolean addUser(tblUser user);

	/**
	 * 
	 * @Title: checkUserName 
	 * @Description: TODO
	 * @param userName
	 * @return返回true代表用户名字可用  返回false代表用户名不可用
	 * @return: boolean
	 */
	boolean checkUserName(String userName);

}
