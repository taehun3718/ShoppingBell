package com.ktds.high.board.hotdeal.biz;

import java.util.List;

import com.ktds.high.board.hotdeal.vo.HotdealVO;

public interface HotdealBiz {

	void write(HotdealVO hotdealVO);

	List<HotdealVO> hotdealList();

	HotdealVO hotdealDetail(String hotdealBoardId);

	void hotdealDelete(String hotdealBoardId);

	void hotdealDoUpdate(HotdealVO hotdealVO);
}
