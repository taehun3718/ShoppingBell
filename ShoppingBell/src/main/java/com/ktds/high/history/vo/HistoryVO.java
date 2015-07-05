package com.ktds.high.history.vo;

public class HistoryVO {
	
public class OperationHistory{
		
		public static final String LOGIN_HISTORY = "LH";
		public static final String FREE_HISTORY = "FH";
		public static final String MEMBER_HISTORY = "MH";
		public static final String HOTDEAL_HISTORY = "HH";
		public static final String POST_HISTORY = "PH";
		public static final String REQUEST_HISTORY = "RH";
		public static final String FREE_REPLY_HISTORY = "FRPH";
		
	}
	
	private int historyId;
	private String ip;
	private String operationType;
	private String operationDescription;
	private String createdDate;
	private String emailId;
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationDescription() {
		return operationDescription;
	}
	public void setOperationDescription(String operationDescription) {
		this.operationDescription = operationDescription;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
}
