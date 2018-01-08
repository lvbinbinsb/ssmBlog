package com.neusoft.ssmpro.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_admin.admin_id
     *
     * @mbggenerated
     */
    private Integer adminId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_admin.admin_name
     *
     * @mbggenerated
     */
    private String adminName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_admin.admin_password
     *
     * @mbggenerated
     */
    private String adminPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_admin.admin_lastlogintime
     *
     * @mbggenerated
     */
    private String adminLastlogintime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_admin.admin_ctime
     *
     * @mbggenerated
     */
    private Date adminCtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_admin.admin_id
     *
     * @return the value of tbl_admin.admin_id
     *
     * @mbggenerated
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_admin.admin_id
     *
     * @param adminId the value for tbl_admin.admin_id
     *
     * @mbggenerated
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_admin.admin_name
     *
     * @return the value of tbl_admin.admin_name
     *
     * @mbggenerated
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_admin.admin_name
     *
     * @param adminName the value for tbl_admin.admin_name
     *
     * @mbggenerated
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_admin.admin_password
     *
     * @return the value of tbl_admin.admin_password
     *
     * @mbggenerated
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_admin.admin_password
     *
     * @param adminPassword the value for tbl_admin.admin_password
     *
     * @mbggenerated
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_admin.admin_lastlogintime
     *
     * @return the value of tbl_admin.admin_lastlogintime
     *
     * @mbggenerated
     */
    public String getAdminLastlogintime() {
        return adminLastlogintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_admin.admin_lastlogintime
     *
     * @param adminLastlogintime the value for tbl_admin.admin_lastlogintime
     *
     * @mbggenerated
     */
    public void setAdminLastlogintime(String adminLastlogintime) {
        this.adminLastlogintime = adminLastlogintime == null ? null : adminLastlogintime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_admin.admin_ctime
     *
     * @return the value of tbl_admin.admin_ctime
     *
     * @mbggenerated
     */
    public Date getAdminCtime() {
        return adminCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_admin.admin_ctime
     *
     * @param adminCtime the value for tbl_admin.admin_ctime
     *
     * @mbggenerated
     */
    public void setAdminCtime(Date adminCtime) {
        this.adminCtime = adminCtime;
    }
}