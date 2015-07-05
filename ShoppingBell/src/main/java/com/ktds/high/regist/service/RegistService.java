package com.ktds.high.regist.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.regist.vo.UserRequestVO;

public interface RegistService {

	public ModelAndView registUserRequest(UserRequestVO userRequestVO, Errors erros, HttpServletRequest request);

}
