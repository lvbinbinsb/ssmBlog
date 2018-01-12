package com.neusoft.ssmpro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.CategoryNav;
import com.neusoft.ssmpro.service.CategoryNavService;
import com.neusoft.ssmpro.ztree.ZtreeVo;


@RestController
@RequestMapping("/categoryNav")
public class CategoryNavController {
	
	@Autowired
	private CategoryNavService categoryNavService;
	
	private Integer listPageSize=4;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<CategoryNav> loadategoryNav(@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageHelper.startPage(pn, 7);
		return categoryNavService.loadategoryNav();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public PageInfo<CategoryNav> loadCategoryNavPost(@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageHelper.startPage(pn, listPageSize);
		List<CategoryNav> list=categoryNavService.loadAllCategoryNav();
		PageInfo<CategoryNav> info =new PageInfo<CategoryNav>(list, 3);
		return info;
	}
	
	@RequestMapping(value="/listByMap",method=RequestMethod.POST)
	public List<CategoryNav> listByStatus(String categorynavStatus){
		return categoryNavService.listByStatus(categorynavStatus);
	}
	
	@RequestMapping(value="/add")
	public boolean add(CategoryNav categoryNav) {
		return categoryNavService.addCateGoryNav(categoryNav);
	}
	
	@RequestMapping(value="/switchstatus")
	public boolean switchstatus(CategoryNav categoryNav) {
		return categoryNavService.switchstatus(categoryNav);
	}

	@RequestMapping(value="/del/{categoryNavId}",method=RequestMethod.GET)
	public boolean delCategoryNav(@PathVariable("categoryNavId")Integer categoryNavId) {
		return categoryNavService.deleteCategoryNav(categoryNavId);
	}
	
	@RequestMapping(value="/editCategory",method=RequestMethod.POST)
	public boolean editCategory(CategoryNav categoryNav) {
		return categoryNavService.editCategory(categoryNav);
	}
	
	@RequestMapping(value="/getZtree")
	public List<ZtreeVo> getZtree(String categorynavStatus){
		List<CategoryNav> categoryNavs = categoryNavService.listByStatus(categorynavStatus);
		List<ZtreeVo> trees=new ArrayList<ZtreeVo>();
		categoryNavs.forEach((cv)->{
			ZtreeVo treeNode=new ZtreeVo();
			treeNode.setId(cv.getCategorynavId());
			treeNode.setPid(0);
			treeNode.setName(cv.getCategorynavName());
			treeNode.setIsParent(true);
			trees.add(treeNode);
		});
		return trees;
	}
	
}
