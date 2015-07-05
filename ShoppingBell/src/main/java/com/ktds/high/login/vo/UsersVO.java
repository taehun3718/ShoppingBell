package com.ktds.high.login.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class UsersVO {
	
	@Email(message="올바른 이메일형식을 입력하세요.")
	@NotEmpty(message="id를 입력하세요.")
	private String emailId;
	
	@Length(min=8, max=50, message="비밀번호는 8~50자 입니다.")
	@NotEmpty(message="password를 입력하세요.")
	private String password;
	
	@Length(min=2, max=50, message="닉네임은 2~50자 입니다.")
	@NotEmpty(message="nickname를 입력하세요.")
	private String nickname;
	private int point;
	private String salt;
	private String createdDate;
	private String modifiedDate;
	private String deleteFlag;
	private String blockFlag;
	private String isAdminYN;
	private String forceLogin;
	private String birth;
	private String gender;
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getForceLogin() {
		return forceLogin;
	}
	public void setForceLogin(String forceLogin) {
		this.forceLogin = forceLogin;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getBlockFlag() {
		return blockFlag;
	}
	public void setBlockFlag(String blockFlag) {
		this.blockFlag = blockFlag;
	}
	public String getIsAdminYN() {
		return isAdminYN;
	}
	public void setIsAdminYN(String isAdminYN) {
		this.isAdminYN = isAdminYN;
	}

	
	
}
