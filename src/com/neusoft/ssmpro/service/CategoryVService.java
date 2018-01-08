package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.entity.CategoryV;

public interface CategoryVService {

	List<CategoryV> loadByNavId(Integer navId);
	
	List<Integer> loadCategoryVIds();
}
