package com.ktds.high.board.free.biz;

import java.util.List;

import com.ktds.high.board.free.vo.FreeListVO;
import com.ktds.high.board.free.vo.FreeSearchVO;
import com.ktds.high.board.free.vo.FreeVO;

public interface FreeBiz {

	public void doWrite(FreeVO freeVO);

	public FreeVO viewDetail(String freeId);
	public FreeListVO viewList(FreeSearchVO freeSearchVO);
	public List<FreeVO> viewMainList();
	
	public void doModify(FreeVO freeVO);
	public void doDelete(FreeVO freeVO);
	
	public void updateHitCount(String freeId);

	public void soldOut(FreeVO freeVO);

}
