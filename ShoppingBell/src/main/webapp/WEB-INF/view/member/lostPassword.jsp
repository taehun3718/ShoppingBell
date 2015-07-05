<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    
	    $("#pwd").hide();
		
	    $("#lostPwdBtn").click(function(event){
			
			$.post("<c:url value="/member/doLostPassword"/>", {"emailId" : $("#emailId").val()}, function(data) {
				if(data.result){
					$.post("<c:url value="/member/temporaryPassword"/>", {"emailId" : $("#emailId").val()}, function(data) {
						if(data.password ){
							$("#pwd").show();
							$("#tempPassword").html("임시비밀번호는 " + data.password + "입니다.<br/> 로그인 후 비밀번호를 변경하세요.");
						}
					});
				} 
				else{
					$("#pwd").show();
					$("#tempPassword").html("일치하는 Email이 없습니다.");
					$("#emailId").focus();
				}
			
		    });
	    });
	    $("#close").click(function(){
	    	$("#pwd").hide();
	    });
	
	});
</script>
<style type="text/css">

 #pwd {
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
	z-index : 2;
	text-align: center;
}

#close{
	font-size: x-large;
	cursor: pointer;
	text-align: right;
	padding: 5px 15px 0px 0px;
}

#tempPassword{
	padding-top: 20px;
	vertical-align: middle;
}
 
</style>
</head>
<body>
<h1> Lost My Password</h1>

<form name="passwordForm" id="passwordForm">
	<table>
		<tr>
			<th>Email을 입력하세요.</th>
			<td>
				<input type="text" id="emailId" name="emailId" placeholder="email을 입력하세요."/>
			</td>
		</tr>
	</table>
</form>
<input type="button" value="임시비밀번호 받기" id="lostPwdBtn" name="lostPwdBtn"/>
	<div id="pwd">
		<div id="close">X</div>
		<div id="tempPassword"></div>
	</div>
</body>
</html>