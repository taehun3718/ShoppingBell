package com.ktds.high.board.free.dao;

import java.util.List;

import com.ktds.high.board.free.vo.FreeSearchVO;
import com.ktds.high.board.free.vo.FreeVO;

public interface FreeDAO {

	public void doWrite(FreeVO freeVO);
	
	public FreeVO viewDetail(String freeId);
	public List<FreeVO> viewList(FreeSearchVO freeSearchVO);
	
	public void doModify(FreeVO freeVO);
	public void doDelete(FreeVO freeVO);
	
	public void updateHitCount(String freeId);
	public int getArticleCount(FreeSearchVO freeSearchVO);

	public void soldOut(FreeVO freeVO);

	public List<FreeVO> viewMainList();
}
