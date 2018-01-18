package com.neusoft.ssmpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ShiroAuthorizingController {

	private static final Logger logger=LogManager.getLogger(ShiroAuthorizingController.class);
	
	@ExceptionHandler(value=AuthorizationException.class)
	public void toUnthorizationPage(HttpServletRequest request,HttpServletResponse response,Exception e) {
		logger.error("没有权限:"+e.getMessage());
		request.setAttribute("error", e.getMessage());
		try {
			request.getRequestDispatcher("/errorPage/401/401.html").forward(request, response);
			return ;
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
