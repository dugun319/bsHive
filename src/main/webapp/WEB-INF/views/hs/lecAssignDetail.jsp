<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제상세</title>
<style type="text/css">

</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제상세</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>과제상세</h1>
			<input type="hidden" name="lctr_num" value="${hsAssignWrite.lctr_num }">
			<table>
				<tr>
					<th>차수</th>
					<td>${hsAssignWrite.cycl }</td>
					<th>강의명</th>
					<td>${hsAssignWrite.lctr_name }</td>
				</tr>
				<tr>
					<th>교수명</th>
					<td colspan="3">${hsAssignWrite.emp_nm }</td>
				</tr>
				<tr>
					<th>주제</th>
					<td colspan="3">${hsAssignWrite.asmt_tpc }</td>
				</tr>
				<tr>
					<th>상세내용</th>
					<td colspan="3">${hsAssignWrite.asmt_dtl_cn }</td>
				</tr>
				<tr>
					<th>제출마감일</th>
					<td colspan="3">${hsAssignWrite.asmt_ddln }</td>
				</tr>
				<tr>
					<th><label for="file">참고문서</label></th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<button class="btn btn-outline-secondary" onclick="location.href='/hs/lecAssignList?lctr_num=${hsAssignWrite.lctr_num}'">목록</button>
						<button class="btn btn-outline-primary" onclick="location.href='/hs/lecAssignUpdate?lctr_num=${hsAssignWrite.lctr_num}&cycl=${hsAssignWrite.cycl }'">수정</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>