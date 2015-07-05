package com.ktds.high.member.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.regist.vo.UserRequestVO;

public interface MemberBiz {

	void regist(UsersVO usersVO);

	boolean checkDuplicateUserID(String emailId);

	void modify(UsersVO usersVO);

	void exit(UsersVO usersVO);

	boolean lostPassword(String emailId);

	void temporaryPassword(UsersVO usersVO);

	List<UserRequestVO> myAlarmList(String emailId);

	void myAlarmDelete(String selectedAlarm);
	
	void plusPoint(UsersVO usersVO, HttpServletRequest request);
	
	void minusPoint(UsersVO usersVO, HttpServletRequest request);
}
