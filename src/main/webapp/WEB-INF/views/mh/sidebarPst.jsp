<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>
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
    <div class="title">고객 센터</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <a href="/mh/gongList"><div>공지사항</div></a>
            <a href="/mh/yakList"><div>약관</div></a>
            <a href="/mh/fnqList"><div>FAQ</div></a>
            <a href="/mh/gongList"><div>문의</div></a>
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