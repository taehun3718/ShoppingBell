package com.ktds.high.board.hotdeal.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.board.hotdeal.dao.HotdealDAO;
import com.ktds.high.board.hotdeal.vo.HotdealVO;

public class HotdealDAOImpl extends SqlSessionDaoSupport implements HotdealDAO {

	@Override
	public void write(HotdealVO hotdealVO) {
		
		getSqlSession().insert("hotdealDAO.write", hotdealVO);
	}

	@Override
	public List<HotdealVO> hotdealList() {
		
		return getSqlSession().selectList("hotdealDAO.hotdealList");
	}

	@Override
	public HotdealVO hotdealDetail(String hotdealBoardId) {
		
		return getSqlSession().selectOne("hotdealDAO.hotdealDetail", hotdealBoardId);
	}

	@Override
	public void hotdealDelete(String hotdealBoardId) {
		
		getSqlSession().update("hotdealDAO.hotdealDelete", hotdealBoardId);
	}

	@Override
	public void hotdealDoUpdate(HotdealVO hotdealVO) {
		
		getSqlSession().update("hotdealDAO.hotdealDoUpdate", hotdealVO);
	}
}
