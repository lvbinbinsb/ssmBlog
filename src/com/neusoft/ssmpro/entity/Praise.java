package com.neusoft.ssmpro.entity;

import java.util.Date;

public class Praise {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_praise.praise_id
     *
     * @mbggenerated
     */
    private Integer praiseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_praise.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_praise.blogid
     *
     * @mbggenerated
     */
    private Integer blogid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_praise.praise_ctime
     *
     * @mbggenerated
     */
    private Date praiseCtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_praise.praise_id
     *
     * @return the value of tbl_praise.praise_id
     *
     * @mbggenerated
     */
    public Integer getPraiseId() {
        return praiseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_praise.praise_id
     *
     * @param praiseId the value for tbl_praise.praise_id
     *
     * @mbggenerated
     */
    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_praise.username
     *
     * @return the value of tbl_praise.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_praise.username
     *
     * @param username the value for tbl_praise.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_praise.blogid
     *
     * @return the value of tbl_praise.blogid
     *
     * @mbggenerated
     */
    public Integer getBlogid() {
        return blogid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_praise.blogid
     *
     * @param blogid the value for tbl_praise.blogid
     *
     * @mbggenerated
     */
    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_praise.praise_ctime
     *
     * @return the value of tbl_praise.praise_ctime
     *
     * @mbggenerated
     */
    public Date getPraiseCtime() {
        return praiseCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_praise.praise_ctime
     *
     * @param praiseCtime the value for tbl_praise.praise_ctime
     *
     * @mbggenerated
     */
    public void setPraiseCtime(Date praiseCtime) {
        this.praiseCtime = praiseCtime;
    }
}