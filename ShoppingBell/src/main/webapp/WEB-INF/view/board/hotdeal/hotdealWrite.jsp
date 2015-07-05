<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#goSave").click(function() {
				
				if($("#hotdealSubject").val() == "") {
					
					alert("제목을 입력하세요");
					$("#hotdealSubject").focus();
					return;
				}
				
				if($("#hotdealContent").val() == "") {
					
					alert("회사를 입력하세요");
					$("#hotdealContent").focus();
					return;
				}
				
				<c:if test="${not empty hotdealUpdate.hotdealBoardId}">
					$("#hotDealWriteUpdateForm").attr ("action", "<c:url value="/hotdeal/doUpdate" />");
				</c:if>
				<c:if test="${empty hotdealUpdate.hotdealBoardId}">
					$("#hotDealWriteUpdateForm").attr ("action", "<c:url value="/hotdeal/doWrite" />");
				</c:if>
				
				$("#hotDealWriteUpdateForm").attr ("method", "post");
				$("#hotDealWriteUpdateForm").submit();
			});
		});
	
	</script>
</head>
<body>
	<form:form commandName="hotdealVO" name="hotDealWriteUpdateForm" id="hotDealWriteUpdateForm" enctype="multipart/form-data">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="hotdealSubject" id="hotdealSubject" value="<c:out value="${hotdealUpdate.hotdealSubject}" />">
					<form:errors path="hotdealSubject" />
				</td>
			</tr>
			<tr>
				<th>회사</th>
				<td>
					<input type="text" name="hotdealContent" id="hotdealContent" value="<c:out value="${hotdealUpdate.hotdealContent}" />">
					<form:errors path="hotdealContent" />
				</td>
			</tr>
			<tr>
				<th>광고 이미지</th>
				<td>
					<input type="file" name="hotdealUploadedFile" id="hotdealUploadedFile">${hotdealUpdate.hotdealRealName}
					<form:errors path="hotdealUploadedFile" />
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="hotdealBoardId" id="hotdealBoardId" value="<c:out value="${hotdealUpdate.hotdealBoardId}" />">
		<input type="button" value="확인" id="goSave">
	</form:form>
</body>
</html>