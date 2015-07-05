<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("#deleteBtn").click(function() {   
			$("#deleteForm").attr("action", "<c:url value="/member/myPage/myAlarm/alarmDelete" />");
			$("#deleteForm").attr("method", "post");
			$("#deleteForm").submit();
		});
		
		$("#allCheck").click(function(){ 
			var isAllCheck = $(this).prop("checked");  
			$("input.chk").prop("checked", isAllCheck);
		});
	});
</script>
</head>
<body>
<h1>My Alarm List</h1>
<form id="deleteForm" name="deleteForm">
	<table border="1">
		<tr>
			<th rowspan="2">
				<input type="checkbox" name="allCheck" id="allCheck"/>
			</th>
			<th rowspan="2"> 상품명 </th>
			<th colspan="2"> 상품옵션 </th>
			<th rowspan="2"> 검색 사이트 </th>
			<th rowspan="2"> 알람 시작 일 </th>
		</tr>
		<tr>
			<th> 가격 </th>
			<th> 사이즈</th>
		</tr>
		<c:if test="${ empty userRequestList}">
			<tr>
				<td colspan="6">등록된 알림이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="userRequest" items="${userRequestList}">
			<tr>
				<td>
					<input type="checkbox" name="chk" class="chk" value="${userRequest.userRequestId }" />
				</td>
				<td>${userRequest.productName }</td>
				<td>${userRequest.productPrice }</td>
				<td>${userRequest.productSize }</td>
				<td>${userRequest.shopType }</td>
				<td>${userRequest.startDate }</td>
			</tr>
		</c:forEach>
	</table>
</form>
<input type="button" id="deleteBtn" name="deleteBtn" value="삭제"/>
</body>
</html>