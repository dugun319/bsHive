<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<title>로그인 페이지</title>
	<link rel="stylesheet" type="text/css" href="/css/jh_joinSuccess.css">
   
</head>
<body>
<div class="container">
    <!-- 배경 이미지 -->
    <div class="background_image">
        <img src="/images/로그인배경.jpg" alt="배경 이미지" class="background_image_img">
    </div>

    <!-- 회원가입 완료 박스 -->
    <div class="completion-box">
        <!-- 회원가입 완료 이미지 -->
        <img src="/images/회원가입 완료.png" alt="회원탈퇴 완료">
        <!-- 완료 메시지 -->
        <div class="completion-message">회원탈퇴 완료되었습니다.</div>
         <div class="button-container">
            <a href="/" class="btn">홈으로</a>
            <a href="/jh/loginForm" class="btn">로그인</a>
        </div>
    </div>
</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>