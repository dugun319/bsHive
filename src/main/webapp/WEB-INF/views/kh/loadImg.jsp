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
<script type="text/javascript">
	function close() {
		alert("창을 닫습니다");
		window.close();
	}

</script>


<style type="text/css">
body{
	margin: 0;
	padding: 0;
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
}

#buttonDiv {
	width: 700px;
	text-align: center;
}

#buttonDiv button{
	width: 100px;
	height: 30px;
	font-size: 12px;
	font-weight: 700;
	background-color: #999999;
	color: #323232;
}

#buttonDiv button:hover{
	width: 105px;
	height: 30px;
	font-size: 12px;
	font-weight: 700;
	color: #fdfdfd;
	background-color: #134b84;
}


</style>
</head>
<body>
	<div id="fullBody">
		<img	src="<%=request.getContextPath()%>${filePath}"
				width="700px" 
				height="900px">
	</div>
	<div id="buttonDiv">
		<button type="button" onclick="close()">
			확인
		</button>
	</div>
	
</body>
</html>