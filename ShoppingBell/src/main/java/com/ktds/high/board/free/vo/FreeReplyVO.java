package com.ktds.high.board.free.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class FreeReplyVO {
	private String replyId;
	private String freeId;
	private String emailId;
	private String nickName;
	
	@NotEmpty(message="내용을 입력하세요.")
	private String content;
	private String createdDate;
	private String modifiedDate;
	
	private boolean isMyReply;
	
	
	

	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setMyReply(boolean isMyReply) {
		this.isMyReply = isMyReply;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	
	public boolean getIsMyReply() {
		return isMyReply;
	}
	public boolean isMyReply() {
		return isMyReply;
	}
	public void setIsMyReply(boolean isMyReply) {
		this.isMyReply = isMyReply;
	}
}
