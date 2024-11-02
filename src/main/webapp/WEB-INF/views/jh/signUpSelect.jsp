<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<title>로그인 페이지</title>
	<link rel="stylesheet" type="text/css" href="/css/jh_signUpSelect.css">
   
</head>
<body>
    <div class="container">
        <!-- 이미지 -->
        
    	<div class="background_image">
			<img src="/images/로그인배경.jpg" alt="배경 이미지" class="background_image_img">
    	</div>
    
        <!-- 로그인 폼 -->
        <div class="login_container">
        	<div class="image_container">
	        	<a href="/jh/joinAgreeStdnt?mbr_se=1">
		            <img src="/images/학생.jpg" alt="회원가입 학생타입">
		            <div class="overlay-text1">학생</div>
		         </a>   
	        </div>
	        <div class="image_container">
		        <a href="/jh/joinAgreeEmp?mbr_se=2">
		            <img src="/images/교수.jpg" alt="회원가입 교수타입">
		            <div class="overlay-text2">교수</div>
		        </a>
	        </div>
	        
        </div>
    </div>
</body>
</html>
