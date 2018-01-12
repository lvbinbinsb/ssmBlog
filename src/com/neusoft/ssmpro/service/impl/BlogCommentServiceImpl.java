package com.neusoft.ssmpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.mapper.CommentContentMapper;
import com.neusoft.ssmpro.service.BlogCommentService;
import com.neusoft.ssmpro.vo.BlogComment;

@Service
public class BlogCommentServiceImpl implements BlogCommentService{

	@Autowired
	private CommentContentMapper commentContentMapper;

	@Override
	public List<BlogComment> loadBlogCommentByBlogID(Long blogId) {
		return commentContentMapper.loadBlogCommentByBlogId(blogId);
	}
	
	
}
