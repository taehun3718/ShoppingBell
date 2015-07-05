package com.ktds.high.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.common.util.AjaxUtil;
import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.vo.HistoryVO;
import com.ktds.high.history.vo.HistoryVO.OperationHistory;
import com.ktds.high.login.biz.LoginBiz;
import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.biz.MemberBiz;
import com.ktds.high.member.service.MemberService;
import com.ktds.high.regist.vo.UserRequestVO;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;
	private LoginBiz loginBiz;
	private HistoryBiz historyBiz;
	
	public void setHistoryBiz(HistoryBiz historyBiz) {
		this.historyBiz = historyBiz;
	}

	public void setLoginBiz(LoginBiz loginBiz) {
		this.loginBiz = loginBiz;
	}

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}
	
	@Override
	public ModelAndView regist(UsersVO usersVO, Errors errors, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		String emailId = usersVO.getEmailId();
		
		HistoryVO registHistoryVO = new HistoryVO();
		registHistoryVO.setIp(request.getRemoteAddr());
		registHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		
		if(errors.hasErrors()) {
			registHistoryVO.setEmailId("비회원");
			registHistoryVO.setOperationDescription("회원가입에 실패했습니다. 회원가입에 실패한 Email Id : " + emailId);
			view.setViewName("member/regist");
		}
		else {
			try{
		
				view.setViewName("redirect:/login");
				this.memberBiz.regist(usersVO);

				registHistoryVO.setEmailId(emailId);
				registHistoryVO.setOperationDescription("회원가입에 성공했습니다.");
			}
			catch(RuntimeException e){
				registHistoryVO.setEmailId("비회원");
				registHistoryVO.setOperationDescription("회원가입에 실패했습니다. 회원가입에 실패한 emailId : " + emailId);
			}	
		}	
		historyBiz.putHistory(registHistoryVO);
		return view;
	}
	
	@Override
	public void checkDuplicateUserID(String emailId, HttpServletResponse response) {
		
		boolean isDuplicateUserID = this.memberBiz.checkDuplicateUserID(emailId); 
		AjaxUtil.sendResponse(response, isDuplicateUserID+"");
	}
	
	@Override
	public ModelAndView myPage(HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		UsersVO userInfo = (UsersVO) session.getAttribute("_MEMBER_");
		
		String userBirth = userInfo.getBirth().split(" ")[0];
		userInfo.setBirth(userBirth);
		
		view.addObject("userInfo", userInfo);
		view.setViewName("member/myPage");
		
		return view;
	}
	
	@Override
	public ModelAndView myPageModify(HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		UsersVO userInfo = (UsersVO) session.getAttribute("_MEMBER_");
		
		String userBirth = userInfo.getBirth().split(" ")[0];
		userInfo.setBirth(userBirth);
		
		view.addObject("userInfo", userInfo);
		view.setViewName("member/myPageModify");
		
		return view;
	}
	
	@Override
	public ModelAndView modify(UsersVO usersVO, HttpServletRequest request, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		HttpSession session =  request.getSession();
		
		
		UsersVO beforeUserInfo = (UsersVO)session.getAttribute("_MEMBER_");
		String beforeNickname = beforeUserInfo.getNickname();
		String beforePassword = beforeUserInfo.getPassword();
		
		HistoryVO memberModifyHistoryVO = new HistoryVO();
		memberModifyHistoryVO.setEmailId(usersVO.getEmailId());
		memberModifyHistoryVO.setIp(request.getRemoteAddr());
		memberModifyHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		
		if(errors.hasErrors()) {
			UsersVO userInfo = (UsersVO) session.getAttribute("_MEMBER_");
			
			memberModifyHistoryVO.setOperationDescription("회원수정에 실패했습니다. 이유 : Nickname 혹은 비밀번호가 조건에 일치하지 않음.");
			
			view.addObject("userInfo", userInfo);
			view.setViewName("member/myPageMoify");
		}
		else {
			try{
				String password= usersVO.getPassword();
				String emailId = ((UsersVO) session.getAttribute("_MEMBER_")).getEmailId();
				usersVO.setEmailId(emailId);
				
				this.memberBiz.modify(usersVO);
				usersVO.setPassword(password);
				UsersVO userInfo = this.loginBiz.login(usersVO);
				
				if(beforeNickname.equals(usersVO.getNickname()) && !beforePassword.equals(password)){
					memberModifyHistoryVO.setOperationDescription("회원수정에 성공했습니다. 수정부분 : 비밀번호 ");
				}
				else if(!beforeNickname.equals(usersVO.getNickname()) && beforePassword.equals(password)){
					memberModifyHistoryVO.setOperationDescription("회원수정에 성공했습니다. 수정부분 : Nickname ( "+ beforeNickname + "->" + usersVO.getNickname()+" )");
				}
				else {
					memberModifyHistoryVO.setOperationDescription("회원수정에 성공했습니다. 수정부분 : 비밀번호, Nickname ( "+ beforeNickname + "->" + usersVO.getNickname()+" )");
				}
				
				session.setAttribute("_MEMBER_", userInfo);
				view.setViewName("redirect:/member/myPageMoify");
			}
			catch(RuntimeException e){
				memberModifyHistoryVO.setOperationDescription("회원수정에 실패했습니다. 이유 : 에러발생!");
			}
		}
		historyBiz.putHistory(memberModifyHistoryVO);
		return view;
	}
	
	@Override
	public ModelAndView exit(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		UsersVO usersVO = (UsersVO) session.getAttribute("_MEMBER_");
		String password = usersVO.getPassword();
		
		HistoryVO memberExitHistoryVO = new HistoryVO();
		memberExitHistoryVO.setEmailId(usersVO.getEmailId());
		memberExitHistoryVO.setIp(request.getRemoteAddr());
		memberExitHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		
		try{
			usersVO.setDeleteFlag("Y");
			
			this.memberBiz.exit(usersVO);
			
			usersVO.setPassword(password);
			
			session.invalidate();			
			
			memberExitHistoryVO.setOperationDescription("회원이 탈퇴에 성공했습니다.");
			view.setViewName("redirect:/login");
		}
		catch(RuntimeException e){		
			memberExitHistoryVO.setOperationDescription("회원이 탈퇴에 실패했습니다.");
		}
		historyBiz.putHistory(memberExitHistoryVO);
		return view;
	}
	
	@Override
	public Map<String, Object> lostPassword(String emailId, HttpServletRequest request) {
		
		Map<String, Object> reuslt = new HashMap<String, Object>();

		HistoryVO memberLostPwdHistoryVO = new HistoryVO();
		memberLostPwdHistoryVO.setEmailId("비로그인");
		memberLostPwdHistoryVO.setIp(request.getRemoteAddr());
		memberLostPwdHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		
		try{
			boolean emailIdYN = this.memberBiz.lostPassword(emailId);
			
			reuslt.put("result", emailIdYN);
			memberLostPwdHistoryVO.setOperationDescription("임시 비밀번호를 발급을 시도했습니다. Email Id : " + emailId);
		}
		catch(RuntimeException e){
			memberLostPwdHistoryVO.setOperationDescription("임시 비밀번호를 발급에 실패했습니다. 이유 : 에러발생!  " );
		}
		
		historyBiz.putHistory(memberLostPwdHistoryVO);
		return reuslt;
	}
	
	@Override
	public Map<String, String> temporaryPassword(UsersVO usersVO, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<String, String>();

		HistoryVO memberTempPwdHistoryVO = new HistoryVO();
		memberTempPwdHistoryVO.setEmailId("비로그인");
		memberTempPwdHistoryVO.setIp(request.getRemoteAddr());
		memberTempPwdHistoryVO.setOperationType(OperationHistory.MEMBER_HISTORY);
		
		try{
			String temPassword = "";
			for(int i = 0; i < 8; i++){
				char lowerStr = (char)(Math.random() * 26 + 97);
				if(i%2 == 0){
					temPassword += (int)(Math.random() * 10);
				}
				else{
					temPassword += lowerStr;
				}
			}
			
			usersVO.setPassword(temPassword);
			this.memberBiz.temporaryPassword(usersVO);
			
			usersVO.setPassword(temPassword);
			
			result.put("password", temPassword);
			memberTempPwdHistoryVO.setOperationDescription("임시 비밀번호를 발급에 성공했습니다. Email Id : " + usersVO.getEmailId());
		}
		catch(RuntimeException e){
			memberTempPwdHistoryVO.setOperationDescription("임시 비밀번호 발급에 실패 했습니다. 이유 : 에러 발생!");
		}
		
		historyBiz.putHistory(memberTempPwdHistoryVO);
		return result;
	}
	
	@Override
	public ModelAndView myAlarmList(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		
		String emailId = ((UsersVO) session.getAttribute("_MEMBER_")).getEmailId();
		
		List<UserRequestVO> userRequest = this.memberBiz.myAlarmList(emailId);
		
		view.addObject("userRequestList", userRequest);
		view.setViewName("member/myPage/myAlarm");
		return view;
	}

	@Override
	public ModelAndView myAlarmDelete(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		String[] selectedAlarms = request.getParameterValues("chk");
		
		for(String selectedAlarm : selectedAlarms){
			this.memberBiz.myAlarmDelete(selectedAlarm);
		}
		view.setViewName("redirect:/member/myPage/myAlarm");
		
		return view;
	}
}
