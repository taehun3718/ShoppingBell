package com.ktds.high.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.vo.HistoryVO;
import com.ktds.high.history.vo.HistoryVO.OperationHistory;
import com.ktds.high.login.biz.LoginBiz;
import com.ktds.high.login.service.LoginService;
import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.util.SessionStore;

public class LoginServiceImpl implements LoginService {

	private LoginBiz loginBiz;
	private HistoryBiz historyBiz;
	
	public void setHistoryBiz(HistoryBiz historyBiz) {
		this.historyBiz = historyBiz;
	}

	public void setLoginBiz(LoginBiz loginBiz) {
		this.loginBiz = loginBiz;
	}
	
	@Override
	public Map<String, Object> login(UsersVO usersVO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String password = usersVO.getPassword();
		UsersVO userInfo = this.loginBiz.login(usersVO);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", userInfo != null);
		HistoryVO loginHistoryVO = new HistoryVO();
		loginHistoryVO.setEmailId(usersVO.getEmailId());
		loginHistoryVO.setIp(request.getRemoteAddr());
		loginHistoryVO.setOperationType(OperationHistory.LOGIN_HISTORY);
		
		if(userInfo != null){
			
			userInfo.setPassword(password);
			
			SessionStore sessionStore = SessionStore.getInstance();
			
			if(!sessionStore.isExists(usersVO.getEmailId())){
				session.setAttribute("_MEMBER_", userInfo);
				sessionStore.putSession(userInfo.getEmailId(), session);
				loginHistoryVO.setOperationDescription("로그인에 성공했습니다.");
			}
			else{
				if(usersVO.getForceLogin().equals("")){
					result.put("result", false);
					result.put("because", "1");
					
					loginHistoryVO.setOperationDescription("로그인에 실패했습니다. 이유 : 이미 로그인 된 아이디.");
				}
				else{
					
					sessionStore.getSession(usersVO.getEmailId()).invalidate();
					
					sessionStore.removeSession(usersVO.getEmailId());
					
					session.setAttribute("_MEMBER_", userInfo);
					
					sessionStore.putSession(userInfo.getEmailId(), session);
					
					loginHistoryVO.setOperationDescription("재로그인에 성공했습니다.");
				}
			}
		}
		else{
			result.put("because", 2);
			loginHistoryVO.setOperationDescription("로그인에 실패했습니다. 이유 : 아이디나 비밀번호 틀림.");
			
		}
		
		historyBiz.putHistory(loginHistoryVO);
		return result;
	}
	
	@Override
	public void logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		SessionStore sessionStore = SessionStore.getInstance();
		UsersVO userInfo = (UsersVO) session.getAttribute("_MEMBER_");
		String emailId = userInfo.getEmailId();
		
		HistoryVO logoutHistoryVO = new HistoryVO();
		logoutHistoryVO.setEmailId(emailId);
		logoutHistoryVO.setIp(request.getRemoteAddr());
		logoutHistoryVO.setOperationType(OperationHistory.LOGIN_HISTORY);
		
		try {
			sessionStore.getSession(userInfo.getEmailId()).invalidate();
			sessionStore.removeSession(userInfo.getEmailId());
			session.invalidate();
			
			logoutHistoryVO.setOperationDescription("로그아웃에 성공했습니다.");
		} catch (RuntimeException e) {
			logoutHistoryVO.setOperationDescription("로그아웃에 실패했습니다. 이유 : 에러발생");
		}
			historyBiz.putHistory(logoutHistoryVO);
	}
}