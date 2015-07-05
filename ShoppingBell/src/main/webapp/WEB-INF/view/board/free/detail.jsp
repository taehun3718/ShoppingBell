<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib 	prefix="c"
			uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/ShoppingBell/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(document).ready(function() {	
	
	/* Board  */
	$(".boardModify").click(function() {
		location.href = "<c:url value="/board/free/modify/${freeVO.freeId }/" />" 
	});
	
	$(".boardDelete").click(function() {
		if(confirm("정말 삭제하시겠습니까?")) {
			location.href = "<c:url value="/board/free/delete/${freeVO.freeId}/" />" 
		}
	});
	
	$(".soldOut").click(function() {
		if(confirm("판매 완료 하시겠습니까?")) {
			location.href = "<c:url value="/board/free/soldOut/${freeVO.freeId}/" />" 
		}
	});
	
	if("${freeVO.onSale }" == '판매완료') {
		$(".soldOut").hide(); 
	}
	
	/* Reply */
	$("#cancelBtn").hide();
	$("#modifyBtn").hide();
	
	$("#cancelBtn").click(function() {
		$("#replyId").val("");
		$("#content").val("");
		$("#replyBtn").val("등록");
		
		$(this).hide();
	});
	
	$(".replyDelete").click(function() {
		
		if(confirm("정말 삭제할까요?")) {
			var replyId = $(this).attr("class").split(" ")[1];
			location.href = "<c:url value="/reply/delete/${freeVO.freeId}/" />" + replyId  
		}
		
	});
	
	$(".replyModify").click(function() {
		var replyId = $(this).attr("class").split(" ")[1];
		$.post("<c:url value="/reply/${freeVO.freeId}/" />" + replyId
				, {}
				, function(data) {
					
					$("#cancelBtn").show();
					$("#modifyBtn").show();
					$("#replyBtn").hide();
					
					$("#replyId").val(data.replyId);
					$("#content").val(data.content);
				}
		);
	});
	
	$("#replyBtn").click(function() {

				if($("#content").val() == "") {
					alert("댓글 내용을 입력하세요");
					return;
				}
				
				$("#replyForm").attr("action", "<c:url value="/reply/write"/>");
				$("#replyForm").attr("method", "post");
				$("#replyForm").submit();
			});
	
	$("#modifyBtn").click(function() {

				$("#replyForm").attr("action", "<c:url value="/reply/modify"/>");
				$("#replyForm").attr("method", "post");
				$("#replyForm").submit();
			});
});
</script>
</head>
<body>
<h1>상품 상세보기</h1>
<br/><br/>
<form id="detailForm" name="detailForm">
	<table align="center" width="1000">
		<tr>
			<th colspan="2">
				(<c:out value="${freeVO.freeId }" />) &nbsp&nbsp
				<c:if test="${freeVO.onSale eq '판매중'}">
					<font color="green" size="3">
				</c:if>
				<c:if test="${freeVO.onSale eq '판매완료'}">
					<font color="red" size="3">
				</c:if>
					<b><c:out value="${freeVO.onSale }" /></b>
					</font>	
			</th>	
		</tr>
		<tr>
			<td>
				<table border="1" height="460">

					<tr>
						<td colspan="4">
							<img src="<c:url value="/download/${freeVO.realName}/${freeVO.randomName }"/>" 
							     width="350" height="350"><br/>
						</a>
						</td>
					</tr>
					<tr>
						<th>#품명</th>
						<td colspan="3"><c:out value="${freeVO.productName }" /> </td>
					</tr>
					<tr>
						<th>#판매가격</th>
						<td colspan="3"><c:out value="${freeVO.productPrice } " /> </td>
					</tr>
					<tr>
						<th>#상품 종류</th>
						<td colspan="3"><c:out value="${freeVO.productType } " /> </td>
					</tr>
					<!-- if걸어주기 -->
					<c:if test="${freeVO.productType eq '신발'}">
						<tr>
							<th>#사이즈</th>
							<td colspan="3"><c:out value="${freeVO.size }" /> </td>
						</tr>
					</c:if>
					
					<tr>
						<th>#기타 태그</th>
						<td colspan="3"><c:out value="${freeVO.etc }" /> </td>
					</tr>
				</table>
			</td>
			<td>
				<table border="1" width="600" height="460" align="right">
					<tr>
						<th>Subject</th>
						<td colspan="3"><c:out value="${freeVO.subject }" /></td>
					</tr>
					<tr>
						<th>Writer</th>
						<td>
							<c:out value="${freeVO.nickName }" />
							(<c:out value="${freeVO.emailId }" />)
						</td>
						<th>Hit</th>
						<td><c:out value="${freeVO.hit }" /></td> 
					</tr>
					<tr>
						<th>Created Date(Modified Date)</th>
						<td colspan="3">
							<c:out value="${freeVO.createdDate }" />
							(<c:out value="${freeVO.modifiedDate }" />)
						</td>
					</tr>
					<tr height="410">
						<th>Content</th>
						<td colspan="3">${freeVO.content }"</td>
					</tr>
				</table>

			</td>
		</tr>
	</table>	
	<p align="center">
		<span class="boardModify">수정</span>
		<span class="boardDelete">삭제</span>
		<a href="<c:url value="/board/free/list"/>">목록</a> <br/>
		<span class="soldOut">판매완료</span>
	</p>
</form>
	
	<!-- Reply -->
	<table align="center" width="1000">
		<tr>
			<th colspan="3">( Reply )</th>	
		</tr>
	</table>
	<table border="1" align="center" width="1000">
		<tr align="center">
			<td width="120">작성자</td>
			<td>내용</td>
			<td width="300">작성시간 (수정시간)</td>
		</tr>
		<c:forEach items="${replies}" var="reply">
			<tr>
				<td>
					<b><center>${reply.nickName}</center></b>
				</td>
				<td>
					${reply.content}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<c:if test="${reply.isMyReply}">
						<i><u></u>
						<span class="replyModify ${reply.replyId}">[수정]</span>
						<span class="replyDelete ${reply.replyId}">[삭제]</span>
						</i>
					</c:if> 
				</td>
				<td>
					<center>${reply.createdDate} (${reply.modifiedDate}) </center>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<br/>
	<table align="center" width="1000">
			<tr>
				<td colspan="2">
						<form name="replyForm" id="replyForm">
							<input type="hidden" id="replyId" name="replyId" />
							<input type="hidden" id="freeId" name="freeId" value="<c:out value="${freeVO.freeId}" />">
							
							<textarea name="content" id="content" cols="130">댓글을 입력해 주세요.</textarea><br/>
				</td>
				<td width="200">
							<div style="text-align:center">
								<input type="button" id="replyBtn" value="등록" />
								<input type="button" id="modifyBtn" value="수정" />
								<input type="button" id="cancelBtn" value="취소" />
							</div>
						</form>
				</td>
			</tr>
	</table>


</body>
</html>