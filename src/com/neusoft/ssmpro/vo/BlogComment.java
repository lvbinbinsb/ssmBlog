package com.neusoft.ssmpro.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BlogComment implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Timestamp getCommentContentCtime() {
		return commentContentCtime;
	}

	public void setCommentContentCtime(Timestamp commentContentCtime) {
		this.commentContentCtime = commentContentCtime;
	}

	public String getCommentContentQuestion() {
		return commentContentQuestion;
	}

	public void setCommentContentQuestion(String commentContentQuestion) {
		this.commentContentQuestion = commentContentQuestion;
	}

	public String getCommentContentAnswer() {
		return commentContentAnswer;
	}

	public void setCommentContentAnswer(String commentContentAnswer) {
		this.commentContentAnswer = commentContentAnswer;
	}

	public Integer getCommentContentId() {
		return commentContentId;
	}

	public void setCommentContentId(Integer commentContentId) {
		this.commentContentId = commentContentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * SELECT c.comment_id , c.username ,c.blogid , cc.comment_content_ctime
	 * ,cc.comment_content_id ,cc.comment_content_question
	 * ,cc.comment_content_answer from tbl_comment c ,tbl_comment_content cc where
	 * c.comment_id=cc.commentid and c.blogid=4
	 */
	
	private Integer commentId;
	
	private String username;
	
	private Long blogId;
	
	private Timestamp commentContentCtime;
	
	private String commentContentQuestion;
	
	private String commentContentAnswer;
	
	private Integer commentContentId;

	@Override
	public String toString() {
		return "BlogComment [commentId=" + commentId + ", username=" + username + ", blogId=" + blogId
				+ ", commentContentCtime=" + commentContentCtime + ", commentContentQuestion=" + commentContentQuestion
				+ ", commentContentAnswer=" + commentContentAnswer + ", commentContentId=" + commentContentId + "]";
	}
	
	
	
}
