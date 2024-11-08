<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 임시로 fn 제작 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생과제제출물(교수)</title>
<style type="text/css">

</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">학생 과제 확인</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>출결확인</h1>
				<c:if test="${not empty board.filegroup}">
                        <c:forEach var="filePath" items="${fn:split(View.file_group, ',')}">
                    <c:set var="fileName" value="${fn:substringAfter(filePath, '')}" />
                            <a download="${fileName }" href="download?filePath=${filePath.trim()}" type="media_type">
                                ${fileName} 다운로드 </a>
                            <br>
                        </c:forEach>
                </c:if>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>