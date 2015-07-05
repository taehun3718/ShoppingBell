package com.ktds.high.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {
	
	
	public static void sendResponse(HttpServletResponse response, String message) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(message);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	/**
	 * resource src/main/resources에 있는 
	 * 파일을 불러와서 ajax로 리턴한다.
	 * @param 클래스
	 * @param HttpServletResponse 객
	 * @param src/main/resources에 있는 파일 이름
	 */
	public static void sendResponseResource(Class<?> cls
											, HttpServletResponse response
											, String fileName){
		try {
			
			URL url = cls.getClassLoader().getResource(fileName);
			File file = new File(url.getPath()
					.substring(1
							, url.getPath().length()));

			BufferedReader br 
				= new BufferedReader(new FileReader(file));
			
			String responseBody = "";
			String s;

			while ((s = br.readLine()) != null) {
				responseBody += s;
			}
			
			sendResponse(response, responseBody);
			br.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
		}
	}
}

