package com.ssm_Oracle.service;

import java.util.List;

import com.ssm_Oracle.entity.User;

public interface TestService {

	public void insert(User user);
	
	public void update(User user);
	
	public void delete(User user);
	
	public User selectByPrimary(User user);
	
	public List<User> selectList(User user);
	
	public User selectone(User user);
}
