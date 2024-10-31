<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
공지사항 리스트

<table>
<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>
		<c:set var="num" value="1"></c:set>
		<c:forEach items="${listGong }" var="board">		
		<tr onclick="location.href='/mh/GongView?pst_num=${board.pst_num}'" style="cursor: pointer;">
		<td>${num}</td>
		<td>${board.pst_ttl }</td>
		<td>${board.wrt_ymd }</td>
		</tr>
		</c:forEach>
</table>

</body>
</html>