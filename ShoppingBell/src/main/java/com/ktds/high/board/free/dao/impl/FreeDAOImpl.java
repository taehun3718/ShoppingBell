package com.ktds.high.board.free.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.board.free.dao.FreeDAO;
import com.ktds.high.board.free.vo.FreeSearchVO;
import com.ktds.high.board.free.vo.FreeVO;

public class FreeDAOImpl extends SqlSessionDaoSupport implements FreeDAO{

	@Override
	public void doWrite(FreeVO freeVO) {
		getSqlSession().insert("freeDAO.writeArticle", freeVO);
	}

	@Override
	public void doModify(FreeVO freeVO) {
		getSqlSession().update("freeDAO.updateArticle", freeVO);		
	}

	@Override
	public void doDelete(FreeVO freeVO) {
		getSqlSession().update("freeDAO.deleteArticle", freeVO);
	}
	
	@Override
	public void soldOut(FreeVO freeVO) {
		getSqlSession().update("freeDAO.soldOut", freeVO);
	}

	@Override
	public FreeVO viewDetail(String freeId) {
		return getSqlSession().selectOne("freeDAO.getArticleById", freeId);
	}
	
	@Override
	public List<FreeVO> viewList(FreeSearchVO freeSearchVO) {
		return getSqlSession().selectList("freeDAO.getAllArticleList", freeSearchVO);
	}
	
	@Override
	public List<FreeVO> viewMainList() {
		return getSqlSession().selectList("freeDAO.getMainArticleList");
	}
	
	@Override
	public void updateHitCount(String freeId) {
		getSqlSession().update("freeDAO.updateHitCount", freeId);
	}

	@Override
	public int getArticleCount(FreeSearchVO freeSearchVO) {
		return getSqlSession().selectOne("freeDAO.getArticleCount", freeSearchVO);
	}
}
