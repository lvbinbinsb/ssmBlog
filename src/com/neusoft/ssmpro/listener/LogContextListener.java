package com.neusoft.ssmpro.listener;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
@WebListener
public class LogContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		InputStream is=null;
		try {
			is=Resources.getResourceAsStream("log4j.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PropertyConfigurator.configure(is);
		System.out.println("TOMCAT已经加载log4j.properties");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
