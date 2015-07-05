package com.ktds.high.regist.vo;


public class UserRequestVO {

	private String userRequestId;
	private String userEmail;
	private String userGender;
	private String userBirth;
	private String productName;
	private int productPrice;
	private String productSize;
	private String shopType;
	private String shopSearchUrl;
	private String startDate;
	
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserRequestId() {
		return userRequestId;
	}
	public void setUserRequestId(String userRequestId) {
		this.userRequestId = userRequestId;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	private String deleteDate;
	
	public String getShopSearchUrl() {
		return shopSearchUrl;
	}
	public void setShopSearchUrl(String shopSearchUrl) {
		this.shopSearchUrl = shopSearchUrl;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
}
