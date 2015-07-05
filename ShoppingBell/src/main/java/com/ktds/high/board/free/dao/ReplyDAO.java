package com.ktds.high.board.free.dao;

import java.util.List;

import com.ktds.high.board.free.vo.FreeReplyVO;


public interface ReplyDAO {
	public List<FreeReplyVO> getReplies(String freeId);

	public int getReplyCount(String freeId);

	public void write(FreeReplyVO replyVO);

	public FreeReplyVO getReply(FreeReplyVO replyVO);

	public void modify(FreeReplyVO replyVO);

	public void delete(FreeReplyVO replyVO);


}
