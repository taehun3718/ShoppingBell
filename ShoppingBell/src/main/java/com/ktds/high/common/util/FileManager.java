package com.ktds.high.common.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
/**
 * 업로드를 하기 위해 만들어진 클래스
 * @author TaeHoon Kim
 *
 */
public class FileManager {
	
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
	
	private String DESTINATION_DIRECTORY = "D:\\";
	private String MKDIR = "postPicture";
	
	private MultipartFile fileOne;
	private MultipartFile fileTwo;
	/**
	 * 파일을 초기화한다. 디렉토리가 없으면 디렉토리를 생성한다.
	 * @param MultipartFile 1
	 * @param MultipartFile 2
	 */
	public FileManager(MultipartFile fileOne
					, MultipartFile fileTwo) {
		this.fileOne = fileOne;
		this.fileTwo = fileTwo;

		File destFileDir = new File(DESTINATION_DIRECTORY
				+ MKDIR);

		if (!destFileDir.exists()) {
			destFileDir.mkdirs();
		}
	}
	   /**
	    * 파일을 초기화한다. 디렉토리가 없으면 디렉토리를 생성한다.
	    * @param MultipartFile 1
	    * @param MultipartFile 2
	    * @param mkdir(디렉토리 이름)
	    */
	   public FileManager(MultipartFile fileOne
	         , MultipartFile fileTwo, String mkdir) {
	      this.fileOne = fileOne;
	      this.fileTwo = fileTwo;
	      
	      setMKDIR(mkdir);
	      
	      File destFileDir = new File(DESTINATION_DIRECTORY
	            + MKDIR);
	      
	      if (!destFileDir.exists()) {
	         destFileDir.mkdirs();
	      }
	   }
	/**
	 * 지정된 디렉토리에 파일을 전송한다. 파일 전송에 성공한 수를 리턴한다.
	 * @param fileNameOne
	 * @param fileNameTwo
	 * @return 파일 전송 성공 횟수
	 */
	public int transfer(String fileNameOne, String fileNameTwo) {

		int transferCnt = 0;
		File destFile = new File(DESTINATION_DIRECTORY + MKDIR
				, fileNameOne);

		if (fileOne != null) {

			transferCnt++;
			try {
				fileOne.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		destFile = new File(DESTINATION_DIRECTORY + MKDIR
				, fileNameTwo);

		if (fileTwo != null) {
			transferCnt++;
			try {
				fileTwo.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		return transferCnt;
	}
	
	public void setDirectory(char dir) {
		char[] drive = {'a','b','c','d','e','f','g'
						,'h','i','j','k','l','m','n'
						,'o','p','q','r','s','t','u'
						,'v','w','x','y','z'};
		
		for(char d : drive) {
			if(dir==d) {
				DESTINATION_DIRECTORY = dir + "://";
				return;
			}
		}
		new InvalidDriveException("이 드라이브 문자는 잘못되었습니다.");
	}
	
	public void setMKDIR(String MKDIR) {
		this.MKDIR = MKDIR;
	}
}
