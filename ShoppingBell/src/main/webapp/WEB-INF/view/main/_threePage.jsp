<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

	<link rel="stylesheet" href="<c:url value="/css/swiper.min.css" />">
	
	<style type="text/css">
		
		.swiper-container {
			margin: 0 auto;
			width: 100%;
			height: 100%;
		}
		
		.swiper-slide {
			text-align: center;
			font-size: 18px;
			background: #fff;
		
			display: -webkit-box;
			display: -ms-flexbox;
			display: -webkit-flex;
			display: flex;
			-webkit-box-pack: center;
			-ms-flex-pack: center;
			-webkit-justify-content: center;
			justify-content: center;
			-webkit-box-align: center;
			-ms-flex-align: center;
			-webkit-align-items: center;
			align-items: center;
		}
		
		.swiper-pagination {
			margin-bottom:  100px;
		}
		
	</style>
</head>
<body>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<img alt="" src="http://www.funnyshopper.com/gn/data/file/kshophot/32620820__11B9F8B0A1_C3D6C0FAB0A1BCEEC7CE.jpg">
			</div>
			<div class="swiper-slide">
				<img alt="" src="https://www.pasteuri.com/upload/community/2014090217160131937.jpg">
			</div>
			<div class="swiper-slide">
				<img alt="" src=https://lh6.ggpht.com/-857SLg60YPgJj61hJ9vAmCYOZ1a_MdhfNac2AEoV1avL2jSXUa28gis6sgK5x6zYQM=h900>
			</div>
			<div class="swiper-slide">
				<img alt="" src="https://lh3.ggpht.com/aroRWbv0j6n684NjgrVX7PPLsJNGh8drOcZnwJ0Y6JJTqXZ1xTP-3X93W4pFscehK4M=h900">
			</div>
			<div class="swiper-slide">
				<img alt="" src="http://image.lottesuper.co.kr/static-root/fileupload/images/Hotdeal_0320.jpg">
			</div>
		</div>
	
		<div class="swiper-pagination"></div>
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
	</div>
	
	<script type="text/javascript" src="<c:url value="/js/swiper.min.js" />"></script>

	<script>
		
		var swiper = new Swiper('.swiper-container', {
			pagination: '.swiper-pagination',
			nextButton: '.swiper-button-next',
			prevButton: '.swiper-button-prev',
			paginationClickable: true,
			spaceBetween: 30,
			centeredSlides: true,
			autoplay: 3000,
			autoplayDisableOnInteraction: false
		});
		
	</script>
</body>
