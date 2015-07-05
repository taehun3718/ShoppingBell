<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		// 최초 사용하지 않는 항목은 숨겨 놓는다
		$(".optionPrice").hide();
		$(".optionGuide").hide();
		$(".sizeOption").hide();
		
		// 가격에는 정규식을 활용해 숫자만 입력받는다
		$("#productPrice").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9]/g,""));
		});
		
		// 체크 박스 해제, 선택 이벤트 발생시 초기화
		$("#option").change(function() {
			
			if ( $("#option").val() == 0 ) {
				$("#productSize option:eq(0)").attr("selected", "selected");
			}
			
		});
		
		// 체크 박스 해제, 선택 이벤트 발생시 초기화
		$("#priceCheck").change(function() {
			
			$("#productPrice").val("");
			
		});
		
		// 체크 박스 해제, 선택 이벤트 발생시 초기화
		$("#optionCheck").change(function() {
			
			$(".optionGuide option:eq(0)").attr("selected", "selected");
			
			if ( $("#option").val() == "" ) {
				$(".sizeOption").hide();
			}
			
		});
		
		$("#priceCheck").click(function () {
			
			if ( $('input:checkbox[id="priceCheck"]').is(":checked") ) {
				$(".optionPrice").show();
			}
			
			else {
				$(".optionPrice").hide();
			}
			
		});
		
		$("#optionCheck").click(function () {
			
			if ( $('input:checkbox[id="optionCheck"]').is(":checked") ) {
				$(".optionGuide").show();
			}
			
			else {
				$(".optionGuide").hide();
			}
			
		});
		
		$("#option").change(function() {
			
			if ( $('#option option:selected').val() == "size" ) {
				$(".sizeOption").show();
			}
			
			else {
				$(".sizeOption").hide();
			}
			
		});
		
		$("#registBtn").click(function() {
			
			if ( $("#productName").val() == "" ) {
				alert("상품명을 입력해주세요");
				return;
			}
			
			else if ( $("#productPrice").val() == "" && $("#productSize").val() == 0 ) {
				alert("가격과 옵션 중 하나를 필수로 입력하셔야 알림이 제공됩니다");
				return;
			}
			
			else {
				
				if ( $("#productPrice").val() == "" ) {
					$("#productPrice").val("0");
				}
				
				$("#registRequestForm").attr("action", "<c:url value="/regist/doRegist"/>")
				$("#registRequestForm").attr("method", "post");
				$("#registRequestForm").submit();
			}
		});
		
		$("#myRequesttBtn").click(function() {
			location.href("<c:url value="/regist/myRequest"/>");
		});
		
	});

</script>

	<style type="text/css">
		
		.registRequestForm {
			margin: 0 auto;
			width: 500px;
		}
		
		.request {
			margin-bottom: 30px;
			text-align: center;
		}
		
		.requestName {
			margin-bottom: 30pz;
			width: 100%;
		}
		
		.requestCheckbox {
			margin-bottom: 20px;
			padding: 0px;
		}
		
		.requestPrice {
			margin: 0 auto;
			padding-bottom: 30px;
			width: 100%;
		}
		
		.option2 {
			padding-left: 40px;
		}
		
		.requestSelect {
			margin-bottom: 30px;
			padding: 0px;
			width: 94%;
		}
		
		.requestSize {
			margin-top: 30px;
			padding: 0px;
			
		}
		
		.requestButton {
			margin: 30px auto;
			padding: 0px;
			width: 100%;
		}
		
	</style>
</head>
<body>
	<form:form class="form-horizontal registRequestForm" role="form" commandName="userRequestVO" id="registRequestForm" name="registRequestForm">
	
		<h3 class="request">SHOPPING BELL</h3>
		
		<div class="form-group requestName">
			<div class="col-sm-12">
				<input type="text" id="productName" name="productName" class="form-control" autofocus="autofocus" placeholder="모델명을 입력하세요 ex)" required="required"/>
			</div>
		</div>
		<div class="col-sm-12 requestCheckbox">
			<label class="checkbox-inline">
				<input type="checkbox" value="option1" id="priceCheck" name="priceCheck">Price
			</label>
			<label class="checkbox-inline option2">
				<input type="checkbox" value="option2" id="optionCheck" name="priceCheck">Option
			</label>
		</div>
		<div class="form-group optionPrice requestPrice">
			<div class="col-sm-12">
				<input type="text" id="productPrice" name="productPrice" class="form-control" placeholder="가격을 입력하세요" required="required" value="0"/>
				<form:errors path="productPrice"/>
			</div>
		</div>
		<div class="col-sm-12 optionGuide requestSelect">
			<select class="form-control" id="option"  name="option">
				<option value="">항목을 선택하세요</option>
				<option value="size">사이즈</option>
			</select>
			<div class="col-sm-12 sizeOption requestSize">
				<select class="form-control" id="productSize"  name="productSize">
					<option value="0">사이즈를 선택하세요</option>
					<option value="220">220</option>
					<option value="225">225</option>
					<option value="230">230</option>
					<option value="235">235</option>
					<option value="240">240</option>
					<option value="245">245</option>
					<option value="250">250</option>
					<option value="255">255</option>
					<option value="260">260</option>
					<option value="265">265</option>
					<option value="270">270</option>
					<option value="275">275</option>
					<option value="280">280</option>
				</select>
			</div>
		</div>
		<div class="col-sm-12 requestSelect">
			<select class="form-control" id="option" name="shopType">
				<option value="">쇼핑몰을 선택하세요</option>
				<option value="11ST">11번가</option>
				<option value="ACTION">옥션</option>
				<option value="SHOPBELL">쇼핑벨 (중고장터)</option>
			</select>
		</div>
		<div class="form-group requestButton">
			<div class="col-sm-6">
				<input class="btn btn-sm btn-primary btn-block" type="button" value="알림 등록" id="registBtn" name="registBtn">
			</div>
			<div class="col-sm-6">
				<input class="btn btn-sm btn-primary btn-block" type="button" value="내 알림 목록" id="myRequesttBtn" name="myRequesttBtn">
			</div>
		</div>
	</form:form>
</body>
</html>
