package com.neusoft.ssmpro.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.ssmpro.entity.Blog;
import com.neusoft.ssmpro.entity.Mark;

public class BlogVo extends Blog implements Serializable{
	
	private List<Mark> marks=new ArrayList<Mark>();

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "BlogVo [marks=" + marks + ", Blog=" + super.toString() + "]";
	}
	
	
}
