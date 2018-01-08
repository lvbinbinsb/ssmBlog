package com.neusoft.ssmpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.entity.Mark;
import com.neusoft.ssmpro.mapper.MarkMapper;
import com.neusoft.ssmpro.service.MarkService;
@Service("markService")
public class MarkServiceImpl implements MarkService{

	@Autowired
	private MarkMapper markMapper;
	
	@Override
	public List<Mark> loadByBlodId(Long blogId) {
		return markMapper.loadByBlogId(blogId);
	}

}
