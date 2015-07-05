<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib 	prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib	prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib 	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title><decorator:title default="SHOPPING BELL" /></title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Load jQuery -->
	<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
	
	<!-- Load Bootstrap -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<!-- Load custom js plugins -->
	<script type="text/javascript" src="<c:url value="/js/bootswatch.js" />"></script>
	
	<!-- Load styles -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/css/bootswatch.min.css" />">
	
	<style type="text/css">
		
		.menuPage {
			margin: 0px;
			padding: 0px;
			height: 875px;
		}
		
		.sevenPage1 {
			margin: 35px;
			padding: 0px;
			background-color: white;
			background-size: cover;
			height: 25px;
		}
		
		.copyright1 {
			text-align: center;
			display: block;
		}
		
	</style>
	
	<decorator:head />
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		
			<div class="navbar-header">
				<a  class="navbar-brand" href="<c:url value="/index" />">SHOPPING BELL<!-- <img alt="" src="..."> --></a>
				<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
					<span class="icon-bar"></span>
				</button>
			</div>
			
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/hotdeal/list" />">HOT DEAL</a></li>
					<li><a href="<c:url value="/post/post" />">POST</a></li>
					<li><a href="<c:url value="/board/free/list/init" />">FREE</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Themes <span class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li class="dropdown-header">Nav header</li>
							<li class="divider"></li>
							<li><a href="../default/">Default</a></li>
							<li class="divider"></li>
							<li><a href="../cerulean/">Cerulean</a></li>
						</ul>
					</li>
				</ul>
				
				<p class="navbar-form navbar-right">
					<button type="button" class="btn btn-primary btn-sm">Log in</button>
				</p>
				
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${_MEMBER_ eq null}">
						<li><a href="<c:url value="/login" />">로그인</a></li>
						<li><a href="<c:url value="/member/regist" />">회원가입</a></li>
					</c:if>
					<c:if test="${_MEMBER_ ne null}">
						<li><a href="<c:url value="/logout" />">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	
	<section class="jumbotron menuPage">
		<div class="container">
			<decorator:body />
		</div>
	</section>
	
	<footer class="jumbotron sevenPage1">
		<div class="container">
			<div class="footerContent1">
				<p class="copyright1">&copy; 2015 All rights reserved - Theme by <a href="http://koreaypg.tumblr.com" target="_blank"><b>Pil Gyun Yu</b></a></p>
			</div>
		</div>
	</footer>
</body>
</html>
