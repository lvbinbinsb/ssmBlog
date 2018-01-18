package com.neusoft.ssmpro.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ssmpro.entity.Global;
import com.neusoft.ssmpro.service.GlobalService;

@RestController
@RequestMapping("/global")
public class GlobalController {

	@Autowired
	private GlobalService globalService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Global loadById(@PathVariable("id")Integer id) {
		return globalService.loadById(id);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@RequiresRoles(value= {"superAdmin"})
	public Boolean updateBlog(Global global,HttpServletRequest request) {
		return globalService.updateGlobal(global);	
	}
}
