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
			<th>번호</th>
			<th>제목</th>
			<th>회사</th>
		</tr>
		<c:forEach var="hotdealList" items="${hotdealList}">
			<tr>
				<td>
					<c:out value="${hotdealList.hotdealBoardId}" />
				</td>
				<td>
					<a href="<c:url value="/hotdeal/detail/${hotdealList.hotdealBoardId}" />"> 
						<c:out value="${hotdealList.hotdealSubject}" />
					</a>
				</td>
				<td>
					<c:out value="${hotdealList.hotdealContent}" />
				</td>
				<td>
					<c:out value="${hotdealList.hotdealcreatedDate}" />
				</td>
				<td>
					<c:out value="${hotdealList.hotdealModifiedDate}" />
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<input type="button" value="글쓰기" onclick="javascript:window.location='<c:url value="/hotdeal/write" />'">
</body>
</html>