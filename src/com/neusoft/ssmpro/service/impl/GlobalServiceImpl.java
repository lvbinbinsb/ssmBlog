package com.neusoft.ssmpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.Global;
import com.neusoft.ssmpro.mapper.GlobalMapper;
import com.neusoft.ssmpro.service.GlobalService;
@Service("globalService")
public class GlobalServiceImpl implements GlobalService{

	@Autowired
	private GlobalMapper globalMapper;

	@Override
	public Global loadById(Integer id) {
		return globalMapper.selectByPrimaryKey(id);
	}
	
	
}
