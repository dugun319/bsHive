<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jh_jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/jh_mypage.css">

</head>
<body>

<header class="header">
    <h1>헤더</h1>
</header>

<div class="body">
    <div class="title">마이페이지</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <div class="active">마이페이지</div>
            <div>회원정보변경</div>
            <div>비밀번호 변경</div>
            <div>수강신청현황</div>
            <div>나의 강의실</div>
            <div>내가 등록한 글</div>
            <div>회원탈퇴</div>
        </div>

        <div class="content">
        	<form action="/pw_chk" method="get">
	            <div class="content-box" onclick="this.closest('form').submit();">
	        		<img src="/images/회원정보변경.png" alt="회원정보변경" class="content-image">
	        		<p>회원정보변경</p>
	    		</div>
    		</form>
    		
    		<form action="/your-controller-url" method="POST">
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/비밀번호변경.png" alt="비밀번호변경" class="content-image">
        			<p>비밀번호 변경</p>
    			</div>
    		</form>
    		
    		<form action="/your-controller-url" method="POST">
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/수강신청현황.png" alt="수강신청현황" class="content-image">
        			<p>수강신청현황</p>
    			</div>   
    		</form>
    		
    		<form action="/your-controller-url" method="POST">
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/나의 강의실.png" alt="나의 강의실" class="content-image">
        			<p>나의 강의실</p>
    			</div> 
    		</form>	
    		
    		<form action="/your-controller-url" method="POST">
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/내가 등록한 글.png" alt="내가 등록한 글" class="content-image">
        			<p>내가 등록한 글</p>
    			</div> 	
    		</form>	
        </div>
    </div>
</div>

<footer class="footer">
        &copy; 2024 MyPage Corp. All rights reserved.
</footer>




	
</body>
</html>