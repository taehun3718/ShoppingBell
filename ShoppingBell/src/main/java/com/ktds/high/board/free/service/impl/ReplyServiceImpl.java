package com.ktds.high.board.free.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.biz.ReplyBiz;
import com.ktds.high.board.free.service.ReplyService;
import com.ktds.high.board.free.vo.FreeReplyVO;
import com.ktds.high.board.free.vo.FreeVO;
import com.ktds.high.common.util.FileManager;
import com.ktds.high.login.biz.LoginBiz;
import com.ktds.high.login.vo.UsersVO;

public class ReplyServiceImpl implements ReplyService{

	private ReplyBiz replyBiz;
	private LoginBiz loginBiz;

	public void setReplyBiz(ReplyBiz replyBiz) {
		this.replyBiz = replyBiz;
	}

	public void setLoginBiz(LoginBiz loginBiz) {
		this.loginBiz = loginBiz;
	}


	@Override
	public ModelAndView write(HttpServletRequest request, FreeReplyVO replyVO, Errors errors) {
		
		HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
		
		replyVO.setEmailId(id);
		
		this.replyBiz.write(replyVO);
		
		return new ModelAndView("redirect:/board/free/detail/" + replyVO.getFreeId());

	}
	

	
	
	

	@Override
	public ModelAndView modify(HttpServletRequest request, FreeReplyVO replyVO,
			Errors errors) {
		HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
		
		replyVO.setEmailId(id);
		
		this.replyBiz.modify(replyVO);
		
		return new ModelAndView("redirect:/board/free/detail/" + replyVO.getFreeId());
		
	}
	
	@Override
	public FreeReplyVO getReply(String freeId, String replyId) {
		FreeReplyVO replyVO = new FreeReplyVO();
		replyVO.setFreeId(freeId);
		replyVO.setReplyId(replyId);
		
		replyVO = this.replyBiz.getReply(replyVO);
		
		replyVO.setContent(
				replyVO.getContent().replaceAll("<br/>", "\n")
				);
		
		return replyVO;
	}
	
	@Override
	public ModelAndView delete(HttpServletRequest request, String freeId, String replyId) {
		
		/*HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
		
		FreeReplyVO replyVO = new FreeReplyVO();
		replyVO.setReplyId(replyId);
		*/
		
		FreeReplyVO replyVO = new FreeReplyVO();
		replyVO.setReplyId(replyId);
		
		System.out.println("replyId : " + replyId);
		this.replyBiz.delete(replyVO);
			
		return new ModelAndView("redirect:/board/free/detail/" + freeId );
	}
}
