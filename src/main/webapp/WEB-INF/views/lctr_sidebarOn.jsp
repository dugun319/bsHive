<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이드바 내강의실</title>
<link rel="stylesheet" type="text/css" href="/css/lctr_sidebar_On.css">
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
            <a href="/se/lctrViewList1"><div class="<%= request.getRequestURI().equals("/se/lctrViewList") ? "active" : "" %>">강의목록</div></a>
        <%-- </c:if>
        
        <c:if test="${lgn.unq_num.startWith('2') }"> --%>
            <%-- <a href="/hs/lecProfAttend"><div class="<%= request.getRequestURI().equals("/hs/lecProfAttend") ? "active" : "" %>">출석입력(교수용)</div></a>
        <%-- </c:if> --%>
        </div>
    </div>
</div>
</body>
</html>