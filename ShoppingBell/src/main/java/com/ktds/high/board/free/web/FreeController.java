package com.ktds.high.board.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.service.FreeService;
import com.ktds.high.board.free.vo.FreeVO;

@Controller
public class FreeController {

	private FreeService freeService;

	public void setFreeService(FreeService freeService) {
		this.freeService = freeService;
	}
	
	@RequestMapping("/board/free/write")
	public ModelAndView viewWrite() {

		ModelAndView view = new ModelAndView();
		view.setViewName("board/free/write");
		return view;	
	}
	
	@RequestMapping("/board/free/doWrite")
	public ModelAndView doWrite(HttpServletRequest request
								, @Valid FreeVO freeVO
								, Errors errors) {
		return this.freeService.doWrite(request, freeVO, errors);
	}
	
	@RequestMapping("/board/free/list/init")
	public ModelAndView viewInitList(HttpSession session) {
		return this.freeService.viewInitList(session);
	}
	
	@RequestMapping("/board/free/list")
	public ModelAndView viewList(HttpServletRequest request) {
		return this.freeService.viewList(request);
	}
	
	
	
	@RequestMapping("/board/free/detail/{freeId}")
	public ModelAndView viewDetail(HttpSession session, @PathVariable String freeId) {
		return this.freeService.viewDetail(session, freeId);
	}
	
	@RequestMapping("/board/free/modify/{freeId}")
	public ModelAndView viewModify(HttpSession session, @PathVariable String freeId) {
		return this.freeService.viewModify(session, freeId);
	}
	
	@RequestMapping("/board/free/doModify")
	public ModelAndView doModify(HttpServletRequest request
								, @Valid FreeVO freeVO
								, Errors errors) {
	
	return this.freeService.doModify(request, freeVO, errors);
	}
	
	@RequestMapping("/board/free/delete/{freeId}")
	public ModelAndView doDelete(HttpServletRequest request
								, @Valid FreeVO freeVO
								, Errors errors) {
		return this.freeService.doDelete(request, freeVO, errors);
	}
	
	@RequestMapping("/board/free/soldOut/{freeId}")
	public ModelAndView soldOut(HttpServletRequest request
			, @Valid FreeVO freeVO
			, Errors errors) {
		return this.freeService.soldOut(request, freeVO, errors);
	}
	
	@RequestMapping("/download/{realName}/{randomName}")
	public void fileDownload(@PathVariable String realName, @PathVariable String randomName
			, HttpServletResponse response, HttpServletRequest request){
		this.freeService.fileDownload(realName, randomName, response, request);
	}


	
}
