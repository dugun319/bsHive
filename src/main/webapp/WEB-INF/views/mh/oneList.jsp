<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@ include file="sidebarPst.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

			<table class="border">
				<form action="oneWrite">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성일</td>
					<td>작성자</td>
				</tr>
				<c:set var="num" value="1"></c:set>
				<c:forEach items="${listOne }" var="board">
					<tr onclick="location.href='/mh/oneView?pst_num=${board.pst_num}'"
						style="cursor: pointer;">
						<td>${num}</td>
						<td>${board.pst_ttl }</td>
						<td>${board.wrt_ymd }</td>
						<td>${board.stdnt_nm }</td>
					</tr>
					<c:set var="num" value="${num + 1}" />
				</c:forEach>
				
			</table>
					<table id="paging">

				<c:if test="${page.startPage > page.pageBlock }">
					<a
						href="oneList?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<a href="oneList?currentPage=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage }">
					<a
						href="oneList?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
				</c:if>

        </table>
			<input type="submit" value="글작성"></form>
		</div>

	</div>


</body>
</html>