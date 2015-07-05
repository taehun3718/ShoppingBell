package com.ktds.high.common.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 다중 업로드를 하기 위해 만들어진 클래스
 * 
 * @author TaeHoon Kim
 *
 */
public class MultiFileManager {

	public class InvalidDriveException extends RuntimeException {
		private static final long serialVersionUID = 3529881975349979576L;

		public InvalidDriveException() {
			super();
		}

		public InvalidDriveException(String message) {
			super(message);
		}

		public InvalidDriveException(Throwable t) {
			super(t);
		}

		public InvalidDriveException(String message, Throwable t) {
			super(message, t);
		}
	}

	private static String DESTINATION_DIRECTORY = "D:\\";
	private static String MKDIR = "postPicture";

	private MultipartFile[] multipartFiles;

	/**
	 * 파일을 초기화한다. 디렉토리가 없으면 디렉토리를 생성한다.
	 * @param MultipartFile []files
	 */
	public MultiFileManager(MultipartFile[] files) {
		this.multipartFiles = files;
		File destFileDir = new File(MultiFileManager.DESTINATION_DIRECTORY + MultiFileManager.MKDIR);
		if (!destFileDir.exists()) {
			destFileDir.mkdirs();
		}
	}

	/**
	 * 파일을 초기화한다. 디렉토리가 없으면 디렉토리를 생성한다.
	 * 
	 * @param MultipartFile []files
	 * @param mkdir(디렉토리 이름)
	 */
	public MultiFileManager(MultipartFile[] files, String mkdir) {
		this.multipartFiles = files;

		setMKDIR(mkdir);

		File destFileDir = new File(MultiFileManager.DESTINATION_DIRECTORY + MultiFileManager.MKDIR);

		if (!destFileDir.exists()) {
			destFileDir.mkdirs();
		}
	}

	/**
	 * 지정된 디렉토리에 파일을 전송한다. 파일 전송에 성공한 수를 리턴한다.
	 * multipartFiles과 리스트의 이름 순서는 반드시 같아야 한다.
	 * @param List<String> 순차적 파일 이름 지정
	 * @return 파일 전송 성공 횟수
	 */
	public int transfer(List<String> fileNames) {
		
		int i;
		int transferCnt = 0;
		int length = fileNames.size();
		
		String fileName;
		MultipartFile mf = null;
		File destFile = null;
		for(i=0; i<length; i++) {
			
			mf = multipartFiles[i];
			fileName = fileNames.get(i);
			if(fileName!=null && !fileName.equals(""))
				destFile =  new File(MultiFileManager.DESTINATION_DIRECTORY + MultiFileManager.MKDIR, fileName);
			
			if(fileName!=null && !fileName.equals("")) {
				
				try {
					
					mf.transferTo(destFile);
					transferCnt++;
				} 
				catch (IllegalStateException e) {
					throw new RuntimeException(e.getMessage(), e);
					
				} 
				catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				
			}
		}
		return transferCnt;
	}

	public void setDirectory(char dir) {
		char[] drive = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };

		for (char d : drive) {
			if (dir == d) {
				MultiFileManager.DESTINATION_DIRECTORY = dir + "://";
				return;
			}
		}
		throw new InvalidDriveException("이 드라이브 문자는 잘못되었습니다.");
	}

	public static String getDESTINATION_DIRECTORY() {
		return DESTINATION_DIRECTORY;
	}

	public static void setDESTINATION_DIRECTORY(String DESTINATION_DIRECTORY) {
		MultiFileManager.DESTINATION_DIRECTORY = DESTINATION_DIRECTORY;
	}

	public static String getMKDIR() {
		return MKDIR;
	}

	public void setMKDIR(String MKDIR) {
		MultiFileManager.MKDIR = MKDIR;
	}
}
