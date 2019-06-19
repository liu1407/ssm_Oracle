package com.ssm_Oracle.service.impl;

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

//	@Autowired
//	private UserMapper userMapper;
	
	@Autowired
	private Dao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);
	
	@Override
	public User getLogin(User user) {
		// TODO Auto-generated method stub
		user = dao.selectOne("User.getLogin", user,false);
		if(CommonUtils.isEmpty(user)){
			logger.info("用户名不存在");
		}
		return user;
	}

	@Override
	public User getpass(User user) {
		// TODO Auto-generated method stub
		user = dao.selectOne("User.getpass", user,false);
		if(CommonUtils.isEmpty(user)){
			logger.info("密码不正确");
		}
		return user;
	}

	@Override
	public User getothernameres(User user) {
		// TODO Auto-generated method stub
		user = dao.selectOne("User.getpass", user,false);
		if(CommonUtils.isEmpty(user)){
			logger.info("昵称不存在");
		}
		return user;
	}

	@Override
	public User getemailres(User user) {
		// TODO Auto-generated method stub
		user = dao.selectOne("User.getpass", user,false);
		if(CommonUtils.isEmpty(user)){
			logger.info("Email不存在");
		}
		return user;
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		if(CommonUtils.isEmpty(user)){
			logger.info("传入的参数为空");
			return false;
		}
		if(CommonUtils.isEmpty(user.getId())){
			logger.info("传入的id为空");
			return false;
		}
		User tmpUser = dao.selectByPrimary(user,false);
		if(CommonUtils.isNotEmpty(tmpUser)){
			logger.info("传入的id已存在");
			return false;
		}
		
		int iRet = dao.insert(user);
		if(iRet !=1){
			logger.info("新增失败");
			return false;
		}
		return true;
	}

	
}
