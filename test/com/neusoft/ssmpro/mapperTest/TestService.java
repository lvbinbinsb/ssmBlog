package com.neusoft.ssmpro.mapperTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.service.BlogCommentService;
import com.neusoft.ssmpro.service.BlogService;
import com.neusoft.ssmpro.service.BlogSolrService;
import com.neusoft.ssmpro.service.CategoryVService;
import com.neusoft.ssmpro.vo.BlogVo;
import com.neusoft.ssmpro.ztree.ZtreeVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml","classpath:spring-mvc.xml","classpath:spring-solr.xml"})
public class TestService {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogSolrService solrService;
	
	@Autowired
	private BlogCommentService bcService;
	
	@Autowired
	private CategoryVService CategoryVService;
	
	@Test
	public void test3() {
		ZtreeVo newNode=new ZtreeVo();
		newNode.setName("测试呀");
		newNode.setPid(7);
		ZtreeVo node = CategoryVService.insertNode(newNode);
		System.out.println(node);
	}
	
	
	@Test
	public void test() {
		bcService.loadBlogCommentByBlogID(4l).forEach((bc)->{System.out.println(bc);});
	}
	
	@Test
	public void test2() {
		solrService.loadBlogSolrByPage("mysql", 1, 3, new PageInfo());
	}
	
	
	
	@Test
	public void test1() {
		Integer pn=1;
		Integer categorynavId=2;
		PageHelper.startPage(pn, 4);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("categorynavid", categorynavId);
		List<BlogVo> list= blogService.loadBlogAndMarkByCondition(map,null);
		list.forEach((bv)->{System.out.println(bv.getBlogTitle());});
		//根据标签决定吧
		System.out.println(map);
	}
		


}

