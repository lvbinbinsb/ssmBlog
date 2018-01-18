package com.neusoft.ssmpro.controller;

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
import com.neusoft.ssmpro.entity.Mark;
import com.neusoft.ssmpro.service.MarkService;

@RestController
@RequestMapping("/mark")
public class MarkController {
	
	@Autowired
	private MarkService markService;

	private Integer pageSize=5;
	
	@RequestMapping(value="/blog/{blogId}",method=RequestMethod.GET)
	public List<Mark> loadMarkByBlogId(@PathVariable("blogId")Long blogId){
		return markService.loadByBlodId(blogId);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public PageInfo<Mark> list(@RequestParam(value="pn",defaultValue="1")Integer pn){
		PageHelper.startPage(pn, pageSize);
		List<Mark> list=markService.listMarkPage();
		PageInfo<Mark> info=new PageInfo<Mark>(list,3);
		return info;
	}
	
	@RequestMapping(value="/add")
	public boolean addMark(Mark mark) {
		return markService.addMark(mark);
	}
	
	@RequestMapping(value="/delete")
	public boolean deleteMark(@RequestParam("markId")Integer markId) {
		return markService.deleteMark(markId);
	}
	
	@RequestMapping(value="/switchstatus")
	public boolean switchstatus(Mark mark) {
		return markService.switchstatus(mark);
	}
	
	@RequestMapping(value="/edit")
	public boolean editMark(Mark mark) {
		return markService.editMark(mark);
	}
}
