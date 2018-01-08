package com.neusoft.ssmpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.mapper.CategoryVMapper;
import com.neusoft.ssmpro.service.CategoryVService;
@Service
public class CategoryVServiceImpl implements CategoryVService{

	@Autowired
	private CategoryVMapper categoryVMapper;
	
	@Override
	public List<CategoryV> loadByNavId(Integer navId) {
		return categoryVMapper.loadCategoryVByNavId(navId);
	}

	@Override
	public List<Integer> loadCategoryVIds() {
		return categoryVMapper.loadFirstNavVIds();
	}

}
