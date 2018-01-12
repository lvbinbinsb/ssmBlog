package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.vo.BlogComment;

public interface BlogCommentService {

	List<BlogComment> loadBlogCommentByBlogID(Long blogId);
}
