package com.neusoft.ssmpro.mapper;

import com.neusoft.ssmpro.entity.Admin;
import java.util.List;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin
     *
     * @mbggenerated
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin
     *
     * @mbggenerated
     */
    Admin selectByPrimaryKey(Integer adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin
     *
     * @mbggenerated
     */
    List<Admin> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Admin record);
}