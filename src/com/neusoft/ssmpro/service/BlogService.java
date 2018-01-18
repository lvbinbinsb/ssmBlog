package com.neusoft.ssmpro.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.vo.BlogVo;

public interface BlogService {
	
	
	List<Blog> loadBlogByCondition(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: loadBlogAndMarkByCondition 
	 * @Description: TODO
	 * @param map  blog_id
	 * @param info 
	 * @return
	 * @return: List<BlogVo>
	 */
	List<BlogVo> loadBlogAndMarkByCondition(Map<String, Object> map, PageInfo<BlogVo> info);
	
	List<BlogVo> loadBlogAndMarkByCondition(Map<String, Object> map);

	boolean checkBlogExist(Long blogId);

	//更新blog
	void updateBlog(Map<String, Object> map);

	List<Blog> getTop3HotBlog();

	List<Blog> loadBlogAndMarkByMoreCondition(Map<String, Object> map);

	List<Blog> getAllBlog();

	boolean switchStatus(Long blogId, String status);

	boolean deleteBlog(Long blogId);

	boolean insertBlog(Blog blog);
	
	Blog loadBlogById(long blogId);

	boolean editBlog(Blog blog);

	boolean addBlogDemo(Blog blog);
	
}
