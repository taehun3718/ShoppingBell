package com.ktds.high.member.dao.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.dao.MemberDAO;
import com.ktds.high.regist.vo.UserRequestVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	@Override
	public void regist(UsersVO usersVO) {
		getSqlSession().insert("memberDAO.regist", usersVO);
	}
	
	@Override
	public boolean checkDuplicateUserID(String emailId) {
		int count = getSqlSession().selectOne("memberDAO.checkDuplicateUserID",emailId );
		return count == 1;
	}
	
	@Override
	public void modify(UsersVO usersVO) {
		getSqlSession().update("memberDAO.modify", usersVO);
	}
	
	@Override
	public void exit(UsersVO usersVO) {
		getSqlSession().update("memberDAO.modify", usersVO);
	}
	
	@Override
	public boolean lostPassword(String emailId) {
		int count = getSqlSession().selectOne("memberDAO.lostPassword",emailId );
		return count == 1;
	}
	
	@Override
	public void temporaryPassword(UsersVO usersVO) {
		getSqlSession().update("memberDAO.updateTemporaryPassword", usersVO);
	}
	
	@Override
	public List<UserRequestVO> myAlarmList(String emailId) {
		return getSqlSession().selectList("memberDAO.myAlarmList", emailId);
	}
	
	@Override
	public void myAlarmDelete(String selectedAlarm) {
		getSqlSession().delete("memberDAO.myAlarmDelete", selectedAlarm);
	}
	
	@Override
	public void minusPoint(UsersVO usersVO) {
		getSqlSession().update("memberDAO.minusPoint", usersVO);
	}
	
	@Override
	public void plusPoint(UsersVO usersVO) {
		getSqlSession().update("memberDAO.plusPoint", usersVO);
	}
}
