<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.2.0/isotope.pkgd.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<c:url value="/js/jquery.bxslider.min.js" />"></script>
<link href="<c:url value="/css/jquery.bxslider.css" />" rel="stylesheet" />
<style type="text/css">
* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: sans-serif;
}

/* ---- button ---- */
.button {
	display: inline-block;
	padding: 10px 18px;
	margin-bottom: 10px;
	background: #EEE;
	border: none;
	border-radius: 7px;
	background-image: linear-gradient(to bottom, hsla(0, 0%, 0%, 0),
		hsla(0, 0%, 0%, 0.2));
	color: #222;
	font-family: sans-serif;
	font-size: 16px;
	text-shadow: 0 1px white;
	cursor: pointer;
}

.button:hover {
	background-color: #8CF;
	text-shadow: 0 1px hsla(0, 0%, 100%, 0.5);
	color: #222;
}

.button:active, .button.is-checked {
	background-color: #28F;
}

.button.is-checked {
	color: white;
	text-shadow: 0 -1px hsla(0, 0%, 0%, 0.8);
}

.button:active {
	box-shadow: inset 0 1px 10px hsla(0, 0%, 0%, 0.8);
}

/* ---- button-group ---- */
.button-group:after {
	content: '';
	display: block;
	clear: both;
}

.button-group .button {
	float: left;
	border-radius: 0;
	margin-left: 0;
	margin-right: 1px;
}

.button-group .button:first-child {
	border-radius: 0.5em 0 0 0.5em;
}

.button-group .button:last-child {
	border-radius: 0 0.5em 0.5em 0;
}

/* ---- isotope ---- */
.grid {
	border: 1px solid #333;
}

/* clear fix */
.grid:after {
	content: '';
	display: block;
	clear: both;
}

/* ---- .element-item ---- */
.element-item {
	position: relative;
	float: left;
	width: 365px;
	height: 365px;
	margin: 5px;
	padding: 10px;
	background: #888;
	color: #262524;
}

.element-item>* {
	margin: 0;
	padding: 0;
}

.element-item .name {
	position: absolute;
	left: 10px;
	top: 60px;
	text-transform: none;
	letter-spacing: 0;
	font-size: 12px;
	font-weight: normal;
}

.element-item .symbol {
	position: absolute;
	left: 10px;
	top: 0px;
	font-size: 42px;
	font-weight: bold;
	color: white;
}

.element-item .number {
	position: absolute;
	right: 8px;
	top: 5px;
}

.element-item .weight {
	position: absolute;
	left: 10px;
	top: 76px;
	font-size: 12px;
}

.element-item.alkali {
	background: #F00;
	background: hsl(0, 100%, 50%);
}

.element-item.alkaline-earth {
	background: #F80;
	background: hsl(36, 100%, 50%);
}

.element-item.lanthanoid {
	background: #FF0;
	background: hsl(72, 100%, 50%);
}

.element-item.actinoid {
	background: #0F0;
	background: hsl(108, 100%, 50%);
}

.element-item.transition {
	background: #0F8;
	background: hsl(144, 100%, 50%);
}

.element-item.post-transition {
	background: #0FF;
	background: hsl(180, 100%, 50%);
}

.element-item.metalloid {
	background: #08F;
	background: hsl(216, 100%, 50%);
}

.element-item.diatomic {
	background: #00F;
	background: hsl(252, 100%, 50%);
}

.element-item.halogen {
	background: #F0F;
	background: hsl(288, 100%, 50%);
}

.element-item.noble-gas {
	background: #F08;
	background: hsl(324, 100%, 50%);
}

.imgDiv {
	margin : 0 auto;
}
</style>
<script type="text/javascript">

	$(document).ready(function() {
		
		$(".element-item").click(function() {
			
			var imgId = $(this).find('.hotdealId').val();
			var src = "/ShoppingBell//hotdeal/fileDownload/" + imgId;
			
			$("#hotdealImg").attr("src", src);
			
			$('.modal').modal();
			
		});
		
		
		
	});


	$(function() {
		// init Isotope
		var $grid = $('.grid').isotope({
			itemSelector : '.element-item',
			layoutMode : 'fitRows'
		});
		// filter functions
		var filterFns = {
			// show if number is greater than 50
			numberGreaterThan50 : function() {
				var number = $(this).find('.number').text();
				return parseInt(number, 10) > 50;
			},
			// show if name ends with -ium
			ium : function() {
				var name = $(this).find('.name').text();
				return name.match(/ium$/);
			}
		};
		
		// bind filter button click
		$('.filters-button-group').on('click', 'button', function() {
			var filterValue = $(this).attr('data-filter');
			// use filterFn if matches value
			filterValue = filterFns[filterValue] || filterValue;
			$grid.isotope({
				filter : filterValue
			});
		});
		// change is-checked class on buttons
		$('.button-group').each(function(i, buttonGroup) {
			var $buttonGroup = $(buttonGroup);
			$buttonGroup.on('click', 'button', function() {
				$buttonGroup.find('.is-checked').removeClass('is-checked');
				$(this).addClass('is-checked');
			});
		});

	});

	/* $(document).ready(function(){ 
		$(".bxslider").bxSlider({
			
		});
	});  */
</script>

</head>
<body>
	<br />
	<br />

	<%-- <div style="width:600px"> 
		<ul class="bxslider">
			<c:forEach var="hotdealList" items="${hotdealList}">
				<li><img src="<c:url value="/hotdeal/fileDownload/${hotdealList.hotdealBoardId}" />"></li> 
			</c:forEach>
		</ul> 
	</div> --%>

	<div class="button-group filters-button-group">
		<button class="button is-checked" data-filter="*">show all</button>
		<button class="button" data-filter=".clothing">의류</button>
		<button class="button" data-filter=".goods">뷰티/잡화</button>
		<button class="button" data-filter=".digital">디지털/가전</button>
	</div>

	<div class="grid">
		<c:forEach var="hotdealList" items="${hotdealList}">
			<div class="element-item ${hotdealList.hotdealCategory }" data-category="${hotdealList.hotdealCategory}">
				<img width="350" height="350"
					src="<c:url value="/hotdeal/fileDownload/${hotdealList.hotdealBoardId}" />">
				<input type="hidden" class="hotdealId" value="${hotdealList.hotdealBoardId }"/>
			</div>
		</c:forEach>
	</div>
	
	<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button> -->
	
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
		<div class="modal-content" style="text-align:center;">
			<div id="imgDiv" style="display:inline-block;">
			<a href="http://www.naver.com"><img id="hotdealImg"
					src=""/></a>
			</div>
		</div>
	</div>
</div>
	
</body>
</html>