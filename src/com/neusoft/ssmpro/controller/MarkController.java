package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.entity.Mark;
import com.neusoft.ssmpro.service.MarkService;

@RestController
@RequestMapping("/mark")
public class MarkController {
	
	@Autowired
	private MarkService markService;

	@RequestMapping(value="/blog/{blogId}",method=RequestMethod.GET)
	public List<Mark> loadMarkByBlogId(@PathVariable("blogId")Long blogId){
		return markService.loadByBlodId(blogId);
	}
}
