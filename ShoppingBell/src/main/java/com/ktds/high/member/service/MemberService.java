package com.ktds.high.member.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.regist.vo.UserRequestVO;

public interface MemberService {

	ModelAndView regist(UsersVO usersVO, Errors errors, HttpServletRequest request);

	void checkDuplicateUserID(String emailId, HttpServletResponse response);

	ModelAndView myPage(HttpSession session);

	ModelAndView modify(UsersVO usersVO, HttpServletRequest request, Errors errors);

	ModelAndView exit(HttpServletRequest request);

	Map<String, Object> lostPassword(String emailId, HttpServletRequest request);

	Map<String, String> temporaryPassword(UsersVO usersVO, HttpServletRequest request);

	ModelAndView myAlarmList(HttpServletRequest request);

	ModelAndView myAlarmDelete(HttpServletRequest request);

	ModelAndView myPageModify(HttpSession session);



}
