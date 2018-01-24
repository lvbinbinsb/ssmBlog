package com.neusoft.ssmpro.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.service.CategoryVService;
import com.neusoft.ssmpro.vo.MSG;
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
	@RequiresRoles(value= {"superAdmin"})
	public boolean  updateNode(ZtreeVo node) {
//		System.out.println(node);
		return categoryVService.updateNode(node);
	}
	
	@RequestMapping(value="/deleteNode",method=RequestMethod.GET)
	@RequiresRoles(value= {"superAdmin"})
	public boolean  deleteNode(Integer id) {
//		System.out.println(node);
		return categoryVService.deleteNode(id);
	}
	
	@RequestMapping(value="/addNode",method=RequestMethod.POST)
	@RequiresRoles(value= {"superAdmin"})
	public MSG  addNode(@Validated ZtreeVo newNode,BindingResult result) {
		List<ObjectError> errors = result.getAllErrors();
		Map<String,String> errorInfos=new HashMap<String,String>();
		if(errors!=null&&errors.size()>0) {
		for (ObjectError objectError : errors) {
				errorInfos.put(objectError.getObjectName(),objectError.getDefaultMessage());
			}
		return MSG.fail().add("errors",errorInfos );
		}
		newNode=categoryVService.insertNode(newNode);
		return MSG.Success().add("newNode", newNode);
	}
	
	@RequestMapping(value="/changeCategoryNav",method=RequestMethod.POST)
	@RequiresRoles(value= {"superAdmin"})
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
