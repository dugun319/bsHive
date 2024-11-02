<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이드바 내강의실</title>
<link rel="stylesheet" type="text/css" href="/css/sidebarLctr.css">
<script type="text/javascript">
	window.onload = function() {
	    const links = document.querySelectorAll('.list a div');
	    const currentPath = window.location.pathname;
	
	    links.forEach(link => {
	        if (link.parentElement.getAttribute('href') === currentPath) {
	            link.classList.add('active');
	        }
	    });
	};
</script>
</head>
<body>
<div class="body">
    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
        <%-- <c:if test="${lgn.unq_num.startWith('1') }"> --%>
            <a href="/hs/lecSchedule"><div class="<%= request.getRequestURI().equals("/hs/lecSchedule") ? "active" : "" %>">강의계획서</div></a>
            <a href="/hs/lecAttendance"><div class="<%= request.getRequestURI().equals("/hs/lecAttendance") ? "active" : "" %>">출석확인</div></a>
            <a href="/hs/lecAssignment"><div class="<%= request.getRequestURI().equals("/hs/lecAssignment") ? "active" : "" %>">과제제출</div></a>
            <a href="/hs/lecTest"><div class="<%= request.getRequestURI().equals("/hs/lecTest") ? "active" : "" %>">시험</div></a>
            <a href="/hb/courseEval"><div class="<%= request.getRequestURI().equals("/courseEval") ? "active" : "" %>">평가</div></a>
            <a href="/hb/showResult"><div class="<%= request.getRequestURI().equals("/showResult") ? "active" : "" %>">성적확인</div></a>
        <%-- </c:if>
        
        <c:if test="${lgn.unq_num.startWith('2') }"> --%>
            <a href="/hs/lecProfAttend"><div class="<%= request.getRequestURI().equals("/hs/lecProfAttend") ? "active" : "" %>">출석입력(교수용)</div></a>
            <a href="/hs/lecAssignWrite"><div class="<%= request.getRequestURI().equals("/hs/lecAssignWrite") ? "active" : "" %>">과제입력(교수)</div></a>
            <a href="/hs/lecProfConfirmAssign"><div class="<%= request.getRequestURI().equals("/hs/lecProfConfirmAssign") ? "active" : "" %>">학생과제제출물(교수)</div></a>
            <a href="/hs/lecTestWrite"><div class="<%= request.getRequestURI().equals("/hs/lecTestWrite") ? "active" : "" %>">시험입력(교수)</div></a>
            <a href="/hb/showFinalResult"><div class="<%= request.getRequestURI().equals("/hs/lecTestRead") ? "active" : "" %>">학생시험결과(교수)</div></a>
            <a href="/hb/lecTestResult"><div class="<%= request.getRequestURI().equals("/hs/lecTestRead") ? "active" : "" %>">성적입력(교수)</div></a>
        <%-- </c:if> --%>
        </div>
    </div>
</div>
</body>
</html>