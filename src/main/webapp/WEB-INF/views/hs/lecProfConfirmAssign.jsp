<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!-- 임시로 fn 제작 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생과제제출물(교수)</title>
<style type="text/css">
/* 메인 콘텐츠 스타일 */
    .main {
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    h1 {
        color: #134b84;
        font-size: 28px;
        margin-bottom: 20px;
    }

    /* 과제 카드 스타일 */
    .assign-card {
        margin-bottom: 30px;
        padding: 20px;
        background-color: # fdfdfd;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .assign-info {
        font-size: 18px;
        color: #134b84;
        font-weight: bold;
        margin-bottom: 20px;
    }

    /* 학생 리스트 테이블 스타일 */
    .student-table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 30px;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .student-table th, .student-table td {
        padding: 12px;
        text-align: center;
        border: 1px solid #ddd;
    }

    .student-table th {
        background-color: #134b84;
        color: white;
        font-weight: bold;
    }

    .student-table td {
        background-color: #fdfdfd;
    }

    .student-table tr:nth-child(even) td {
        background-color: #f5f5f5;
    }

</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제 확인</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%-- lctr_num 가져오기 --%>
			<c:set var="lctrNum" value="${lctr.lctr_num}" />
			
			<%-- 앞에서 6번째 자리를 추출 (인덱스 5) --%>
			<c:set var="sixthFromStart" value="${fn:substring(lctrNum, 5, 6)}" />
			
			<%-- 숫자로 변환 --%>
			<c:set var="sixthFromStartNum" value="${sixthFromStart}" />

			<%-- 조건에 따라 사이드바 파일을 동적으로 설정 --%>
			<c:choose>
			    <c:when test="${sixthFromStartNum >= 1 and sixthFromStartNum <= 4}">
			        <!-- 온라인 강의일 경우 -->
			        <%@ include file="../se/sidebarOn.jsp" %>
			    </c:when>
			    <c:when test="${sixthFromStartNum >= 5 and sixthFromStartNum <= 9}">
			        <!-- 오프라인 강의일 경우 -->
			        <%@ include file="../sidebarLctr.jsp" %>
			    </c:when>
			</c:choose>
		</div>
		<div class="main">
			<h1>학생과제제출물(교수)</h1>
			<c:forEach var="AssignAbmsn" items="${AssignAbmsn }">
			<div class="assign-card">
				<p class="assign-info">${AssignAbmsn.cycl }차 제출인원: ${AssignAbmsn.asmtSubCount }명</p>
				<c:forEach var="student" items="${AssignAbmsn.studentList }">
				<table class="student-table">
					<tr>
						<th>학번</th>
						<th>이름</th>
						<th>상세보기</th>
					</tr>
					<tr>
						<td>${student.unq_num }</td>
						<td>${student.stdnt_nm }</td>
						<td><button class="btn btn-outline-dark btn-sm" onclick="location.href='/hs/lecProfConfirmAssDetail?lctr_num=${student.lctr_num}&cycl=${student.cycl }&unq_num=${student.unq_num }'">보기</button> </td>
					</tr>
				</table>
				</c:forEach>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>