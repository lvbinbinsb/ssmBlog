package com.neusoft.ssmpro.service.impl;


import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.mapper.BlogMapper;
import com.neusoft.ssmpro.service.BlogService;
import com.neusoft.ssmpro.vo.BlogVo;
@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper blogMapper;

	@Override
	public List<Blog> loadBlogByCondition(Map<String, Object> map) {
		return blogMapper.loadBlogByCondition(map);
	}

	@Override
	public List<BlogVo> loadBlogAndMarkByCondition(Map<String, Object> map, PageInfo<BlogVo> info) {
		List<BlogVo> list = blogMapper.loadBlogAndMarkByCondition(map);
		//对结果集合进行操作  返回集合只包括三个对象;
				if(list.size()<3&&list.size()>0) {
					info.setList(list);
					return list;
				}else{
					List<BlogVo> subList = Arrays.asList(list.get(0),list.get(1),list.get(2));	
					info.setList(subList);
					return subList;
				}
	}
	
	public List<BlogVo> loadBlogAndMarkByCondition(Map<String, Object> map) {
		List<BlogVo> list = blogMapper.loadBlogAndMarkByCondition(map);
		return list==null?null:list;
	}

	@Override
	public boolean checkBlogExist(Long blogId) {
		return blogMapper.checkBlogExist(blogId)==null?false:true;
	}

	@Override
	public void updateBlog(Map<String, Object> map) {
		blogMapper.updateBlogByMap(map);
	}

	@Override
	public List<Blog> getTop3HotBlog() {
		return blogMapper.loadTop3HotBlog();
	}

	@Override
	public List<Blog> loadBlogAndMarkByMoreCondition(Map<String, Object> map) {
		return blogMapper.loadBlogByMoreCondition(map);
	}


}
