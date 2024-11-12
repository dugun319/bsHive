<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의실 메인페이지</title>
<style type="text/css">
	.main {
		text-align: center;
	}
	.lecimg {
		width: 250px;
	}
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">내 강의실</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<br><br>
			<h1 style="color:#134b84;">Lecture Room</h1>
			<br>
			<h5><span style="color: #daaf67; font-weight: bold;">${lctr.lctr_name }</span>강의에 오신 것을 환영합니다.</h5>
			<h5>유익한 학습하시길 바랍니다.</h5>
			<br>
			<img alt="내강의실이미지" src="<%=request.getContextPath()%>/images/lec_img.png" class="lecimg">
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>