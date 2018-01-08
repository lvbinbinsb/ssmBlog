package com.neusoft.ssmpro.entity;

import java.io.Serializable;
import java.util.Date;

public class CategoryV implements Serializable{
    @Override
	public String toString() {
		return "CategoryV [categoryvId=" + categoryvId + ", categoryvNo=" + categoryvNo + ", categoryvName="
				+ categoryvName + ", categoryvStatus=" + categoryvStatus + ", categorynavid=" + categorynavid
				+ ", categoryvCtime=" + categoryvCtime + "]";
	}

    private Integer categoryvId;

    private Integer categoryvNo;

    private String categoryvName;
    
    private String categoryvStatus;

    private Integer categorynavid;

    private Date categoryvCtime;

    public Integer getCategoryvId() {
        return categoryvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categoryv_id
     *
     * @param categoryvId the value for tbl_category_v.categoryv_id
     *
     * @mbggenerated
     */
    public void setCategoryvId(Integer categoryvId) {
        this.categoryvId = categoryvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_v.categoryv_no
     *
     * @return the value of tbl_category_v.categoryv_no
     *
     * @mbggenerated
     */
    public Integer getCategoryvNo() {
        return categoryvNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categoryv_no
     *
     * @param categoryvNo the value for tbl_category_v.categoryv_no
     *
     * @mbggenerated
     */
    public void setCategoryvNo(Integer categoryvNo) {
        this.categoryvNo = categoryvNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_v.categoryv_name
     *
     * @return the value of tbl_category_v.categoryv_name
     *
     * @mbggenerated
     */
    public String getCategoryvName() {
        return categoryvName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categoryv_name
     *
     * @param categoryvName the value for tbl_category_v.categoryv_name
     *
     * @mbggenerated
     */
    public void setCategoryvName(String categoryvName) {
        this.categoryvName = categoryvName == null ? null : categoryvName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_v.categoryv_status
     *
     * @return the value of tbl_category_v.categoryv_status
     *
     * @mbggenerated
     */
    public String getCategoryvStatus() {
        return categoryvStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categoryv_status
     *
     * @param categoryvStatus the value for tbl_category_v.categoryv_status
     *
     * @mbggenerated
     */
    public void setCategoryvStatus(String categoryvStatus) {
        this.categoryvStatus = categoryvStatus == null ? null : categoryvStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_v.categorynavid
     *
     * @return the value of tbl_category_v.categorynavid
     *
     * @mbggenerated
     */
    public Integer getCategorynavid() {
        return categorynavid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categorynavid
     *
     * @param categorynavid the value for tbl_category_v.categorynavid
     *
     * @mbggenerated
     */
    public void setCategorynavid(Integer categorynavid) {
        this.categorynavid = categorynavid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_v.categoryv_ctime
     *
     * @return the value of tbl_category_v.categoryv_ctime
     *
     * @mbggenerated
     */
    public Date getCategoryvCtime() {
        return categoryvCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_v.categoryv_ctime
     *
     * @param categoryvCtime the value for tbl_category_v.categoryv_ctime
     *
     * @mbggenerated
     */
    public void setCategoryvCtime(Date categoryvCtime) {
        this.categoryvCtime = categoryvCtime;
    }
}