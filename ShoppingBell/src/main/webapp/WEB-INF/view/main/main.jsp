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
	<meta name="viewport" content="width=device-width, initial-scale=1 initial-scale=1, minimum-scale=1, maximum-scale=1">
	
	<!-- Load jQuery -->
	<script type="text/javascript" src="<c:url value="/js/jquery-1.11.2.js" />"></script>
	
	<!-- Load Bootstrap JS -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<!-- Load custom JS -->
	<script type="text/javascript" src="<c:url value="/js/bootswatch.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/swiper.min.js" />"></script>
	
	<!-- Load ShoppingBell CSS -->
<%-- 	<link rel="stylesheet" href="<c:url value="/css/shoppingBell.css" />"> --%>
	
	<!-- Load Bootstrap CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	
	<!--Load custom CSS -->
	<link rel="stylesheet" href="<c:url value="/css/bootswatch.min.css" />">
	<link rel="stylesheet" href="<c:url value="/css/swiper.min.css" />">
	<link rel="stylesheet" href="<c:url value="/css/font-awesome.css" />">
	
	<script type="text/javascript">
		
	</script>
	
	<style type="text/css">
		.menubar {
			margin: 0px;
			padding: 0px;
		}
		
		.onePage {
			margin-bottom: 0px;
			padding: 0px;
			background: url("http://40.media.tumblr.com/a34a1b71f57f3f92d2037eae771e6490/tumblr_nd68btGbS11tu5go3o1_1280.jpg") no-repeat;
			background-size: cover;
			height: 1000px;
		}
		
		.twoPage {
			margin: 0px;
			padding: 0px;
			background-color: green;
			background-size: cover;
			height: 1000px;
		}
		
		.threePage {
			margin: 0px;
			padding: 0px;
			height: 1000px;
		}
		
		.fourPage {
			margin-bottom: 0px;
			padding: 0px;
			background-color: yellow;
			background-size: cover;
			height: 1000px;
		}
		
		.fivePage {
			margin-bottom: 0px;
			padding: 0px;
			background-color: green;
			background-size: cover;
			height: 1000px;
		}
		
		.sixPage {
			margin-bottom: 0px;
			padding: 0px;
			background-color: dark;
			background-size: cover;
			height: 1000px;
		}
		
	</style>
	
	<decorator:head />
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top menubar">
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
					<li><a href="<c:url value="/board/free/list" />">FREE</a></li>
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
					<c:if test="${_MEMBER_ eq null }">
						<li><a href="<c:url value="/login" />">로그인</a></li>
						<li><a href="<c:url value="/member/regist" />">회원가입</a></li>
					</c:if>
					<c:if test="${_MEMBER_ ne null }">
						<li><a href="<c:url value="/logout" />">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	
	<section class="jumbotron onePage"></section>
	
	<section class="jumbotron twoPage">
		<jsp:include page="_twoPage.jsp"></jsp:include>
	</section>
	
	<section class="jumbotron threePage">
		<jsp:include page="_threePage.jsp"></jsp:include>
	</section>
	
	<section class="jumbotron fourPage"></section>
	
	<section class="jumbotron fivePage"></section>
	
	<section class="jumbotron sixPage">
		<jsp:include page="_sixPage.jsp"></jsp:include>
	</section>
	
	<footer class="jumbotron sevenPage">
		<jsp:include page="_sevenPage.jsp"></jsp:include>
	</footer>
	
</body>
</html>
