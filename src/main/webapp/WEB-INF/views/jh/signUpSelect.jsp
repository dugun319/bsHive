<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
	<link rel="stylesheet" type="text/css" href="/css/jh_signUpSelect.css">
   
</head>
<body>
    <div class="container">
    	<div class="background_image">
			<img src="/images/로그인배경.jpg" alt="배경 이미지" class="background_image_img">
    	</div>
        <div class="login_container">
    		<div class="image_container">
        	<!-- 학생 회원가입으로 이동 -->
	        	<a href="/jh/joinAgree?mbr_se=1">
		            <img src="/images/학생.jpg" alt="회원가입 학생타입">
		            <div class="overlay-text1">학생</div>
	        	</a>
    		</div>
	    	<div class="image_container">
	        <!-- 교수 회원가입으로 이동 -->
		        <a href="/jh/joinAgree?mbr_se=2">
		            <img src="/images/교수.jpg" alt="회원가입 교수타입">
		            <div class="overlay-text2">교수</div>
		        </a>
			</div>
		</div>
    </div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>
