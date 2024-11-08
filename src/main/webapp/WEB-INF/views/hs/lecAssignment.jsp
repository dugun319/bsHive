<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제제출</title>
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
	<form action="AssignInsert" method="post" enctype="multipart/form-data">
		<h1>과제제출</h1>
		<input type="hidden" name="lctr_num" value="${hsAssignWrite.lctr_num }">
		<input type="hidden" name="cycl" value="${hsAssignWrite.cycl }">
		<table>
			<tr>
				<th>차수</th>
				<td>${hsAssignWrite.cycl }차</td>
				<th>강의명</th>
				<td>${hsAssignWrite.lctr_name }</td>
			</tr>
			<tr>
				<th>교수명</th>
				<td>${hsAssignWrite.emp_nm }</td>
				<th>제출마감일</th>
				<td>${hsAssignWrite.asmt_ddln }</td>
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
				<th><label for="file">참고문서</label></th>
				<td colspan="3"></td>
			</tr>
		</table>
		<table>
			<tr>
				<th>이름</th>
				<td colspan="3">${hsAssignWrite.unq_num } ${hsAssignWrite.stdnt_nm }</td>
			</tr>
			<tr>
				<th>제출 내용</th>
				<td><textarea class="form-control" aria-label="With textarea" name="crans_cnt" id="floatingTextarea" style="height: 100px;"></textarea> </td>
			</tr>
			<div class="form-group">
				<tr>
					<th><label for="file">파일</label></th>
					<td><input class="form-control-file" id="file" name="file" type="file" multiple></td>
				</tr>
			</div>
			<tr>
				<td colspan="4">※ 제출 완료 후에는 수정 불가하오니 신중히 검토 후 제출해주시기 바랍니다.</td>
			</tr>
		</table>
        <button type="submit" class="btn btn-outline-primary">제출</button>
	</form>
	</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>