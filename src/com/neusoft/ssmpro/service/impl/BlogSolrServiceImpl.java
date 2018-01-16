package com.neusoft.ssmpro.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.service.BlogSolrService;

@Service("blogSolrService")
public class BlogSolrServiceImpl implements BlogSolrService {

	@Autowired
	private HttpSolrServer httpSolrServer;

	@Override
	public void loadBlogSolrByPage(String keyWord, Integer pn, Integer pageSize, PageInfo<Blog> pageInfo) {
		pageInfo.setPageNum(pn);
		pageInfo.setPageSize(pageSize);

		// solrJ查询
		SolrQuery query = new SolrQuery();
		// 3、设置查询条件
		query.set("q", "blog_title:"+keyWord, "blog_content:spring"+keyWord);// 设置查询关键字
		query.setSort("blog_id", ORDER.desc);// 按照id降序排列
		int start=(pn-1)*pageSize;
		query.setStart(start);
		query.setRows(pageSize);// 分页条件
		query.set("df", "blog_title");
		// 开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("blog_content");
		query.addHighlightField("blog_title");
		query.setHighlightSimplePre("<font color=red>");
		query.setHighlightSimplePost("</font>");
		// 查询并获取相应的结果！
		QueryResponse response = null;
		try {
			response = httpSolrServer.query(query);
			// 设置总结果集数
			pageInfo.setTotal(response.getResults().getNumFound());
			Map<String, Map<String, List<String>>> hiMap = response.getHighlighting();
			List<Blog> list = response.getBeans(Blog.class);
			pageInfo.setSize(list.size());
			for (Blog blog : list) {
				Map<String, List<String>> map = hiMap.get(blog.getBlogId().toString());
				System.out.println(blog.getBlogId());
				blog.setBlogTitle(map.get("blog_title").get(0));
				blog.setBlogContent(map.get("blog_content").get(0));
			}
			pageInfo.setList(list);
			setPageInfo(pageInfo);
		} catch (SolrServerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: setPageInfo
	 * @Description: TODO
	 * @param pageInfo
	 *            需要包含 当前页 结果集 总记录数 每页大小 当前页数量
	 * @return: void
	 */
	public void setPageInfo(PageInfo pageInfo) {
		if (pageInfo != null) {
			pageInfo.setPages(pageInfo.getTotal() % pageInfo.getPageSize() == 0
					? (int) (pageInfo.getTotal() / pageInfo.getPageSize())
					: (int) (pageInfo.getTotal() / pageInfo.getPageSize() + 1));
			pageInfo.setPrePage((pageInfo.getPageNum() == 1 ? 1 : pageInfo.getPageNum() - 1));
			pageInfo.setNextPage(
					(pageInfo.getPageNum() == pageInfo.getPages() ? pageInfo.getPages() : pageInfo.getPageNum() + 1));
			pageInfo.setIsFirstPage(pageInfo.getPageNum() == 1 ? true : false);
			pageInfo.setIsLastPage(pageInfo.getPageNum() == pageInfo.getPages() ? true : false);
			pageInfo.setHasNextPage(pageInfo.getPageNum() != pageInfo.getPages() ? true : false);
			pageInfo.setHasPreviousPage(pageInfo.getPageNum() != 1 ? true : false);
			int[] navigatepageNums=null;
			if(pageInfo.getPages()>3) {
				navigatepageNums=new int[3];
			}else {
				navigatepageNums=new int[pageInfo.getPages()>1?pageInfo.getPages():1];
			}
			for(int i=0;i<navigatepageNums.length;i++) {
				navigatepageNums[i]=i+1;
			}
			pageInfo.setNavigatepageNums(navigatepageNums);
		}
	}

	@Override
	public void buildIndexAfterAddBlog(Blog blog) {
		try {
			httpSolrServer.addBean(blog);
			httpSolrServer.commit();
		} catch (IOException e) {
			try {
				httpSolrServer.rollback();
			} catch (SolrServerException | IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SolrServerException e) {
			try {
				httpSolrServer.rollback();
			} catch (SolrServerException | IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	
	
}
