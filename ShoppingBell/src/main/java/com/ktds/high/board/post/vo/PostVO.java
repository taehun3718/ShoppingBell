package com.ktds.high.board.post.vo;

import java.util.UUID;

import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.multipart.MultipartFile;

public class PostVO {
	private String postingId;
	@NotEmpty(message="제목을 입력하세요.")
	private String subject;
	@NotEmpty(message="내용을 입력하세요.")
	private String content;
	private String emailId;
	private int postingLike;
	private String realName;
	private String randomName;
	private String createdDate;
	private String modifiedDate;
	private String numOfReply;
	private int hit;
	private String category;
	
	
	private MultipartFile uploadedFile;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
		setRandomName(UUID.randomUUID().toString());
	}
	
	public String getRandomName() {
		return randomName;
	}
	private void setRandomName(String randomName) {
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
	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String getContextBy() {
		String str= "";
		String parsedString = "";//구분자로 분리된 내용들이 여기에 다 들어감
		
		//내용이 있나 확인.(분리자로 다 구분되어 있음)
		String[] token = getContent().split("#shb#");
		
		int numOfContents = Integer.parseInt(token[0]);
		int i;
		Element e;
		
		for(i=1; i<=numOfContents; i++) {
			parsedString = token[i];
			e = Jsoup.parse(parsedString, "UTF-8"); // 2. 문자열로 파싱할 때
			parsedString = e.text();
			str += parsedString;
		}
		if(str.length()>20)
			return str.substring(0, 20) + "...";
		else
			return str.substring(0, str.length());
	}
	public String getNumOfReply() {
		return numOfReply;
	}
	public void setNumOfReply(String numOfReply) {
		this.numOfReply = numOfReply;
	}
	
	public String getNumOfFiles() {
		String[] token = this.getRandomName().split("#shb#");
		int numofFiles = Integer.parseInt(token[0]);
		
		return numofFiles > 0 ? numofFiles + "" :  0 + "";
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
