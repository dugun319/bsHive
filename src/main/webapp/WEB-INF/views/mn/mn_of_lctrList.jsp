<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/css/of_lctrList.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강신청리스트</title>
</head>
<header>
        <%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>수강신청 리스트</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">수강신청</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="lctrList_main_container">
		<div class="lctrList_main_grid_container">
			<div class="lctrList_main_sidebar">
				<%@ include file="../sidebarLctr.jsp" %>
			</div>
			<div class="lctrList_main_content">
				<div class="lctrList_main_search">
					<input type="text" class="keyword" id="keyword" placeholder="검색어를 입력해주세요.">
					<button onclick="searchList()" class="lctrList_main_search_but"><img alt="" src="" class="lctrList_main_search_butimg"></button>
				</div>
				<table class="lctrList_main_table">
					<tr class="tr_1">
						<th class="col_1">번호</th>
						<th class="col_2">프로그램명</th>
						<th class="col_3">교육기간</th>
						<th class="col_4">수강신청마감</th>
						<th class="col_5">수강신청</th>
					</tr>
					<c:forEach var="lctr" items="${lctrList }">
						<tr class="tr_2">
							<td>
							</td>
							<td>
								<div class="lctrList_main_td1">${lctr.lctr_name }</div>
								<div class="lctrList_main_td2">${lctr.sbjct_nm }</div>
							</td>
							<td>
								<div class="lctrList_main_td1">${lctr.bgng_ymd }&nbsp;~&nbsp;${lctr.end_ymd }</div>
							</td>
							<td>
								<div class="lctrList_main_td1">${lctr.end_date }</div>
							</td>
							<td>
								<button type="button">수강신청</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>