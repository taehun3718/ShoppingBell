package com.ktds.high.board.post.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.post.biz.PostBiz;
import com.ktds.high.board.post.service.PostService;
import com.ktds.high.board.post.vo.MultiPostDetailVO;
import com.ktds.high.board.post.vo.MultiPostVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;
import com.ktds.high.board.post.vo.ResultVO;
import com.ktds.high.common.util.MultiFileManager;
import com.ktds.high.common.util.TokenParserImpl;
import com.ktds.high.history.biz.HistoryBiz;
import com.ktds.high.history.vo.HistoryVO;
import com.ktds.high.history.vo.HistoryVO.OperationHistory;
import com.ktds.high.login.vo.UsersVO;
import com.ktds.high.member.biz.MemberBiz;

public class PostServiceImpl implements PostService {

	private PostBiz postBiz;
	private MemberBiz memberBiz;
	private HistoryBiz historyBiz;

	public void setHistoryBiz(HistoryBiz historyBiz) {
		this.historyBiz = historyBiz;
	}


	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}


	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}
	
	@Override
	public void fileDownload(String postingId
							, String fileId
							, HttpServletRequest request
							, HttpServletResponse response) {
		PostVO fileInfo = postBiz.getPostDetailById(postingId);
		downloadFile(fileId, request, response, fileInfo);
		
	}
	
	//다운로드를 실제 하는 로직
	private void downloadFile(String fileId
							, HttpServletRequest request
							, HttpServletResponse response
							, PostVO fileInfo) {
		
		//파일 토큰에서 UUID 파일 리스트를 불러온다.
		TokenParserImpl tokenParser 
			= new TokenParserImpl("#shb#", fileInfo.getRandomName());
		List<String> randFileNameList = tokenParser.getParsedContext();	
		
		tokenParser.setContext(fileInfo.getRealName());
		List<String> realFileNameList = tokenParser.getParsedContext();	

		
		if(realFileNameList.size()!=0) {
			if(realFileNameList.get(Integer.parseInt(fileId))!=null) {
			
				File downloadFile = new File(MultiFileManager.getDESTINATION_DIRECTORY() 
						  +  MultiFileManager.getMKDIR() 
						  + "//" + randFileNameList.get(Integer.parseInt(fileId)));
				
				//내 컴퓨터에 파일을 다운로드 하는 로직
				download_To_LocalFile(fileId
										, request
										, response
										, realFileNameList.get(Integer.parseInt(fileId))
										, downloadFile);
			}
		}
	}


	private void download_To_LocalFile(String fileId
									 , HttpServletRequest request
									 , HttpServletResponse response
									 , String realfileName
									 , File downloadFile) {
		try {
			
			if ( downloadFile == null ) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			response.setContentType("applicaton/download; charset=utf-8");
			response.setContentLength((int)downloadFile.length());
			
			// 사용자의 브라우저 종류를 가져온다
			String userAgent = request.getHeader("User-Agent");
			boolean isMsie = userAgent.indexOf("MSIE") > -1;
			
			String fileName = null;
			
			if ( isMsie ) {
				fileName = URLEncoder.encode(realfileName, "UTF-8");
			}
			
			else {
				fileName = new String(realfileName.getBytes("UTF-8"), "ISO-8859-1");
			}
				
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			OutputStream out = response.getOutputStream();
			FileInputStream fis = null;
			
			try { 
				fis = new FileInputStream(downloadFile);
				FileCopyUtils.copy(fis, out);
				out.flush();
			}
			
			finally {
				if ( fis != null ) {
					fis.close();
				}
				if ( out != null ) {
					out.close();
				}
			}
		}
		
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public ModelAndView writePost(MultiPostVO multiPostVO
								, Errors errors
								, HttpServletRequest request) {
		
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyWritePostVO = new HistoryVO();
		historyWritePostVO.setIp(request.getRemoteAddr());
		historyWritePostVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if (errors.hasErrors() || usersVO==null) {
			historyWritePostVO.setOperationDescription("글쓰기 실패: 사용자 로그인이 되 있지 않습니다.");
			historyWritePostVO.setEmailId("n/a");
			
			historyBiz.putHistory(historyWritePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/login");
			
			return view;
		}
		else {
			historyWritePostVO.setEmailId(usersVO.getEmailId());
			multiPostVO.setEmailId(usersVO.getEmailId());
		}
		
		MultipartFile[] files = multiPostVO.getUploadedFile();	
		
		tranferData_and_setFileName(multiPostVO, files);
		
		boolean isWrited = postBiz.writePost(multiPostVO);
		
		if (isWrited) {
			usersVO.setPoint(20);
			memberBiz.plusPoint(usersVO, request);
			
			historyWritePostVO.setOperationDescription("글쓰기 성공 포인트가 20 증가합니다.");
			historyBiz.putHistory(historyWritePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/post/post");
			
			return view;
			
		} else {
			
			historyWritePostVO.setOperationDescription("글쓰기 실패: 데이터베이스 트랜잭션 처리에 실패하였습니다.");
			historyBiz.putHistory(historyWritePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("error/error");
			
			return view;
		}
	}

	private void tranferData_and_setFileName(MultiPostVO multiPostVO
											, MultipartFile[] files) {
		int i;
		int fileNameSize = files.length;
		
		MultiFileManager fileManager = new MultiFileManager(files);
		
		String realName[] = new String[fileNameSize];
		String randName[] = new String[fileNameSize];
			
		for(i=0; i<fileNameSize; i++) {
			realName[i] = files[i].getOriginalFilename();
			
			if(!realName[i].equals("")) {
				randName[i] = UUID.randomUUID().toString();
			}
		}
		
		multiPostVO.setRealName(realName);
		multiPostVO.setRandomName(randName);
		
		List<String> fileNameList = new ArrayList<String>();
		for(String fileRandName : randName)
			if(fileRandName!=null)
				fileNameList.add(fileRandName);
			else
				fileNameList.add(null);
		
		fileManager.transfer(fileNameList);
		
	}
	
	
	@Override
	public ModelAndView postList(PostSearchVO postSearchVO) {

		List<PostVO> postList = postBiz.postList(postSearchVO);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("board/post/postList");
		view.addObject("postList", postList);

		return view;
	}

	@Override
	public ModelAndView getMultiPostDetailById(String postId, HttpServletRequest request) {
		MultiPostDetailVO multiPostDetailVO	= postBiz.getMultiPostDetailById(postId);
		List<PostReplyVO> postReplyList		= postBiz.postReplyList(postId);
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		if(usersVO==null){
			postBiz.postHit(postId);
		}
		else if( !multiPostDetailVO.getEmailId().equals(usersVO.getEmailId())) {
			postBiz.postHit(postId);
		}
		multiPostDetailVO	= postBiz.getMultiPostDetailById(postId);
		ModelAndView view = new ModelAndView();
		
		view.setViewName("board/post/postDetailLikeVingle");
		
		view.addObject("multiPostDetailVO", multiPostDetailVO);
		view.addObject("usersVO", usersVO);
		view.addObject("postReplyList", postReplyList);
		
		return view;
	}
	
	@Override
	public ModelAndView getModifyPostLikeVinglePage(String postId) {
		MultiPostDetailVO multipostDetailVO = postBiz.getMultiPostDetailById(postId);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("board/post/modifyPostLikeVingle");
		view.addObject("multipostDetailVO", multipostDetailVO);
		
		return view;
	}
	
	@Override
	public ModelAndView updateMultiPost(MultiPostVO multiPostVO
										, String postId
										, HttpServletRequest request) {
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyUpdatePostVO = new HistoryVO();
		historyUpdatePostVO.setIp(request.getRemoteAddr());
		historyUpdatePostVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if(usersVO == null) {
			historyUpdatePostVO.setOperationDescription("글수정 실패: 사용자 로그인이 되지 않았습니다.");
			historyUpdatePostVO.setEmailId("n/a");
			historyBiz.putHistory(historyUpdatePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/login");
			
			return view;
		}
		else{
			historyUpdatePostVO.setEmailId(usersVO.getEmailId());
			multiPostVO.setEmailId(usersVO.getEmailId());
			multiPostVO.setPostingId(postId);
			
		}
		
		MultipartFile[] files = multiPostVO.getUploadedFile();
		tranferData_and_setFileName(multiPostVO, files);
		
		boolean isModified = postBiz.updateMultiPost(multiPostVO);
		
		if (isModified) {
			
			historyUpdatePostVO.setOperationDescription("글수정 성공");
			historyBiz.putHistory(historyUpdatePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/post/post");
			
			return view;
		} else {
			
			historyUpdatePostVO.setOperationDescription("글수정 실패: 데이터베이스 트랜잭션 실패");
			historyBiz.putHistory(historyUpdatePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("error/error");
			
			return view;
		}
	}

	@Override
	public ModelAndView deletePost(String postId
								, HttpServletRequest request) {
		
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyDeletePostVO = new HistoryVO();
		historyDeletePostVO.setIp(request.getRemoteAddr());
		historyDeletePostVO.setOperationType(OperationHistory.POST_HISTORY);

		
		if(usersVO==null) {
			historyDeletePostVO.setOperationDescription("글삭제 실패: 사용자 로그인이 되지 않았습니다.");
			historyDeletePostVO.setEmailId("n/a");
			historyBiz.putHistory(historyDeletePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/login");
			
			return view;
		}
		else {
			historyDeletePostVO.setEmailId(usersVO.getEmailId());
		}
		
		boolean isDeleted = postBiz.deletePost(postId);
		
		
		if (isDeleted) {
			historyDeletePostVO.setOperationDescription("글삭제 성공: Delete Flag Y");
			historyBiz.putHistory(historyDeletePostVO);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/post/post");

			return view;
			
		} else {
			ModelAndView view = new ModelAndView();
			view.setViewName("error/error");
			
			historyDeletePostVO.setOperationDescription("글삭제 실패: 데이터베이스 트랜잭션 실패.");
			historyBiz.putHistory(historyDeletePostVO);
			
			return view;
		}
	}
	
	@Override
	public String doReply(PostReplyVO postReplyVO
						, HttpServletRequest request) {
		
		
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyReplyPostVO = new HistoryVO();
		historyReplyPostVO.setIp(request.getRemoteAddr());
		historyReplyPostVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if(usersVO==null) {
			historyReplyPostVO.setEmailId("n/a");
			historyReplyPostVO.setOperationDescription("댓글 작성 실패: 로그인 안됨");
			historyBiz.putHistory(historyReplyPostVO);
			
			return "redirect:/login";
		}
		else{
			historyReplyPostVO.setEmailId(postReplyVO.getEmailId());
		}
		
		
		boolean isInsertedReply = postBiz.doReply(postReplyVO);
		
		if (isInsertedReply) {
			historyReplyPostVO.setOperationDescription("댓글 작성 성공");
			historyBiz.putHistory(historyReplyPostVO);
			
			return "redirect:/post/postDetail/" + postReplyVO.getPostingId();
		}
		else {
			historyReplyPostVO.setOperationDescription("댓글 작성 실패: 트랜잭션 에러");
			historyBiz.putHistory(historyReplyPostVO);
			
			return "redirect:error/error";
		}
	}

	@Override
	public String doReplyModify(PostReplyVO postReplyVO
							, HttpServletRequest request) {
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyReplyModifyPostVO = new HistoryVO();
		historyReplyModifyPostVO.setIp(request.getRemoteAddr());
		historyReplyModifyPostVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if(usersVO==null) {
			historyReplyModifyPostVO.setEmailId("n/a");
			historyReplyModifyPostVO.setOperationDescription("댓글 수정 실패: 로그인 안됨");
			historyBiz.putHistory(historyReplyModifyPostVO);
			
			return "redirect:/login";
		}
		else {
			historyReplyModifyPostVO.setEmailId(postReplyVO.getEmailId());
			postReplyVO.setEmailId(usersVO.getEmailId());
		}
		
		boolean isModifiedReply = postBiz.doReplyModify(postReplyVO);

		if(isModifiedReply) {
			historyReplyModifyPostVO.setOperationDescription("댓글 수정 성공");
			historyBiz.putHistory(historyReplyModifyPostVO);
			
			return "redirect:/post/postDetail/" + postReplyVO.getPostingId();
		}
		else {
			historyReplyModifyPostVO.setOperationDescription("댓글 수정 실패: 트랜잭션 실패");
			historyBiz.putHistory(historyReplyModifyPostVO);
			
			return "redirect:error/error";
		}
	}
	@Override
	public String doReplyDelete(PostReplyVO postReplyVO, HttpServletRequest request) {
		
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyReplyDeleteVO = new HistoryVO();
		historyReplyDeleteVO.setIp(request.getRemoteAddr());
		historyReplyDeleteVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if(usersVO==null) {
			historyReplyDeleteVO.setEmailId("n/a");
			historyReplyDeleteVO.setOperationDescription("댓글 삭제 실패: 로그인 안됨");
			historyBiz.putHistory(historyReplyDeleteVO);
			
			return "redirect:/login";
		}
		else {
			historyReplyDeleteVO.setEmailId(usersVO.getEmailId());
		}
		boolean isUpdatedReply = postBiz.doReplyDelete(postReplyVO);
		
		if(isUpdatedReply) {
			historyReplyDeleteVO.setOperationDescription("댓글 삭제 성공 : 플래그 설정됨");
			historyBiz.putHistory(historyReplyDeleteVO);
			
			return "redirect:/post/postDetail/" + postReplyVO.getPostingId();
		}
		else {
			historyReplyDeleteVO.setOperationDescription("댓글 삭제 실패 : 데이터베이스 트랜잭션 실패");
			historyBiz.putHistory(historyReplyDeleteVO);
			
			return "error/error";
		}
	}
	@Override
	public ResultVO doPostLike(PostVO postVO
							, HttpServletRequest request) {

		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		
		HistoryVO historyDoPostLikeVO = new HistoryVO();
		historyDoPostLikeVO.setIp(request.getRemoteAddr());
		historyDoPostLikeVO.setOperationType(OperationHistory.POST_HISTORY);
		
		if(usersVO==null) {
			historyDoPostLikeVO.setEmailId("n/a");
			historyDoPostLikeVO.setOperationDescription("댓글 좋아요 실패: 로그인 실패");
			historyBiz.putHistory(historyDoPostLikeVO);
			
			return new ResultVO(false, "사용자 로그인이 되지 않음");
		}
		else {
			historyDoPostLikeVO.setEmailId(usersVO.getEmailId());;
			//자신이 쓴 글은 추천될 수 없음.
			if(postVO.getEmailId().equals(usersVO.getEmailId())) {
				historyDoPostLikeVO.setOperationDescription("댓글: 자신의 글은 추천할 수 없음.");
				historyBiz.putHistory(historyDoPostLikeVO);
				
				return new ResultVO(false, "자신의 글은 추천할 수 없습니다.");
			}
			//자신의 글이 아니믈 추천할 수 있도록 vo에 email 지정
			postVO.setEmailId(usersVO.getEmailId());
			if(postBiz.doPostLike(postVO)) {
				historyDoPostLikeVO.setOperationDescription("댓글 추천됨.");
				historyBiz.putHistory(historyDoPostLikeVO);
				
				return new ResultVO(true, "추천되었습니다.");
			}
			else {
				historyDoPostLikeVO.setOperationDescription("댓글 추천 해제됨.");
				historyBiz.putHistory(historyDoPostLikeVO);
				
				postBiz.doPostLikeMinus(postVO);
				return new ResultVO(false, "추천을 해제하였습니다.!");
			}
		}
	}
}
