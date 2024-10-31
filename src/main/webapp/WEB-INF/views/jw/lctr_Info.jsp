<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="lctrList">
		<h3>강의정보</h3>
		<table>
			<tbody>
				<c:forEach var="lctr" items="${ listOnlnLct }">
					${ lctr.lctr_NM}
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>