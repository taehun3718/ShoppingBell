package com.ktds.high.main.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.biz.FreeBiz;
import com.ktds.high.board.free.vo.FreeVO;
import com.ktds.high.board.hotdeal.biz.HotdealBiz;
import com.ktds.high.board.hotdeal.vo.HotdealVO;
import com.ktds.high.board.post.biz.PostBiz;
import com.ktds.high.board.post.vo.PostVO;
import com.ktds.high.main.service.MainService;

public class MainServiceImpl implements MainService {

	private HotdealBiz hotdealBiz;
	private PostBiz postBiz;
	private FreeBiz freeBiz;

	public void setHotdealBiz(HotdealBiz hotdealBiz) {
		this.hotdealBiz = hotdealBiz;
	}

	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}

	public void setFreeBiz(FreeBiz freeBiz) {
		this.freeBiz = freeBiz;
	}

	@Override
	public ModelAndView mainView() {

		ModelAndView view = new ModelAndView();
		
		List<HotdealVO> hotdaelMain = this.hotdealBiz.hotdealList();
		List<PostVO> postMain = this.postBiz.postMainList();
		
		List<FreeVO> freeMain =  this.freeBiz.viewMainList();
		
		view.addObject("hotdaelMain", hotdaelMain);
		view.addObject("postMain", postMain);
		view.addObject("freeMain", freeMain);
		
		view.setViewName("main/index");
		
		return view;
	}
}
