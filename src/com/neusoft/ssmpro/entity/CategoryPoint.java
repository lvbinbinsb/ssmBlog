package com.neusoft.ssmpro.entity;

import java.util.Date;

public class CategoryPoint {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categorypoint_id
     *
     * @mbggenerated
     */
    private Integer categorypointId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categorypoint_no
     *
     * @mbggenerated
     */
    private Integer categorypointNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categorypoint_name
     *
     * @mbggenerated
     */
    private String categorypointName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categorypoint_status
     *
     * @mbggenerated
     */
    private String categorypointStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categoryvid
     *
     * @mbggenerated
     */
    private Integer categoryvid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_point.categorypoint_ctime
     *
     * @mbggenerated
     */
    private Date categorypointCtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categorypoint_id
     *
     * @return the value of tbl_category_point.categorypoint_id
     *
     * @mbggenerated
     */
    public Integer getCategorypointId() {
        return categorypointId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categorypoint_id
     *
     * @param categorypointId the value for tbl_category_point.categorypoint_id
     *
     * @mbggenerated
     */
    public void setCategorypointId(Integer categorypointId) {
        this.categorypointId = categorypointId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categorypoint_no
     *
     * @return the value of tbl_category_point.categorypoint_no
     *
     * @mbggenerated
     */
    public Integer getCategorypointNo() {
        return categorypointNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categorypoint_no
     *
     * @param categorypointNo the value for tbl_category_point.categorypoint_no
     *
     * @mbggenerated
     */
    public void setCategorypointNo(Integer categorypointNo) {
        this.categorypointNo = categorypointNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categorypoint_name
     *
     * @return the value of tbl_category_point.categorypoint_name
     *
     * @mbggenerated
     */
    public String getCategorypointName() {
        return categorypointName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categorypoint_name
     *
     * @param categorypointName the value for tbl_category_point.categorypoint_name
     *
     * @mbggenerated
     */
    public void setCategorypointName(String categorypointName) {
        this.categorypointName = categorypointName == null ? null : categorypointName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categorypoint_status
     *
     * @return the value of tbl_category_point.categorypoint_status
     *
     * @mbggenerated
     */
    public String getCategorypointStatus() {
        return categorypointStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categorypoint_status
     *
     * @param categorypointStatus the value for tbl_category_point.categorypoint_status
     *
     * @mbggenerated
     */
    public void setCategorypointStatus(String categorypointStatus) {
        this.categorypointStatus = categorypointStatus == null ? null : categorypointStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categoryvid
     *
     * @return the value of tbl_category_point.categoryvid
     *
     * @mbggenerated
     */
    public Integer getCategoryvid() {
        return categoryvid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categoryvid
     *
     * @param categoryvid the value for tbl_category_point.categoryvid
     *
     * @mbggenerated
     */
    public void setCategoryvid(Integer categoryvid) {
        this.categoryvid = categoryvid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_point.categorypoint_ctime
     *
     * @return the value of tbl_category_point.categorypoint_ctime
     *
     * @mbggenerated
     */
    public Date getCategorypointCtime() {
        return categorypointCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_point.categorypoint_ctime
     *
     * @param categorypointCtime the value for tbl_category_point.categorypoint_ctime
     *
     * @mbggenerated
     */
    public void setCategorypointCtime(Date categorypointCtime) {
        this.categorypointCtime = categorypointCtime;
    }
}