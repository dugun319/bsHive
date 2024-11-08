<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/css/mn_of_lctrList.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강신청리스트</title>
<script type="text/javascript">
	function searchList() {
		var keyword = document.getElementById('keyword').value;
		console.log("keyword >>> " + keyword);
		$.ajax({
			type: 'GET',
			url : '/mn/search_lctrList',
			data: {keyword:keyword},
			success: function(data) {
				window.location.href = '/mn/search_lctrList?keyword=' + encodeURIComponent(keyword);
			},
			error: function(err) {
				console.log('오류 발생 : ', err);
			}
		})
	}
	
	let isFiltered = false;
	
	function goToLink(url) {
		window.location.href = url;
	}
</script>
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
					<div class="lctrList_main_search_searchbut">
						<input type="text" class="keyword" id="keyword" placeholder="검색어를 입력해주세요.">
						<button onclick="searchList()" class="lctrList_main_search_but">검색<img alt="" src="" class="lctrList_main_search_butimg"></button>
					</div>
					<c:if test="${sessionScope.user.mbr_se == 2 }">
						<div class="lctrList_main_insert">
							<button onclick="goToLink('/mn/insertformlctr')">글작성하기</button>
						</div>
					</c:if>
				</div>
				
				<c:if test="${empty lctrList}">
				    <p>등록된 프로그램이 없습니다.</p>
				</c:if>
				
				<table class="lctrList_main_table">
				    <tr class="tr_1">
				        <th class="col_1">번호</th>
				        <th class="col_2">프로그램명</th>
				        <th class="col_3">교육기간</th>
				        <th class="col_4">수강신청마감</th>
				        <th class="col_5">수강신청</th>
				    </tr>
				
				    <!-- 페이징 처리 -->
				    <c:set var="remainingCount" value="${listCot}" />
				
				    <!-- 항목 번호 매기기 -->
				    <c:forEach var="lctr" items="${lctrList}" varStatus="status">
				        <tr class="tr_2">
				            <td onclick="goToLink('/mn/mn_lctrInfo?lctr_num=${lctr.lctr_num}')">
				                <div class="remainingCount">${(listCot - (currentPage - 1) * 10 - status.index)}</div>
				            </td>
				            <td onclick="goToLink('/mn/mn_lctrInfo?lctr_num=${lctr.lctr_num}')">
				                <div class="lctrList_main_td1">${lctr.lctr_name}</div>
				            </td>
				            <td onclick="goToLink('/mn/mn_lctrInfo?lctr_num=${lctr.lctr_num}')">
				                <div class="lctrList_main_td1">${lctr.bgng_ymd} ~ ${lctr.end_ymd}</div>
				            </td>
				            <td onclick="goToLink('/mn/mn_lctrInfo?lctr_num=${lctr.lctr_num}')">
				                <div class="lctrList_main_td1">${lctr.end_date}</div>
				            </td>
				            <td>
				                <button type="button" onclick="goToLink('/mn/mn_CourseregPage?lctr_num=${lctr.lctr_num}')">수강신청</button>
				            </td>
				        </tr>
				    </c:forEach>
				</table>
				
				<!-- Pagination -->
				<div class="manager_pagination">
				    <a class="manager_pagination_a <c:if test='${currentPage == 1}'>disabled</c:if>" href="<c:if test='${currentPage > 1}'>?pageNum=${currentPage - 1}</c:if>" title="Previous Page">
				        <div class="manager_pagination_a_back">이전</div>
				    </a>
				
				    <c:set var="startPage" value="${currentPage - 2}" />
				    <c:set var="endPage" value="${currentPage + 2}" />
				
				    <!-- startPage가 1보다 작은 경우 1로 설정 -->
				    <c:if test="${startPage < 1}">
				        <c:set var="startPage" value="1" />
				    </c:if>
				
				    <!-- endPage가 totalPages를 초과하는 경우 totalPages로 설정 -->
				    <c:if test="${endPage > totalPages}">
				        <c:set var="endPage" value="${totalPages}" />
				    </c:if>
				
				    <!-- startPage가 endPage보다 큰 경우 startPage를 endPage로 설정 -->
				    <c:if test="${startPage > endPage}">
				        <c:set var="startPage" value="${endPage}" />
				    </c:if>
				
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <c:choose>
				            <c:when test="${i == currentPage}">
				                <span class="manager_pagination_current">${i}</span>
				            </c:when>
				            <c:otherwise>
				                <a class="manager_pagination_a" href="?pageNum=${i}">${i}</a>
				            </c:otherwise>
				        </c:choose>
				    </c:forEach>
				
				    <a class="manager_pagination_a <c:if test='${currentPage == totalPages}'>disabled</c:if>" href="<c:if test='${currentPage < totalPages}'>?pageNum=${currentPage + 1}</c:if>" title="Next Page">
				        <div class="manager_pagination_a_next">다음</div>
				    </a>
				</div>

			</div>
		</div>
	</div>
	
	<footer>
		<%@ include file="../footer.jsp" %>
	</footer>
</body>
</html>