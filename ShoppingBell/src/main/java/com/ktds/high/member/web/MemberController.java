package com.ktds.high.member.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.service.MemberService;

@Controller
public class MemberController {
	
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/member/regist")
	public ModelAndView registView(){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/regist");
		return view;
	}
	
	@RequestMapping("/member/doRegist")
	public ModelAndView doRegist(@Valid UsersVO usersVO, Errors errors, HttpServletRequest request){	
		
		return this.memberService.regist(usersVO, errors, request);
	}
	
	@RequestMapping("/member/checkDuplicateUserIDAjax")
	public void checkDuplicateUserID(
			@RequestParam String emailId
			, HttpServletResponse response){
		
		this.memberService.checkDuplicateUserID(emailId, response); 
	}
	
	@RequestMapping("/member/myPage")
	public ModelAndView myPageView(HttpSession session){
		
		return this.memberService.myPage(session);
	}
	
	@RequestMapping("/member/myPageModify")
	public ModelAndView myPageModify(HttpSession session){
		
		return this.memberService.myPageModify(session);
	}
	
	@RequestMapping("/member/modify")
	public ModelAndView modify(@Valid UsersVO usersVO, Errors errors, HttpServletRequest request){
		
		return this.memberService.modify(usersVO, request, errors);
	}
	
	@RequestMapping("/member/exit")
	public ModelAndView exit(HttpServletRequest request){
		
		return this.memberService.exit(request);
	}
	
	@RequestMapping("/member/lostPassword")
	public ModelAndView lostPasswordView(){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/lostPassword");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/member/doLostPassword")
	public Map<String, Object> lostPassword(@RequestParam String emailId, HttpServletRequest request){
		
		Map<String, Object> result = this.memberService.lostPassword(emailId, request);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/member/temporaryPassword")
	public Map<String, String> temporaryPassword(UsersVO usersVO, HttpServletRequest request){
		
		Map<String, String> password = this.memberService.temporaryPassword(usersVO, request);
		
		return password;
	}
	
	@RequestMapping("/member/myPage/myAlarm")
	public ModelAndView myAlarmList(HttpServletRequest request){
		
		return this.memberService.myAlarmList(request);
	}
	
	@RequestMapping("/member/myPage/myAlarm/alarmDelete")
	public ModelAndView myAlarmDelete(HttpServletRequest request){
		return this.memberService.myAlarmDelete(request);
	}

}

