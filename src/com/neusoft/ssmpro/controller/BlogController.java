package com.neusoft.ssmpro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.service.BlogService;
import com.neusoft.ssmpro.vo.BlogVo;

@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(
			@RequestParam(value = "categorynavId", required = false, defaultValue = "2") Integer categorynavId,
			@RequestParam(value = "categoryvid", required = false, defaultValue = "1") Integer categoryvid,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(pn, 3);
		map.put("categorynavid", categorynavId);
		map.put("categoryvid", categoryvid);
		map.put("pn", pn);
		map.put("pageSize", 3);
		// map.put("categorypointid", 2);
		List<Blog> list = blogService.loadBlogByCondition(map);
		PageInfo<Blog> info = new PageInfo<Blog>(list, 5);
		// System.out.println(list.size());
		return info;
	}
	
	@RequestMapping(value="/getHomePage",method = RequestMethod.GET)
	public PageInfo getHomePage(){
		PageHelper.startPage(1, 3);
		Map<String, Object> map=new HashMap<>();
		map.put("blogOrder","blog_ctime");
		map.put("shunxu","asc");
		List<Blog> list = blogService.loadBlogAndMarkByMoreCondition(map);
		PageInfo<Blog> pageInfo=new PageInfo<Blog>(list,5);
		return pageInfo;
	}
	
	@RequestMapping(value = "/{blogId}", method = RequestMethod.POST)
	public BlogVo getBlogById(@PathVariable("blogId") Long blogId,
			@RequestParam(value = "blogRead", defaultValue = "0",required=false) Integer blogRead,
			@RequestParam(value = "blogPraise", defaultValue = "0",required=false) Integer blogPraise, HttpServletRequest request) {
		String ip = getIpAddr(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blogId);
		List<BlogVo> list = blogService.loadBlogAndMarkByCondition(map);
		// 验证BlogId合法性
		boolean flag = blogService.checkBlogExist(blogId);
		if (flag) {
			Map<String, Object> param = new HashMap<String, Object>();
			map.put("blogId", blogId);
			if (blogRead != null) {
				map.put("blogRead", blogRead);
			}
			if (blogPraise != null) {
				map.put("blogPraise", blogPraise);
			}
			// blog存在 合法的
			// 更新阅读数
			if (!isExistBlogIp(blogId, ip)) {
				blogService.updateBlog(map);
				insertBlogIp(blogId,ip);
			}
		}
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@RequestMapping(value = "/praise/{blogId}", method = RequestMethod.POST)
	public void changePraise(@PathVariable("blogId") Long blogId,
			@RequestParam(value = "blogRead", defaultValue = "0") Integer blogRead,
			@RequestParam(value = "blogPraise", defaultValue = "0") Integer blogPraise) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blogId);
		List<BlogVo> list = blogService.loadBlogAndMarkByCondition(map);
		// 验证BlogId合法性
		boolean flag = blogService.checkBlogExist(blogId);
		if (flag) {
			Map<String, Object> param = new HashMap<String, Object>();
			map.put("blogId", blogId);
			if (blogRead != null) {
				map.put("blogRead", blogRead);
			}
			if (blogPraise != null) {
				map.put("blogPraise", blogPraise);
			}
			// blog存在 合法的
			// 更新阅读数
			blogService.updateBlog(map);
		}
	}
	@RequestMapping(value="/top3hot",method= {RequestMethod.GET,RequestMethod.POST})
	public List<Blog> getTop3HotBlog(){
		return blogService.getTop3HotBlog();
	}
	
	// checkIp
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
//		System.out.println("x-forwarded-for ip: " + ip);
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			// System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			// System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			// System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			// System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			// System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			// System.out.println("getRemoteAddr ip: " + ip);
		}
		// System.out.println("获取客户端ip: " + ip);
		return ip;
	}

	/**
	 * 
	 * @Title: isExistBlogIp
	 * @Description: TODO
	 * @param blog_id
	 * @param ip
	 * @return
	 * @return: boolean
	 */
	public boolean isExistBlogIp(Long blog_id, String ip) {
		String sql = "select count(id) from tbl_blog_ip where blog_id =?  and visitor_ip= ?";
		Integer size = jdbcTemplate.queryForObject(sql, new Object[] { blog_id, ip }, Integer.class);
		return size > 0 ? true : false;
	}
	
	public void insertBlogIp(Long blog_id, String ip) {
			String sql = "insert into  tbl_blog_ip (blog_id ,visitor_ip ) values (? ,? )";
			jdbcTemplate.update(sql, blog_id,ip);
	}
}
