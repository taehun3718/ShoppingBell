<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib 	prefix="c"
			uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>list</title>
<meta name="description" content="BlocksIt.js jQuery plugin Demonstration #2 Pinterest dynamic grid with CSS3 Transitions by inWebson.com"/>
<meta name="keywords" content="demonstration,demo,jquery,blocksit.js,css3,dynamic,grid,layout,inwebson"/>
<link rel='stylesheet' href='<c:url value="/resources/css/free/style.css" />' media='screen' />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="<c:url value="/js/blocksit.min.js" />"></script>
<script type="text/javascript">

		$(document).ready(function() {
			
			/* css */
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
					numOfCol: 3,
					offsetX: 2,
					offsetY: 20 
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
			
			/* 검색 버튼 */
			$("#searchBtn").click(function() {				
				$("#searchForm").attr("action", "<c:url value="/board/free/list"/>");
				$("#searchForm").attr("method", "POST");
				$("#searchForm").submit();
			});
		});
</script>
<link rel="shortcut icon" href="http://www.inwebson.com/wp-content/themes/inwebson2/favicon.ico" />
<link rel="canonical" href="http://www.inwebson.com/demo/blocksit-js/demo2/" />
</head>

<body>
<h1>중고장터</h1>
<form name="searchForm" id="searchForm">
	검색
	<select class="searchType" name="searchType">
		<option value="subject" id="subject" >제목</option>
		<option value="nickName" id="nickName">닉네임</option>
		<option value="productName" id="productName">품명</option>
		<option value="productType" id="productType">상품종류</option>
		<option value="size" id="size">사이즈</option>
		<option value="etc" id="etc">기타</option>
		<!-- 기간내 -->
		<!-- 판매중인지 -->
	</select>
	<input type="text" name="searchKeyword" id="searchKeyword" />
	<tr>
		<td colspan="2">
			<input type="button" value="검색" id="searchBtn" />
		</td>
	</tr>
</form>

<a href="<c:url value="/board/free/list/init" />">전체 글 보기</a>


<section id="wrapper">
<div id="container">
<c:forEach items="${freeList.freeList }" var="free">
	<div class="grid">
		<div class="imgholder">
		<table border="1">
				<tr  align="center">
					<th colspan="3">
						(<c:out value="${free.freeId }" />)
					</th>
					<td>
						<c:if test="${free.onSale eq '판매중'}">
							<font color="green">
						</c:if>
						<c:if test="${free.onSale eq '판매완료'}">
							<font color="red">
						</c:if>
								<b><c:out value="${free.onSale }" /></b>
							</font>
					</td>
				</tr>
			
			 	<tr  align="center">
					<th>Subject</th>
					<td colspan="3"><c:out value="${free.subject }" /></td>
				</tr>
				<tr>
					<th>Writer</th>
					<td><c:out value="${free.nickName }" /></td>
					<th>Hit</th>
					<td><c:out value="${free.hit }" /></td> 
				</tr>
				 	<tr  align="center">
					<th>Created Date</th>
					<td colspan="3"><c:out value="${free.createdDate }" /></td>
				</tr>
				<tr>
					<td colspan="4">
					<a href="<c:url value="/board/free/detail/${free.freeId}"/>">
						<img src="<c:url value="/download/${free.realName}/${free.randomName }"/>" 
						     width="350" height="350"><br/>
					</a>
					</td>
				</tr>
				<tr>
					<th>#품명</th>
					<td colspan="3"><c:out value="${free.productName }" /> </td>
				</tr>
				<tr>
					<th>#판매가격</th>
					<td colspan="3"><c:out value="${free.productPrice } " /> </td>
				</tr>
				<tr>
					<th>#상품종류</th>
					<c:if test="${free.productType eq '신발'}">
						<td><c:out value="${free.productType } " /> </td>
						<th>#사이즈</th>
						<td><c:out value="${free.size }" /> </td>
					</c:if>
					<c:if test="${free.productType != '신발'}">
						<td colspan="3"><c:out value="${free.productType } " />
					</c:if>
					
				</tr>
				<tr>
					<th>#기타 태그</th>
					<td colspan="3"><c:out value="${free.etc }" /> </td>
				</tr>
				
			</table>
		</div>
	</div>
</c:forEach>
</div>
</section>
${freeList.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "")}<br/>
<a href="<c:url value="/board/free/write"/>">글쓰기</a>
</body>
</html>