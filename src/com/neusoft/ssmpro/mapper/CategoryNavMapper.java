package com.neusoft.ssmpro.mapper;

import com.neusoft.ssmpro.entity.CategoryNav;
import java.util.List;

public interface CategoryNavMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_nav
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer categorynavId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_nav
     *
     * @mbggenerated
     */
    int insert(CategoryNav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_nav
     *
     * @mbggenerated
     */
    CategoryNav selectByPrimaryKey(Integer categorynavId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_nav
     *
     * @mbggenerated
     */
    List<CategoryNav> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_nav
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CategoryNav record);
    
    List<CategoryNav> loadCategoryNavFirstSeven();
    
}