package com.ktds.high.board.post.vo;

public class PostReplyVO {
	private String postingReplyId;
	private String postingId;
	private String emailId;
	private String postingReplyContent;
	private String postingReplyCreatedDate;
	private String postingReplyModifiedDate;
	private String postingReplyDelFlag;
	
	//Join
	private String nickName;
	
	public String getPostingReplyId() {
		return postingReplyId;
	}
	
	public void setPostingReplyId(String postingReplyId) {
		this.postingReplyId = postingReplyId;
	}
	
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
	
	public String getPostingReplyContent() {
		return postingReplyContent;
	}
	
	public void setPostingReplyContent(String postingReplyContent) {
		this.postingReplyContent = postingReplyContent;
	}
	
	public String getPostingReplyCreatedDate() {
		return postingReplyCreatedDate;
	}
	
	public void setPostingReplyCreatedDate(String postingReplyCreatedDate) {
		this.postingReplyCreatedDate = postingReplyCreatedDate;
	}
	
	public String getPostingReplyModifredDate() {
		return postingReplyModifiedDate;
	}
	
	public void setPostingReplyModifredDate(String postingReplyModifredDate) {
		this.postingReplyModifiedDate = postingReplyModifredDate;
	}
	
	public String getPostingReplyDelFlag() {
		return postingReplyDelFlag;
	}
	
	public void setPostingReplyDelFlag(String postingReplyDelFlag) {
		this.postingReplyDelFlag = postingReplyDelFlag;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
