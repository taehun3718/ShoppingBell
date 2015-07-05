package com.ktds.high.board.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.service.ReplyService;
import com.ktds.high.board.free.vo.FreeReplyVO;

@Controller
public class ReplyController {

	private ReplyService replyService;

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@RequestMapping("/reply/write")
	public ModelAndView write(HttpServletRequest request
							, @Valid FreeReplyVO replyVO
							, Errors errors) {
		return replyService.write(request, replyVO, errors);
	}
	
	@RequestMapping("/reply/modify")
	public ModelAndView modify(HttpServletRequest request, @Valid FreeReplyVO replyVO, Errors errors) {
		return replyService.modify(request, replyVO, errors);
	}
	
	@RequestMapping("/reply/{freeId}/{replyId}")
	@ResponseBody
	public FreeReplyVO getReply(@PathVariable String freeId, @PathVariable String replyId) {
		return replyService.getReply(freeId, replyId);
	}
	
	@RequestMapping("/reply/delete/{freeId}/{replyId}")
	public ModelAndView delete(HttpServletRequest request, @PathVariable String freeId, @PathVariable String replyId) {
		return replyService.delete(request, freeId, replyId);
	}
	
	
}

