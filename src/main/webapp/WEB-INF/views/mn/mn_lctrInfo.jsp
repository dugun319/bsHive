<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의계획서</title>
<style type="text/css">
	body {
        width: 1920px !important; /* body 폭 설정 */
        margin: 0; /* 기본 여백 제거 */
        padding: 0; /* 기본 패딩 제거 */
        box-sizing: border-box; /* 박스 모델 설정 */
    }
    
	.container1 {
        display: grid;
        grid-template-columns: 410px 1180px; /* 왼쪽 메뉴와 오른쪽 콘텐츠 영역 비율 */
        grid-gap: 15px; /* 좌우 간격 */
        width: 1700px; /* 콘텐츠 영역 폭 */
        margin: 50px auto; /* 가운데 정렬 및 상단 여백 */
    }
    
    .main {
        width: 1180px; /* 오른쪽 콘텐츠 영역 폭 */
    }
	
	a {
		color: black;	/* 글자 색상 설정 */
		text-decoration: none;	/* 밑줄제거 */
	}
	
	a:hover {
		font-weight: bold;
	}
	
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
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
		
			<h1>상세강의정보</h1>
			<c:forEach var="lctr" items="${lctr }">
			<h3>[강의기본정보]</h3>
			<table class="lecInfo">
				<tr>
					<td></td>
					<th>강의명</th>
					<td><div>${lctr.lctr_name }</div></td>
				</tr>
				<tr>
					<th>강사명</th>
					<td>${lctr.emp_nm }</td>
				</tr>
				<tr>
					<th>강의 기간</th>
					<td>${lctr.bgng_ymd } ~ ${lctr.end_ymd }</td>
					<th>강의 시간</th>
					<td>
						<c:choose>
							<c:when test="${lctr.dow_day == 1 }">
								월		
							</c:when>
							<c:when test="${lctr.dow_day == 2 }">
								화
							</c:when>
							<c:when test="${lctr.dow_day == 3 }">
								수
							</c:when>
							<c:when test="${lctr.dow_day == 4 }">
								목
							</c:when>
							<c:otherwise>
								금
							</c:otherwise>
						</c:choose>
						요일 ${lctr.bgng_tm } ~ ${lctr.end_tm }</td>
				</tr>
				<tr>
					<th>모집 정원</th>
					<td>${lctr.pscp_nope }명</td>
					<th>수강료</th>
					<td>${lctr.fnsh_cost } 원</td>
				</tr>
			</table>
			
			<br><br>
			
			<h3>[강의계획]</h4>
			<table class="lecSched">
				<tr>
					<th>강의개요</th>
					<td>${lctr.lctr_otln }</td>
				</tr>
				<tr>
					<th>교육목적</th>
					<td>${lctr.edu_prps }</td>
				</tr>
				<tr>
					<th>교육내용</th>
					<td>${lctr.edu_cn }</td>
				</tr>
				<tr>
					<th>평가방법</th>
					<td>${lctr.evl_mthd }</td>
				</tr>
				<tr>
					<th>참고자료내용</th>
					<td>${lctr.ref_data_cn }</td>
				</tr>
				<tr>
					<th>특이사항</th>
					<td>${lctr.excptn_mttr }</td>
				</tr>
			</table>
			<br><br>
			
			<h1>주차별 강의 계획</h1>
			<table class="lctrWeek">
				<tr>
					<th>주</th>
					<th>강의계획</th>
					<th>수업자료</th>
					<th>강의실</th>
				</tr>
				<c:forEach var="week" items="${week }">
					<tr>
						<td style="text-align: center;">${week.lctr_weeks }주차 <br>(${week.lctr_ymd })</td>
						<td>${week.lctr_plan }</td>
						<td style="text-align: center;">
							<c:choose>
								<c:when test="${week.file_group == null || week.file_group == ''  }">
									없음
								</c:when>
								<c:otherwise>
									<img alt="file_icon" src="<%=request.getContextPath()%>/images/main/파일.png" style="width: 24px; height: auto;">
								</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: center;">${week.lctrm_num }호</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button type="button" onclick="goToLink('/mn/mn_CourseregPage?lctr_num=${lctr.lctr_num}')">수강신청</button>
				<button type="button" value="/">목록으로</button>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>
