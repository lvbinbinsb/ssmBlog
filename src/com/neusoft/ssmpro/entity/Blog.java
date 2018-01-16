package com.neusoft.ssmpro.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Blog implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */

	private static final long serialVersionUID = -4113079677441067676L;

	//Field是solrJ的注释  用于schema.xml对应
	@Field("blog_id")
	private Integer blogId;
	@Field("blog_no")
	private Integer blogNo;
	@Field("blog_title")
	private String blogTitle;
	@Field("blog_praise")
	private Integer blogPraise;
	@Field("blog_read")
	private Integer blogRead;
	@Field("blog_comment")
	private Integer blogComment;
	@Field("blog_status")
	private String blogStatus;
	@Field("categorynavid")
	private Integer categorynavid;
	@Field("categoryvid")
	private Integer categoryvid;
	@Field("categorypointid")
	private Integer categorypointid;
	@Field("blog_ctime")
	private Date blogCtime;
	@Field("blog_content")
	private String blogContent;

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Integer getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(Integer blogNo) {
		this.blogNo = blogNo;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public Integer getBlogPraise() {
		return blogPraise;
	}

	public void setBlogPraise(Integer blogPraise) {
		this.blogPraise = blogPraise;
	}

	public Integer getBlogRead() {
		return blogRead;
	}

	public void setBlogRead(Integer blogRead) {
		this.blogRead = blogRead;
	}

	public Integer getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(Integer blogComment) {
		this.blogComment = blogComment;
	}

	public String getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
	}

	public Integer getCategorynavid() {
		return categorynavid;
	}

	public void setCategorynavid(Integer categorynavid) {
		this.categorynavid = categorynavid;
	}

	public Integer getCategoryvid() {
		return categoryvid;
	}

	public void setCategoryvid(Integer categoryvid) {
		this.categoryvid = categoryvid;
	}

	public Integer getCategorypointid() {
		return categorypointid;
	}

	public void setCategorypointid(Integer categorypointid) {
		this.categorypointid = categorypointid;
	}

	public Date getBlogCtime() {
		return blogCtime;
	}

	public void setBlogCtime(Date blogCtime) {
		this.blogCtime = blogCtime;
	}

	public String getBlogContent() {
		return blogContent;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogNo=" + blogNo + ", blogTitle=" + blogTitle + ", blogPraise="
				+ blogPraise + ", blogRead=" + blogRead + ", blogComment=" + blogComment + ", blogStatus=" + blogStatus
				+ ", categorynavid=" + categorynavid + ", categoryvid=" + categoryvid + ", categorypointid="
				+ categorypointid + ", blogCtime=" + blogCtime + ", blogContent=" + blogContent + "]";
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
    
}