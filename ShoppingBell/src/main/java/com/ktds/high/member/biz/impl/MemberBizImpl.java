package com.ktds.high.member.biz.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.high.common.util.PasswordUtil;
import com.ktds.high.history.dao.HistoryDAO;
import com.ktds.high.history.vo.HistoryVO;
import com.ktds.high.history.vo.HistoryVO.OperationHistory;
import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.biz.MemberBiz;
import com.ktds.high.member.dao.MemberDAO;
import com.ktds.high.regist.vo.UserRequestVO;

public class MemberBizImpl implements MemberBiz {
	
	private MemberDAO memberDAO;
	private HistoryDAO historyDAO;
	
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public void regist(UsersVO usersVO) {
				
		usersVO = PasswordUtil.generatePassword(usersVO);

		this.memberDAO.regist(usersVO);
	}
	
	@Override
	public boolean checkDuplicateUserID(String emailId) {
		return this.memberDAO.checkDuplicateUserID(emailId);
	}
	
	@Override
	public void modify(UsersVO usersVO) {
		
		usersVO = PasswordUtil.generatePassword(usersVO);

		this.memberDAO.modify(usersVO);
	}
	
	@Override
	public void exit(UsersVO usersVO) {
		
		usersVO = PasswordUtil.generatePassword(usersVO);
		
		this.memberDAO.exit(usersVO);
	}
	
	@Override
	public boolean lostPassword(String emailId) {
		return this.memberDAO.lostPassword(emailId);
	}
	
	@Override
	public void temporaryPassword(UsersVO usersVO) {
		
		usersVO = PasswordUtil.generatePassword(usersVO);
		
		this.memberDAO.temporaryPassword(usersVO);
	}
	
	@Override
	public List<UserRequestVO> myAlarmList(String emailId) {
		return this.memberDAO.myAlarmList(emailId);
	}
	
	@Override
	public void myAlarmDelete(String selectedAlarm) {
		this.memberDAO.myAlarmDelete(selectedAlarm);
	}
	
	@Override
	public void plusPoint(UsersVO usersVO, HttpServletRequest request) {
		
		HistoryVO memberPlusPointHistoryVO = new HistoryVO();
		memberPlusPointHistoryVO.setEmailId(usersVO.getEmailId());
		memberPlusPointHistoryVO.setIp(request.getRemoteAddr());
		memberPlusPointHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		memberPlusPointHistoryVO.setOperationDescription("회원의 포인트가 증가했습니다. 증가 포인트 : " + usersVO.getPoint());
		historyDAO.putHistory(memberPlusPointHistoryVO);
		
		this.memberDAO.plusPoint(usersVO);
	}
	
	@Override
	public void minusPoint(UsersVO usersVO, HttpServletRequest request) {
		
		HistoryVO memberMinusPointHistoryVO = new HistoryVO();
		
		System.out.println("HISTORY VO:" + memberMinusPointHistoryVO);
		
		memberMinusPointHistoryVO.setEmailId(usersVO.getEmailId());
		memberMinusPointHistoryVO.setIp(request.getRemoteAddr());
		memberMinusPointHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		memberMinusPointHistoryVO.setOperationDescription("회원의 포인트가 감소했습니다. 감소 포인트 : " + usersVO.getPoint());
		historyDAO.putHistory(memberMinusPointHistoryVO);
		
		this.memberDAO.minusPoint(usersVO);
	}
}
