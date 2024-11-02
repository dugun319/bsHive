<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강의 리스트</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<input type="hidden" name="unq_num"  value="${unq_num}">
	<table>
		<c:forEach items="${onln_Lctr_List}" var="lL">
		<tr>
			<td>차시번호 : ${lL.unit_num}</td>
			<td onclick="location.href='/se/lctrView?conts_id=${lL.conts_id}&vdo_id=${lL.vdo_id}'" style="cursor: pointer;">컨텐츠명 : ${lL.conts_nm}</td>
			<td>컨텐츠ID : ${lL.conts_id}</td>	
			<td>재생시간 : ${lL.play_hr}</td>
			<td>최대시간 : ${lL.max_dtl}</td>
			<td>최종시간 : ${lL.last_dtl}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
