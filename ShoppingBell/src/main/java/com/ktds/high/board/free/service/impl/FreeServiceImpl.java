package com.ktds.high.board.free.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.free.biz.FreeBiz;
import com.ktds.high.board.free.biz.ReplyBiz;
import com.ktds.high.board.free.service.FreeService;
import com.ktds.high.board.free.vo.FreeListVO;
import com.ktds.high.board.free.vo.FreeSearchVO;
import com.ktds.high.board.free.vo.FreeVO;
import com.ktds.high.board.free.vo.FreeReplyVO;
import com.ktds.high.common.util.FileManager;
import com.ktds.high.common.util.Paging;
import com.ktds.high.common.util.RequestUtil;
import com.ktds.high.login.vo.UsersVO;

public class FreeServiceImpl implements FreeService{

	private FreeBiz freeBiz;
	private ReplyBiz replyBiz;

	public void setFreeBiz(FreeBiz freeBiz) {
		this.freeBiz = freeBiz;
	}
	
	public void setReplyBiz(ReplyBiz replyBiz) {
		this.replyBiz = replyBiz;
	}



	@Override
	public ModelAndView doWrite(HttpServletRequest request, FreeVO freeVO, Errors errors) {

		MultipartFile file = freeVO.getUploadFile();
		//파일 업로드
		FileManager fileManager = new FileManager(file, null, "freepicture");

		String uploadFileName = file.getOriginalFilename();
		String randomFileName = UUID.randomUUID().toString();

		freeVO.setRealName(uploadFileName);
		freeVO.setRandomName(randomFileName);
		fileManager.transfer(freeVO.getRandomName(), "");
		
		
		HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
				
		freeVO.setEmailId(id);
		
		this.freeBiz.doWrite(freeVO);
		
		
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/board/free/list");
		return view;

	}

	@Override
	public void fileDownload(@PathVariable String realName, @PathVariable String randomName
			, HttpServletResponse response, HttpServletRequest request){

		File downloadFile = new File("d:/freepicture/" + randomName);
		try{
			if(downloadFile==null){
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			response.setContentType("application/download; charset=UTF-8" ); 
			response.setContentLength((int) downloadFile.length());

			//사용자의 브라우저 종류를 가져온다.
			String userAgent = request.getHeader("User-Agent");
			boolean isMsie = userAgent.indexOf("MSIE")>-1;

			String fileName = null;
			if(isMsie){
				fileName = URLEncoder.encode(realName,"UTF-8");

			}
			else{
				fileName = new String(realName.getBytes("UTF-8"),"UTF-8");
			}

			response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");

			OutputStream out = response.getOutputStream();
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(downloadFile);
				FileCopyUtils.copy(fis, out);
				out.flush();
			} finally {
				if(fis!=null){
					fis.close();
				}
				if(out!=null){
					out.close();
				}
			}
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage(), e);
		}

	}
	
	@Override
	public ModelAndView viewInitList(HttpSession session) {
		session.removeAttribute("_SEARCH_KEYWORD_");
		return new ModelAndView("redirect:/board/free/list");
	}
	
	@Override
	public ModelAndView viewList(HttpServletRequest request) {
		
		FreeSearchVO freeSearchVO = new FreeSearchVO();
		HttpSession session = request.getSession();
		
		FreeSearchVO searchVOInSession = (FreeSearchVO)session.getAttribute("_SEARCH_KEYWORD_");
		
		if(searchVOInSession == null) {
			searchVOInSession = new FreeSearchVO();
			searchVOInSession.setPaging(new Paging());
		}
		
		Paging paging = new Paging();
		
		paging.setPageNumber(RequestUtil.
				getParam(request,
						"pageNo", 
						searchVOInSession.getPaging().
						getPageNo() + ""));
		
		freeSearchVO.setPaging(paging);
		
		String subject = RequestUtil.
				getParam(request, "subject", searchVOInSession.getSubject());
		freeSearchVO.setSubject(subject);
		
		String nickName = RequestUtil.
				getParam(request, "nickName", searchVOInSession.getNickName());
		freeSearchVO.setNickName(nickName);
		
		String productType = RequestUtil.
				getParam(request, "productType", searchVOInSession.getProductType());
		freeSearchVO.setProductType(productType);
		
		String productName = RequestUtil.
				getParam(request, "productName", searchVOInSession.getProductName());
		freeSearchVO.setProductName(productName);
		
		String size = RequestUtil.
				getParam(request, "size", searchVOInSession.getSize());
		freeSearchVO.setSize(size);
		
		String etc = RequestUtil.
				getParam(request, "etc", searchVOInSession.getEtc());
		freeSearchVO.setEtc(etc);
		
		String onSale = RequestUtil.
				getParam(request, "onSale", searchVOInSession.getOnSale());
		freeSearchVO.setOnSale(onSale);
		
		String searchType = RequestUtil.
				getParam(request, "searchType", searchVOInSession.getSearchType());
		freeSearchVO.setSearchType(searchType);
		
		String searchKeyword = RequestUtil.
				getParam(request, "searchKeyword", searchVOInSession.getSearchKeyword());
		freeSearchVO.setSearchKeyword(searchKeyword);
	
		
		session.setAttribute("_SEARCH_KEYWORD_", freeSearchVO);
		
		FreeListVO freeList = this.freeBiz.viewList(freeSearchVO);
		/*freeList = this.replyBiz.getReplyCount(freeList);*/
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("freeSearchVO", freeSearchVO);
		
		view.setViewName("board/free/list");
		view.addObject("freeList", freeList);
		return view;

		
	}
	@Override
	public ModelAndView viewModify(HttpSession session, String freeId) {
		
		FreeVO originalArticleInfo = this.freeBiz.viewDetail(freeId);
		
		/*UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();*/
		
		String content = originalArticleInfo.getContent();
		content = content.replaceAll("<br/>", "\n");
		originalArticleInfo.setContent(content);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/board/free/modify");
		view.addObject("originalArticleInfo", originalArticleInfo);
		return view;
	}
	
	@Override
	public ModelAndView doModify(HttpServletRequest request, FreeVO freeVO, Errors errors) {
		
		MultipartFile file = freeVO.getUploadFile();
		//파일 업로드
		FileManager fileManager = new FileManager(file, null, "freepicture");

		String uploadFileName = file.getOriginalFilename();
		String randomFileName = UUID.randomUUID().toString();

		freeVO.setRealName(uploadFileName);
		freeVO.setRandomName(randomFileName);
		fileManager.transfer(freeVO.getRandomName(), "");
		
		
		FreeVO originalArticleInfo = this.freeBiz.viewDetail(freeVO.getFreeId());
		
		/*HttpSession session  = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
		
		if( !originalArticleInfo.getEmailId().equals(id) ) {
			throw new RuntimeException("수정 권한이 없습니다.");
		}
		
		freeVO.setEmailId(id);*/
		
		this.freeBiz.doModify(freeVO);
		
		
		/*ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/board/free/list");
		return view;*/
		return new ModelAndView("redirect:/board/free/detail/" + freeVO.getFreeId());

	}
	@Override
	public ModelAndView doDelete(HttpServletRequest request, FreeVO freeVO, Errors errors) {
		
		/*HttpSession session  = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
				
		freeVO.setEmailId(id);*/
		
		this.freeBiz.doDelete(freeVO);
		
		
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/board/free/list");
		return view;
	}
	@Override
	public ModelAndView soldOut(HttpServletRequest request, FreeVO freeVO, Errors errors) {
		
		/*HttpSession session  = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_MEMBER_");
		String id = userVO.getEmailId();
				
		freeVO.setEmailId(id);*/
		
		this.freeBiz.soldOut(freeVO);
		
		return new ModelAndView("redirect:/board/free/detail/" + freeVO.getFreeId());
	}
	
	@Override
	public ModelAndView updateHitCount(String id) {
		this.freeBiz.updateHitCount(id);
		
		ModelAndView view = new ModelAndView();
		
		/*view.addObject("freeVO", freeVO);*/
		view.setViewName("/board/free/detail");
		return view;
	}

	@Override
	public ModelAndView viewDetail(HttpSession session, String freeId) {
		
		UsersVO userVO = (UsersVO)session.getAttribute("_MEMBER_");
		String emailId = userVO.getEmailId();
		
		FreeVO freeVO = this.freeBiz.viewDetail(freeId);
		
		 
		List<FreeReplyVO> replies = this.replyBiz.getReplies(freeId);
		
		for(FreeReplyVO reply : replies) {
			reply.setIsMyReply(reply.getEmailId().equals(emailId));
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/board/free/detail");
		view.addObject("freeVO", freeVO);
		view.addObject("replies", replies);
		return view;
	}

	
}
