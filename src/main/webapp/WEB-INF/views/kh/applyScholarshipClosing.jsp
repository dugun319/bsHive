<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function() {
		alert("전송이 완료되었습니다");
	    const closePopup = true;
	    if (closePopup) {
	    window.close();
		}
	};
</script>
</head>
<body>
	<h1>예상금액은</h1>
	<h1>${schDetail.sprt_cost}</h1>
</body>
</html>