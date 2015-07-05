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
	<h1>HISTORY LIST</h1>
	<table border="2">
	<c:forEach var="history" items="${historyList }">
		<tr>
		<td><c:out value="${history.historyId }" /></td>
		<td><c:out value="${history.ip }" /></td>
		<td><c:out value="${history.operationType }" /></td>
		<td><c:out value="${history.operationDescription }" /></td>
		<td><c:out value="${history.createdDate }" /></td>
		<td><c:out value="${history.emailId }" /></td>
	</tr>
	</c:forEach>
	</table>
	
</body>
</html>