package com.ktds.high.board.post.biz;

import java.util.List;

import com.ktds.high.board.post.vo.MultiPostDetailVO;
import com.ktds.high.board.post.vo.MultiPostVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;

public interface PostBiz {
	/**
	 * <pre>
	 * writePost: 포스팅을 리스트를 불러온다..
	 * </pre> 
	 * @param postSearchVO 
	 * @return 포스팅이 있으면 list를 그렇지 않으면 null 리턴
	 */
	public List<PostVO> postList(PostSearchVO postSearchVO);
	public List<PostVO> postMainList();
	/**
	 * 특정한 포스팅을 불러온다. Contents가 여러개이면 여러개를 불러올 수 있다.
	 * @param postId
	 * @return
	 */
	public MultiPostDetailVO getMultiPostDetailById(String postId);
	/**
	 * <pre>
	 * writePost: 포스팅을 적는다.
	 * </pre> 
	 * @param MultiPostVO 게시글 제목 / 내용 / 파일
	 * @return 포스팅이 성공했을때 true 그렇지 않으면 false
	 */
	public boolean writePost(MultiPostVO multiPostVO);
	/**
	 * 특정 포스팅을 삭제한다. 
	 * @param 포스팅 게시물 Id
	 * @return 포스팅에 삭제되면 true, 그렇지 않으면 false
	 */
	public boolean deletePost(String postId);
	/**
	 * 포스팅을 업데이트 한다.
	 * @param multiPostVO
	 * @return
	 */
	public boolean updateMultiPost(MultiPostVO multiPostVO);
	/**
	 * 특정 포스팅에 댓글을 다는 로직
	 * @param 포스팅 게시물 Id
	 * @return 댓글 삽입에 성공하면 true, 그렇지 않으면 false
	 */
	public boolean doReply(PostReplyVO postReplyVO);
	/**
	 * 특정 포스팅의 댓글 리스트를 보여주는 로직
	 * @param postId
	 * @return 포스팅 댓글 리스트
	 */
	public List<PostReplyVO> postReplyList(String postId);
	/**
	 * 특정 포스팅의 댓글을 수정하는 로직
	 * @param postReplyVO
	 * @return  포스팅 댓글 수정에 성공하면 true, 그렇지 않으면 false
	 */
	public boolean doReplyModify(PostReplyVO postReplyVO);
	/**
	 * 특정 포스팅의 댓글의 flag를 true 하는 로직
	 * @param postReplyVO
	 * @return  포스팅 댓글 삭제 flag에 성공하면 true, 그렇지 않으면 false
	 */
	public boolean doReplyDelete(PostReplyVO postReplyVO);
	/**
	 * 특정 포스팅의 like를 +1 해주는 로직
	 * @param postVO
	 */
	public boolean doPostLike(PostVO postVO);
	/**
	 * 자신의 Like를 삭제한다.
	 * @param postVO
	 */
	public boolean doPostLikeMinus(PostVO postVO);
	public PostVO getPostDetailById(String postingId);
	/**
	 * 포스팅 조회수를 늘린다.
	 * @return
	 */
	boolean postHit(String postingId);
	
	
}
