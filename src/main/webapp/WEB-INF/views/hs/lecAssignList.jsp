<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제목록</title>
<style type="text/css">
/* 메인 콘텐츠 영역 이후 스타일 */
    .main {
        width: 100%;
        background-color: #fff;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    /* 과제 목록 제목 스타일 */
    h1 {
        font-size: 28px;
        color: #134b84;
        border-bottom: 2px solid #134b84;
        padding-bottom: 15px;
        margin-bottom: 20px;
    }

    /* 테이블 스타일 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th {
        background-color: #134b84;
        color: white;
        font-weight: normal;
        text-align: center !important;
        padding: 12px;
        font-size: 16px;
    }

    td {
        padding: 12px;
        text-align: center;
        background-color: #fff;
        color: #333;
        font-size: 14px;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #f1f1f1;
        cursor: pointer;
        font-weight: bold;
    }

    /* + 과제추가 버튼 스타일 */
    .btn {
        padding: 10px 20px;
        font-size: 14px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #134b84;
        color: white;
        transition: background-color 0.3s ease;
        margin-top: 20px;
    }

    .btn:hover {
        background-color: #0c3b67;
        
    }
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
			<h1>과제목록</h1>
			<button class="btn btn-outline-secondary btn-sm" onclick="location.href='/hs/lecAssignWrite?lctr_num=${lctr.lctr_num}'">+과제추가</button>
			<table>
				<tr>
					<th>차수</th>
					<th>강의명</th>
					<th>과제명</th>
					<th>마감일자</th>
				</tr>
				<c:forEach var="asmtList" items="${asmtList }">
					<tr onclick="location.href='/hs/lecAssignDetail?lctr_num=${asmtList.lctr_num }&cycl=${asmtList.cycl }';">
						<td>${asmtList.cycl }차시</td>
						<td>${asmtList.lctr_name }</td>
						<td>${asmtList.asmt_tpc }</td>
						<td>${asmtList.asmt_ddln }</td>
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