<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@ include file="adminHeader.jsp"%>
	</header>
	
	<div class="container">
        <div class="left-menu">
        	<%@ include file="tree.jsp"%>
        </div>
        <div class="main-content">
        	<h1>관리자님 환영합니다</h1>
        	<iframe width="1200" height="700" src="https://www.youtube.com/embed/Cv3nEnKeFJY?si=RN7sIYt3oICptjIE" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
        </div>
    </div>
    <footer id="pagingDiv">
    	<div id="paging">
				<c:if test="${page.startPage > page.pageBlock }">
					<a href="/kh/admin/prdocList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}">[Previous]</a>
				</c:if>
				
				<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
					<a href="/kh/admin/prdocList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}">[${i }]</a>
				</c:forEach>
				
				<c:if test="${page.startPage < page.pageBlock }">
					<a href="/kh/admin/prdocList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}">[Next]</a>
				</c:if>
			</div>    
    </footer>
	
	
</body>
</html>