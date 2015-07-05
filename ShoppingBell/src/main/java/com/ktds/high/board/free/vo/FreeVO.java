package com.ktds.high.board.free.vo;

import org.springframework.web.multipart.MultipartFile;

public class FreeVO {

	private String freeId;
	private String subject;
	private String emailId;
	private String nickName;
	private String content;
	private MultipartFile uploadFile;
	private String realName;
	private String randomName;
	private String createdDate;
	private String modifiedDate;
	private int hit;
	private String freeBlockFlag;
	private String freeDeleteFlag;
	private String productType;
	private String productPrice;
	private String productName;
	private String size;
	private String etc;
	private String onSale;
	
	private int replyCount;
	
	
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRandomName() {
		return randomName;
	}
	public void setRandomName(String randomName) {
		this.randomName = randomName;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFreeBlockFlag() {
		return freeBlockFlag;
	}
	public void setFreeBlockFlag(String freeBlockFlag) {
		this.freeBlockFlag = freeBlockFlag;
	}
	public String getFreeDeleteFlag() {
		return freeDeleteFlag;
	}
	public void setFreeDeleteFlag(String freeDeleteFlag) {
		this.freeDeleteFlag = freeDeleteFlag;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getOnSale() {
		return onSale;
	}
	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}
	
	
	
}
