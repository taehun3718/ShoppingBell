package com.ktds.high.board.hotdeal.dao;

import java.util.List;

import com.ktds.high.board.hotdeal.vo.HotdealVO;

public interface HotdealDAO {

	void write(HotdealVO hotdealVO);

	List<HotdealVO> hotdealList();

	HotdealVO hotdealDetail(String hotdealBoardId);

	void hotdealDelete(String hotdealBoardId);

	void hotdealDoUpdate(HotdealVO hotdealVO);
}
