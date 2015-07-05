package com.ktds.high.login.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.login.service.LoginService;
import com.ktds.high.login.vo.UsersVO;

@Controller
public class LoginController {
	
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("/login")
	public ModelAndView login(){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("login/login");
		return view; 
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Map<String, Object> doLogin(
			UsersVO usersVO
			, HttpServletRequest request){
		
		Map<String, Object> result = this.loginService.login(usersVO, request);
		
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView view  = new ModelAndView();
		
		this.loginService.logout(request);
		
		view.setViewName("redirect:/login");
		return view;
	}
}
