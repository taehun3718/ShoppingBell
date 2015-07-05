package com.ktds.high.board.free.biz;

import java.util.List;

import com.ktds.high.board.free.vo.FreeListVO;
import com.ktds.high.board.free.vo.FreeReplyVO;


public interface ReplyBiz {

	public List<FreeReplyVO> getReplies(String freeId);

	public FreeListVO getReplyCount(FreeListVO freeList);

	public void write(FreeReplyVO replyVO);

	public FreeReplyVO getReply(FreeReplyVO replyVO);

	public void modify(FreeReplyVO replyVO);

	public void delete(FreeReplyVO replyVO);


}
