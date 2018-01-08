package com.neusoft.ssmpro.mapper;

import com.neusoft.ssmpro.entity.Global;
import java.util.List;

public interface GlobalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_global
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer globalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_global
     *
     * @mbggenerated
     */
    int insert(Global record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_global
     *
     * @mbggenerated
     */
    Global selectByPrimaryKey(Integer globalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_global
     *
     * @mbggenerated
     */
    List<Global> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_global
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Global record);
}