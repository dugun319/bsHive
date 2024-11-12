<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의계획서</title>
<style type="text/css">
	/* 메인 콘텐츠 영역 */
	.main {
	    background-color: #fff;
	    padding: 30px;
	    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	    margin-top: 20px;
	}
	
	/* 섹션 제목 스타일 */
	h1 {
	    font-size: 28px;
	    color: #134b84;
	    border-bottom: 2px solid #134b84;
	    padding-bottom: 15px;
	    margin-bottom: 20px;
	}
	
	h3 {
	    font-size: 22px;
	    color: #134b84;
	    margin-top: 50px;
	}
		
	/* 공통 테이블 헤더 스타일 */
	th {
	    background-color: #134b84;   /* 배경색 */
	    color: white;                 /* 글자색 */
	    font-weight: normal;          /* 글자 두께 */
	    text-align: center;             /* 텍스트 왼쪽 정렬 */
	    padding: 12px;                /* 내부 여백 */
	    font-size: 16px;              /* 폰트 크기 */
	    height: 50px;                 /* 헤더 높이 */
	    width: 180px;
	}
	
	/* 강의기본정보 table */
	.lecInfo {
	    width: 100%;
	    margin-bottom: 30px;
	    border-collapse: collapse;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	    background-color: #fafafa;
	    text-align: center;
	}
	
	.lecInfo td {
	    padding: 12px;
	    text-align: left !important;
	    background-color: #fff;
	    color: #333;
	}

	
	/* 강의 계획 table */
	.lecSched {
	    width: 100%;
	    border-collapse: collapse;
	    margin-bottom: 30px;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	    background-color: #fafafa;
	    text-align: center;
	}
	
	.lecSched td {
	    padding: 15px;
	    background-color: #fff;
	    color: #333;
	    text-align: left !important;
	    
	    
	}
	
	/* 주차별 강의 계획 table */
	.lctrWeek {
	    width: 100%;
	    margin-top: 30px;
	    border-collapse: collapse;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	    background-color: #fafafa;
	}

	.lctrWeek th {
    	background-color: #134b84;
	    color: white;
	    text-align: center;
	    padding: 12px;
	}

	.lctrWeek td {
		padding: 12px;
	    text-align: left;
	    background-color: #fff;
	    color: #333;
	}

	/* 홀수, 짝수 행에 따른 배경색 설정 */
	.lctrWeek tr:nth-child(odd) td {
		background-color: #ffffff; /* 홀수행 배경색 */
	}

	.lctrWeek tr:nth-child(even) td {
		background-color: #fdfdfd; /* 짝수행 배경색 */
	}
	
	/* 셀의 텍스트 가운데 정렬 */
	.lctrWeek td, .lecSched td {
	    text-align: center;
	}
	
	/* 강의주차 셀 텍스트 스타일 */
	.lctrWeek td {
	    font-size: 14px;
	    font-weight: bold;
	}
	
	/* 세부 사항 텍스트 스타일 */
	.lecSched td, .lctrWeek td {
	    font-size: 14px;
	    color: #666;
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
					<th><label for="file">수업자료</label></th>
					<th>강의실</th>
				</tr>
				<c:forEach var="lctrWeek" items="${lctrWeek }">
					<tr>
						<td style="text-align: center;">${lctrWeek.lctr_weeks }주차 <br>(${lctrWeek.lctr_ymd })</td>
						<td>${lctrWeek.lctr_plan }</td>
						<div class="form-group">
							<td>
								<div>
			            			<c:forEach var="filePath" items="${filePaths}">
			                			<a download="${filePath.dwnld_file_nm}" href="download?filePath=${filePath.file_path_nm}" type="media_type">
			                   				${filePath.dwnld_file_nm}
			        	       			</a>
			                			<br>
			            			</c:forEach>
			        			</div>
							</td>
						</div>
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
