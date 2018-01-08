package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.entity.CategoryNav;

public interface CategoryNavService {
	/**
	 * @Title: loadategoryNav 
	 * @Description: 返回前七个一级菜单
	 * @return: List<CategoryNav>
	 */
	List<CategoryNav> loadategoryNav();
	
}
