package com.neusoft.ssmpro.mapper;

import com.neusoft.ssmpro.entity.Praise;
import java.util.List;

public interface PraiseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_praise
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer praiseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_praise
     *
     * @mbggenerated
     */
    int insert(Praise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_praise
     *
     * @mbggenerated
     */
    Praise selectByPrimaryKey(Integer praiseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_praise
     *
     * @mbggenerated
     */
    List<Praise> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_praise
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Praise record);
}