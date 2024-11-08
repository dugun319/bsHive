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
    <div class="title">수강신청현황</div>
    <div class="main-container"> 
        <div class="list">
            <%@ include file="myPageSideBar.jsp" %>
        </div>

        <div class="content">
		    <div class="block_1">
    			<h2>수강신청현황</h2>
			    <table class="border">
			        <thead>
						<tr>
						    <th>순번</th> <!-- 순번 추가 -->
						    <th>강의번호</th>
						    <th>강의명</th>
						    <th>강사명</th>
						    <th>신청일</th>
						    <th>개설일</th>
						    <th>마감일</th>
						    <th>정원인원수</th>
						    <th>나의고유번호(학생)</th>
						</tr>
			        </thead>
			        <tbody>
			             <c:forEach var="regClaStdt" items="${regClaStdt}" varStatus="status">
				            <tr>
				                <td>${status.index + 1}</td> 
				                <td>${regClaStdt.LCTR_NUM}</td>
				                <td>
									<c:choose>
								        <c:when test="${fn:length(regClaStdt.LCTR_NAME) > 15}">
								            ${fn:substring(regClaStdt.LCTR_NAME, 0, 15)}...
								        </c:when>
								        <c:otherwise>
								            ${regClaStdt.LCTR_NAME}
								        </c:otherwise>
								    </c:choose>
								</td>
				                <td>${regClaStdt.EMP_NM}</td>
				                <td>${regClaStdt.APLY_YMD}</td> 
				                <td>${regClaStdt.APLY_YDM}</td> 
				                <td>${regClaStdt.END_DATE}</td> 
				                <td>${regClaStdt.PSCP_NOPE}</td> 
				                <td>${regClaStdt.UNQ_NUM}</td> 
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