package com.neusoft.ssmpro.mapper;

import java.util.List;
import java.util.Map;

import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.vo.BlogVo;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer blogId);

    int insert(Blog record);

    Blog selectByPrimaryKey(Integer blogId);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);
    
    List<Blog> loadBlogByCondition(Map<String, Object> map);
    
    List<BlogVo> loadBlogAndMarkByCondition(Map<String, Object> map);

	Blog checkBlogExist(Long blogId);

	void updateBlogByMap(Map<String, Object> map);

	List<Blog> loadTop3HotBlog();
	
	List<Blog> loadBlogByMoreCondition(Map<String, Object> map);
}