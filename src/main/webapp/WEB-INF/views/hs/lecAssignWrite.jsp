<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제입력(Prof)</title>
<style type="text/css">

</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제입력</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<form action="profAsmtWrite" method="post" enctype="multipart/form-data">
		<div class="main">
			<h1>과제입력</h1>
			<input type="hidden" name="lctr_num" value="${hsAssignWrite.lctr_num }">
			<table>
				<tr>
					<th>차수</th>
					<td>
						<div class="form-floating">
							<input type="number" name="cycl" class="form-control" placeholder="숫자로만 입력하세요" id="floatingInput" required="required">
							<label for="floatingInput" style="color: grey;">숫자로 입력</label>
						</div>
					</td>
					<th>강의명</th>
					<td>${hsAssignWrite.lctr_name }</td>
				</tr>
				<tr>
					<th>교수명</th>
					<td colspan="3">${hsAssignWrite.emp_nm }</td>
				</tr>
				<tr>
					<th>주제</th>
					<td colspan="3">
						<div class="form-floating">
	  						<input type="text" name="asmt_tpc" class="form-control" id="floatingInput" placeholder="과제주제를 입력하세요" required="required">
	  						<label for="floatingInput" style="color: grey;">과제 주제</label>
	  					</div>
					</td>
				</tr>
				<tr>
					<th>상세내용</th>
					<td colspan="3">
						<div class="form-floating">
							<textarea class="form-control" aria-label="With textarea" name="asmt_dtl_cn" id="floatingTextarea" required="required" style="height: 100px;"></textarea>
							<label for="floatingTextarea">과제 관련해 자세히 적어주세요</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>제출마감일</th>
					<td colspan="3">
						<input type="date" name="asmt_ddln" class="form-control" required="required">
					</td>
				</tr>
				<tr>
					<th><label for="file">참고문서</label></th>
					<td colspan="3">
                		<input type="file" class="form-control" id="file" name="file" multiple>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<button type="submit" class="btn btn-outline-primary">등록</button>
					</td>
				</tr>
			</table>
		</div>
		</form>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>