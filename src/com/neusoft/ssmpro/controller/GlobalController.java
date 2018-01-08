package com.neusoft.ssmpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
}
