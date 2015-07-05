package com.ktds.high.board.post.vo;
/**
 * Ajax통신을 통해 데이터가 올바르게 받았는지
 * 받았거나 실패하면 그 이유가 무엇인지 확인하기 위해 넣기 위한 ResultVO
 * 
 * response body가 필요하며
 * jackson-bind라는 의존성 DI가 요구됨
 * 
 * @author TaeHoon Kim
 *
 */
public class ResultVO {
	private boolean isSuccess;
	private String because;
	/**
	 * ResponseBody에 싣기 위한 객체
	 * @param b: 성공했는지 실패 했는지 boolean 지정
	 * @param becauseMsg: 이유를 지정
	 */
	public ResultVO(boolean b, String becauseMsg) {
		this.isSuccess = b;
		this.because = becauseMsg;
	}
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getBecause() {
		return because;
	}
	public void setBecause(String because) {
		this.because = because;
	}
}
