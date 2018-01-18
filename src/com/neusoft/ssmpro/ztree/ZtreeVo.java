package com.neusoft.ssmpro.ztree;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;


public class ZtreeVo implements Serializable{

	private Integer id;
	
	private Integer pid;
	@NotBlank(message="博客分类名称不能为空")
	private String name;
	
	private boolean open=true;

	private boolean isParent;
	public boolean getIsParent() {
		return isParent;
	}

	@Override
	public String toString() {
		return "ZtreeVo [id=" + id + ", pid=" + pid + ", name=" + name + ", open=" + open + ", isParent=" + isParent
				+ "]";
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
