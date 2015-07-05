package com.ktds.high.regist.dao;

import com.ktds.high.regist.vo.UserRequestVO;

public interface RegistDAO {

	public void registUserRequest(UserRequestVO userRequestVO);

	public void registUserRequestHistory(UserRequestVO userRequestVO);

}
