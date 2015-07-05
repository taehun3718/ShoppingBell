package com.ktds.high.board.post.dao;

import java.util.List;
import java.util.Map;

import com.ktds.high.board.post.vo.MultiPostDetailVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;

public interface PostDAO {
	/**
	 * postingId를 리턴받는다.
	 * @return String
	 */
	public String selectPostingId();
	/**
	 * <pre>
	 * writePost: 포스팅을 적는다.
	 * </pre> 
	 * @param PostVO에 게시글 제목 / 내용
	 * @return 포스팅이 성공했을때 true 그렇지 않으면 false
	 */
	public boolean writePost(PostVO map);
	/**
	 * <pre>
	 * writePost: 포스팅을 리스트를 불러온다..
	 * </pre> 
	 * @param postSearchVO 
	 * @return 포스팅이 있으면 list를 그렇지 않으면 null 리턴
	 */
	public List<PostVO> postList(PostSearchVO postSearchVO);
	/**
	 * 포스팅 리스트를 불러온다.
	 * @return postSearchVO
	 */
	public List<PostVO> postList();
	/**
	 * 특정한 포스팅을 불러온다.
	 * @param 포스팅 게시물Id
	 * @return 포스팅VO
	 */
	public PostVO getPostDetailById(String postId);
	/**
	 * 특정 포스팅을 업데이트한다.
	 * @param 포스팅VO
	 * @return 포스팅에 업데이트가 올바르게되면 true, 그렇지 않으면 false 
	 */
	public boolean updatePost(PostVO postVO);
	/**
	 * 특정 포스팅을 삭제한다. 
	 * @param 포스팅 게시물 Id
	 * @return 포스팅에 삭제되면 true, 그렇지 않으면 false
	 */
	public boolean deletePost(String postId);
	/**
	 * 댓글을 삽입한다.
	 * @param 댓글 객체VO
	 * @return update success: true, failure: false
	 */
	public boolean doReply(PostReplyVO postReplyVO);
	/**
	 * 포스팅 댓글 리스트
	 * @param postId
	 * @return
	 */
	public List<PostReplyVO> postReplyList(String postId);
	/**
	 * 포스팅 댓글 수정
	 * @param postReplyVO
	 * @return
	 */
	public boolean doReplyModify(PostReplyVO postReplyVO);
	/**
	 * 포스팅 댓글 삭제 플래스 업데이트
	 * @param postReplyVO
	 * @return
	 */
	public boolean doReplyDelete(PostReplyVO postReplyVO);
	/**
	 * 포스팅 like 건수가 1건일 경우 true 그렇지 않을 경우 false
	 * @param postVO
	 * @return
	 */
	public boolean doPostLike(PostVO postVO);
	/**
	 * 포스팅 like가 게시물에 존재하는지 확인
	 * @param brdId
	 * @return
	 */
	public boolean isExistLike(Map<String, Object> like);
	/**
	 * POST 게시판 테이블에 오직 이미지 정보와 시퀀스 ID만 삽입
	 * @param fileInfo
	 * @return 게시물ID
	 */
	public boolean insertImageInfo(PostVO fileInfo);
	public boolean writePost(Map<String, Object> map);
	public MultiPostDetailVO getMultiPostDetailById(String postId);
	/**
	 * 자신이 추천한 like를 회수한다.(테이블에서 삭제한다.)
	 * @param postVO
	 * @return
	 */
	public boolean doPostLikeMinus(PostVO postVO);
	/**
	 * 포스팅을 업데이트 한다.
	 * @param map
	 * @return
	 */
	public boolean updateMultiPost(Map<String, Object> map);
	/**
	 * 게시물의 HIT를 증가시킨다.
	 * @param postingId
	 * @return
	 */
	public boolean postHit(String postingId);
}
