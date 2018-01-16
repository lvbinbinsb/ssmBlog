package com.neusoft.ssmpro.mapperTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.entity.Global;
import com.neusoft.ssmpro.entity.Mark;
import com.neusoft.ssmpro.mapper.BlogMapper;
import com.neusoft.ssmpro.mapper.CategoryNavMapper;
import com.neusoft.ssmpro.mapper.CategoryVMapper;
import com.neusoft.ssmpro.mapper.CommentContentMapper;
import com.neusoft.ssmpro.mapper.GlobalMapper;
import com.neusoft.ssmpro.mapper.MarkMapper;
import com.neusoft.ssmpro.vo.BlogComment;
import com.neusoft.ssmpro.vo.BlogVo;
import com.neusoft.ssmpro.ztree.ZtreeVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-solr.xml" })
public class TestDataSource {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private GlobalMapper mapper;
	
	@Autowired
	private CategoryNavMapper categoryNavMapper;
	
	@Autowired
	private CategoryVMapper categoryVMapper;

	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	private MarkMapper markMapper;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommentContentMapper ccMapper;
	
	@Test
	public void addBlog() {
		Blog blog=new Blog();
		blog.setBlogTitle("这是一条测试博客");
		blog.setBlogStatus("1");
		blog.setBlogContent("xixixiix");
		blog.setCategorynavid(2);
		blog.setCategoryvid(4);
		int result = blogMapper.insert(blog);
		System.out.println(result+",结果Id为"+blog.getBlogId());
	}
	
	
	@Test
	public void testNode() {
		ZtreeVo newNode=new ZtreeVo();
		newNode.setId(35);
		newNode.setPid(6);
		int i = categoryVMapper.changeCategoryNav(newNode);
		System.out.println(i);
	}
	
	
	
	@Test
	public void testDs() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(def);
		for (int i = 1030; i < 1036; i++) {
			
			transactionManager.commit(transactionStatus);
		}
	}

	
	@Test
	public void testBlogExist() {
//		System.out.println(blogMapper.checkBlogExist(150l));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("blogId", 1);
		map.put("blogRead", "false");
		map.put("blogPraise", -1);
		blogMapper.updateBlogByMap(map);
	}
	
	@Test
	public void testBlogComment() {
		List<BlogComment> list = ccMapper.loadBlogCommentByBlogId(4l);
		System.out.println(list.size());
		list.forEach((bc)->{System.out.println(bc);});
	}
	
	@Test
	public void testjdbcTemplate() {
		String sql="select count(id) from tbl_blog_ip where blog_id =?  and visitor_ip= ?";
		Integer size= jdbcTemplate.queryForObject(sql,new Object[] {5,"127.0.0.1"},Integer.class);
		System.out.println(size);
	}
	
	@Test
	public void testMapper() {
			Global global = mapper.selectByPrimaryKey(1);
			//System.out.println(global);
			List<Blog> list = blogMapper.loadTop3HotBlog();
			list.forEach((b)->{System.out.println(b);});
	}
	
	@Test
	public void test2() {
		categoryNavMapper.loadCategoryNavFirstSeven().forEach((cn)->{System.out.println(cn);});
	}
	
	@Test
	public void test3() {
		List<CategoryV> list = categoryVMapper.loadCategoryVByNavId(2);
		list.forEach((cv)->{System.out.println(cv);});
	}
	
	@Test
	public void test4() {
		Map<String, Object> map=new HashMap<>();
//		map.put("categorynavid", 2);
		map.put("blogId", 2);
		List<Blog> list = blogMapper.loadBlogByCondition(map);
		list.forEach((b)->{System.out.println(b);});
	}
	
	@Test
	public void test05() {
		Map<String, Object> map=new HashMap<String,Object>();
//		PageHelper.startPage(1,2);
		map.put("blog_id", 2);
//		map.put("categorynavid", 2);
		List<BlogVo> list = blogMapper.loadBlogAndMarkByCondition(map);
		list.forEach((bv)->{System.out.println(bv.getBlogTitle());System.out.println(bv.getMarks());});
		
	}
	
	@Test
	public void test06() {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("start", 0);
		map.put("pageSize",2);
		map.put("categorynavid",2);
		List<BlogVo> list = blogMapper.loadBlogAndMarkByCondition(map);
		System.out.println(list.size());
	}
	
	@Test
	public void test07() {
		List<Mark> list = markMapper.loadByBlogId(1l);
		System.out.println(list);
		
		List<Integer> lists = categoryVMapper.loadFirstNavVIds();
		System.out.println(lists);
	}
}
