<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제상세</title>
<style type="text/css">
	/* 메인 콘텐츠 영역 이후 스타일 */
    .main {
        width: 70%;
        background-color: #fff;
        padding: 30px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        margin-left: 15%;
    }

    /* 과제상세 제목 스타일 */
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
        width: 180px;
    }

    td {
        padding: 12px;
        text-align: left;
        background-color: #fff;
        color: #333;
        font-size: 14px;
    }

    td[colspan="3"] {
        text-align: left;
        padding-left: 20px;
    }
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
					<td colspan="3">
						<div>
            				<c:forEach var="filePath" items="${filePath}">
                				<a download="${filePath.dwnld_file_nm}" href="download?filePath=${filePath.file_path_nm}" type="media_type">
                   					${filePath.dwnld_file_nm}
                				</a>
                				<br>
            				</c:forEach>
        				</div>
					</td>
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