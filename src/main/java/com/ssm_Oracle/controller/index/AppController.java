package com.ssm_Oracle.controller.index;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm_Oracle.controller.login.LoginController;

@Controller
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model){
		logger.info("检查user是否传过来："+model.containsAttribute("user"));
		return "index/index";
	}
}
