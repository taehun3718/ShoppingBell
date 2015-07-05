package com.ktds.high.common.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getParam(HttpServletRequest request
						, String paramName
						, String defaultValue) {
		String paramValue = request.getParameter(paramName);
		
		if(paramValue == null || paramValue.length() == 0) {
			if(defaultValue == null) {
				defaultValue = "";
			}
			paramValue = defaultValue;
		}
		
		return paramValue;
	}
	
}
