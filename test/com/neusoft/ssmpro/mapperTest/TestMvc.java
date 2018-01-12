package com.neusoft.ssmpro.mapperTest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml","classpath:spring-mvc.xml","classpath:spring-solr.xml"})
@WebAppConfiguration
public class TestMvc
{
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testBlogComment() throws Exception {
		String str = mockMvc.perform(get("/blogComment/"+4)).andReturn().getResponse().getContentAsString();
		System.out.println(str);
	}
	
	
	@Test
	public void test1() throws Exception, Exception {
//		System.out.println(mockMvc);
		
		String str = mockMvc.perform(get("/categoryNav/")).andReturn().getResponse().getContentAsString();
		System.out.println(str);
	}
	
	@Test
	public void testBlog() throws Exception, Exception {
//		String str = mockMvc.perform(get("/blog/list")).andReturn().getResponse().getContentAsString();
//		System.out.println(str);
		
		String SS = mockMvc.perform(post("/blog/list").param("pn", "1").param("categorynavId", "2")).andReturn().getResponse().getContentAsString();
		System.out.println(SS);
	}
	
}
