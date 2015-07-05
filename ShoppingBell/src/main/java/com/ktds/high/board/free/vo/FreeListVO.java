package com.ktds.high.board.free.vo;

import java.util.ArrayList;
import java.util.List;

import com.ktds.high.common.util.Paging;


public class FreeListVO {
	private List<FreeVO> freeList;
	private Paging paging;
	
	public List<FreeVO> getFreeList() {
		List<FreeVO> temp = new ArrayList<FreeVO>();
		temp.addAll(freeList);
		return temp;
	}
	public void setFreeList(List<FreeVO> freeList) {
		List<FreeVO> temp = new ArrayList<FreeVO>();
		temp.addAll(freeList);
		this.freeList = temp;
	}
	
	public Paging getPaging() {
		return paging.getClone();
	}
	public void setPaging(Paging paging) {
		this.paging = paging.getClone();
	}
	
}
