package com.neusoft.ssmpro.service;

import java.util.List;

import com.neusoft.ssmpro.entity.Mark;

public interface MarkService {

	List<Mark> loadByBlodId(Long blogId);
}
