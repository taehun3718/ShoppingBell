package com.ktds.high.board.hotdeal.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.hotdeal.biz.HotdealBiz;
import com.ktds.high.board.hotdeal.service.HotdealService;
import com.ktds.high.board.hotdeal.vo.HotdealVO;

public class HotdealServiceImpl implements HotdealService {

	private HotdealBiz hotdealBiz;

	public void setHotdealBiz(HotdealBiz hotdealBiz) {
		this.hotdealBiz = hotdealBiz;
	}

	@Override
	public ModelAndView write(@Valid HotdealVO hotdealVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		
		MultipartFile hotFile = hotdealVO.getHotdealUploadedFile();
		
		if(errors.hasErrors()) {
			
			view.setViewName("board/hotdeal/hotdealWrite");
		}
		else {
			
			if(hotFile != null) {
				
				Calendar calendar = Calendar.getInstance();
				
				String today = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
				
				String hotdealFileName = hotFile.getOriginalFilename();
				String hotdealRandomFileName = UUID.randomUUID().toString();
				
				hotdealVO.setHotdealRealName(hotdealFileName);
				hotdealVO.setHotdealRandomName(today + "\\" + hotdealRandomFileName);
				
				File hotdeFileDir = new File("D:\\hotdealFiles\\" + today);
				
				
				if(!hotdeFileDir.exists()) { hotdeFileDir.mkdirs(); }
				
				File hotdealFile = new File("D:\\hotdealFiles\\" + today, hotdealRandomFileName);
				
				try { hotFile.transferTo(hotdealFile); }
				catch(IllegalStateException | IOException exception) { throw new RuntimeException(exception.getMessage(), exception); }
			}
			
			this.hotdealBiz.write(hotdealVO);
			
			view.setViewName("redirect:/hotdeal/list");
		}
		
		return view;
	}

	public ModelAndView hotdealList() {
		
		ModelAndView view = new ModelAndView();
		
		List<HotdealVO> hotdealList = this.hotdealBiz.hotdealList();
		
		view.addObject("hotdealList", hotdealList);
		view.setViewName("board/hotdeal/hotdealList");
		
		return view;
	}

	@Override
	public ModelAndView hotdealDetail(String hotdealBoardId) {
		
		ModelAndView view = new ModelAndView();
		
		HotdealVO hotdealDetail = this.hotdealBiz.hotdealDetail(hotdealBoardId);
		
		view.addObject("hotdealDetail", hotdealDetail);
		view.setViewName("board/hotdeal/hotdealDetail");
		
		return view;
	}

	@Override
	public ModelAndView hotdealDelete(String hotdealBoardId) {
		
		ModelAndView view = new ModelAndView();
		
		this.hotdealBiz.hotdealDelete(hotdealBoardId);
		
		view.setViewName("redirect:/hotdeal/list");
		
		return view;
	}

	@Override
	public ModelAndView hotdealUpdate(String hotdealBoardId) {
		
		ModelAndView view = new ModelAndView();
		
		HotdealVO hotdealUpdate = this.hotdealBiz.hotdealDetail(hotdealBoardId);
		
		view.addObject("hotdealUpdate", hotdealUpdate);
		view.setViewName("board/hotdeal/hotdealWrite");
		
		return view;
	}

	@Override
	public ModelAndView hotdealDoUpdate(@Valid HotdealVO hotdealVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		
		MultipartFile hotFileUpdate = hotdealVO.getHotdealUploadedFile();
		
		if(errors.hasErrors()) {
			
			view.setViewName("board/hotdeal/hotdealDetail");
		}
		else {
			
			if(hotFileUpdate != null) {
				
				Calendar calendar = Calendar.getInstance();
				
				String todayUpdate = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
				
				String hotdealUpdateFileName = hotFileUpdate.getOriginalFilename();
				String hotdealUpdateRandomFileName = UUID.randomUUID().toString();
				
				hotdealVO.setHotdealRealName(hotdealUpdateFileName);
				hotdealVO.setHotdealRandomName(todayUpdate + "\\" + hotdealUpdateRandomFileName);
				
				File hotdeFileDir = new File("D:\\hotdealFiles\\" + todayUpdate);
				
				if(!hotdeFileDir.exists()) { hotdeFileDir.mkdirs(); }
				
				File hotdealFile = new File("D:\\hotdealFiles\\" + todayUpdate, hotdealUpdateRandomFileName);
				
				try { hotFileUpdate.transferTo(hotdealFile); }
				catch(IllegalStateException | IOException exception) { throw new RuntimeException(exception.getMessage(), exception); }
			}
			
			this.hotdealBiz.hotdealDoUpdate(hotdealVO);
			
			view.setViewName("redirect:/hotdeal/list");
		}
		
		return view;
	}

	@Override
	public void hotdealFileDownload(@PathVariable String hotdealBoardId, HttpServletRequest request, HttpServletResponse response) {
		
		HotdealVO dealInfo =  this.hotdealBiz.hotdealDetail(hotdealBoardId);
		
		String hotdealRandomName = dealInfo.getHotdealRandomName();
		String hotdealRealName = dealInfo.getHotdealRealName();
		
		hotdealRandomName = hotdealRandomName.replace("\\", "/");
		
		File downloadFile = new File("D:\\hotdealFiles\\" + hotdealRandomName);
		
		try {
			
			if(downloadFile == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			response.setContentType("application/download; charset=UTF-8");
			response.setContentLengthLong((int) downloadFile.length());
			
			String userAgent = request.getHeader("User-Agent");
			boolean isMsie = userAgent.indexOf("MSIE") > -1;
			
			String fileName = null;
			
			if(isMsie) { fileName = URLEncoder.encode(hotdealRealName, "UTF-8"); }
			else { fileName = new String(hotdealRealName.getBytes("UTF-8"), "ISO-8859-1"); }
			
			response.setHeader("Content-Disposition", "attachment\"" + fileName + "\";");
			response.setHeader("Content-Transfer-Encoding",	"binary");
			
			OutputStream outputStream = response.getOutputStream();
			FileInputStream fileInputStream = null;
			
			try {
				fileInputStream = new FileInputStream(downloadFile);
				FileCopyUtils.copy(fileInputStream, outputStream);
				outputStream.flush();
			}
			finally {
				
				if(fileInputStream != null) fileInputStream.close();
				if(outputStream != null) outputStream.close();
			}
		}
		catch(Exception exception) { throw new RuntimeException(exception.getMessage(), exception); }
	}
	
	@Override
	public ModelAndView Test() {
		ModelAndView view = new ModelAndView();
		
		List<HotdealVO> hotdealList = this.hotdealBiz.hotdealList();
		
		view.addObject("hotdealList", hotdealList);
		view.setViewName("board/hotdeal/hotdealTest");
		
		return view;
	}

	@Override
	public ModelAndView hotdealView() {
		
		ModelAndView view = new ModelAndView();
		
		List<HotdealVO> hotdealView = this.hotdealBiz.hotdealList();
		
		view.addObject("hotdealView", hotdealView);
		view.setViewName("main/index");
		
		return view;
	}
}
