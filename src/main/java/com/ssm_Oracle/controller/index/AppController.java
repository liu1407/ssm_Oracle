package com.ssm_Oracle.controller.index;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm_Oracle.entity.User;
import com.ssm_Oracle.util.CommonUtils;

@Controller
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		logger.info("检查user是否传过来："+request.getParameter("user"));
		return "index/index";
	}
}
