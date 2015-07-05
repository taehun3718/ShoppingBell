<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="container">
	
		<div class="serviceSubject">
			<h1 class="wow fadeInDown">고객 센터</h1>
			<p class="wow fadeInUp serviceContent" data-wow-delay="0.1s">사랑합니다 승환님</p>
		</div>
		
		<form class="row serviceInput">
			<div class="form-group col-md-6">
				<input name="name" type="text" placeholder="Name" class="form-control" />
			</div>
			<div class="form-group col-md-6">
				<input name="mail" type="email" placeholder="Email" class="form-control" />
			</div>
			<div class="form-group col-md-12">
				<input name="subject" type="text" placeholder="Subject" class="form-control" />
			</div>
			<div class="form-group col-md-12">
				<textarea name="message" class="form-control" rows="10" placeholder="Message"></textarea>
			</div>
			<div class="form-group col-md-12">
				<input class="btn btn-lg btn-primary btn-block" type="button" value="로그인">
			</div>
		</form>
		
		<div class="serviceAddress">
			<div class="row">
				<div class="col-md-4 text-center wow zoomIn">
					<i class="fa fa-phone circled"></i>
					<span>847-939-9359</span>
				</div>
				<div class="col-md-4 text-center wow zoomIn" data-wow-delay="0.2s">
					<i class="fa fa-envelope circled"></i>
					<span>sevenmail@sevenmail.com</span>
				</div>
				<div class="col-md-4 text-center wow zoomIn" data-wow-delay="0.4s">
					<i class="fa fa-globe circled"></i>
					<span>4116 Oak Avenue</span>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>