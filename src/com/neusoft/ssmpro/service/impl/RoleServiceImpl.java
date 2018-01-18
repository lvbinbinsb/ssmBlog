package com.neusoft.ssmpro.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ssmpro.mapper.TblRoleMapper;
import com.neusoft.ssmpro.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private TblRoleMapper roleMapper;

	@Override
	public Set<String> getRoleSet(String userName) {
		Set<String> roleNames=new HashSet<String>();
		roleMapper.getRoleSet(userName).forEach((r)->{roleNames.add(r.getRoleName());});
		return roleNames;
	}
	
	
	
}
