package com.ktds.high.board.post.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class MultiPostDetailVO {
	
	private String postingId;
	@NotEmpty(message="제목을 입력하세요.")
	private String subject;
	@NotEmpty(message="내용을 입력하세요.")
	private List<String> contents;
	private String emailId;
	private int postingLike;
	private List<String> realNames;
	private List<String> randomNames;
	private String createdDate;
	private String modifiedDate;
	private int hit;
	private String category;
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	private int numOfRealName;
	
	public MultiPostDetailVO() {
		contents	= new ArrayList<String>();
		realNames	= new ArrayList<String>();
		randomNames	= new ArrayList<String>();
	}
	
	public String getPostingId() {
		return postingId;
	}
	public void setPostingId(String postingId) {
		this.postingId = postingId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
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
	public List<String> getRealNames() {
		return realNames;
	}
	public void setRealNames(List<String> realNames) {
		this.realNames = realNames;
	}
	public List<String> getRandomNames() {
		return randomNames;
	}
	public void setRandomNames(List<String> randomNames) {
		this.randomNames = randomNames;
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
	
	public void addContents(String s) {
		contents.add(s);
	}
	
	public void addRealName(String s) {
		realNames.add(s);
	}
	public void addRandName(String s) {
		randomNames.add(s);
	}
	
	public int getContentSize() {
		return contents.size();
	}

	public void setNumOfRealName(int numOfRealName) {
		this.numOfRealName = numOfRealName;
	}

	public String getCategory() {
		return category;
	}

	public int getNumOfRealName() {
		return numOfRealName;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
