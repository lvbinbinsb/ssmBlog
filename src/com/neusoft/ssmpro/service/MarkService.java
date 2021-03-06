package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.entity.Mark;

public interface MarkService {

	List<Mark> loadByBlodId(Long blogId);

	List<Mark> listMarkPage();
	
	boolean  addMark(Mark mark);
	
	boolean deleteMark(Integer markId);

	boolean switchstatus(Mark mark);

	boolean updateBlogAndMark(List<Integer> markIds,Long blogId);

	boolean removeAndAddRelation(Integer blogId, List<Integer> markIds);

	boolean editMark(Mark mark);
}
