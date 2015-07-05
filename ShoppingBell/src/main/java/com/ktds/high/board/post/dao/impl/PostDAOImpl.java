package com.ktds.high.board.post.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.high.board.post.dao.PostDAO;
import com.ktds.high.board.post.vo.LikeVO;
import com.ktds.high.board.post.vo.MultiPostDetailVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;

public class PostDAOImpl extends SqlSessionDaoSupport implements PostDAO{

	@Override
	public String selectPostingId() {
		return getSqlSession().selectOne("postDAO.getPostingId");
	}
	
	@Override
	public boolean insertImageInfo(PostVO fileInfo) {
		
		int insertedCount = getSqlSession().insert("postDAO.insertImageInfo", fileInfo);
		return insertedCount > 0 ? true : false;
	}
	
	@Override
	public boolean writePost(PostVO postVO) {
		int writedCount = getSqlSession().insert("postDAO.writePost", postVO);
		return writedCount > 0 ? true : false;
	}
	
	@Override
	public boolean writePost(Map<String, Object> map) {
		int writedCount = getSqlSession().insert("postDAO.writeMultiPost", map);
		return writedCount > 0 ? true : false;
	}
	
	@Override
	public List<PostVO> postList() {
		return getSqlSession().selectList("postDAO.postList");
	}
	
	@Override
	public List<PostVO> postList(PostSearchVO postSearchVO) {
		return getSqlSession().selectList("postDAO.postList", postSearchVO);
		
	}

	@Override
	public PostVO getPostDetailById(String postId) {
		PostVO postVO =  getSqlSession().selectOne("postDAO.postDetailById", postId);
		return postVO;
	}
	
	@Override
	public MultiPostDetailVO getMultiPostDetailById(String postId) {
		PostVO postVO =  getSqlSession().selectOne("postDAO.postDetailById", postId);
		return setMultiPostVO(postVO);
	}

	private MultiPostDetailVO setMultiPostVO(PostVO postVO) {
		MultiPostDetailVO multiPostDetailVO =  new MultiPostDetailVO();
		
		multiPostDetailVO.setSubject(postVO.getSubject());
		multiPostDetailVO.setEmailId(postVO.getEmailId());
		multiPostDetailVO.setCreatedDate(postVO.getCreatedDate());
		multiPostDetailVO.setModifiedDate(postVO.getModifiedDate());
		multiPostDetailVO.setPostingLike(postVO.getPostingLike());
		multiPostDetailVO.setPostingId(postVO.getPostingId());
		multiPostDetailVO.setHit(postVO.getHit());
		multiPostDetailVO.setCategory(postVO.getCategory());
		
		int i;
		String parsedString = "";//구분자로 분리된 내용들이 여기에 다 들어감
		
		//내용이 있나 확인.(분리자로 다 구분되어 있음)
		String[] token = postVO.getContent().split("#shb#");
		int numOfContents = Integer.parseInt(token[0]);
		for(i=1; i<=numOfContents; i++) {
			parsedString = token[i];
			if(parsedString!=null || (!parsedString.equals(""))) {
				multiPostDetailVO.addContents(parsedString);	
			}
			
		}
		
		token = postVO.getRealName().split("#shb#");
		numOfContents = Integer.parseInt(token[0]);
		
		multiPostDetailVO.setNumOfRealName(numOfContents);
		
		if(numOfContents>=1) {
			for(i=1; i<=numOfContents; i++) {
				parsedString = token[i];

				if(parsedString!=null || (!parsedString.equals(""))) {
					multiPostDetailVO.addRealName(parsedString);	
				}
				
			}
		}
		
		token = postVO.getRandomName().split("#shb#");
		numOfContents = Integer.parseInt(token[0]);
		
		if(numOfContents>=1) {
			for(i=1; i<=numOfContents; i++) {
				parsedString = token[i];
				if(parsedString!=null || (!parsedString.equals(""))) {
					multiPostDetailVO.addRandName(parsedString);	
				}
				
			}
		}
		return multiPostDetailVO;
	}

	@Override
	public boolean updatePost(PostVO postVO) {
		int updatedCount = getSqlSession().update("postDAO.updatePost", postVO);
		return updatedCount > 0 ? true : false;
	}
	
	@Override
	public boolean updateMultiPost(Map<String, Object> map) {
		int updatedCount = getSqlSession().update("postDAO.updateMultiPost", map);
		return updatedCount > 0 ? true : false;
		//return true;
	}

	@Override
	public boolean deletePost(String postId) {
		int deletedFlagCount = getSqlSession().update("postDAO.updateDeleteFlagPost", postId);
		return deletedFlagCount > 0 ? true : false;
	}
	
	@Override
	public boolean doReply(PostReplyVO postReplyVO) {
		int writedReplyCount = getSqlSession().insert("postDAO.writePostReply", postReplyVO);		
		return writedReplyCount > 0 ? true : false;
	}
	
	@Override
	public List<PostReplyVO> postReplyList(String postId) {
		return getSqlSession().selectList("postDAO.postReplyList", postId);
	}
	
	@Override
	public boolean doReplyModify(PostReplyVO postReplyVO) {
		int updatedCount = getSqlSession().update("postDAO.updatePostReplyById", postReplyVO);
		return updatedCount > 0 ? true : false;
	}
	
	@Override
	public boolean doReplyDelete(PostReplyVO postReplyVO) {
		int updatedCount = getSqlSession().update("postDAO.updatePostReplyDeleteFlagById", postReplyVO);
		return updatedCount > 0 ? true : false;
	}
	
	@Override
	public boolean isExistLike(Map<String, Object> map) {
		
		LikeVO likeVO = getSqlSession().selectOne("postDAO.selectLike", map);
		return likeVO != null ? true : false;
	}
	
	@Override
	public boolean doPostLike(PostVO postVO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brdId", postVO.getPostingId());
		map.put("emailId", postVO.getEmailId());
		
		if(isExistLike(map)) {
			//이미 좋아요!를 클릭하였으므로 LIKE를 -1을 하도록.함
			return false;
		}
		else {
			
			int insertedLikeCount = getSqlSession().insert("postDAO.insertLike", map);
			 
			//좋아요를 클릭한 정보를 LIKE 테이블에 집어넣고 테이블 삽입이 성공하였을 경우
			if(insertedLikeCount > 0) {
				//Post Board like Count 증가
				 int isUpdatedCount = getSqlSession().update("postDAO.updatePostLikeById", postVO);
				 return isUpdatedCount > 0 ? true : false;
			}
			//해킹으로 인하여 시도되었을 가능성이 있음.
			else
				return false;
		}
	}
	
	@Override
	public boolean doPostLikeMinus(PostVO postVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brdId", postVO.getPostingId());
		map.put("emailId", postVO.getEmailId());
		
		int deletedLikeCount = getSqlSession().delete("postDAO.deleteLike", map);
		
		//테이블 삭제에 성공하였다면?
		if(deletedLikeCount > 0) {
			int isUpdatedLikeMinusCount = getSqlSession().update("postDAO.updatePostLikeMinusById", postVO);
			return isUpdatedLikeMinusCount > 0 ? true : false;
		}
		else
			return false;
	}
	
	@Override
	public boolean postHit(String postingId) {
		int plusHitCnt = getSqlSession().delete("postDAO.updatePostHit", postingId);
		return plusHitCnt > 0 ? true : false;
	}
}
