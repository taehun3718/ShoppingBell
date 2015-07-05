<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<div id="dialog" title="이미지 업로드">

	<div class="divUpload">
		<input type="file" id="uploadedFile" name="uploadedFile"
			accept="image/*" />
	</div>
	<div class="uploadConfirm" style="cursor: pointer; text-align: right;">
		<br />확인
	</div>

</div>

<div class="tutorial.lveTitle" style="margin: auto; text-align: center;">
	<span>이곳은 제목을 입력하는 input 박스입니다.</span>
</div>

<div
	style="margin: auto; text-align: center; width: 800px; background-color: #C63966;">

	<div class="lveTitle" style="">
		<input type="text" placeholder="제목을 입력해주세요." id="subject"
			name="subject"
			style="text-align: center; font-size: 20px; font-weight: bold; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;" />
	</div>
</div>
<div style="margin: auto; text-align: center;">
	<span>이곳은 이미지를 첨부하는 기능입니다.</span>
	<div style="text-align: center;">
		<img src="http://dealerweb.grantday.co.nz/Images/camera-img.png"
			width="50" height="40" style="vertical-align: middle;" /> <span
			class="uploadImage"
			style="font-family: Helvetica; cursor: pointer; color: #d1d1d1; font-weight: bold;">업로드
		</span>
	</div>
</div>

<div style="margin: auto; text-align: center; width: 800px;">
	<div>
		<span>이곳은 content를 입력하는 teaxtArea 박스입니다.</span>
	</div>
	<textarea placeholder="내용을 입력해주세요." id="content" name="content"
		style="text-align: left; border: 0px; overflow: hidden; word-wrap: break-word; width: 680px; height: 100px;"></textarea>
</div>
<div class="clear"></div>

<br />
<input type="button" id="btnSubmit" value="글쓰기!" />
/
<a href="<c:url value="/post/post"/>">리스트로</a>