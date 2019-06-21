package com.ssm_Oracle.service;

import java.util.Map;

import com.ssm_Oracle.entity.User;

public interface IUserService {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public Map<String,Object> login(User user);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public Map<String,Object> register(User user); 
}
