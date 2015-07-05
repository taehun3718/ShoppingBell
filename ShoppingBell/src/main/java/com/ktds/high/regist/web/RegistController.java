package com.ktds.high.regist.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.regist.vo.UserRequestVO;
import com.ktds.high.regist.service.RegistService;

@Controller
public class RegistController {
	
	private RegistService registService;

	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}
	
	@RequestMapping("/regist")
	public ModelAndView regist() {
		ModelAndView view = new ModelAndView();
		view.setViewName("regist/registRequest");
		return view;
	}
	
	@RequestMapping("/regist/doRegist")
	public ModelAndView registUserRequest(@Valid UserRequestVO userRequestVO, Errors erros, HttpServletRequest request) {
		return this.registService.registUserRequest(userRequestVO, erros, request);
	}
	
	@RequestMapping("/regist/myRequest")
	public ModelAndView myRequest() {
		ModelAndView view = new ModelAndView();
		view.setViewName("regist/myRequest");
		return view;
	}
}
