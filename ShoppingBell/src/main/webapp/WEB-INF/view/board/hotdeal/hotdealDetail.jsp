<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th>
			<td>${hotdealDetail.hotdealBoardId}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${hotdealDetail.hotdealSubject}</td>
		</tr>
		<tr>
			<th>회사</th>
			<td>${hotdealDetail.hotdealContent}</td>
		</tr>
		<tr>
			<th>작성 날짜</th>
			<td>${hotdealDetail.hotdealcreatedDate}</td>
		</tr>
		<tr>
			<th>수정 날짜</th>
			<td>${hotdealDetail.hotdealModifiedDate}</td>
		</tr>
		<tr>
			<th>진짜 이름</th>
			<td>${hotdealDetail.hotdealUploadedFile}</td>
		</tr>
		<tr>
			<th>랜덤 이름</th>
			<td>${hotdealDetail.hotdealRealName}</td>
		</tr>
		<tr>
			<th>랜덤 이름</th>
			<td>${hotdealDetail.hotdealRandomName}</td>
			<td>
				<img src="<c:url value="/hotdeal/fileDownload/${hotdealDetail.hotdealBoardId}" />">
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${hotdealDetail.emailId}</td>
		</tr>
		<tr>
			<th>차단</th>
			<td>${hotdealDetail.hotdealDelFlag}</td>
		</tr>
	</table>
	
	<input type="button" value="수정" onclick="javascript:window.location='<c:url value="/hotdeal/update/${hotdealDetail.hotdealBoardId}" />'">
	<input type="button" value="삭제" onclick="javascript:window.location='<c:url value="/hotdeal/delete/${hotdealDetail.hotdealBoardId}" />'">
	<input type="button" value="목록" onclick="javascript:window.location='<c:url value="/hotdeal/list" />'">
</body>
</html>