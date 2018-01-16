package com.neusoft.ssmpro.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.CategoryV;
import com.neusoft.ssmpro.mapper.CategoryVMapper;
import com.neusoft.ssmpro.service.CategoryVService;
import com.neusoft.ssmpro.ztree.ZtreeVo;
@Service
public class CategoryVServiceImpl implements CategoryVService{

	@Autowired
	private CategoryVMapper categoryVMapper;
	
	@Override
	public List<CategoryV> loadByNavId(Integer navId) {
		return categoryVMapper.loadCategoryVByNavId(navId);
	}

	@Override
	public List<Integer> loadCategoryVIds() {
		return categoryVMapper.loadFirstNavVIds();
	}

	@Override
	public List<CategoryV> listbypage(Integer categorynavId) {
		return categoryVMapper.listbypage(categorynavId);
	}

	@Override
	public List<ZtreeVo> loadNodesByNavId(Integer id) {
		return categoryVMapper.loadTreeNodeByNavId(id);
	}

	@Override
	public boolean updateNode(ZtreeVo node) {
		return categoryVMapper.updateNode(node);
	}

	@Override
	public boolean deleteNode(Integer id) {
		return categoryVMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	
	@Override
	public ZtreeVo insertNode(ZtreeVo newNode) {
		categoryVMapper.addNode(newNode);
//		System.out.println(newNode);
		CategoryV record=new CategoryV();
		record.setCategorynavid(newNode.getPid());
		record.setCategoryvId(newNode.getId());
		record.setCategoryvName(newNode.getName());
		record.setCategoryvNo(newNode.getId());
		record.setCategoryvStatus("0");
		categoryVMapper.updateByPrimaryKey(record);
		return newNode;
	}

	public boolean changeCategoryNav(ZtreeVo newNode) {
		return categoryVMapper.changeCategoryNav(newNode)>0?true:false;
	}

	@Override
	public List<CategoryV> loadAllByNavId(Integer navId) {
		// TODO Auto-generated method stub
		return categoryVMapper.loadAllCategoryVByNavId(navId);
	}

}
