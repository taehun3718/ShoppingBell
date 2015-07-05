<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#explainedPoint").hide();
		
		$("#bellPoint").click(function() {
			
			$("#explainedPoint").show();
		});
		
		$("#close").click(function() {
			$("#explainedPoint").hide();
		});
	});
</script>

<style type="text/css">

#bellPoint{
	cursor: pointer;
	font-size: x-small;
	color: #8BBDFF;
	float: right;
	padding-top: 5px; 
}

#explainedPoint{
	position: fixed;
	outline: none !important;
	-webkit-backface-visibility: hidden;
	left : 36%;
	top: 34%;
	background-color: #ffffff;
	border-radius : 10px 10px 10px 10px;
	box-shadow : 0px 0px 1px 2px #66afe9;
	color : #111;
	width : 300px;
	height: 150px;
	padding : 5px;
	z-index : 1;
	text-align: center;
}

#close {
	font-size: x-large;
	cursor: pointer;
	text-align: right;
	padding: 5px 15px 0px 0px;
}
</style>
</head>
<body>
<h1>My Page</h1>
<table>
	<tr>
		<th>Email Id</th>
		<td><c:out value="${userInfo.emailId }"/></td>
	</tr>
	<tr>
		<th>Nickname</th>
		<td><c:out value="${userInfo.nickname }"/></td>
	</tr>
	<tr>
		<th>Birth</th>
		<td><c:out value="${userInfo.birth }"/></td>
	</tr>
	<tr>
		<th>Member Point</th>
		<td>
			<c:out value="${userInfo.point }"/> bell <span id="bellPoint"> bell point? </span>
		</td>
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
<a href="<c:url value="/member/exit"/>">탈퇴</a>

<div id="explainedPoint">
	<div id="close">X</div>
	Point 제도 설명 <br/>
	회원가입 시 : 100 벨 <br/>
	알람 신청 시 : -30 벨 <br/>
	Posting Board 글 작성 시 : 20 벨
</div>
</body>
</html>