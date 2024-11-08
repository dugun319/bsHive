<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jh_myPageSideBar.css">
<script type="text/javascript">
    window.onload = function() {
        const links = document.querySelectorAll('.select.button a'); // '.select.button' 클래스의 a 요소를 선택
        const currentPath = window.location.pathname; // 현재 경로 가져오기

        links.forEach(link => {
            if (link.getAttribute('href') === currentPath) {
                link.classList.add('active'); // href가 현재 경로와 일치하면 active 클래스 추가
            }
        });

        // 기존 리스트 링크에 대해서도 active 클래스 적용
        const listLinks = document.querySelectorAll('.body a'); // '.body' 클래스의 a 요소를 선택
        listLinks.forEach(link => {
            if (link.getAttribute('href') === currentPath) {
                link.classList.add('active'); // href가 현재 경로와 일치하면 active 클래스 추가
            }
        });
    };
</script>
</head>
<body>

<div class="body"> 
    <a href="/jh/myPage" class="link-style <%= request.getRequestURI().equals("/jh/myPage") ? "active" : "" %>">마이페이지</a>
    <a href="/jh/pwChk" class="link-style <%= request.getRequestURI().equals("/jh/pwChk") || request.getRequestURI().startsWith("/jh/updateMyInfor") || request.getRequestURI().equals("/jh/realPwChk") ? "active" : "" %>">회원정보변경</a>
    <a href="/jh/changePassword" class="link-style <%= request.getRequestURI().equals("/jh/changePassword") ? "active" : "" %>">비밀번호 변경</a>
    
    <% Boolean myOfflineClass = (Boolean) request.getAttribute("myOfflineClass"); %>
	<% Integer mbr_se = (Integer) session.getAttribute("mbr_se"); %>

	<% if (mbr_se != null) { %>
	    <% if (mbr_se == 1) { %>
	    	<a href="/jh/registeredClassStudent" class="link-style <%= request.getRequestURI().equals("/jh/registeredClassStudent") ? "active" : "" %>">수강신청현황</a>
	        <a href="/jh/myClassroom" class="link-style <%= myOfflineClass != null && myOfflineClass ? "active" : (request.getRequestURI().equals("/jh/myClassroom") ? "active" : "") %>">나의 강의실</a>
	    <% } else if (mbr_se == 2) { %>
	        <a href="/jh/registeredClassProfessor" class="link-style <%= request.getRequestURI().equals("/jh/registeredClassProfessor") ? "active" : "" %>">강의신청현황</a>
	        <a href="/jh/myProfClassroom" class="link-style <%= myOfflineClass != null && myOfflineClass ? "active" : (request.getRequestURI().equals("/jh/myProfClassroom") ? "active" : "") %>">나의 강의실</a>
	    <% } %>
	<% } %>

    <a href="/jh/myPosts" class="link-style <%= request.getRequestURI().equals("/jh/myPosts") ? "active" : "" %>">내가 등록한 글</a>
    <a href="/jh/deleteAccount" class="link-style <%= request.getRequestURI().equals("/jh/deleteAccount") ? "active" : "" %>">회원탈퇴</a>
</div>

</body>
</html>