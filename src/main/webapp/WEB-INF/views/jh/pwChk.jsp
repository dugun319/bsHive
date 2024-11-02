<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/jh_myPage.css">
<link rel="stylesheet" type="text/css" href="/css/jh_pwChk.css">

</head>
<body>

<header class="header">
    <h1>헤더</h1>
</header>

<div class="body">
    <div class="title">회원정보변경</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <div>마이페이지</div>
            <div class="active">회원정보변경</div>
            <div>비밀번호 변경</div>
            <div>수강신청현황</div>
            <div>나의 강의실</div>
            <div>내가 등록한 글</div>
            <div>회원탈퇴</div>
        </div>
        
<div class="content">
    <h1>본인확인을 위해 비밀번호를 입력해 주세요.</h1>
    <div class="pw_chk">    
        <form action="/login" method="post" id="loginForm">
            <div class="input-group" id="input-group2">
                <label for="username">아이디</label>
                <input type="text" name="username" id="username" value="ujh1115" readonly>
            </div>
            <div class="input-group">
                <label for="password">비밀번호</label>
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요." required="required">
            </div>
            <div class="button-container"> 
                <input type="submit" value="확인" class="input_submit">
                <input type="button" value="취소" class="input_cancel" onclick="window.location.href='/your-cancel-url';">
            </div>
        </form>
    </div>
</div>






    </div>
    
</div>

<footer class="footer">
        &copy; 2024 MyPage Corp. All rights reserved.
</footer>




	
</body>
</html>