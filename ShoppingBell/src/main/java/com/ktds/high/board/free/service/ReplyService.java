package com.ktds.high.board.free.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.vo.FreeReplyVO;


public interface ReplyService {
	public ModelAndView write(HttpServletRequest request, FreeReplyVO replyVO, Errors errors);

	public FreeReplyVO getReply(String freeId, String replyId);

	public ModelAndView modify(HttpServletRequest request, FreeReplyVO replyVO, Errors errors);

	public ModelAndView delete(HttpServletRequest request, String freeId, String replyId);

}
