package com.ktds.high.board.post.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MultiPostVO {
	private String postingId;
	@NotEmpty(message="제목을 입력하세요.")
	private String subject;
	@NotEmpty(message="내용을 입력하세요.")
	private String[] content;
	private String emailId;
	private int postingLike;
	private String[] realName;
	private String[] randomName;
	private String createdDate;
	private String modifiedDate;
	private String category;
	
	private int numOfRealName;

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private MultipartFile[] uploadedFile;
	
	
	public String getPostingId() {
		return postingId;
	}
	public void setPostingId(String postingId) {
		this.postingId = postingId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getPostingLike() {
		return postingLike;
	}
	public void setPostingLike(int postingLike) {
		this.postingLike = postingLike;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
	public MultipartFile[] getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(MultipartFile[] uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public String[] getRealName() {
		return realName;
	}
	public void setRealName(String[] realName) {
		this.realName = realName;
	}
	public String[] getRandomName() {
		return randomName;
	}
	public void setRandomName(String[] randomName) {
		this.randomName = randomName;
	}
	public int getNumOfRealName() {
		return numOfRealName;
	}
	public void setNumOfRealName(int numOfRealName) {
		this.numOfRealName = numOfRealName;
	}
}
