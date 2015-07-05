package com.ktds.high.history.biz;

import java.util.List;

import com.ktds.high.history.vo.HistoryVO;

public interface HistoryBiz {

	public List<HistoryVO> getHistory();
	public void putHistory(HistoryVO historyVO);

}
