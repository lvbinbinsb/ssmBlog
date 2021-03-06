package com.neusoft.ssmpro.mapper;

import com.neusoft.ssmpro.entity.TblRole;
import java.util.List;

public interface TblRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated
     */
    int insert(TblRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated
     */
    TblRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated
     */
    List<TblRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblRole record);
    
    List<TblRole> getRoleSet(String userName);
}