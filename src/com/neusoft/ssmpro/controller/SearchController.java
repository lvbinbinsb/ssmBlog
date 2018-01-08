package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.service.BlogSolrService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private static Integer pageSize=3;
	@Autowired
	private BlogSolrService blogSolrService;
	
	@RequestMapping(value="/{keyWord}",method=RequestMethod.POST)
	public PageInfo<Blog> loadBlogSolrByPage(@PathVariable("keyWord")String keyWord,@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageInfo<Blog> pageInfo=new PageInfo<Blog>();
		blogSolrService.loadBlogSolrByPage(keyWord,pn,pageSize,pageInfo);
		return pageInfo;
	}
	
}
