<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제목록</title>
<style type="text/css">
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>과제목록</h1>
			<button class="btn btn-outline-secondary btn-sm" onclick="location.href='/hs/lecAssignWrite?lctr_num=${lctr.lctr_num}'">+과제추가</button>
			<table>
				<tr>
					<th>차수</th>
					<th>강의명</th>
					<th>과제명</th>
					<th>마감일자</th>
					<th>제출여부</th>
				</tr>
				<c:forEach var="asmtList" items="${asmtList }">
					<tr onclick="location.href='/hs/lecAssignment?lctr_num=${asmtList.lctr_num }&cycl=${asmtList.cycl }';" style="cursor: pointer;">
						<td>${asmtList.cycl }차시</td>
						<td>${asmtList.lctr_name }</td>
						<td>${asmtList.asmt_tpc }</td>
						<td>${asmtList.asmt_ddln }</td>
						<td>
							<c:if test="${not dataPresent}">
	                			<button class="btn btn-outline-primary btn-sm">제출</button>
	            			</c:if>
	            			<c:if test="${dataPresent}">
	                			제출완료
	            			</c:if>
            			<td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>