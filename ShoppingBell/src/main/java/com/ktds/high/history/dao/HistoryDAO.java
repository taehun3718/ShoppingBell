package com.ktds.high.history.dao;

import java.util.List;

import com.ktds.high.history.vo.HistoryVO;

public interface HistoryDAO {

	public List<HistoryVO> getHistory();

	public void putHistory(HistoryVO historyVO);

}
