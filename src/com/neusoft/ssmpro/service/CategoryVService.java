package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.ztree.ZtreeVo;

public interface CategoryVService {

	List<CategoryV> loadByNavId(Integer navId);
	
	List<Integer> loadCategoryVIds();

	List<CategoryV> listbypage(Integer categorynavId);

	List<ZtreeVo> loadNodesByNavId(Integer id);

	boolean updateNode(ZtreeVo node);

	boolean deleteNode(Integer id);

	ZtreeVo insertNode(ZtreeVo newNode);
	
	boolean changeCategoryNav(ZtreeVo newNode);

	List<CategoryV> loadAllByNavId(Integer navId);
}
