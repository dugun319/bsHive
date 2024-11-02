<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>온라인 강의 조회</title>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<br><br><br><br><br><br><br><br><br>
	<table>
		<tr>
			<td>강의명</td><td>${onlnDtl.lctr_name}
		</tr>
		<tr>
			<td>강의설명</td><td>${onlnDtl.lctr_expln}</td>
		</tr>
		<tr>	
			<td>강의자료</td><td>${onlnDtl.lctr_data}</td>
		</tr>
		<tr>	
			<td>시작일</td><td>${onlnDtl.bgng_ymd}</td>
		</tr>
		<tr>
			<td>종료일</td><td>${onlnDtl.end_ymd}</td>
		</tr>
		<tr>
			<td>모집인원수</td><td>${onlnDtl.rcrt_nope}</td>
		<tr>	
			<td>수료기준</td><td>${onlnDtl.fnsh_crtr}</td>
		</tr>	
	</table>
	<br><br><br>
	<button>수강신청</button><button onclick="window.history.back()">뒤로가기</button>

</body>
</html>