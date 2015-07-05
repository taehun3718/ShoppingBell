package com.ktds.high.history.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface HistoryService {
	public void putHistory(String message);
	public ModelAndView getHistory(HttpServletRequest request);
	
}
