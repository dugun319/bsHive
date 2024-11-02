<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ include file="sidebarPst.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <div class="content">

<form action="fnqWrite">
<table>
<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>
		<c:set var="num" value="1"></c:set>
		<c:forEach items="${listFaq }" var="board">		
		<tr onclick="location.href='/mh/fnqView?pst_num=${board.pst_num}'" style="cursor: pointer;">
		<td>${num}</td>
		<td>${board.pst_ttl }</td>
		<td>${board.wrt_ymd }</td>
		</tr>
		<c:set var="num" value="${num + 1}" />
		</c:forEach>
</table>

    <input type="submit" value="글작성">
    </form>
    </div>
</body>
</html>