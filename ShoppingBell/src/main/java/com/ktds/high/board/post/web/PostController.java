package com.ktds.high.board.post.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.high.board.post.service.PostService;
import com.ktds.high.board.post.vo.MultiPostVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;
import com.ktds.high.board.post.vo.ResultVO;
import com.ktds.high.login.vo.UsersVO;

@Controller
public class PostController {

	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	/**
	 * 포스팅의 리스트를 가져온다.
	 * @return
	 */
	@RequestMapping("/post/post")
	public ModelAndView articlePost(PostSearchVO postSearchVO) {
		System.out.println("KEY:" + postSearchVO.getSearchKeyword());
		System.out.println("CAT:" + postSearchVO.getCategory());
		return postService.postList(postSearchVO);
	}
	/**
	 * 포스팅의 세부 정보를 가져온다.
	 * @param postId
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/postDetail/{postId}")
	public ModelAndView articlePost(@PathVariable String postId,
			HttpServletRequest request) {
		return postService.getMultiPostDetailById(postId, request);
	}
	/**
	 * 글쓰기 페이지를 가져온다.
	 * @return
	 */
	@RequestMapping("/post/writePost")
	public ModelAndView writePost(HttpServletRequest request) {
		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		if(usersVO!=null)
			return new ModelAndView("board/post/writePostLikeVingle");
		else
			return new ModelAndView("redirect:/login");
	}
	/**
	 * 글을 쓴다.
	 * @param multiPostVO
	 * @param search
	 * @param errors
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/doWritePost")
	public ModelAndView doWritePost(@Valid MultiPostVO multiPostVO,
									@RequestParam("category") String category
									, Errors errors
									, HttpServletRequest request) {
		multiPostVO.setCategory(category);
		return postService.writePost(multiPostVO, errors, request);
	}
	
	
	/**
	 * 포스팅 수정페이지를 가져온다.
	 * @param postId
	 * @return
	 */
	@RequestMapping("/post/modifyPostLikeVingle/{postId}")
	public ModelAndView getModifyPostLikeVinglePage(@PathVariable String postId) {
		return postService.getModifyPostLikeVinglePage(postId);
	}

	/**
	 * 글을 수정한다. 파일도 수정되면 파일도 변경된다.
	 * @param postId
	 * @param multiPostVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/doModify/{postId}")
	public ModelAndView doModifyPost(@PathVariable String postId
									, MultiPostVO multiPostVO
									, HttpServletRequest request) {
		return postService.updateMultiPost(multiPostVO,  postId, request);
	}
	/**
	 * 댓글을 삭제한다. 
	 * @param postId
	 * @return
	 */
	@RequestMapping("/post/doDelete")
	public ModelAndView doDelete(@RequestParam("postId") String postId
								, HttpServletRequest request) {
		return postService.deletePost(postId, request);
	}
	/**
	 * 댓글을 적는다.
	 * @param postReplyVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/doReply")
	public String doReply(PostReplyVO postReplyVO, HttpServletRequest request) {

		UsersVO usersVO = (UsersVO) request.getSession().getAttribute("_MEMBER_");
		postReplyVO.setEmailId(usersVO.getEmailId());

		return postService.doReply(postReplyVO, request);
	}
	/**
	 * 댓글을 수정한다.
	 * @param postReplyVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/doReplyModify")
	public String doModifyReply(PostReplyVO postReplyVO, HttpServletRequest request) {

		return postService.doReplyModify(postReplyVO, request);
	}
	/**
	 * 댓글을 삭제한다.
	 * @param postReplyVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/post/doReplyDelete")
	public String doDeleteReply(PostReplyVO postReplyVO, HttpServletRequest request) {

		return postService.doReplyDelete(postReplyVO, request);
	}

	/**
	 * Response Body에 좋아요 데이터를 싣는다.
	 * @param postVO
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/post/doPostLike")
	public ResultVO doPostLike(PostVO postVO, HttpServletRequest request) {
		return postService.doPostLike(postVO, request);
	}
	/**
	 * 파일을 다운로드한다. 포스팅id, 파일 인덱스가 포함된다.
	 * @param postingId
	 * @param fileId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/post/download/{postingId}/{fileId}")
	public void fileDownload(@PathVariable String postingId
							, @PathVariable String fileId
							, HttpServletRequest request
							, HttpServletResponse response) {
		postService.fileDownload(postingId, fileId, request, response);
	}
}
