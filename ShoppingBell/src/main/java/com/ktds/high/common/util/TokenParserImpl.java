package com.ktds.high.common.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 파싱을 효율적으로 하기 위한 유틸..
 * @author TaeHoon Kim
 *
 */
interface TokenParser {
	/**
	 * 문자열 파싱 메소드
	 * @return List
	 */
	public List<String> getParsedContext();
}


public class TokenParserImpl implements TokenParser {
	
	int numOfToken;

	private String token;
	private String context;
	
	/**
	 * 파일에서 토큰을 파싱한다.
	 * @param token(파싱될 구분자)
	 * @param context(파싱하고 싶은 문자열)
	 */
	public TokenParserImpl(String token
					 , String context) {
		this.token		= token;
		this.context	= context;
	}

	@Override
	public List<String> getParsedContext() {
		List<String> parsedContext = new ArrayList<String>();
		String[] contexts = context.split(token);
		
		int i=0;
		for(String c : contexts) {
			if(i++ == 0)
				setNumOfToken(Integer.parseInt(c));
			else
				parsedContext.add(c);
		}
		return parsedContext;
	}
	
	public int getNumOfToken() {
		return numOfToken;
	}

	private void setNumOfToken(int numOfToken) {
		this.numOfToken = numOfToken;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setContext(String context) {
		this.context = context;
	}


}