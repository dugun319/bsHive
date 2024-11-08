<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의계획서</title>
<style type="text/css">
	
	/* 강의기본정보 table */
	.leclecInfo table td {
	
	}
	
	/* 강의 계획 table */
	.lecSched table td {
	
	}
	
	/* 주차별 강의 계획 table */
	.lctrWeek table {
    width: 100%; /* 테이블 너비를 100%로 설정 */
    border-collapse: collapse; /* 테두리 겹침 제거 */
	}

	.lctrWeek th {
    	background-color: #999999; /* 헤더 배경색 */
		color: #fdfdfd; /* 헤더 글자색 */
		padding: 10px; /* 패딩 추가 */
	}

	.lctrWeek td {
		padding: 10px; /* 패딩 추가 */
		color: #323232; /* 데이터 셀 글자색 */
	}

	/* 홀수, 짝수 행에 따른 배경색 설정 */
	.lctrWeek tr:nth-child(odd) td {
		background-color: #ffffff; /* 홀수행 배경색 */
	}

	.lctrWeek tr:nth-child(even) td {
		background-color: #fdfdfd; /* 짝수행 배경색 */
	}
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">강의계획</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>강의계획서</h1>
			
			<h3>[강의기본정보]</h3>
			<c:forEach var="hsLec" items="${hsLec }">
			<table class="lecInfo">
					<tr>
						<th>강의명</th>
						<td>${hsLec.lctr_name }</td>
						<th>강사명</th>
						<td >${hsLec.emp_nm }</td>
					</tr>
					<tr>
						<th>강의 기간</th>
						<td>${hsLec.bgng_ymd } ~ ${hsLec.end_ymd }</td>
						<th>강의 시간</th>
						<td>${hsLec.content }요일 ${hsLec.bgng_tm } ~ ${hsLec.end_tm }</td>
					</tr>
					<tr>
						<th>모집 정원</th>
						<td>${hsLec.pscp_nope }명</td>
						<th>수강료</th>
						<td>${hsLec.fnsh_cost } 원</td>
					</tr>
			</table>
			
			<br><br>
			
			<h3>[강의계획]</h4>
			<table class="lecSched">
				<tr>
					<th>강의개요</th>
					<td>${hsLec.lctr_otln }</td>
				</tr>
				<tr>
					<th>교육목적</th>
					<td>${hsLec.edu_prps }</td>
				</tr>
				<tr>
					<th>교육내용</th>
					<td>${hsLec.edu_cn }</td>
				</tr>
				<tr>
					<th>평가방법</th>
					<td>${hsLec.evl_mthd }</td>
				</tr>
				<tr>
					<th>참고자료내용</th>
					<td>${hsLec.ref_data_cn }</td>
				</tr>
				<tr>
					<th>특이사항</th>
					<td>${hsLec.excptn_mttr }</td>
				</tr>
			</table>
			</c:forEach>
			
			<br><br>
			
			<h1>주차별 강의 계획</h1>
			<table class="lctrWeek">
				<tr>
					<th>주</th>
					<th>강의계획</th>
					<th>수업자료</th>
					<th>강의실</th>
				</tr>
				<c:forEach var="lctrWeek" items="${lctrWeek }">
					<tr>
						<td style="text-align: center;">${lctrWeek.lctr_weeks }주차 <br>(${lctrWeek.lctr_ymd })</td>
						<td>${lctrWeek.lctr_plan }</td>
						<td style="text-align: center;">${lctrWeek.file_group }</td>
						<td style="text-align: center;">${lctrWeek.lctrm_num }</td>
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
