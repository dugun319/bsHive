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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
body{
	margin: 0;
	padding: 0;
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
}

#semester{
	position: absolute;
	top: 180px;
	left: 70px;
	font-size: 20px;
	font-weight: 700;
}

#div_name{
	position: absolute;
	top: 180px;
	left: 720px;
	font-size: 20px;
	font-weight: 700;
}

#basicInfo{
	position: absolute;
	top: 230px;
	left: 55px;
	font-size: 20px;
	font-weight: 700;
}

/*
#basicInfo table, th, td{
	border: 1px solid black;
	border-collapse: collapse;
}
*/

#cell1{
	width: 160px;
	height: 65px;
	
}

#cell2{
	width: 460px;
	font-size: 24px;
	font-weight: 700;
	padding-left: 20px;
}

#cell3{
	width: 150px;
}

#cell4{
	width: 150px;
	font-size: 24px;
	font-weight: 700;
	padding-left: 20px;
}


</style>
</head>
<body>
	<div id="fullBody">
		<img	src="<%=request.getContextPath()%>/images/main/kh_syllabus.png"
				width="1050px" 
				height="1485px">
	</div>
	
	
</body>
</html>