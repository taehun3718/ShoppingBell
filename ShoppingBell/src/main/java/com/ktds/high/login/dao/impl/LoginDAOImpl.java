package com.ktds.high.login.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.login.dao.LoginDAO;
import com.ktds.high.login.vo.UsersVO;

public class LoginDAOImpl extends SqlSessionDaoSupport implements LoginDAO {

	@Override
	public UsersVO login(UsersVO usersVO) {

		UsersVO loginUser = getSqlSession().selectOne("loginDAO.login", usersVO);
		return loginUser;
	}
	
	@Override
	public String getSaltUserId(String emailId) {
		
		return getSqlSession().selectOne("loginDAO.getSaltByUserId", emailId);
	}
}
