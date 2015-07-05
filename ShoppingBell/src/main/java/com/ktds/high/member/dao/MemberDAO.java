package com.ktds.high.member.dao;


import java.util.List;

import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.regist.vo.UserRequestVO;

public interface MemberDAO {

	void regist(UsersVO usersVO);

	boolean checkDuplicateUserID(String emailId);

	void modify(UsersVO usersVO);

	void exit(UsersVO usersVO);

	boolean lostPassword(String emailId);

	void temporaryPassword(UsersVO usersVO);

	List<UserRequestVO> myAlarmList(String emailId);

	void myAlarmDelete(String selectedAlarm);

	void plusPoint(UsersVO usersVO);

	void minusPoint(UsersVO usersVO);

}
