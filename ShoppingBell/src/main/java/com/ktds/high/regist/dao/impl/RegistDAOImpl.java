package com.ktds.high.regist.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.regist.dao.RegistDAO;
import com.ktds.high.regist.vo.UserRequestVO;

public class RegistDAOImpl extends SqlSessionDaoSupport implements RegistDAO{

	@Override
	public void registUserRequest(UserRequestVO userRequestVO) {
		getSqlSession().insert("registDAO.registUserRequest", userRequestVO);
	}
	
	@Override
	public void registUserRequestHistory(UserRequestVO userRequestVO) {
		getSqlSession().insert("registDAO.registUserRequestHistory", userRequestVO);
	}
}
