package com.ktds.high.board.hotdeal.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class HotdealVO {

	private String hotdealBoardId;
	
	@NotEmpty(message="제목을 입력하세요")
	private String hotdealSubject;
	
	@NotEmpty(message="내용을 입력하세요")
	private String hotdealContent;
	
	private String hotdealcreatedDate;
	private String hotdealModifiedDate;
	private MultipartFile hotdealUploadedFile;
	private String hotdealRealName;
	private String hotdealRandomName;
	private String emailId;
	private String hotdealDelFlag;
	private String hotdealCategory;

	public String getHotdealCategory() {
		return hotdealCategory;
	}
	public void setHotdealCategory(String hotdealCategory) {
		this.hotdealCategory = hotdealCategory;
	}
	public String getHotdealBoardId() {
		return hotdealBoardId;
	}
	public void setHotdealBoardId(String hotdealBoardId) {
		this.hotdealBoardId = hotdealBoardId;
	}
	public String getHotdealSubject() {
		return hotdealSubject;
	}
	public void setHotdealSubject(String hotdealSubject) {
		this.hotdealSubject = hotdealSubject;
	}
	public String getHotdealContent() {
		return hotdealContent;
	}
	public void setHotdealContent(String hotdealContent) {
		this.hotdealContent = hotdealContent;
	}
	public String getHotdealcreatedDate() {
		return hotdealcreatedDate;
	}
	public void setHotdealcreatedDate(String hotdealcreatedDate) {
		this.hotdealcreatedDate = hotdealcreatedDate;
	}
	public String getHotdealModifiedDate() {
		return hotdealModifiedDate;
	}
	public void setHotdealModifiedDate(String hotdealModifiedDate) {
		this.hotdealModifiedDate = hotdealModifiedDate;
	}
	public MultipartFile getHotdealUploadedFile() {
		return hotdealUploadedFile;
	}
	public void setHotdealUploadedFile(MultipartFile hotdealUploadedFile) {
		this.hotdealUploadedFile = hotdealUploadedFile;
	}
	public String getHotdealRealName() {
		return hotdealRealName;
	}
	public void setHotdealRealName(String hotdealRealName) {
		this.hotdealRealName = hotdealRealName;
	}
	public String getHotdealRandomName() {
		return hotdealRandomName;
	}
	public void setHotdealRandomName(String hotdealRandomName) {
		this.hotdealRandomName = hotdealRandomName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getHotdealDelFlag() {
		return hotdealDelFlag;
	}
	public void setHotdealDelFlag(String hotdealDelFlag) {
		this.hotdealDelFlag = hotdealDelFlag;
	}

}
