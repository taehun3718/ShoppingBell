package com.ktds.high.board.hotdeal.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.high.board.hotdeal.biz.HotdealBiz;
import com.ktds.high.board.hotdeal.dao.HotdealDAO;
import com.ktds.high.board.hotdeal.vo.HotdealVO;

public class HotdealBizImpl implements HotdealBiz {

	private HotdealDAO hotdealDAO;

	public void setHotdealDAO(HotdealDAO hotdealDAO) {
		this.hotdealDAO = hotdealDAO;
	}

	@Override
	public void write(HotdealVO hotdealVO) {
		
		this.hotdealDAO.write(hotdealVO);
	}

	public List<HotdealVO> hotdealList() {
		
		return this.hotdealDAO.hotdealList();
	}

	@Override
	public HotdealVO hotdealDetail(String hotdealBoardId) {
		
		return this.hotdealDAO.hotdealDetail(hotdealBoardId);
	}

	@Override
	public void hotdealDelete(String hotdealBoardId) {
		
		this.hotdealDAO.hotdealDelete(hotdealBoardId);
	}

	@Override
	public void hotdealDoUpdate(HotdealVO hotdealVO) {
		
		HotdealVO originalHotdeal = this.hotdealDetail(hotdealVO.getHotdealBoardId());
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("hotdealBoardId", hotdealVO.getHotdealBoardId());
		
		if(!originalHotdeal.getHotdealSubject().equals(hotdealVO.getHotdealBoardId())) {
			
			map.put("hotdealSubject", hotdealVO.getHotdealSubject());
		}
		
		if(!originalHotdeal.getHotdealContent().equals(hotdealVO.getHotdealContent())) {
			
			map.put("hotdealCompany", hotdealVO.getHotdealContent());
		}
		
		if(!originalHotdeal.getHotdealRealName().equals(hotdealVO.getHotdealRealName())) {
			
			map.put("hotdealRealName", hotdealVO.getHotdealRealName());
		}
		
		if(!originalHotdeal.getHotdealRandomName().equals(hotdealVO.getHotdealRandomName())) {
			
			map.put("hotdealRandomName", hotdealVO.getHotdealRandomName());
		}
		
		if(map.size() > 1) {
			
			this.hotdealDAO.hotdealDoUpdate(hotdealVO);
		}
	}
}
