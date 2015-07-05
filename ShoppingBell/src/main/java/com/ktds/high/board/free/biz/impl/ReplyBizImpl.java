package com.ktds.high.board.free.biz.impl;

import java.util.List;

import com.ktds.high.board.free.biz.ReplyBiz;
import com.ktds.high.board.free.dao.ReplyDAO;
import com.ktds.high.board.free.vo.FreeListVO;
import com.ktds.high.board.free.vo.FreeVO;
import com.ktds.high.board.free.vo.FreeReplyVO;


public class ReplyBizImpl implements ReplyBiz{
	private ReplyDAO replyDAO;

	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public List<FreeReplyVO> getReplies(String freeId) {
		return replyDAO.getReplies(freeId);
	}
	
	@Override
	public FreeListVO getReplyCount(FreeListVO freeList) {
		
		List<FreeVO> articles = freeList.getFreeList();
		
		for(FreeVO article : articles) {
			article.setReplyCount(replyDAO.getReplyCount(article.getFreeId()));
		}
		
		freeList.setFreeList(articles);
		
		return freeList;
	}
	
	@Override
	public void write(FreeReplyVO replyVO) {
		this.replyDAO.write(replyVO);
	}
	
	@Override
	public void modify(FreeReplyVO replyVO) {
		this.replyDAO.modify(replyVO);
	}
	
	@Override
	public void delete(FreeReplyVO replyVO) {
		this.replyDAO.delete(replyVO);
	}
	
	@Override
	public FreeReplyVO getReply(FreeReplyVO replyVO) {
		return this.replyDAO.getReply(replyVO);
	}
	
}

