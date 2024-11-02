<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/sidebar.css">

</head>
<body>

<header class="header">
    <h1>헤더</h1>
</header>

<div class="body">
    <div class="title">나의 강의실</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <a href="/jh/myPage"><div>마이페이지</div></a>
            <div>회원정보변경</div>
            <div>비밀번호 변경</div>
            <div>수강신청현황</div>
            <div class="active">나의 강의실</div>
            <div>내가 등록한 글</div>
            <div>회원탈퇴</div>
        </div>

        <div class="content">
		   
		   
		   <!-- 받은 쪽지함 섹션 -->
		    <div class="block_1">
		    <hr/>
		        <h2>강의목록</h2>
		        <table class="border">
		           
		           
		        </table>
		    </div>
		    
        </div>
    </div>
</div>

<footer class="footer">
        &copy; 2024 MyPage Corp. All rights reserved.
</footer>




	
</body>
</html>