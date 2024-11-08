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
    <div class="title">나의 강의실</div>
    <div class="main-container"> 
        <div class="list">
            <%@ include file="myPageSideBar.jsp" %>
        </div>

        <div class="content">
		    <div class="block_1">
				<div class="select button">
				    <a href="/jh/myProfClassroom" class="${myOnlineClass ? 'active' : ''}">온라인 강의</a>
				    <a href="/jh/myProfOfflineClass" class="${myOfflineClass ? 'active' : ''}">오프라인 강의</a>
				</div>

				<!-- 온라인 강의 불러오기-->
				<c:if test="${!myOfflineClass}"> 
	    			<h2>온라인 강의목록</h2>
				    <table class="border">
				        <thead>
							<tr>
							    <th>순번</th>
							    <th>강의번호</th>
							    <th>강의명</th>
							    <th>강의상태</th>
							    <th>개설일</th>
							    <th>마감일</th>
							    <th>정원인원수</th>
							    <th>시작일</th>
							    <th>종료일</th>
							    <th>모집인원수</th>
							    <th>나의 고유번호(교수)</th>
							</tr>
				        </thead>
				        <tbody>
				            <c:forEach var="myClass" items="${myClass}" varStatus="status">
				                <tr>
				                    <td>${status.index + 1}</td> <!-- 순번 표시 -->
				                    <td>${myClass.lctr_num}</td>
				                    <td>
										<c:choose>
									        <c:when test="${fn:length(myClass.lctr_name) > 15}">
									            ${fn:substring(myClass.lctr_name, 0, 15)}...
									        </c:when>
									        <c:otherwise>
									            ${myClass.lctr_name}
									        </c:otherwise>
									    </c:choose>
									</td>
				                    <td>${myClass.aply_type}</td>
				                    <td>${myClass.aply_ydm}</td>
				                    <td>${myClass.end_date}</td>
				                    <td>${myClass.pscp_nope}</td>
				                    <td>${myClass.bgng_ymd}</td>
				                    <td>${myClass.end_ymd}</td>
				                    <td>${myClass.rcrt_nope}</td>
				                    <td>${myClass.unq_num2}</td>
				                    <td style="display: none;">${myClass.unq_num2}</td>
				                </tr>					
				            </c:forEach>
				        </tbody>
				    </table>
				</c:if>
	
				<c:if test="${myOfflineClass}">
				    <h2>오프라인 강의목록</h2>
				    <table class="border">
				        <thead>
							<tr>
							    <th>순번</th>
							    <th>강의번호</th>
							    <th>강의명</th>
							    <th>강의상태</th>
							    <th>개설일</th>
							    <th>마감일</th>
							    <th>정원인원수</th>
							    <th>시작일</th>
							    <th>종료일</th>
							    <th>모집인원수</th>
							    <th>나의 고유번호(교수)</th>
							</tr>
				        </thead>
				        <tbody>
				            <c:forEach var="myClass" items="${myClass}" varStatus="status">
				                <tr>
				                    <td>${status.index + 1}</td> <!-- 순번 표시 -->
				                    <td>${myClass.lctr_num}</td>
				                    <td>
										<c:choose>
									        <c:when test="${fn:length(myClass.lctr_name) > 15}">
									            ${fn:substring(myClass.lctr_name, 0, 15)}...
									        </c:when>
									        <c:otherwise>
									            ${myClass.lctr_name}
									        </c:otherwise>
									    </c:choose>
									</td>
				                    <td>${myClass.aply_type}</td>
				                    <td>${myClass.aply_ydm}</td>
				                    <td>${myClass.end_date}</td>
				                    <td>${myClass.pscp_nope}</td>
				                    <td>${myClass.bgng_ymd}</td>
				                    <td>${myClass.end_ymd}</td>
				                    <td>${myClass.rcrt_nope}</td>
				                    <td>${myClass.unq_num2}</td>
				                    <td style="display: none;">${myClass.unq_num2}</td>
				                </tr>					
				            </c:forEach>
				        </tbody>
				    </table>
				</c:if>
		    </div>
        </div>      
    </div>
</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>