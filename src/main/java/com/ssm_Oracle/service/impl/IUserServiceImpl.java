package com.ssm_Oracle.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm_Oracle.dao.Dao;
import com.ssm_Oracle.entity.User;
import com.ssm_Oracle.service.IUserService;
import com.ssm_Oracle.util.CommonUtils;

@Service
public class IUserServiceImpl implements IUserService{

	@Autowired
	private Dao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);

	@Override
	public Map<String, Object> login(User user) {
		// TODO Auto-generated method stub
		Map<String ,Object> map = new HashMap<>();
		User Suser = new User();
		if(CommonUtils.isNotEmpty(user.getUsername())){
			Suser = dao.selectOne("User.selectUserByUsername", user,false);
		}else if(CommonUtils.isNotEmpty(user.getEmail())){
			Suser = dao.selectOne("User.selectUserByEmail", user,false);
		}
		
		//0-登陆成功、1-密码错误、2-用户类型不匹配、3-用户名不存在、-1-其他错误
		//用户名不存在
		if(CommonUtils.isEmpty(Suser)){
			map.put("retCode", "3");
			map.put("msg", "用户不存在");
			return map;
		}
		
		if(user.getPassword().equals(Suser.getPassword()) == false){
			map.put("retCode", "1");
			map.put("msg", "密码错误");
			return map;
		}
		
		if(user.getUserType().equals(Suser.getUserType()) == false){
			map.put("retCode", "2");
			map.put("msg", "用户类型不匹配");
			return map;
		}
		
		map.put("retCode", "0");
		map.put("msg", "登陆成功");
		map.put("user", Suser);
		
		return map;
	}

	@Override
	public Map<String, Object> register(User user) {
		// TODO Auto-generated method stub
		Map<String ,Object> map = new HashMap<>();
		User Suser = new User();
		Suser = dao.selectOne("User.selectUserByUsername", user, false);
		//0-成功、1-用户名已存在、2-Email已存在、-1-其他错误
		if(CommonUtils.isNotEmpty(Suser)){
			map.put("retCode", "1");
			map.put("msg", "用户名已存在");
			return map;
		}
		Suser = dao.selectOne("User.selectUserByEmail", user, false);
		if(CommonUtils.isNotEmpty(Suser)){
			map.put("retCode", "2");
			map.put("msg", "Email已存在");
			return map;
		}
		
		 //格式化时间类型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		
		String id = UUID.randomUUID().toString();
		
		user.setId(id);
		user.setEstablishTime(nowTime);
		
		int iRet = dao.insert(user);
		logger.info("新增返回值："+iRet);
		if(iRet != 1){
			map.put("retCode", "-1");
			map.put("msg", "新增失败");
			return map;
		}
		
		map.put("retCode", "0");
		map.put("msg", "注册成功");
		return map;
	}
}
