package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.service.CategoryVService;

@RestController
@RequestMapping("/categoryV")
public class CategoryVController {

	@Autowired
	private CategoryVService categoryVService;
	
	@RequestMapping(value="/{navId}",method=RequestMethod.GET)
	public List<CategoryV> getCategoryV(@PathVariable(value="navId") Integer navId){
		return categoryVService.loadByNavId(navId);
	}
	
	@RequestMapping(value="/load",method=RequestMethod.GET)
	public List<Integer> loadCateGoryFirstIds(){
		return categoryVService.loadCategoryVIds();
	}
}
