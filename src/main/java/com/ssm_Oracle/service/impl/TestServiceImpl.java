package com.ssm_Oracle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm_Oracle.dao.Dao;
import com.ssm_Oracle.entity.User;
import com.ssm_Oracle.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	Dao dao;

	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		int iRet = dao.insert(user);
		if(iRet != 1){
			logger.error("新增失败");
			return;
		}
		logger.info("新增成功");
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		int iRet = dao.update(user);
		if(iRet != 1){
			logger.error("修改失败");
			return;
		}
		logger.info("修改成功");
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		int iRet = dao.delete(user);
		if(iRet != 1){
			logger.error("删除失败");
			return;
		}
		logger.info("删除成功");
	}

	@Override
	public User selectByPrimary(User user) {
		// TODO Auto-generated method stub
		user = dao.selectByPrimary(user,false);
		if(user == null){
			logger.info("查询用户信息信息为空");
		}
		
		return user;
	}

	@Override
	public List<User> selectList(User user) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<>();
		userList = dao.selectList("selectUsersByUsername", user,false);
		if(userList == null || userList.size()<=0){
			logger.info("查询用户信息信息为空");
		}
		
		return userList;
	}

	@Override
	public User selectone(User user) {
		// TODO Auto-generated method stub
		user = dao.selectOne("User.selectUserByEmail", user, false);
		if(user == null){
			logger.info("查询用户信息信息为空");
		}
		
		return user;
	}
	
	
}
