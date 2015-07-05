package com.ktds.high.history.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.service.HistoryService;
import com.ktds.high.history.vo.HistoryVO;

public class HistoryServiceImpl implements HistoryService{

	private HistoryBiz historyBiz;
	
	
	
	public void setHistoryBiz(HistoryBiz historyBiz) {
		this.historyBiz = historyBiz;
	}

	@Override
	public void putHistory(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView getHistory(HttpServletRequest request) {
		List<HistoryVO> historyList = this.historyBiz.getHistory();
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("historyList",historyList);
		view.setViewName("history/viewHistoryList");
		return view;
	}

}
