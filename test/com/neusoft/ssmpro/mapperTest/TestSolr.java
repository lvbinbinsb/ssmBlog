package com.neusoft.ssmpro.mapperTest;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.neusoft.ssmpro.entity.Blog;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-solr.xml" })
@WebAppConfiguration
public class TestSolr {

	@Autowired
	public HttpSolrServer httpSolrServer;
	
	@Test
	public void test1() throws Exception {
		 try {
			 SolrQuery query = new SolrQuery();
		        //3.设置查询条件
		        query.set("q", "blog_id:1");
		        //4.执行查询
		        QueryResponse queryResponse = httpSolrServer.query(query);
		        
		        List<Blog> list = queryResponse.getBeans(Blog.class);
		        System.out.println(list);
		        System.out.println("===========");
		        //5.取文档列表public class SolrDocumentList extends ArrayList<SolrDocument>
		        SolrDocumentList documentList = queryResponse.getResults();
		        for (SolrDocument solrDocument : documentList) {
		            //取各个文档信息
		            System.out.println("商品id:"+solrDocument.get("blog_id")+" ");
		            System.out.println("商品标题:"+solrDocument.get("blog_title")+" ");
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
