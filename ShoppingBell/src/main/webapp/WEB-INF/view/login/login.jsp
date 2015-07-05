<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.cookie.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#confirmLogin").hide();
		$("#confirmAgainLogin").hide();
		
		var emailId = $.cookie('emailId');
	    if(emailId != undefined) {
	        $("#emailId").val(emailId);
	        $("#rememberId").prop("checked",true);
	    }
		
		$("#loginBtn").click(function() {
			
			if( $("#emailId").val() == "" ) {
				$("#confirmLogin").show();
				$("#checkMessage").html("ID를 입력하세요.");
				$("#emailId").focus();
				return;
			}
			if( $("#password").val() == "" ) {
				$("#confirmLogin").show();
				$("#checkMessage").text("PASSWORD를 입력하세요.");
				$("#password").focus();
				return;
			}
			
			$.post("<c:url value="/doLogin"/>", $("#loginForm").serialize(), function(data) {
				
				if(data.result){
					
					if($("#rememberId").prop("checked")) {
			        	$.cookie('emailId', $("#emailId").val());
			        } 
					else {
			        	$.removeCookie("emailId");
			        }
					
					location.href="<c:url value="/member/myPage"/>";
				} 
				else{
					if(data.because == "1") {
						$("#confirmAgainLogin").show();
						$("#againLoginMessage").html("이미 로그인이 된 아이디입니다. <br/> 로그아웃하시고 로그인하시겠습니까?");
					}
					else if(data.because == "2"){
						$("#confirmLogin").show();
						$("#checkMessage").html("로그인이 실패했습니다. <br/> 아이디 혹은 비밀번호를 확인해 주세요.");
						$("#emailId").focus();
					}
				}
			});
		});
		
		$("#ok").click(function(){
			$("#forceLogin").val("force");
			$.post("<c:url value="/doLogin"/>", $("#loginForm").serialize(), function(data){
				location.href="<c:url value="/member/myPage"/>";
			});
	    });
		
		$("#close").click(function(){
	    	$("#confirmLogin").hide();
	    });
		
		$("#cancle").click(function(){
	    	$("#confirmAgainLogin").hide();
			$("#emailId").focus();
	    });
		
	});
</script>
<style type="text/css">

#confirmLogin, #confirmAgainLogin {
	position: fixed;
	outline: none !important;
	-webkit-backface-visibility: hidden;
	left : 36%;
	top: 34%;
	background-color: #ffffff;
	border-radius : 10px 10px 10px 10px;
	box-shadow : 0px 0px 1px 2px #999;
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

#checkMessage, #againLoginMessage{
	padding-top: 20px;
	vertical-align: middle;
}

#ok{
	font-size: x-large;
	cursor: pointer;
	
}
#cancle{
	font-size: x-large;
	cursor: pointer;
}

	.loginForm {
		margin: 250px auto;
		width: 250px;
	}
	
	.login {
		text-align: center;
	}
	
	.loginInput {
		margin: 10px auto;
		width: 100%;
	}
	
	.loginButton {
		margin: 0px auto;
		width: 100%;
	}
	
	.lostPassword {
		margin: 0 auto;
		width: 300px;
		height: 50px;
	}

</style>
</head>
<body>
	<form id="loginForm" name="loginForm" class="form-horizontal loginForm">
		<h3 class="login">SHOPPING BELL</h3>
		<div class="form-group">
			<div class="col-sm-10  loginInput">
				<input type="hidden" name="forceLogin" id="forceLogin" value=""/>
				<input type="email" id="emailId" name="emailId" class="form-control" placeholder="Email" required="required" autofocus="autofocus"/>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required" />
			</div>
		</div>
		Id 저장 <input id="rememberId" type="checkbox">
		<div class="form-group">
			<div class="col-sm-10 loginButton">
				<input class="btn btn-sm btn-primary btn-block" type="button" value="로그인"  id="loginBtn" name="loginBtn">
			</div>
		</div>
		<h6 class="login">
			<a class="lostPassword" href="<c:url value="/member/lostPassword"/>">비밀번호를 찾아주세요</a>
		</h6>
	</form>

	<div id="confirmLogin">
		<div id="close">X</div>
		<div id="checkMessage"></div>
	</div>
	
	<div id="confirmAgainLogin">
		<div id="againLoginMessage"></div>
		<span id="ok">O</span>
		<span id="cancle">X</span>
	</div>
</body>
</html>