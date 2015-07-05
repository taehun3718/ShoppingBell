package com.ktds.high.regist.biz.impl;

import com.ktds.high.regist.biz.RegistBiz;
import com.ktds.high.regist.dao.RegistDAO;
import com.ktds.high.regist.vo.UserRequestVO;

public class RegistBizImpl implements RegistBiz{

	private RegistDAO registDAO;

	public void setRegistDAO(RegistDAO registDAO) {
		this.registDAO = registDAO;
	}
	
	@Override
	public void registUserRequest(UserRequestVO userRequestVO) {
		this.registDAO.registUserRequest(userRequestVO);
	}
	
	@Override
	public void registUserRequestHistory(UserRequestVO userRequestVO) {
		this.registDAO.registUserRequestHistory(userRequestVO);
	}
}
