package com.ktds.high.board.hotdeal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.hotdeal.service.HotdealService;
import com.ktds.high.board.hotdeal.vo.HotdealVO;

@Controller
public class HotdealController {

	private HotdealService hotdealService;

	public void setHotdealService(HotdealService hotdealService) {
		this.hotdealService = hotdealService;
	}

	@RequestMapping("/hotdeal/write")
	public String hotdealWriteView() {
		
		return "board/hotdeal/hotdealWrite";
	}

	@RequestMapping("/hotdeal/doWrite")
	public ModelAndView hotdealWrite(@Valid HotdealVO hotdealVO, Errors errors) {
		
		System.out.println(hotdealVO.getHotdealContent());
		System.out.println(hotdealVO.getHotdealSubject());
		
		return this.hotdealService.write(hotdealVO, errors);
	}

	@RequestMapping("/hotdeal/list")
	public ModelAndView hotdealListView() {
		
		return this.hotdealService.hotdealList();
	}

	@RequestMapping("/hotdeal/detail/{hotdealBoardId}")
	public ModelAndView hotdealDetailView(@PathVariable String hotdealBoardId) {
		
		System.out.println(hotdealBoardId);
		
		return this.hotdealService.hotdealDetail(hotdealBoardId);
	}

	@RequestMapping("/hotdeal/delete/{hotdealBoardId}")
	public ModelAndView hotdealDelete(@PathVariable String hotdealBoardId) {
		
		return this.hotdealService.hotdealDelete(hotdealBoardId);
	}

	@RequestMapping("/hotdeal/update/{hotdealBoardId}")
	public ModelAndView hotdealUpdateView(@PathVariable String hotdealBoardId) {
		
		return this.hotdealService.hotdealUpdate(hotdealBoardId);
	}

	@RequestMapping("/hotdeal/doUpdate")
	public ModelAndView hotdealDoUpdate(@Valid HotdealVO hotdealVO, Errors errors) {
		
		return this.hotdealService.hotdealDoUpdate(hotdealVO, errors);
	}

	@RequestMapping("/hotdeal/fileDownload/{hotdealBoardId}")
	public void hotdealFileDownload(@PathVariable String hotdealBoardId, HttpServletRequest request, HttpServletResponse response) {
		
		this.hotdealService.hotdealFileDownload(hotdealBoardId, request, response);
	}
	
	@RequestMapping("/hotdeal/hotdealTest")
	public ModelAndView hotdealTestView() {
		return this.hotdealService.Test();
	}
}
