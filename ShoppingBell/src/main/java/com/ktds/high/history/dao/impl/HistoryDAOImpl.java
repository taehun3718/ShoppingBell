package com.ktds.high.history.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.history.dao.HistoryDAO;
import com.ktds.high.history.vo.HistoryVO;

public class HistoryDAOImpl extends SqlSessionDaoSupport implements HistoryDAO{

	@Override
	public List<HistoryVO> getHistory() {
		return getSqlSession().selectList("historyDAO.getHistory");
	}

	@Override
	public void putHistory(HistoryVO historyVO) {
		getSqlSession().insert("historyDAO.putHistory",historyVO);
		
	}
	
}
