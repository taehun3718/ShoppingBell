package com.ktds.high.login.dao;

import com.ktds.high.login.vo.UsersVO;

public interface LoginDAO {

	UsersVO login(UsersVO usersVO);

	String getSaltUserId(String emailId);

}
