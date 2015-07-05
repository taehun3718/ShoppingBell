package com.ktds.high.board.free.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.vo.FreeVO;

public interface FreeService {
	
	public ModelAndView doWrite(HttpServletRequest request, FreeVO freeVO, Errors errors);
	public ModelAndView viewDetail(HttpSession session, String freeId);
	public ModelAndView viewInitList(HttpSession session);
	public ModelAndView viewList(HttpServletRequest request);
	
	
	public ModelAndView viewModify(HttpSession session, String freeId);
	public ModelAndView doModify(HttpServletRequest request, FreeVO freeVO, Errors errors);
	
	public ModelAndView doDelete(HttpServletRequest request, FreeVO freeVO, Errors errors);


	public void fileDownload(String realName, String randomName,
			HttpServletResponse response, HttpServletRequest request);

	public ModelAndView soldOut(HttpServletRequest request, FreeVO freeVO,
			Errors errors);
	
	public ModelAndView updateHitCount(String id);






	





	

}
