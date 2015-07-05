package com.ktds.high.board.free.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.board.free.dao.ReplyDAO;
import com.ktds.high.board.free.vo.FreeReplyVO;


public class ReplyDAOImpl extends SqlSessionDaoSupport implements ReplyDAO {

	@Override
	public List<FreeReplyVO> getReplies(String freeId) {
		return getSqlSession().selectList("replyDAO.getReplies", freeId);
	}
	
	@Override
	public int getReplyCount(String freeId) {
		return getSqlSession().selectOne("replyDAO.getReplyCount", freeId);
	}
	
	@Override
	public void write(FreeReplyVO replyVO) {
		getSqlSession().insert("replyDAO.write", replyVO);
	}
	
	@Override
	public void modify(FreeReplyVO replyVO) {
		getSqlSession().update("replyDAO.modify", replyVO);
	}
	
	@Override
	public void delete(FreeReplyVO replyVO) {
		getSqlSession().update("replyDAO.delete", replyVO);
	}
	
	@Override
	public FreeReplyVO getReply(FreeReplyVO replyVO) {
		return getSqlSession().selectOne("replyDAO.getReply", replyVO);
	}
	
}
