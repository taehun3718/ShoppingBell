package com.ktds.high.regist.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.vo.HistoryVO;
import com.ktds.high.history.vo.HistoryVO.OperationHistory;
import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.biz.MemberBiz;
import com.ktds.high.regist.biz.RegistBiz;
import com.ktds.high.regist.service.RegistService;
import com.ktds.high.regist.vo.ShopUrl;
import com.ktds.high.regist.vo.UserRequestVO;

public class RegistServiceImpl implements RegistService{

	private RegistBiz registBiz;
	private MemberBiz memberBiz;
	private HistoryBiz historyBiz;
	
	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	public void setHistoryBiz(HistoryBiz historyBiz) {
		this.historyBiz = historyBiz;
	}
	
	public void setRegistBiz(RegistBiz registBiz) {
		this.registBiz = registBiz;
	}
	
	@Override
	public ModelAndView registUserRequest(UserRequestVO userRequestVO, Errors erros, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		/*if(erros.hasErrors()){
			view.addObject("userRequestVO", userRequestVO);
			view.setViewName("regist/registRequest");
			return view;
		}*/
		
		HttpSession session = request.getSession();
		UsersVO userInfo = (UsersVO) session.getAttribute("_MEMBER_");
		
		userRequestVO.setUserEmail(userInfo.getEmailId());
		userRequestVO.setUserGender(userInfo.getGender());
		
		String userBirth = userInfo.getBirth().split(" ")[0];
		userBirth = userBirth.replaceAll("-", "");
		
		userRequestVO.setUserBirth(userBirth);
		
		if ( userRequestVO.getShopType().equals("11ST") ) {
			userRequestVO.setShopSearchUrl(ShopUrl.ELEVEN_ST);
		}
		
		else if ( userRequestVO.getShopType().equals("ACTION") ) {
			userRequestVO.setShopSearchUrl(ShopUrl.ACTION);
		}
		
		HistoryVO registHistoryVO = new HistoryVO(); 
		registHistoryVO.setEmailId(userInfo.getEmailId());
		registHistoryVO.setIp(request.getRemoteAddr());
		registHistoryVO.setOperationType(OperationHistory.REQUEST_HISTORY);
		
		try {
			this.registBiz.registUserRequest(userRequestVO);
			registHistoryVO.setOperationDescription("사용자 알림 등록에 성공했습니다. 아이디 : " + userInfo.getEmailId());
		}
		catch (RuntimeException re) {
			registHistoryVO.setOperationDescription("사용자 알림 등록에 실패했습니다. 아이디 : " + userInfo.getEmailId() + ", 사유 : " + re.getMessage() );
		}
		finally {
			this.historyBiz.putHistory(registHistoryVO);
		}
		
		try {
			this.registBiz.registUserRequestHistory(userRequestVO);
			registHistoryVO.setOperationDescription("사용자 알림 히스토리 등록에 성공했습니다. 아이디 : " + userInfo.getEmailId());
		}
		catch(RuntimeException re) {
			registHistoryVO.setOperationDescription("사용자 알림 히스토리 등록에 실패했습니다. 아이디 : " + userInfo.getEmailId() + ", 사유 : " + re.getMessage() );
		}
		finally {
			this.historyBiz.putHistory(registHistoryVO);
		}
		
		UsersVO updateUserVO = new UsersVO();
		updateUserVO.setEmailId(userInfo.getEmailId());
		updateUserVO.setPoint(30);
		
		this.memberBiz.minusPoint(updateUserVO, request);
		
		view.setViewName("member/myPage");
		
		return view;
	}
}
