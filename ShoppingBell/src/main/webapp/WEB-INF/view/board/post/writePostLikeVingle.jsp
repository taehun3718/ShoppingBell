<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<!-- jQTE   -->
<link type="text/css" rel="stylesheet" href="<c:url value="/js/jQTE/jquery-te-1.4.0.css" />">
<script src="<c:url value="/js/jQTE/jquery-te-1.4.0.min.js" />"></script>
<!-- jQTE End -->

<script src="<c:url value="/js/jquery-ui.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/post/uploadDialog/jquery-ui.css" />" />
<script src="<c:url value="/js/jquery.form.js" />"></script>


<script type="text/javascript">
	
	var cntOfContents = 1;
	var className= ".appendClass_";
	var addContentsFlag = false;
	
	
	function initFormPage() {
		
		$(".btnAppend").hide();
		
		//hide form
		$(".blah").hide();
		var numOfCont = 5;
		for(i=2; i<=numOfCont; i++) {
			$(className+i).hide();
		}
	}
	
	function showForm(cntOfContents) {
		if(addContentsFlag) {
			console.log("CNT;" + cntOfContents)
			$(className+cntOfContents).slideDown(200);
		}
		$(".btnAppend").hide();
		addContentsFlag = false;
	}
	$(document).ready(	function() {
		
		initFormPage();
		
		console.log("ready to jQuery");
		//$(".content").cleditor();
		
		$("#btnSubmit").click( function() {
			if ($("#subject").val() == "") {
				alert("제목을 입력하세요!");
				return;
			}
			if ($("#content").val() == "") {
				alert("내용을 입력하세요!");
				return;
			}
			
			$("#writeForm").attr("action",	"<c:url value="/post/doWritePost"/>");
			$("#writeForm").attr("method", "POST");
			$("#writeForm").submit();
		});
		
		$(".uploadedFile").on("change", function() {
			
			var previewImageTarget = $(this).parent().parent().parent()
											.children(":eq(1)").children().children();
			readURL(this, previewImageTarget);
		});
		
		
		$(".btnAppend").on("click", function() {
			console.log("btn: append");
			showForm(cntOfContents);
			//http://stackoverflow.com/questions/13583180/jquery-scroll-to-bottom-of-page
			$('html, body').animate({
		        scrollTop: $(document).height()
		    }, 'fast');
		});

	});
	
	//http://start0.tistory.com/59
	function readURL(input, previewImageTarget) {
		if (input.files && input.files[0]) {
			var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
			reader.onload = function(e) {
				//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
				$(previewImageTarget).attr('src', e.target.result);
				$(previewImageTarget).fadeIn(200);
				//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
				//(아래 코드에서 읽어들인 dataURL형식)
			}
			reader.readAsDataURL(input.files[0]);
			//File내용을 읽어 dataURL형식의 문자열로 저장
		}
	}
	
	function editCont()   {
		console.log("edit");
		if (addContentsFlag == false) {
			$(".btnAppend").fadeIn(200);
			addContentsFlag = true;
			console.log("contents");
			cntOfContents++;
		} 
		else {

		}
	}
</script>
</head>
<!--TODO: Validation Check  -->

<form id="writeForm" name="writeForm" enctype="multipart/form-data">
	<!-- 제목 추가 시작-->
	<div class="divTitle" style="margin: auto; text-align: center; width: 800px;">
			<input type="text" placeholder="제목을 입력해주세요." id="subject"
				name="subject"
				style="text-align: center; font-size: 20px; font-weight: bold; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;" />
	</div>
	<!-- 제목 종료-->
	
	<!-- 1번째 내용 추가 클래스  시작 -->
	<div class="appendClass_1">
		<div style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<tr class="inputFileTr">
					<td class="inputFileTd">
						<input type="file" class="uploadedFile" name="uploadedFile"
								accept="image/*" style="text-align: center;"/>
					</td>
				</tr>
				
				<tr class="imgPreviewTr">
					<td class="imgPreviewTd">
						<img	class="blah"	
								width="300"
								height="200"
								src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
								alt="이미지 미리보기" />
					</td>
				</tr>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
		</div>
		
	</div>
	
	<!-- 2번째 내용 추가 클래스  시작 -->
	<div class="appendClass_2">
		<div style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<tr class="inputFileTr">
					<td class="inputFileTd">
						<input type="file" class="uploadedFile" name="uploadedFile"
								accept="image/*" style="text-align: center;"/>
					</td>
				</tr>
				
				<tr class="imgPreviewTr">
					<td class="imgPreviewTd">
						<img	class="blah"	
								width="300"
								height="200"
								src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
								alt="이미지 미리보기" />
					</td>
				</tr>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
		</div>
		
	</div>
	
	<!-- 3번째 내용 추가 클래스  시작 -->
	<div class="appendClass_3">
		<div style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<tr class="inputFileTr">
					<td class="inputFileTd">
						<input type="file" class="uploadedFile" name="uploadedFile"
								accept="image/*" style="text-align: center;"/>
					</td>
				</tr>
				
				<tr class="imgPreviewTr">
					<td class="imgPreviewTd">
						<img	class="blah"	
								width="300"
								height="200"
								src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
								alt="이미지 미리보기" />
					</td>
				</tr>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
		</div>
		
	</div>
	
	<!-- 4번째 내용 추가 클래스  시작 -->
	<div class="appendClass_4">
		<div style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<tr class="inputFileTr">
					<td class="inputFileTd">
						<input type="file" class="uploadedFile" name="uploadedFile"
								accept="image/*" style="text-align: center;"/>
					</td>
				</tr>
				
				<tr class="imgPreviewTr">
					<td class="imgPreviewTd">
						<img	class="blah"	
								width="300"
								height="200"
								src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
								alt="이미지 미리보기" />
					</td>
				</tr>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
		</div>
		
	</div>
	
	<!-- 5번째 내용 추가 클래스  시작 -->	
	<div class="appendClass_5">
		<div style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<tr class="inputFileTr">
					<td class="inputFileTd">
						<input type="file" class="uploadedFile" name="uploadedFile"
								accept="image/*" style="text-align: center;"/>
					</td>
				</tr>
				
				<tr class="imgPreviewTr">
					<td class="imgPreviewTd">
						<img	class="blah"	
								width="300"
								height="200"
								src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
								alt="이미지 미리보기" />
					</td>
				</tr>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
		</div>
		
	</div>
	<!--내용 추가 + 버튼  -->
	<table align="center">
		<tr>
			<td>
				<img	src="https://cdn2.iconfinder.com/data/icons/media-and-navigation-buttons-square/512/Button_11-512.png" 
						class="btnAppend" 
						width="30" height="30"
						style="cursor:pointer;"/>
			</td>
		</tr>
	</table>
	<!-- 글쓰기 버큰 및 카테고리 조회 Select Box, 리스트 버튼  -->
	<table align="center">
		<tr>
			<td>
				<br />
				<select id="category" name="category">
					<option value="0">모든 카테고리</option>
				 	<option value="1">Woman Fashion</option>
				 	<option value="2">Man Fashion</option>
				 	<option value="3">전자기기</option>
				 	<option value="4">책</option>
				 	<option value="5">생활</option>
				</select>
				
				<span id="btnSubmit" style="cursor: pointer;">
					  <img src="http://icons.iconarchive.com/icons/rokey/seed/128/write-document-icon.png" id="btnSubmit"
							style="width:20px; height:20px;"/>글쓰기</span>
				 / <a		href="<c:url value="/post/post"/>">리스트로</a> 
				
			</td>
		</tr>
	</table>
</form>
<!-- jQTE 에디터  -->
<script>
	$(".content").jqte({
			change: function(){ 
				editCont();
			}
		}
	);
</script>
</body>
</html>