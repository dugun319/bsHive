<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS</title>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<br><br><br><br><br><br><br><br><br>
	<div>모집중인 강의수 : ${onlnTotal}</div>
	<br><br><br>
	<table>
		<tr>
			<td>강의번호</td>
			<td>강의명</td>
			<td>학과명</td>
			<td>강의상태</td>
			<td>개설일</td>
			<td>정원인원수</td>
		</tr>
		<c:forEach items="${onlnList}" var="Lctr">
			<tr>	
				<td>${Lctr.lctr_num}</td>
				<td onclick="location.href='/se/onlnDtl?Lctr_Num=${Lctr.lctr_num}'" style="cursor: pointer;">${Lctr.lctr_name}</td>	
				<td>${Lctr.sbjct_nm}</td>
				<td>${Lctr.aply_type}</td>
				<td>${Lctr.aply_ydm}</td>
				<td>${Lctr.pscp_nope}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>