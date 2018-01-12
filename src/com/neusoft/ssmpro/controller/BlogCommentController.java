package com.neusoft.ssmpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.service.BlogCommentService;
import com.neusoft.ssmpro.vo.BlogComment;

@Controller
@RequestMapping("/blogComment")
public class BlogCommentController {

	@Autowired
	private BlogCommentService blogCommentService;
	
	@RequestMapping(value="/{blogId}",method=RequestMethod.GET)
	@ResponseBody
	public Object getComment(@PathVariable("blogId")Long blogId) {
		List<BlogComment> list = blogCommentService.loadBlogCommentByBlogID(blogId);
		System.out.println(list);
		return list;
	}
}
