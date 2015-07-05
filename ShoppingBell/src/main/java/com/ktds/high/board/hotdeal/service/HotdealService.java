package com.ktds.high.board.hotdeal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.hotdeal.vo.HotdealVO;

public interface HotdealService {

	ModelAndView write(HotdealVO hotdealVO, Errors errors);

	ModelAndView hotdealList();

	ModelAndView hotdealDetail(String hotdealBoardId);

	ModelAndView hotdealDelete(String hotdealBoardId);

	ModelAndView hotdealUpdate(String hotdealBoardId);

	ModelAndView hotdealDoUpdate(HotdealVO hotdealVO, Errors errors);

	void hotdealFileDownload(String hotdealBoardId, HttpServletRequest request, HttpServletResponse response);

	ModelAndView Test();

	ModelAndView hotdealView();
}
