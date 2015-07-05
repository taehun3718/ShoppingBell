<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>			
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#modifyBtn").click(function() {
			
			if($("#password").val() == "") {
				alert("수정을 하려면 비밀번호를 입력하세요!");
				return;
			}
			if($("#passwordConfirm").val() == "") {
				alert("수정을 하려면 비밀번호 재확인을 입력하세요!");
				return;
			}
			
			if($("#password").val() != $("#passwordConfirm").val()) {
				alert("비밀번호를 확인하세요!");
				return;
			}
			
			$("#modifyForm").attr("method", "post");
			$("#modifyForm").attr("action", "<c:url value="/member/modify"/>")
			$("#modifyForm").submit();		
		});
	});
</script>
</head>
<body>
<h1>My Page</h1>
<form:form commandName="usersVO" name = "modifyForm" id="modifyForm">
	<input type="hidden" id="emailId" name="emailId" value="${userInfo.emailId}" />
	<table>
		<tr>
			<th>Email Id</th>
			<td><c:out value="${userInfo.emailId }"/></td>
		</tr>
		<tr>
			<th>Nickname</th>
			<td>
				<input type="text" id="nickname" name="nickname" value="${userInfo.nickname}"/>
				<form:errors path="nickname"/>
			</td>
		</tr>
		<tr>
			<th>Password</th>
			<td>
				<input type="password" id="password" name="password"/> 
				<form:errors path="password"/>
			</td>
		</tr>
		<tr>
			<th>Password Confirm</th>
			<td>
				<input type="password" id="passwordConfirm" name="passwordConfirm"/>
			</td>
		</tr>
		<tr>
			<th>Birth</th>
			<td><c:out value="${userInfo.birth }"/></td>
		</tr>
		<tr>
			<th>Member Point</th>
			<td><c:out value="${userInfo.point }"/>벨</td>
		</tr>
		<tr>
			<th>Created Date</th>
			<td><c:out value="${userInfo.createdDate }"/></td>
		</tr>
		<tr>
			<th>Modified Date</th>
			<td><c:out value="${userInfo.modifiedDate }"/></td>
		</tr>
	</table>
</form:form>
	<input type="button" value="수정" name="modifyBtn" id="modifyBtn" />
</body>
</html>