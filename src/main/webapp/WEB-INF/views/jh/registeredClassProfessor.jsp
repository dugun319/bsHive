<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jh_myClassroom.css">
</head>
<body>

<div class="body">
    <div class="title">강의신청현황</div>
    <div class="main-container"> 
        <div class="list">
            <%@ include file="myPageSideBar.jsp" %>
        </div>

        <div class="content">
		    <div class="block_1">
    			<h2>강의신청현황</h2><a href="////" class="regisClass">강의개설 신청</a>
			    <table class="border">
			        <thead>
						<tr>
							<th>순번</th>
						    <th>강의번호</th>
						    <th>강의명</th>
						    <th>정원인원수</th>
						    <th>강의상태</th>
						</tr>
			        </thead>
			        <tbody>
			             <c:forEach var="regClaProf" items="${regClaProf}" varStatus="status">
				            <tr>
				                <td>${status.index + 1}</td> 
				                <td>${regClaProf.LCTR_NUM}</td> 
				               	<td>
									<c:choose>
								        <c:when test="${fn:length(regClaProf.LCTR_NAME) > 15}">
								            ${fn:substring(regClaProf.LCTR_NAME, 0, 15)}...
								        </c:when>
								        <c:otherwise>
								            ${regClaProf.LCTR_NAME}
								        </c:otherwise>
								    </c:choose>
								</td>
				                <td>${regClaProf.PSCP_NOPE}</td>
				                <td>${regClaProf.APLY_TYPE}</td> 
				            </tr>
				        </c:forEach>
			        </tbody>
			    </table>
		    </div>
        </div>      
    </div>
</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>