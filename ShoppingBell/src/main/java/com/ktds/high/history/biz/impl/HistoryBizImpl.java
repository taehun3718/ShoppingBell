package com.ktds.high.history.biz.impl;

import java.util.List;

import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.dao.HistoryDAO;
import com.ktds.high.history.vo.HistoryVO;

public class HistoryBizImpl implements HistoryBiz{

	private HistoryDAO historyDAO;
	
	
	
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}



	@Override
	public List<HistoryVO> getHistory() {
		return this.historyDAO.getHistory();
	}



	@Override
	public void putHistory(HistoryVO historyVO) {
		this.historyDAO.putHistory(historyVO);
		
	}

	
	
}
