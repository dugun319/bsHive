<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS</title>
</head>
<style>
    /* 테이블 스타일 */
    .styled-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        border-radius: 8px;
        overflow: hidden;
        background-color: #fff;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .styled-table th,
    .styled-table td {
        padding: 12px;
        text-align: left;
        font-size: 14px;
        border: 1px solid #ddd;
    }

    .styled-table th {
        background-color: #007bff;
        color: white;
    }

    .styled-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .styled-table tr:hover {
        background-color: #f1f1f1;
        cursor: pointer;
    }

    .styled-table td a {
        color: #007bff;
        text-decoration: none;
    }

    .styled-table td a:hover {
        text-decoration: underline;
    }
</style>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="sidebarOn.jsp" %>
		</div>
		<div class="content1" style="border-top: 2px solid black;">
			<br><br>
			<div class="content1">
			    <h2>모집중인 강의 수: ${onlnTotal}</h2>
			
			    <!-- 강의 정보 테이블 -->
			    <table class="styled-table">
			        <thead>
			            <tr>
			                <th>강의번호</th>
			                <th>강의명</th>
			                <th>학과명</th>
			                <th>강의상태</th>
			                <th>개설일</th>
			                <th>정원인원수</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${onlnList}" var="Lctr">
			                <tr>
			                    <td>${Lctr.lctr_num}</td>
			                    <td onclick="location.href='/se/onlnDtl?Lctr_Num=${Lctr.lctr_num}'" style="cursor: pointer;">
			                        ${Lctr.lctr_name}
			                    </td>
			                    <td>${Lctr.sbjct_nm}</td>
			                    <td>
								    <c:choose>
								        <c:when test="${Lctr.aply_type == '100'}">개설전</c:when>
								        <c:when test="${Lctr.aply_type == '110'}">모집중</c:when>
								        <c:when test="${Lctr.aply_type == '120'}">진행중</c:when>
								        <c:when test="${Lctr.aply_type == '130'}">강의종료</c:when>
								        <c:when test="${Lctr.aply_type == '140'}">폐강</c:when>
								        <c:otherwise>${Lctr.aply_type}</c:otherwise>
								    </c:choose>
								</td>
			                    <td>${Lctr.aply_ydm}</td>
			                    <td>${Lctr.pscp_nope}</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>