package com.ktds.high.common.util;

import org.mindrot.jbcrypt.BCrypt;

import com.ktds.high.login.vo.UsersVO;

public class PasswordUtil {
/**
 * 회원가입 및 회원 정보 수정 시 사용
 * @param usersVO
 * @return
 */
	public static UsersVO generatePassword(UsersVO usersVO) {
		
		String salt = BCrypt.gensalt();
		usersVO.setSalt(salt);
		
		String password = usersVO.getPassword();
		password = BCrypt.hashpw(password, salt);
		usersVO.setPassword(password);
		
		return usersVO;
		
	}
	
	public static UsersVO getPassword(UsersVO usersVO){
		String salt = usersVO.getSalt();
		if(salt == null) {
			salt = BCrypt.gensalt();
		}
		
		usersVO.setSalt(salt);
		
		String password = usersVO.getPassword();
		password = BCrypt.hashpw(password, salt);
		usersVO.setPassword(password);
		
		return usersVO;
	}
}
