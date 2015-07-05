<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib 	prefix="c"
			uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/ShoppingBell/js/jquery-1.11.2.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		// 최초 사용하지 않는 항목은 숨겨 놓는다
		$("#optionGuide").hide(); 
		$("#sizeOption").hide(); 

		// 체크 박스 해제, 선택 이벤트 발생시 초기화
		$("#option").change(function() {
			
			if ( $("#option").val() == 0 ) {
				$("#productSize option:eq(0)").attr("selected", "selected");
			}
			
		});
		
		
		// 체크 박스 해제, 선택 이벤트 발생시 초기화
		$("#optionCheck").change(function() {
			
			$("#optionGuide option:eq(0)").attr("selected", "selected");
			
			if ( $("#option").val() == "" ) {
				$("#sizeOption").hide();
			}
			
		});
		
		
		$("#optionCheck").click(function () {
			
			if ( $('input:checkbox[id="optionCheck"]').is(":checked") ) {
				$("#optionGuide").show();
			}
			
			else {
				$("#optionGuide").hide();
			}
			
		});
		
		$("#option").change(function() {
			
			if ( $('#option option:selected').val() == "size" ) {
				$("#sizeOption").show();
			}
			
			else {
				$("#sizeOption").hide();
			}
			
		});
		
		
		$("#updateBtn").click(function() {
			
			if( $("#productName").val() == "" ) {
				alert("상품명을 입력하세요.");
				return;
			}
			if( $("#productPrice").val() == "" ) {
				alert("가격을 입력하세요.");
				return;
			}
			if( $("#productType").val() == "" ) {
				alert("상품종류를 입력하세요.");
				return;
			}
			
			if( $("#subject").val() == "" ) {
				alert("내용을 입력하세요.");
				return;
			}
			if( $("#content").val() == "" ) {
				alert("내용을 입력하세요.");
				return;
			}
			
			$("#updateForm").attr("action", "<c:url value="/board/free/doModify"/>");
			$("#updateForm").attr("method", "POST");
			$("#updateForm").submit();
		});
		
		/* 파일 업로드 시 옆에 뜨게 */
		 function readURL(input) {
		        if (input.files && input.files[0]) {
		            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
		            reader.onload = function (e) {
		            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
		                $('#blah').attr('src', e.target.result);
		                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
		                //(아래 코드에서 읽어들인 dataURL형식)
		            }                   
		            reader.readAsDataURL(input.files[0]);
		            //File내용을 읽어 dataURL형식의 문자열로 저장
		        }
		    }//readURL()--

		    //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
		    $("#uploadFile").change(function(){
		        //alert(this.value); //선택한 이미지 경로 표시
		        readURL(this);
		    });

	});
	
</script>
</head>
<body>
<h1>내용 수정</h1>
	<br/><br/>
	<form name="updateForm" id="updateForm" enctype="multipart/form-data">
		<input type="hidden" name="freeId" id="freeId" value="${originalArticleInfo.freeId }" />
		<table align="center" width="1100">
		<tr>
			<td>
				<table border="1">
					<tr>
						<td colspan="4" width="350" height="350">
							 <img id="blah" src="<c:url value="/download/${originalArticleInfo.realName}/${originalArticleInfo.randomName }"/>" alt="이미지를 등록하세요" width="350" height="350" />
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table border="1" align="center" width="673">
					<tr>
						<table border="1" align="center" width="673">
							<tr>
								<td colspan="2">해쉬태그에 가급적 띄어쓰기를 지양해 주세요 (정확한 검색 결과를 위함)</td>
							</tr>
							<tr>
								<th>#품명</th>
								<td>
									<input type="text" name="productName" id="productName" value="${originalArticleInfo.productName }"/> 예)634835006
								</td>
							</tr>
							<tr>
								<th>#판매 가격</th>
								<td>
									<input type="text" name="productPrice" id="productPrice" value="${originalArticleInfo.productPrice }"/>원
								</td>
							</tr>
							<tr>
								<th>#상품 종류</th>
								<td>
									<input type="text" name="productType" id="productType" value="${originalArticleInfo.productType }"/>
									신발체크<input type="checkbox" id="optionCheck" name="optionCheck"/>
									예) 신발/옷/티켓/노트북/...
								</td>
							</tr>
							<tr id="optionGuide">
								<th>#사이즈</th>
								<td>
									<input type="text" name="size" id="size" value="${originalArticleInfo.size }"/> 
								</td>
							</tr>
							<tr>
								<th>#기타 태그</th>
								<td>
									<input type="text" name="etc" id="etc" value="${originalArticleInfo.etc }"/> 검색에 나왔으면 하는 내용을 입력하세요(예:나이키허라취)
								</td>
							</tr>	
							<tr>
								<th>상품 이미지</th>
								<td>
									<input type="file" name="uploadFile" id="uploadFile"/><br/>
									${originalArticleInfo.realName }
								</td>
							</tr>
				
						<table border="1" align="center" width="673">
							<tr>
								<th>제목</th>
								<td>
									<input type="text" name="subject" id="subject" value="${originalArticleInfo.subject }"/>
								</td>
							</tr>	
							<tr>
								<th>내용</th>
								<td>
									<textarea rows="10" cols="100" name="content" id="content">${originalArticleInfo.content}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="button" value="수정" id="updateBtn" />
									<a href="<c:url value="/board/free/list"/>">목록</a> 
								</td>
							</tr>
						</td>
					</tr>
				</table>
			</form>
	
</body>
</html>