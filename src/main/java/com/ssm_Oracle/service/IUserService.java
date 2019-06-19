package com.ssm_Oracle.service;

import com.ssm_Oracle.entity.User;

public interface IUserService {

	/**
	 * 根据用户名查询用户是否存在
	 * @param username
	 * @return
	 */
	/*
	 * 这里用@Param("name")String name适用于单个参数的传递，在web层调用此方法的时候，就可以传递web层从前台获取的参数，
	 * 在sql的xml中WHERE email = #{name} or username = #{name}使用此参数，多个参数传递一般使用实体类对象传递
	 */
	public User getLogin(User newUser);
	
	/**
	 * 用户名存在时，查询密码是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public User getpass(User newUser);
	
	/**
	 * 注册时根据输入的昵称查找用户
	 * @param username
	 * @return
	 */
	public User getothernameres(User newUser);
	
	/**
	 * 注册时根据输入的账号查找用户
	 * @param email
	 * @return
	 */
	public User getemailres(User newUser);
	
	/**
	 * 注册方法
	 * @param newUser
	 */
	public boolean saveUser(User newUser);
}
