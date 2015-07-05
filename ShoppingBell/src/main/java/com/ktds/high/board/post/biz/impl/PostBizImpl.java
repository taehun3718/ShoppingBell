package com.ktds.high.board.post.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.high.board.post.biz.PostBiz;
import com.ktds.high.board.post.dao.PostDAO;
import com.ktds.high.board.post.vo.MultiPostDetailVO;
import com.ktds.high.board.post.vo.MultiPostVO;
import com.ktds.high.board.post.vo.PostReplyVO;
import com.ktds.high.board.post.vo.PostSearchVO;
import com.ktds.high.board.post.vo.PostVO;

public class PostBizImpl implements PostBiz{
	private PostDAO postDAO;
	
	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}

	@Override
	public boolean writePost(MultiPostVO multiPostVO) {
		
		// optimize list/////////////////////////////////////////////////////////////////////////////////////
		List<String> realNames = new ArrayList<String>();
		List<String> randNames = new ArrayList<String>();
		
		realNames.addAll(Arrays.asList(multiPostVO.getRealName()));
		for(int i=0; i<realNames.size(); i++) {
			String x = realNames.get(i);
			if(x==null || x.equals("")) {
				realNames.remove(i);
				i=-1;
			}
		}
		
		randNames.addAll(Arrays.asList(multiPostVO.getRandomName()));
		for(int i=0; i<randNames.size(); i++) {
			String x = randNames.get(i);
			if(x==null || x.equals("")) {
				randNames.remove(i);
				i=-1;
			}
		}
		
		List<String> contents = new ArrayList<String>();
		contents.addAll(Arrays.asList(multiPostVO.getContent()));
		
		for(int i=0; i<contents.size(); i++) {
			String x = contents.get(i);
			if(x==null || x.equals("")) {
				contents.remove(i);
				i=-1;
			}
		}
		
		String postingId = postDAO.selectPostingId();
		//Content를 모두 구분자로 이어 붙임.
		Map<String, Object> map = new HashMap<String, Object>();
		
		int length = contents.size();
		
		String subject  = multiPostVO.getSubject();
		//#shb# --> shb: shoppingbell의 구분자
		String content  = contents.size()  + "#shb#";
		String realName = realNames.size() + "#shb#";
		String randName = randNames.size() + "#shb#";
		
		int i;
		
		for(i=0; i<length; i++) {
			
			content += multiPostVO.getContent()[i] + "#shb#";
			
			if(multiPostVO.getRealName()[i]!=null)
				realName += multiPostVO.getRealName()[i]   + "#shb#";
			else
				realName += ""   + "#shb#";
			
			if(multiPostVO.getRandomName()[i]!=null)
				randName += multiPostVO.getRandomName()[i] + "#shb#";
			else
				randName += "" + "#shb#";
		}
		
		map.put("postingLike", 0);
		map.put("postingId", postingId);
		map.put("subject"	 , subject);
		map.put("content"	 , content);
		map.put("emailId"	 , multiPostVO.getEmailId());
		map.put("realName"	 , realName);
		map.put("randomName" , randName);
		map.put("category", multiPostVO.getCategory());
		
		return postDAO.writePost(map);
	}

	@Override
	public List<PostVO> postMainList() {
		return postDAO.postList();
	}
	
	@Override
	public List<PostVO> postList(PostSearchVO postSearchVO) {
		return postDAO.postList(postSearchVO);
	}

	@Override
	public MultiPostDetailVO getMultiPostDetailById(String postId) {
		return postDAO.getMultiPostDetailById(postId);
	}
	
	@Override
	public boolean updateMultiPost(MultiPostVO multiPostVO) {
		
		//사이즈 구하기
		List<String> realfileNameList = new ArrayList<String>();
		List<String> randfileNameList = new ArrayList<String>();
		List<String> contentList = new ArrayList<String>();
		
		//form의 파일 리스트 구하기
		List<String> tmpRealName = new ArrayList<String>();
		List<String> tmpRandName = new ArrayList<String>();
		
		//데이터베이스에 저장된 파일 리스트 구하기
		List<String> tmpDBRealName = new ArrayList<String>();
		List<String> tmpDBRandName = new ArrayList<String>();
		
		MultiPostDetailVO files;
		
		initContentsAndFileList(multiPostVO
							, realfileNameList
							, randfileNameList
							, contentList);
		int length;
		String content;
		//사이즈 구하기 끝...
		length	= contentList.size();
		content	= contentList.size()  + "#shb#";
		
		//Step1.   현재 파일의 리스트를 구한다.
		tmpRealName.addAll(Arrays.asList(multiPostVO.getRealName()));
		tmpRandName.addAll(Arrays.asList(multiPostVO.getRandomName()));
		
		//Step2.   데이터베이스의 파일 리스트를 구한다.
		files = postDAO.getMultiPostDetailById(multiPostVO.getPostingId());
		tmpDBRealName.addAll(files.getRealNames());
		tmpDBRandName.addAll(files.getRandomNames());
		
		//Step3. DB에 넣을 데이터를 초기화 한다.
		Map<String, Object> map = new HashMap<String, Object>();
		
		int i, real_size=0, rand_size=0;
		String realName = "";
		String randName = "";
		
		//이미지가 이미 있는 이미지면 업데이트를 하지 않고, 새로운 이미지가 있으면
		//DB에 업데이트 하도록 이미지 내용을 지정
		//Content, 및 Subject는 내용 넣게.
		for(i=0; i<length; i++) {
			
			if(tmpDBRealName.size()==0 && realfileNameList.size()==0)
				realName += ""  + "#shb#";
			else {
				if(tmpRealName.get(i).equals("")) {
					realName += tmpDBRealName.get(i)  + "#shb#";
					real_size++;
					
				}
				else {
					rand_size++;
					realName += tmpRealName.get(i)	  + "#shb#";
					
				}
			}
			if(tmpDBRealName.size()==0 && realfileNameList.size()==0) {
				randName += ""  + "#shb#";
			}
			else {
				if(tmpRandName.get(i)==null) {
					randName += tmpDBRandName.get(i)  + "#shb#";
				}
				else {
					randName += tmpRandName.get(i)	  + "#shb#";
				}
			}
			content += multiPostVO.getContent()[i] + "#shb#";
		}
		realName = real_size + rand_size + "#shb#" + realName;
		randName = real_size + rand_size + "#shb#" + randName;

		//Step4(Finally) DB에 데이터에 넣을 HashMap Key Value 쌍을 생성한다.
		map.put("postingId", multiPostVO.getPostingId());
		map.put("subject"	 , multiPostVO.getSubject());
		map.put("content"	 , content);
		map.put("emailId"	 , multiPostVO.getEmailId());
		map.put("realName"	 , realName);
		map.put("randomName" , randName);
		map.put("category", multiPostVO.getCategory());

		return postDAO.updateMultiPost(map);
	}

	private void initContentsAndFileList(MultiPostVO multiPostVO
									, List<String> realfileNameList
									, List<String> randfileNameList
									, List<String> contentList) {
		
		realfileNameList.addAll(Arrays.asList(multiPostVO.getRealName()));
		
		for(int i=0; i<realfileNameList.size(); i++) {
			String x = realfileNameList.get(i);
			if(x==null || x.equals("")) {
				realfileNameList.remove(i);
				i=-1;
			}
		}
		randfileNameList.addAll(Arrays.asList(multiPostVO.getRandomName()));
		
		for(int i=0; i<randfileNameList.size(); i++) {
			String x = randfileNameList.get(i);
			if(x==null || x.equals("")) {
				randfileNameList.remove(i);
				i=-1;
			}
		}
		contentList.addAll(Arrays.asList(multiPostVO.getContent()));
		
		for(int i=0; i<contentList.size(); i++) {
			String x = contentList.get(i);
			if(x==null || x.equals("")) {
				contentList.remove(i);
				i=-1;
			}
		}
	}

	@Override
	public boolean deletePost(String postId) {
		return postDAO.deletePost(postId);
	}
	
	@Override
	public boolean doReply(PostReplyVO postReplyVO) {
		return postDAO.doReply(postReplyVO);
	}
	
	@Override
	public List<PostReplyVO> postReplyList(String postId) {
		return postDAO.postReplyList(postId);
	}
	
	@Override
	public boolean doReplyModify(PostReplyVO postReplyVO) {
		return postDAO.doReplyModify(postReplyVO);
	}
	
	@Override
	public boolean doReplyDelete(PostReplyVO postReplyVO) {
		return postDAO.doReplyDelete(postReplyVO);
	}
	
	@Override
	public boolean doPostLike(PostVO postVO) {
		return postDAO.doPostLike(postVO);
	}
	
	@Override
	public boolean doPostLikeMinus(PostVO postVO) {
		return postDAO.doPostLikeMinus(postVO);
	}

	@Override
	public PostVO getPostDetailById(String postingId) {
		return postDAO.getPostDetailById(postingId);
	}
	
	@Override
	public boolean postHit(String postingId) {
		return postDAO.postHit(postingId);
	}
}
