<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Regist</title>
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#emailId").keyup(function() {
			$.post(	"<c:url value="/member/checkDuplicateUserIDAjax"/>", {"emailId" : $("#emailId").val()}
				, function(data) {
					if (data == "true") {
						$("#duplicateResult").text("이미 사용중인 ID 입니다.");
					} 
					else {
						$("#duplicateResult").text("사용 가능한 ID 입니다.");
					}
			});
		});

		$("#registBtn").click(function() {

			var year = $("#year").val();
			var month = $("#month").val();
			var date = $("#date").val();

			month = fillString(month);
			date = fillString(date);

			var birth = year + month + date;
			birth = parseInt(birth);

			if ($("#emailId").val() == "") {
				alert("email을 입력하세요!");
				return;
			}
			if ($("#nickname").val() == "") {
				alert("닉네임을 입력하세요!");
				return;
			}
			if ($("#password").val() == "") {
				alert("비밀번호를 입력하세요!");
				return;
			}
			if ($("#passwordConfirm").val() == "") {
				alert("비밀번호 재확인을 입력하세요!");
				return;
			}
			if ($("#password").val() != $("#passwordConfirm").val()) {
				alert("비밀번호가 일치하지 않습니다!");
				return;
			}
			var idCheckResult = $("#duplicateResult").text();
			if (idCheckResult == "이미 사용중인 ID 입니다.") {
				alert("다른 아이디를 작성해 주세요!");
				return;
			}
			if (year == "selYear") {
				alert("생년월일을 확인하세요!");
				return;
			}
			if (month == "selMonth") {
				alert("생년월일을 확인하세요!");
				return;
			}
			if (month == "selDate") {
				alert("생년월일을 확인하세요!");
				return;
			}
			if (!$(":input:radio[name=gender]:checked").val()) {
				alert("성별을 선택하세요.");
				return;
			}

			$("#birth").val(birth);
			$("#registForm").attr("action","<c:url value="/member/doRegist"/>")
			$("#registForm").attr("method","post");
			$("#registForm").submit();
		});

		function fillString(str) {
			if (str.length == 1) {
				str = "0" + str;
			}
			return str;
		}
	});
</script>
<style type="text/css">
	.registForm {
		margin: 0px auto;
		width: 300px;
		height: 300px;
		line-height: 300px;
	}
	
	.regist {
		text-align: center;
	}
	
	.registInput {
		margin: 10px auto;
		width: 100%;
	}
	
	.email {
		float: left;
	}
	
	.duplicate {
		margin: 10px auto;
		width: 100%;
		height: 20px;
		line-height: 20px;
	}
	
	.registButton {
		margin: 0px auto;
		width: 100%;
	}
	
	span {
		display: block;
		height: 30px;
		font-size: 14px;
		line-height: 2.5;
		color: #555;
		width: 100%;
	}
	
	#year, #month, #date {
		width: 33.3%;
	}
	
	#year, #month  {
		float: left;
	}
	
	#date{
		float: right;
	}
	
	#category-gender{
		line-height: 25px;
	}
	
	.location-control{
		text-align: right;
	}



</style>
</head>
<body>
	<h1>Regist</h1>
	<form:form class="form-horizontal registForm" commandName="usersVO"
		name="registForm" id="registForm">
		<h3 class="regist">SHOPPING BELL</h3>
		<div class="form-group">
			<div class="col-sm-10 registInput">
				<input type="email" id="emailId" name="emailId"
					class="form-control email" placeholder="Email" required="required"
					autofocus="autofocus" />
				<form:errors path="emailId" />
				<div class="col-sm-10 duplicate">
					<span id="duplicateResult"></span>
				</div>
				<input type="text" id="nickname" name="nickname"
					class="form-control" placeholder="NickName [ 2 ~ 50 Letter ]"
					required="required" />
				<form:errors path="nickname" />
				<input type="password" id="password" name="password"
					class="form-control" placeholder="Password [ 8 ~ 50 Letter ]"
					required="required" />
				<form:errors path="password" />
				<input type="password" id="passwordConfirm" name="passwordConfirm"
					class="form-control" placeholder="Password Confirm"
					required="required" />
				<div>
					<span id="dateOfBirth">Date Of Birth</span>
					<select
						id="year" name="year" class="form-control">
						<option selected="selected" value="selYear">Year</option>
						<c:forEach var="year" begin="1930" end="2014" step="1">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select> 
					<select id="month" name="month" class="form-control">
						<option selected="selected" value="selMonth">Month</option>
						<c:forEach var="month" begin="01" end="12" step="1">
							<option value="${month}">${month}</option>
						</c:forEach>
					</select> 
					<select id="date" name="date" class="form-control">
						<option selected="selected" value="selDate">Date</option>
						<c:forEach var="date" begin="01" end="31" step="1">
							<option value="${date}">${date}</option>
						</c:forEach>
					</select> 
				</div>
				<div>
					<span id="category-gender">&nbsp;&nbsp; 성별 </span>
					<div class="form-control location-control">
						<input type="radio" name="gender" id="gender" class="radio-inline" value="남자" />남자
						<input type="radio" id="gender" name="gender" class="radio-inline" value="여자" />여자 
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-10 registButton">
				<input class="btn btn-sm btn-primary btn-block" type="button" 
					value="회원 가입" id="registBtn" name="registBtn">
			</div>
		</div>
		
	<input type="hidden" value="" id="birth" name="birth" />
	</form:form>
</body>
</html>