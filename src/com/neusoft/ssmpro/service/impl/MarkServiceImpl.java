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

	@Override
	public List<Mark> listMarkPage() {
		return markMapper.ListAllMark();
	}

	@Override
	public boolean addMark(Mark mark) {
		return markMapper.addMark(mark);
	}

	@Override
	public boolean deleteMark(Integer markId) {
		return markMapper.deleteByPrimaryKey(markId)>0?true:false;
	}

	@Override
	public boolean switchstatus(Mark mark) {
		if(mark.getMarkStatus().equals("0")) {
			mark.setMarkStatus("1");
		}else if(mark.getMarkStatus().equals("1")) {
			mark.setMarkStatus("0");
		}
		return markMapper.switchstatus(mark);
	}
	
	
	public boolean updateBlogAndMark(List<Integer> markIds,Long blogId) {
		for (Integer id : markIds) {
			markMapper.insertMarkBlog(id,blogId);
		}
		return true;
	}

	@Override
	public boolean removeAndAddRelation(Integer blogId, List<Integer> markIds) {
		int result = markMapper.deleteRelation(blogId);
		for (Integer markId : markIds) {
			markMapper.insertMarkBlog(markId, blogId.longValue());
		}
		return true;
	}

	@Override
	public boolean editMark(Mark mark) {
		return markMapper.editMark(mark);
	}

}
