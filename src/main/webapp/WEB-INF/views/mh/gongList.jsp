<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@ include file="sidebarPst.jsp"%>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항</h2>
	<table>
				<form action="gongWrite">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성일</td>
				</tr>
				<c:set var="num" value="1"></c:set>
				<c:forEach items="${listGong }" var="board">
					<tr onclick="location.href='/mh/gongView?pst_num=${board.pst_num}'"
						style="cursor: pointer;">
						<td>${num}</td>
						<td>${board.pst_ttl }</td>
						<td>${board.wrt_ymd }</td>
					</tr>
					<c:set var="num" value="${num + 1}" />
				</c:forEach>
				
			</table>
	<table id="paging">

				<c:if test="${page.startPage > page.pageBlock }">
					<a
						href="gongList?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<a href="gongList?currentPage=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage }">
					<a
						href="gongList?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
				</c:if>

        </table>
			<c:if test="${admin == 3 }"><input type="submit" value="글작성"></c:if></form>
			 

</body>
</html>