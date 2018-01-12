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

	boolean addCateGoryNav(CategoryNav categoryNav);

	List<CategoryNav> loadAllCategoryNav();

	boolean switchstatus(CategoryNav categoryNav);

	boolean deleteCategoryNav(Integer categoryNavId);

	boolean editCategory(CategoryNav categoryNav);
	
	//返回状态为1的集合
	List<CategoryNav> listByStatus(String categorynavStatus);
	
}
