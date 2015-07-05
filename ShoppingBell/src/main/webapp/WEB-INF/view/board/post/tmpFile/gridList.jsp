<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글 리스트</title>
<link rel='stylesheet' href='<c:url value="/resources/css/post/style.css" />' media='screen' />
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="<c:url value="/js/blocksit.min.js" />"></script>
<script>
$(document).ready(function() {
	console.log("ready to jQuery");
	
	$("#writeBtn").click(function() {
		console.log("click");
		location.href="<c:url value="/post/writePost2"/>"
	});
	
	//vendor script
	$('#header')
	.css({ 'top':-50 })
	.delay(1000)
	.animate({'top': 0}, 800);
	
	$('#footer')
	.css({ 'bottom':-15 })
	.delay(1000)
	.animate({'bottom': 0}, 800);
	
	//blocksit define
	$(window).load( function() {
		$('#container').BlocksIt({
			numOfCol: 5,
			offsetX: 8,
			offsetY: 8
		});
	});
	
	//window resize
	var currentWidth = 1100;
	$(window).resize(function() {
		var winWidth = $(window).width();
		var conWidth;
		if(winWidth < 660) {
			conWidth = 440;
			col = 2
		} else if(winWidth < 880) {
			conWidth = 660;
			col = 3
		} else if(winWidth < 1100) {
			conWidth = 880;
			col = 4;
		} else {
			conWidth = 1100;
			col = 5;
		}
		
		if(conWidth != currentWidth) {
			currentWidth = conWidth;
			$('#container').width(conWidth);
			$('#container').BlocksIt({
				numOfCol: col,
				offsetX: 8,
				offsetY: 8
			});
		}
		
	});
});
</script>
</head>
<body>
<!-- Content -->
<section id="wrapper">
<div align="center">
	<div>
	<span id="writeBtn" style="cursor:pointer;">
					 <img src="http://icons.iconarchive.com/icons/rokey/seed/128/write-document-icon.png" id="btnSubmit"
								style="width:20px; height:20px;"/>포스팅 작성
				</span>
	</div>
	<input type="text" id="search" name="search"/> 
	<select id="category" name="category">
	 	<option value="1">Woman Fashion</option>
	 	<option value="2">Man Fashion</option>
	 	<option value="3">전자기기</option>
	 	<option value="4">IT</option>
	 	<option value="5">책</option>
	 	<option value="6">생활</option>
	</select>
	<span id="searchBtn" style="cursor:pointer;">
		<img src="https://api.likecharity.com/charities/img/search-icon.png"/ 
			width="30" height="30">
	</span>
</div>
<div id="container">
	<c:forEach var="post" items="${postList }">
	<div class="grid">
			<div class="imgholder">
				<div>
					<a href="<c:url value="/post/postDetail/${post.postingId }"/>">${post.subject }</a>
				</div>
					<table align="center">
						<tr>
							<td>
								<c:if test="${post.numOfFiles ne '0'}">
									<img width="100" height="100" src="<c:url value="/post/download/${post.postingId }/0"/>">
								</c:if>
								<c:if test="${ post.numOfFiles eq '0'}">
									이미지가 없어요.
								</c:if>
							</td>
						</tr>
					</table>
			</div>
				<p>${post.contextBy }</p>
			<table>
				<tr>
					<td>
						<img	src="https://cdn3.iconfinder.com/data/icons/inficons-set-2/512/thumb-up-512.png"
								width="20" height="20" title="좋아요"/>${post.postingLike } 
					</td>
					<td>
						<img	src="http://web.mit.edu/athletics/www/testim/aboutims/afflist.jpg"
								width="20" height="20" title="댓글수"/>${post.numOfReply } 
					</td>
					<td>
						<img	src="http://static1.squarespace.com/static/5095ce10e4b02d37bef49b42/t/52b0f4a2e4b0d78ef5762cd9/1387328674837/events-calendar-icon.png"
								width="20" height="20" title="날짜"/>${post.modifiedDate } 
					</td>
				</tr>
			</table>
	</div>
	</c:forEach>
</div>
</section>
</body>
</html>