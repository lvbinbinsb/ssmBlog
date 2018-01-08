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
	
	
}
