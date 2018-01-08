package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.entity.CategoryNav;
import com.neusoft.ssmpro.service.CategoryNavService;

@RestController
@RequestMapping("/categoryNav")
public class CategoryNavController {
	
	@Autowired
	private CategoryNavService categoryNavService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<CategoryNav> loadategoryNav(){
		return categoryNavService.loadategoryNav();
	}
	
	

}
