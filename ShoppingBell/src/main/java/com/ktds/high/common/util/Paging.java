package com.ktds.high.common.util;


public class Paging implements Cloneable {

	/**
	 * 검색된 게시물의 총 개수
	 */
	private int totalArticleCount;
	/**
	 * 한 페이지 내에 보여줄 게시물의 개수
	 */
	private int printArticle;
	
	/**
	 * 한 그룹에 보여줄 페이지의 개수
	 */
	private int printPage;
	
	/**
	 * 페이지번호를 넘겨 받았을 때
	 * 자동으로 계산되는 게시글의 시작 번호
	 */
	private int startArticleNumber;
	
	/**
	 * 페이지번호를 넘겨 받았을 때
	 * 자동으로 계산되는 게시글의 마지막 번호
	 */
	private int endArticleNumber;

	/**
	 * 검색된 게시글이 페이징 되었을 때 보여질 총 페이지 수
	 * 올림 (검색된 게시글 개수 / 한 페이지내에 보여질 게시글 개수)
	 */
	private int totalPage;
	
	/**
	 * 검색된 게시글이 페이징 되었을 때 보여질 총 그룹의 수
	 * 올림 (총 페이지 수 / 한 그룹내에 보여질 페이지 개수)
	 */
	private int totalGroup;

	/**
	 * 현재 페이지가 속한 그룹의 번호
	 */
	private int nowGroupNumber;

	/**
	 * 현재 페이지가 속한 그룹의 시작 페이지 번호
	 */
	private int groupStartPage;

	/**
	 * 현재 그룹에서 다음 그룹으로 넘어갔을 때
	 * 그 그룹에서 시작되는 페이지의 시작번호
	 */
	private int nextGroupPageNumber;
	
	/**
	 * 현재 그룹에서 이전 그룹으로 넘어갔을 때
	 * 그 그룹에서 시작되는 페이지의 시작 번호
	 */
	private int prevGroupPageNumber;

	/**
	 * 현재 페이지의 번호
	 * 기본값은 0
	 */
	private int pageNo;

	public Paging() {
		this.printArticle = 9;
		this.printPage = 10;
	}
	
	public Paging(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		this.startArticleNumber = (this.pageNo * this.printArticle) + 1;
		this.endArticleNumber = this.startArticleNumber + this.printArticle - 1;

		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}

	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;
		
		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	public int getPageNo() {
		return this.pageNo;
	}
	
	public int getTotalCount() {
		return this.totalArticleCount;
	}

	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}

	public int getEndArticleNumber() {
		return this.endArticleNumber;
	}

	public String getPagingList(String link, String pageFormat, String prev,
			String next, String moreParams) {

		StringBuffer buffer = new StringBuffer();

		if (this.nowGroupNumber > 0) {
			buffer.append("<a href=\"?" + link + "=" + this.prevGroupPageNumber
					+ "&" + moreParams + "\">" + prev + "</a>");
		}

		int nextPrintPage = this.groupStartPage + this.printPage;
		if (nextPrintPage > this.totalPage) {
			nextPrintPage = this.totalPage + 1;
		}

		String pageNumber = "";

		for (int i = this.groupStartPage; i < nextPrintPage; i++) {
			pageNumber = pageFormat.replaceAll("@", i + "");
			if ((i - 1) == this.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			buffer.append("<a href=\"?" + link + "=" + (i - 1) + "&"
					+ moreParams + "\">" + pageNumber + "</a>");
		}

		if (this.nowGroupNumber < (this.totalGroup - 1)) {
			buffer.append("<a href=\"?" + link + "=" + this.nextGroupPageNumber
					+ "&" + moreParams + "\">" + next + "</a>");
		}

		return buffer.toString();
	}
	
	public Paging getClone() {
		Paging paging = null;
		
		try {
			paging = (Paging) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return paging;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		Paging paging = new Paging(this.printArticle, this.printPage);
		paging.setPageNumber(this.pageNo + "");
		paging.setTotalArticleCount(this.totalArticleCount);
		
		return paging;
	}

}
