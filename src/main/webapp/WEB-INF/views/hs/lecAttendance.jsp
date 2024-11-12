<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석확인</title>
<style type="text/css">
	/* 메인 콘텐츠 영역 */
	.main {
	    width: 70%;
	    background-color: #fff;
	    padding: 30px;
	    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	}
	
	/* 섹션 제목 스타일 */
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
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">출결조회</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>출결조회</h1>
			<table>
				<tr>
					<th>주차</th>
					<th>출결상태</th>
				</tr>
				<c:forEach var="atndc_state" items="${atndc_state }">
					<tr>
						<td>${atndc_state.lctr_weeks }주차</td>
						<td>
							<c:choose>
					        	<c:when test="${atndc_state.atndc_type == 100}">
					            	<span style="color: blue;">
					                	<c:out value="${atndc_state.content}" />
					                </span>
					            </c:when>
					            <c:when test="${atndc_state.atndc_type == 110}">
					                <span style="color: orange;">
					                	<c:out value="${atndc_state.content}" />
					       			</span>
					           	</c:when>
					           	<c:when test="${atndc_state.atndc_type == 120}">
					                <span style="color: red;">
					                	<c:out value="${atndc_state.content}" />
					       			</span>
					           	</c:when>
					            <c:otherwise>
					            	<span style="color: gray;">
					                	<c:out value="${atndc_state.content}" />
					            	</span>
					            </c:otherwise>
							</c:choose>
						</td>
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