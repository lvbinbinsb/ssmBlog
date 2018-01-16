package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.entity.CategoryNav;
import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.entity.Mark;
import com.neusoft.ssmpro.service.BlogService;
import com.neusoft.ssmpro.service.BlogSolrService;
import com.neusoft.ssmpro.service.CategoryNavService;
import com.neusoft.ssmpro.service.CategoryVService;
import com.neusoft.ssmpro.service.MarkService;

@RestController
public class UEditorBlogController {

	@Autowired
	private MarkService markService;
	
	@Autowired
	private CategoryNavService categoryNavService;
	
	@Autowired
	private BlogSolrService solrService;
	
	@Autowired
	private CategoryVService categoryVService;
	
	@Autowired
	private BlogService blogService;
	
	private Integer markPageSize=5;
	private Integer cateGoryNavPageSize=5;
	private Integer cateGoryVPageSize=5;
	
	@RequestMapping(value="/showBlogMarkPage",method=RequestMethod.GET)
	public PageInfo<Mark> showBlogAddPage(@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageHelper.startPage(pn, markPageSize);
		List<Mark> marks = markService.listMarkPage();
		//获得一级标签列表
		PageInfo<Mark> info=new PageInfo<Mark>(marks);
		return info;
	}
	
	@RequestMapping(value="/showBlogCategoryNavPage",method=RequestMethod.GET)
	public PageInfo<CategoryNav> showBlogCategoryNavPage(){
		List<CategoryNav> categoryNavs = categoryNavService.listByStatus("1");
		//获得一级标签列表
		PageInfo<CategoryNav> info=new PageInfo<CategoryNav>(categoryNavs);
		return info;
	}
	
	@RequestMapping(value="/showBlogCategoryVPage",method=RequestMethod.GET)
	public PageInfo<CategoryV> showBlogCategoryVPage(@RequestParam("navId")Integer navId){
		List<CategoryV> categoryVs = categoryVService.loadAllByNavId(navId);
		//获得一级标签列表
		PageInfo<CategoryV> info=new PageInfo<CategoryV>(categoryVs);
		return info;
	}
	
	@RequestMapping(value="/addBlog",method=RequestMethod.POST)
	public boolean addBlog(Blog blog,@RequestParam("blogMarkId")List<Integer> markIds) {
//		System.out.println(markIds);
		boolean flag=blogService.insertBlog(blog);
		Blog b = blogService.loadBlogById(blog.getBlogId());
		solrService.buildIndexAfterAddBlog(b);
		//更新博客与标签关系
		boolean addMarkFlag=markService.updateBlogAndMark(markIds,blog.getBlogId().longValue());
		return flag&&addMarkFlag;
	}
	
}
