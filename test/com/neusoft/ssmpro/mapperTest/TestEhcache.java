package com.neusoft.ssmpro.mapperTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.mapper.BlogMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-solr.xml" })
public class TestEhcache {

	@Autowired
	private BlogMapper blogMapper;
	
	@Test
	public void test01() {
		System.out.println("第一次");
		Blog blog = blogMapper.selectByPrimaryKey(1);
		System.out.println(blog.getBlogTitle());
		
		System.out.println("第二次");
		Blog blog2 = blogMapper.selectByPrimaryKey(1);
		System.out.println(blog2.getBlogTitle());
		
		System.out.println("第三次");
		Blog blog3 = blogMapper.selectByPrimaryKey(1);
		System.out.println(blog3.getBlogTitle());
	}
	
}
