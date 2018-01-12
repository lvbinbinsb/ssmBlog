package com.neusoft.ssmpro.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.service.CategoryVService;
import com.neusoft.ssmpro.ztree.ZtreeVo;

@RestController
@RequestMapping("/categoryV")
public class CategoryVController {

	@Autowired
	private CategoryVService categoryVService;

	private Integer categoryVPageSize = 5;

	@RequestMapping(value = "/{navId}", method = RequestMethod.GET)
	public List<CategoryV> getCategoryV(@PathVariable(value = "navId") Integer navId) {
		return categoryVService.loadByNavId(navId);
	}

	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public List<Integer> loadCateGoryFirstIds() {
		return categoryVService.loadCategoryVIds();
	}

	@RequestMapping(value = "/listbypage", method = RequestMethod.POST)
	public PageInfo<CategoryV> listbypage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam("categorynavId") Integer categorynavId) {
		PageHelper.startPage(pn, categoryVPageSize);
		List<CategoryV> list=categoryVService.listbypage(categorynavId);
		PageInfo<CategoryV> pageInfo=new PageInfo<CategoryV>(list,3);
		return pageInfo;
	}
	
	@RequestMapping(value="/getNodes",method=RequestMethod.POST)
	public List<ZtreeVo> getNodes(@RequestParam("id")Integer id){
		List<ZtreeVo> trees=categoryVService.loadNodesByNavId(id);
		return trees;
	}
	
	@RequestMapping(value="/updateNode",method=RequestMethod.POST)
	public boolean  updateNode(ZtreeVo node) {
//		System.out.println(node);
		return categoryVService.updateNode(node);
	}
	
	@RequestMapping(value="/deleteNode",method=RequestMethod.GET)
	public boolean  deleteNode(Integer id) {
//		System.out.println(node);
		return categoryVService.deleteNode(id);
	}
	
	@RequestMapping(value="/addNode",method=RequestMethod.POST)
	public ZtreeVo  addNode(ZtreeVo newNode) {
		newNode=categoryVService.insertNode(newNode);
		return newNode;
	}
	
	@RequestMapping(value="/changeCategoryNav",method=RequestMethod.POST)
	public boolean  changeCategoryNav(ZtreeVo newNode) {
		boolean flag=false;
		try {
			flag=categoryVService.changeCategoryNav(newNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
