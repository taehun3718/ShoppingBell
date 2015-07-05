package com.ktds.high.board.free.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.ktds.high.board.free.biz.FreeBiz;
import com.ktds.high.board.free.dao.FreeDAO;
import com.ktds.high.board.free.vo.FreeListVO;
import com.ktds.high.board.free.vo.FreeSearchVO;
import com.ktds.high.board.free.vo.FreeVO;
import com.ktds.high.common.util.Paging;


public class FreeBizImpl implements FreeBiz{

	private FreeDAO freeDAO;
	
	public void setFreeDAO(FreeDAO freeDAO) {
		this.freeDAO = freeDAO;
	}

	@Override
	public void doWrite(FreeVO freeVO) {
		
		String content = freeVO.getContent();
		content = content.replaceAll("\n", "<br/>").replaceAll("\r", "");
		freeVO.setContent(content);
		
		
		this.freeDAO.doWrite(freeVO);
	}

	@Override
	public void doModify(FreeVO freeVO) {
		
		String content = freeVO.getContent();
		content = content.replaceAll("\n", "<br/>").replaceAll("\r", "");
		freeVO.setContent(content);
		
		this.freeDAO.doModify(freeVO);
	}

	@Override
	public void doDelete(FreeVO freeVO) {
		this.freeDAO.doDelete(freeVO);
	}
	@Override
	public void soldOut(FreeVO freeVO) {
		this.freeDAO.soldOut(freeVO);
	}

	@Override
	public FreeVO viewDetail(String freeId) {
		
		
		return freeDAO.viewDetail(freeId);
	}

	@Override
	public FreeListVO viewList(FreeSearchVO freeSearchVO) {
		int count = this.freeDAO.getArticleCount(freeSearchVO);
		
		Paging paging = freeSearchVO.getPaging();
		paging.setTotalArticleCount(count);
		
		FreeListVO list = new FreeListVO();
		list.setPaging(paging);
		if(count > 0) {
			list.setFreeList(this.freeDAO.viewList(freeSearchVO));
		}
		else {
			list.setFreeList(new ArrayList<FreeVO>());
		}
		
		return list;
	}
	@Override
	public List<FreeVO> viewMainList() {
		return this.freeDAO.viewMainList();
	}

	@Override
	public void updateHitCount(String freeId) {
		this.freeDAO.updateHitCount(freeId);
	}
	
}
