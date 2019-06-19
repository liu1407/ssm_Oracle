package com.ssm_Oracle.controller.login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssm_Oracle.entity.User;
import com.ssm_Oracle.service.IUserService;
import com.ssm_Oracle.service.TestService;
import com.ssm_Oracle.util.CommonUtils;
import com.ssm_Oracle.util.TmStringUtils;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private TestService testService;
	
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
		
		return "index/login";//默认是转发，不会显示转发路径
	}
	
	/**
	 * 登录操作
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/logined")
	public String login(HttpServletRequest request, Model model){
		
		String username = request.getParameter("username");
		String password = request .getParameter("password");
		User user = new User();
		//如果用户名和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(username))return "index/allError";
		if(TmStringUtils.isEmpty(password))return "index/allError";
		
		
		//密码进行加密处理
		password = TmStringUtils.md5Base64(password);
		
		//根据邮箱或昵称查询，用户是否存在
		user.setUsername(username);
		user.setPassword(password);
		user = userService.getLogin(user);
		
		//如果存在
		if(CommonUtils.isNotEmpty(user)){
			User userpas = userService.getpass(user);
			if(CommonUtils.isNotEmpty(userpas)){
				//如果密码正确
				//将用户信息放入到会话中...
				request.getSession().setAttribute("user", user);
				//这里使用重定向，返回命名空间的上一级，重定向到命名空间为Krry下的index.krry
				return "redirect:../index";
			}else{
				//如果密码错误
				System.out.println("密码错误");
				return "index/error";
			}
		}else{
			//如果不存在，代码邮箱和密码输入有误
			System.out.println("用户不存在");
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
		return "index/index";
	}
	
	
	/**
	 * 跳转到注册界面
	 * @return
	 */
	@RequestMapping("/rege")
	public String rege(){
		User user = new User();
		user.setEmail("liu1407@126.com");
		
		user = testService.selectone(user);
		if(user == null){
			logger.error("查询为空");
			return "index/resgi";
		}
		logger.info("查询成功--"+user.toString());
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
		User user = new User();
		
		//如果用户名、邮箱和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(username) )return "index/allError";
		if(TmStringUtils.isEmpty(password))return "index/allError";
		if(TmStringUtils.isEmpty(email))return "index/allError";
		
		//密码进行加密处理
		password = TmStringUtils.md5Base64(password);
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		//根据昵称查询，用户是否存在
		User user1 = userService.getothernameres(user);
		//根据账号查询，用户是否存在
		User user2 = userService.getemailres(user);
		
		//若存在
		if(CommonUtils.isNotEmpty(user1)){ //昵称重复
			return "index/allError";
		}
		
		if(CommonUtils.isNotEmpty(user2)){ //email重复
			return "index/allError";	
		}
		
		 //格式化时间类型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		
		String id = UUID.randomUUID().toString();
		//执行到这里，说明可以注册
		User newUser = new User(id, username, password, email, nowTime);
		
		//调用注册方法
		boolean resStr = userService.saveUser(newUser);
		if(resStr == false){
			return "index/allError";
		}
		//将信息设置session作用域
		request.getSession().setAttribute("user", newUser);
		
		//这里使用重定向，返回命名空间的上一级，重定向到index
		return "redirect:../index";
		
	}
}
