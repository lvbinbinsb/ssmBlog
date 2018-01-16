package com.neusoft.ssmpro.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;

public interface BlogSolrService {

	void loadBlogSolrByPage(String keyWord, Integer pn, Integer pageSize, PageInfo<Blog> pageInfo);

	void buildIndexAfterAddBlog(Blog blog);
	
	
}
