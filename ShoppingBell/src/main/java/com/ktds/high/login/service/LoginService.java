package com.ktds.high.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.ktds.high.login.vo.UsersVO;

public interface LoginService {

	Map<String, Object> login(UsersVO usersVO, HttpServletRequest request);

	void logout(HttpServletRequest request);


}
