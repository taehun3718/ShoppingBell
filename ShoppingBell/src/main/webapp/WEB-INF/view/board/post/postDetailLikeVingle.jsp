<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ktds.high.login.vo.UsersVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>포스트 리스트입니다.</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var modifyReplyId;
		
		//댓글 삽입/수정/삭제버튼 활성화를 위한 초기화
		initPostReply();
		
		$("#delete").click(function() {
			if(confirm("삭제하시겠습니까?")) {
				var postId = $("#postId").val();
				
				$("#deleteForm").attr("action","<c:url value="/post/doDelete"/>");
				$("#deleteForm").attr("method", "POST");
				$("#deleteForm").submit();
			}
		});
		$(".doReplyWrite").click(function() {
			
			//Validation Check
			if($("#postingReplyContent").val()=="") {
				alert("내용을 입력해주세요.");
				return;
			}
			
			$("#writeReplyPostForm").attr("action","<c:url value="/post/doReply"/>");
			$("#writeReplyPostForm").attr("method", "POST");
			$("#writeReplyPostForm").submit();
		});
		
		$(".doReplyModify").click(function() {
			$("#postingReplyId").val(modifyReplyId)
			$("#writeReplyPostForm").attr("action","<c:url value="/post/doReplyModify"/>");
			$("#writeReplyPostForm").attr("method", "POST");
			$("#writeReplyPostForm").submit();
			initPostReply();
		});
		
		$(".postReplyModify").on("click", function() {
			console.log("mdfy:" + $(this).parent().parent().children(":eq(0)").attr("id"));
			modifyReplyId = $(this).parent().parent().children(":eq(0)").attr("id");
			var replyCont = $(this).parent().parent().children(":eq(1)").text()
			console.log("replyContId:" + replyCont);
			$("#postingReplyContent").val(replyCont);
			onModifyReply();
		});
		$(".postReplyDelete").on("click", function() {
			
			if(confirm("댓글을 삭제하시겠습니까?")) {
				modifyReplyId = $(this).parent().parent().children(":eq(0)").attr("id");
				console.log("delete:" + $(this).parent().parent().children(":eq(0)").attr("id"));
				$("#postingReplyId").val(modifyReplyId)
				$("#writeReplyPostForm").attr("action","<c:url value="/post/doReplyDelete"/>");
				$("#writeReplyPostForm").attr("method", "POST");
				$("#writeReplyPostForm").submit();
				initPostReply();
			}
		});
		
		$(".doReplyCancel").on("click", function() {
			
			initPostReply();
			$("#postingReplyContent").val("");
		});
		
		$("#btnLike").click(function() {
			var boardId;
			console.log(".btnLike");
			$.post("<c:url value="/post/doPostLike"/>"
					, $("#writeReplyPostForm").serialize()
					, function(data) {
				if(data.isSuccess) {
					alert(data.because);
					location.href="<c:url value="/post/postDetail/${multiPostDetailVO.postingId }"/>";
				}
				else
					alert(data.because);
					location.href="<c:url value="/post/postDetail/${multiPostDetailVO.postingId }"/>";
			});
		});
		
		$("#modify").click(function() {
			location.href = "<c:url value="/post/modifyPostLikeVingle/${multiPostDetailVO.postingId }"/>";
		});
	
	});
	
	function initPostReply() {
		
		$(".doReplyWrite").show();
		$(".doReplyModify").hide();
		$(".doReplyCancel").hide();
		
	}
	
	function onModifyReply() {
		$(".doReplyWrite").hide();
		$(".doReplyModify").show();
		$(".doReplyCancel").show();
		
	}	
</script>

<style>
#delete, #modify, #btnLike{
cursor:pointer;
}

.doReplyWrite, .doReplyModify, .doReplyCancel{
cursor:pointer;
}
</style>

</head>
<body>
	<form	id="deleteForm"
		 	name="deleteForm" >
		<input type="hidden" id="postId" name="postId" value="${multiPostDetailVO.postingId }"/>
	</form>
	<table border="1">
		<tr>
			<!--제목 td  -->
			<td colspan="3">
				제목:${multiPostDetailVO.subject }
			</td>
		</tr>
		<!--제목 아래에 조회 like date-->
		<tr>
			<td>조회: ${multiPostDetailVO.hit }</td>
			<td>like: ${multiPostDetailVO.postingLike }</td>
			<td>date: ${multiPostDetailVO.modifiedDate }</td>
		</tr>
		<tr>
		<!-- Posting Content (이미지, 내용 이미지 내용...)  
			
						+--------+
						|        |
						|  이미지 |
						|        |
						+--------+
					   ---------------
					         내용 
					
						+--------+
						|        |
						|  이미지 |
						|        |
						+--------+
					   ---------------
					         내용 -->
		</tr>
		<c:set var="fileIndex" value="0"/>
		<c:forEach var="content" items="${multiPostDetailVO.contents }">
			<tr>
				<!-- 이미지  -->
				<td colspan="3" align="center">
					<c:if test="${not empty multiPostDetailVO.realNames[fileIndex]}">
						<img width="500" height="300" src="<c:url value="/post/download/${multiPostDetailVO.postingId }/${fileIndex }"/>">
					</c:if>
				</td>
			</tr>
			<!-- 내용  -->
			<tr>
				
				<c:set var="postingId" value="${multiPostDetailVO.postingId }"/>
					<td colspan="3" align="center">
						${content }
					</td>
			</tr>
			<c:set var="fileIndex" value="${fileIndex + 1 }"/>
		</c:forEach>
	</table>
	<!-- 수정 삭제 버튼  -->
	<table>
		<tr>
			<td>
			</td>
			<td>
				<c:if test="${ usersVO.emailId eq multiPostDetailVO.emailId}">
					<span id="modify">수정</span>/
					<span id="delete">삭제</span>
				</c:if>
			</td>
		</tr>
	</table>
	
	<!-- VO: postReplyList -->
	<table class="postReplyListTable" border="1" >
		<tr>
			<td colspan="3" align="center">댓글</td>
		</tr>
		<c:if test="${empty postReplyList}">
			<tr>
				<td>댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="postReply" items="${postReplyList }">
		
		<tr class="postReplyTr">
			
			<td id="${postReply.postingReplyId}">
				${postReply.nickName}</td>
			<td id="${postReply.postingReplyId}">${postReply.postingReplyContent}
			</td>
			<c:if test="${ usersVO.emailId eq postReply.emailId}">
				<!-- 댓글 수정 삭제를 위한 버튼  -->
				<td>
					<span class="postReplyModify" >
						수정
					</span>
				</td>
				
				<td>
					<span class="postReplyDelete" >
						삭제
					</span>
				</td>
			</c:if>
		</tr>
		</c:forEach>
		
	</table>

	<form	id="writeReplyPostForm"
			name="writeReplyPostForm" >
		
		<input type="hidden" id="postingId" 	 name="postingId"	 	value="${multiPostDetailVO.postingId }"/>
		<input type="hidden" id="postingReplyId" name="postingReplyId"	value=""/>
		<input type="hidden" id="emailId"		 name="emailId"		 	value="${multiPostDetailVO.emailId }"/>
		
		<c:if test="${not empty usersVO.emailId }">
			<table>
				<tr>
					<td>
						<input type="text"
								id="postingReplyContent"
								name="postingReplyContent"
								placeholder="댓글을 입력해주세요."/>
					</td>
					<!-- 이 버튼을 누르면 댓글이 수정하거나 취소할 수 있음.  -->
					<td>
						<span class="doReplyWrite" style="cursor:pointer;">등록</span>
						<span class="doReplyModify" style="cursor:pointer;">댓글수정</span>
						<span class="doReplyCancel" style="cursor:pointer;">취소</span>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<!-- 좋아요 버튼  -->
						<span id="">
							<img	src="https://cdn3.iconfinder.com/data/icons/inficons-set-2/512/thumb-up-512.png"
								width="20" height="20"/>좋아요
						</span>
					</td>
				<tr>
			</table>
		</c:if>
	</form>
</body>
</html>