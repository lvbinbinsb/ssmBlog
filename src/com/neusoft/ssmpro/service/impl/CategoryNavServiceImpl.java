package com.neusoft.ssmpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.CategoryNav;
import com.neusoft.ssmpro.mapper.CategoryNavMapper;
import com.neusoft.ssmpro.service.CategoryNavService;

@Service("categoryNavService")
public class CategoryNavServiceImpl implements CategoryNavService{

	@Autowired
	private CategoryNavMapper categoryNavMapper;

	@Override
	public List<CategoryNav> loadategoryNav() {
		return categoryNavMapper.loadCategoryNavFirstSeven();
	}

	public boolean addCateGoryNav(CategoryNav categoryNav) {
		try {
		categoryNavMapper.addGetPriId(categoryNav);
//		System.out.println(categoryNav.getCategorynavId());
		categoryNavMapper.updateNoId(categoryNav.getCategorynavId());
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryNav> loadAllCategoryNav() {
		return categoryNavMapper.loadAllCategoryNav();
	}

	@Override
	public boolean switchstatus(CategoryNav categoryNav) {
		
		if(categoryNav.getCategorynavStatus().equals("0")) {
			categoryNav.setCategorynavStatus("1");
		}else if(categoryNav.getCategorynavStatus().equals("1")) {
			categoryNav.setCategorynavStatus("0");
		}
//		System.out.println(categoryNav);
		return categoryNavMapper.switchstatus(categoryNav);
	}

	@Override
	public boolean deleteCategoryNav(Integer categoryNavId) {
		return categoryNavMapper.deleteByPrimaryKey(categoryNavId)>0?true:false;
	}

	@Override
	public boolean editCategory(CategoryNav categoryNav) {
		return categoryNavMapper.editCategory(categoryNav);
	}

	@Override
	public List<CategoryNav> listByStatus(String categorynavStatus) {
		// TODO Auto-generated method stub
		return categoryNavMapper.listByStatus(categorynavStatus);
	}
	
	
}
