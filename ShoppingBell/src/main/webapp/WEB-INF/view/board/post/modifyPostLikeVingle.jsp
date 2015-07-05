<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

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

	var cntOfContents = ${multipostDetailVO.contentSize};
	var className= ".appendClass_";
	var addContentsFlag = false;
	var tmpImageSrc = "";
	var currentCont = ${multipostDetailVO.contentSize};
	
	function initFormPage() {
		$("#category").val("${multipostDetailVO.category}");
		$(".btnAppend").hide();
		$(".uploadFile").hide();
		//hide form
		//만약 content를 늘리고 싶으면 이부분을 수정해야 컨텐츠를 늘릴 수 있음
		//그와 동시에 DOM도 정적으로 추가
		var numOfCont = 5;
		//현재 수정될 contents 갯수
		for(i=currentCont+1; i<=numOfCont; i++) {
			$(className+i).hide();
		}
	}
	
	function showForm(cntOfContents) {
		if(addContentsFlag) {
			$(className+cntOfContents).slideDown(200);
		}
		//업로드 Input file 보이게
		$(className+cntOfContents).find(".uploadFile").slideDown(200);
		
		$(".btnAppend").hide();
		$('html, body').animate({
			scrollTop: $(document).height()
		}, 'fast');
		//컨텐츠가 추가 되면 컨텐츠 추가 버튼을 비활성화하기 위한 flag 설정
		addContentsFlag = false;
	}
	$(document).ready(	function() {
		
		initFormPage();
		//현재 수정될 contents 갯수
		
		
		console.log("ready to jQuery");

		$("#btnSubmit").click( function() {
			console.log("click");
			if ($("#subject").val() == "") {
				alert("제목을 입력하세요!");
				return;
			}
			if ($("#content").val() == "") {
				alert("내용을 입력하세요!");
				return;
			}
			
			$("#writeForm").attr("action",	"<c:url value="/post/doModify/${multipostDetailVO.postingId}"/>");
			$("#writeForm").attr("method", "POST");
			$("#writeForm").submit();
		});
		
		$(".uploadFile").on("change", function() {
			//선택된 DOM의 이미지 객체를 previewImageTarget에 삽입
			var previewImageTarget = $(this).parent().parent().parent()
											.children(":eq(0)").children().children();
			//이미지 미리보기 function
			readURL(this, previewImageTarget);
		});
		
		
		$(".btnAppend").on("click", function() {
			showForm(cntOfContents);
		});
		
		$(".checkDelete").on("click", function() {
			//Check박스 및 이미지가 비었을 경우 비어있는 이미지를 불러올 때 쓰는 var
			var isChecked = $(this).prop("checked");
			var emptyURL = "http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png";
			
			//Tag에 대한 데이터를 가져오는 var
			var imageSrcTag = $(this).parent().children(":eq(0)");
			var uploadSpanTag = $(this).parent().children(":eq(3)");
			var uploadInputFile = $(this).parent().children(":eq(4)");
			
			if(isChecked) {
				tmpImageSrc = imageSrcTag.attr("src");
				imageSrcTag.attr("src", emptyURL);
				$(uploadSpanTag).css({"text-decoration": "line-through"});
				$(uploadInputFile).show();
			}
			else {
				imageSrcTag.attr("src", tmpImageSrc);
				$(uploadSpanTag).css({"text-decoration": "none"});
				$(uploadInputFile).hide();
			}
		});
	});
	
	//http://start0.tistory.com/59
	function readURL(input, previewImageTarget) {
		if (input.files && input.files[0]) {
			var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
			reader.onload = function(e) {
				//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
				$(previewImageTarget).attr('src', e.target.result);
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
<style>
div.clear {
	clear: both;
}
</style>

<form id="writeForm" name="writeForm" enctype="multipart/form-data">
	<div style="margin: auto; text-align: center; width: 800px;">
			<input type="text" placeholder="제목을 입력해주세요." id="subject"
				name="subject"
				value="${multipostDetailVO.subject }"
				style="text-align: center; font-size: 20px; font-weight: bold; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;" />
	</div>
	<!-- 1번째 내용 추가 클래스  시작 -->
	<div class="appendClass_1">
		<div class="imgUploadClass" style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<c:choose>
					<c:when test="${not empty multipostDetailVO.realNames[0] }">
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="<c:url value="/post/download/${multipostDetailVO.postingId}/0"/>"
										alt="이미지 미리보기" /><br/>
							<input type	=	"checkbox" 
									class="checkDelete"/>
							<span class="uploadedFile">${ multipostDetailVO.realNames[0]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:when>
					<c:otherwise>
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
										alt="이미지 미리보기" /><br/>
							<input type	=	"checkbox" 
									class="checkDelete"/>
							<span class="uploadedFile">${ multipostDetailVO.realNames[0]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;">${multipostDetailVO.contents[0] }</textarea>
		</div>
		
	</div>
	<!-- 2 번째 내용 추가 클래스  시작 -->
	<div class="appendClass_2">
		<div class="imgUploadClass" style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<c:choose>
					<c:when test="${not empty multipostDetailVO.realNames[1] }">
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="<c:url value="/post/download/${multipostDetailVO.postingId}/1"/>"
										alt="이미지 미리보기" /><br/>
							<input type="checkbox" class="checkDelete"/><span class="uploadedFile">${ multipostDetailVO.realNames[1]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:when>
					<c:otherwise>
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
										alt="이미지 미리보기" /><br/>
							<input type	=	"checkbox" 
									class="checkDelete"/>
							<span class="uploadedFile">${ multipostDetailVO.realNames[1]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;">${multipostDetailVO.contents[1] }</textarea>
		</div>
		
	</div>
	<!-- 3번째 내용 추가 클래스  시작 -->
	<div class="appendClass_3">
		<div class="imgUploadClass" style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<c:choose>
					<c:when test="${not empty multipostDetailVO.realNames[2] }">
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="<c:url value="/post/download/${multipostDetailVO.postingId}/2"/>"
										alt="이미지 미리보기" /><br/>
							<input type="checkbox" class="checkDelete"/><span class="uploadedFile">${ multipostDetailVO.realNames[2]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:when>
					<c:otherwise>
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
										alt="이미지 미리보기" /><br/>
							<span class="emptySpan"></span>
							<span class="uploadedFile">${ multipostDetailVO.realNames[2]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;">${multipostDetailVO.contents[2] }</textarea>
		</div>
		
	</div>
	<!--4번째 내용 추가 클래스  시작 -->
	<div class="appendClass_4">
		<div class="imgUploadClass" style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<c:choose>
					<c:when test="${not empty multipostDetailVO.realNames[3] }">
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="<c:url value="/post/download/${multipostDetailVO.postingId}/3"/>"
										alt="이미지 미리보기" /><br/>
							<input type="checkbox" class="checkDelete"/><span class="uploadedFile">${ multipostDetailVO.realNames[3]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:when>
					<c:otherwise>
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
										alt="이미지 미리보기" /><br/>
							<span class="emptySpan"></span>
							<span class="uploadedFile">${ multipostDetailVO.realNames[3]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;">${multipostDetailVO.contents[3] }</textarea>
		</div>
		
	</div>
	<!-- 5번째 내용 추가 클래스  시작 -->
	<div class="appendClass_5">
		<div class="imgUploadClass" style="margin: auto; text-align: center;">
			<table class="fileUploadTable" align="center">
				<c:choose>
					<c:when test="${not empty multipostDetailVO.realNames[4] }">
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="<c:url value="/post/download/${multipostDetailVO.postingId}/4"/>"
										alt="이미지 미리보기" /><br/>
							<input type="checkbox" class="checkDelete"/><span class="uploadedFile">${ multipostDetailVO.realNames[4]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:when>
					<c:otherwise>
					<tr class="imgPreviewTr">
						<td class="imgPreviewTd">
							<img	class="blah"	
										width="300"
										height="200"
										src="http://static.eu2013.lt/uploads/news/images/584x387_crop/image-not-found.png"
										alt="이미지 미리보기" /><br/>
							<span class="emptySpan"></span>
							<span class="uploadedFile">${ multipostDetailVO.realNames[4]}</span>
							<input type="file" class="uploadFile" name="uploadedFile"
									accept="image/*" style="text-align: center;"/>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	
		<div style="margin: auto; text-align: center; width: 800px;">
			<textarea placeholder="내용을 입력해주세요." class="content" id="content" name="content"
				style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;">${multipostDetailVO.contents[4] }</textarea>
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
	<br /> 
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
							style="width:20px; height:20px;"/>글수정</span>
				 / <a		href="<c:url value="/post/post"/>">리스트로</a> 
				
			</td>
		</tr>
	</table>
</form>
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