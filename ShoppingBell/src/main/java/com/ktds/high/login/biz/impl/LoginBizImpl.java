package com.ktds.high.login.biz.impl;

import com.ktds.high.common.util.PasswordUtil;
import com.ktds.high.login.biz.LoginBiz;
import com.ktds.high.login.dao.LoginDAO;
import com.ktds.high.login.vo.UsersVO;

public class LoginBizImpl implements LoginBiz {

	private LoginDAO loginDAO;

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	@Override
	public UsersVO login(UsersVO usersVO) {
		
		String salt = 
				this.loginDAO.getSaltUserId(usersVO.getEmailId());
		
		usersVO.setSalt(salt);
		PasswordUtil.getPassword(usersVO);
		return this.loginDAO.login(usersVO);
	}
	
}
