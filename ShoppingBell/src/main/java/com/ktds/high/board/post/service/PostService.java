package com.ktds.high.board.post.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.post.vo.MultiPostVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;
import com.ktds.high.board.post.vo.ResultVO;

public interface PostService {
	/**
	 * <pre>
	 * writePost: 포스팅을 리스트를 불러온다..
	 * </pre> 
	 * @param postSearchVO 
	 * @return 포스팅이 있으면 list를 그렇지 않으면 null 리턴
	 */
	public ModelAndView postList(PostSearchVO postSearchVO);
	/** 특정한 포스팅을 불러온다. Contents가 여러개이면 여러개를 불러올 수 있다.
	 * @param postId
	 * @return
	 */
	public ModelAndView getMultiPostDetailById(String post, HttpServletRequest request);
	/**
	 * 특정 포스팅을 업데이트한다.
	 * @param 포스팅VO
	 *  @return 포스팅 뷰
	 */
	/**
	 * 게시글을 작성한다.
	 * @param multiPostVO
	 * @param errors
	 * @param session
	 * @param request
	 * @return
	 */
	public ModelAndView writePost(MultiPostVO multiPostVO
								, Errors errors
								, HttpServletRequest request);
	/**
	 * 특정 포스팅을 삭제한다. 
	 * @param 포스팅 게시물 Id
	 * @return 포스팅 뷰
	 */
	public ModelAndView deletePost(String postId, HttpServletRequest request);
	/**
	 * 댓글을 적는다. 성공하면 detail의 주소를 리턴한다.
	 * @param postReplyVO
	 * @return detail 포스트 주소
	 */
	public String doReply(PostReplyVO postReplyVO, HttpServletRequest request);
	/**
	 *  댓글을 수정한다. 성공하면 detail의 주소를 리턴한다.
	 * @param postReplyVO
	 * @return detail 포스트 주소
	 */
	public String doReplyModify(PostReplyVO postReplyVO, HttpServletRequest request);
	/**
	 * 댓글을 삭제한다. DB상에서 삭제하는것이 아닌 댓글 flag만 Y로 바꾼다.
	 * @param postReplyVO
	 * @return detail 포스트 주소
	 */
	public String doReplyDelete(PostReplyVO postReplyVO, HttpServletRequest request);
	/**
	 * Ajax Util을 통해 데이터를 주고 받는다.
	 * Like에 성공하면 true, 그렇지 않으면 false를 보낸다.
	 * @param postVO 
	 * @param session 
	 * @return 
	 */
	public ResultVO doPostLike(PostVO postVO, HttpServletRequest request);
	/**
	 * 파일을 다운받기 위한 메소드
	 * @param postingid
	 * @param fileId
	 * @param request
	 * @param response
	 */
	public void fileDownload(String postingid, String fileId,
			HttpServletRequest request, HttpServletResponse response);
	/**
	 * 수정페이지를 가져옴
	 * @param postId
	 * @return
	 */
	public ModelAndView getModifyPostLikeVinglePage(String postId);
	/**
	 * 포스팅을 업데이트
	 * @param multiPostVO
	 * @param postId 
	 * @param session 
	 * @return
	 */
	public ModelAndView updateMultiPost(MultiPostVO multiPostVO
										, String postId
										, HttpServletRequest request);
	
}
