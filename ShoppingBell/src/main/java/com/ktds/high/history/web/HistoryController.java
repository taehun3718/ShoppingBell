package com.ktds.high.history.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.history.service.HistoryService;

@Controller
public class HistoryController {

	private HistoryService historyService;
	
	
	
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@RequestMapping("/history/historyList")
	public ModelAndView viewList(HttpServletRequest request){
		return this.historyService.getHistory(request);
	}
	
}
