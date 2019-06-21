package com.ssm_Oracle.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssm_Oracle.entity.User;
import com.ssm_Oracle.service.IUserService;
import com.ssm_Oracle.util.CommonUtils;
import com.ssm_Oracle.util.TmStringUtils;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*
	 * 若在下面的@RequestMapping前面加上@ResponseBody，
	 * 若方法是String类型则直接返回的是字符串，不会跳转到该字符串的路径jsp文件
	 * 所以要想跳转到某一jsp页面，不能加上@ResponseBody
	 * 这个@ResponseBody适合ajax返回的数据
	 */  
	
	/*
	 * 在控制层不加@ResponseBody的情况下，return值默认是转发到某路径,不会显示转发路径，显示的是未转发前的路径
	 * 若要重定向，加上redirect:这里默认是当前命名空间的转发，要跳转到另一个control层，需要返回上一级../
	 * 这里使用重定向，返回命名空间的上一级，重定向到命名空间为Krry下的index
	 * return "redirect:../index";
	 * 注意：
	 * 转发不会显示转发路径，显示的是未转发前的路径
	 * 重定向显示的是跳转之后的路径
	 * 
	 * */
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		logger.info("跳转的登陆界面");
		return "index/login";//默认是转发，不会显示转发路径
	}
	
	/**
	 * 登录操作
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/logined")
	public String login(HttpServletRequest request, HttpServletResponse response){
		
		String username = request.getParameter("username");
		String password = request .getParameter("password");
		String userType = request .getParameter("userType");
		User user = new User();
		//如果用户名和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(username) ||TmStringUtils.isEmpty(password)||TmStringUtils.isEmpty(userType)){
			logger.info("传入参数为空");
			return "index/allError";
		}
		//密码进行加密处理
		logger.info("密码加密");
		password = TmStringUtils.md5Base64(password);
		
		//根据邮箱或昵称查询，用户是否存在
		String reg = "[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
		if(username.matches(reg)==false){
			user.setUsername(username);
		}else{
			user.setEmail(username);
		}
		user.setPassword(password);
		user.setUserType(userType);
		Map<String, Object> map = userService.login(user);		
		//如果存在
		if(CommonUtils.isNotEmpty(map)){
			String result = (String)map.get("retCode");
			logger.info("service返回码：" + result);
			if("0".equals(result)){
				//如果密码正确,设置信息，返回首页
				
				//第一种：将用户信息放入到会话中，使用重定向到另一个controller，返回首页（适合还有另外的逻辑需要处理）
//				request.getSession().setAttribute("user", user);
//				return "redirect:../index";
				//第二种将信息设置到request中，直接返回页面（适合只返回数据）
				logger.info("登陆成功");
				user = (User)map.get("user");
				request.setAttribute("user", user);
				return "index/index";
			}else if("1".equals(result)){
				//如果密码错误
				logger.info("密码错误");
				return "index/error";
			}else if("2".equals(result)){
				//如果用户类型不匹配
				logger.info("用户类型不匹配");
				return "index/error";
			}else if("3".equals(result)){
				//如果用户名不存在
				logger.info("用户名不存在");
				return "index/error";
			}else{
				//如果其他错误
				logger.info("其他错误");
				return "index/error";
			}
		}else{
			//如果不存在，代码邮箱和密码输入有误
			logger.info("调用service错误");
			return "index/error";
		}
		
	}
	
	/**
	 * 注销操作
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate(); //清空session值
		logger.info("注销成功");
		return "index/index";
	}
	
	
	/**
	 * 跳转到注册界面
	 * @return
	 */
	@RequestMapping("/rege")
	public String rege(){
		logger.info("跳转登陆界面");
		return "index/resgi";
	}
	
	/**
	 * 注册操作
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/resig")
	 public String resig(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request .getParameter("password");
		String email = request .getParameter("email");
		String userType = request .getParameter("userType");
		User user = new User();
		
		//如果用户名、邮箱和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(username) ||TmStringUtils.isEmpty(password) || TmStringUtils.isEmpty(email) || TmStringUtils.isEmpty(userType)){
			logger.info("传入参数为空");
			return "index/allError";
		}
		//密码进行加密处理
		logger.info("密码加密");
		password = TmStringUtils.md5Base64(password);
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserType(userType);
		Map<String, Object> map = userService.register(user);		
		
		if(CommonUtils.isNotEmpty(map)){
			String result = (String)map.get("retCode");
			logger.info("service返回值：" + result);
			if("0".equals(result)){
				//注册成功，跳转登陆页面
				logger.info("注册成功");
				request.setAttribute("user", user);
				return "redirect:/index";
			}else if("1".equals(result)){
				//如果用户名已存在
				logger.info("用户名已存在");
				return "index/error";
			}else if("2".equals(result)){
				//如果Email已存在
				logger.info("Email已存在");
				return "index/error";
			}else{
				//如果其他错误
				logger.info("其他错误");
				return "index/error";
			}
		}else{
			//如果注册失败
			logger.info("调用service错误");
			return "index/error";
		}
	}
}
