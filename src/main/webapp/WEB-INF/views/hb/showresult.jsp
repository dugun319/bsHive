<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>평가 이후 확인 페이지</title>
    <style>
        header {
            grid-column: 1 / -1; /* 헤더가 전체 가로를 차지하도록 설정 */
        }
        footer {
            grid-column: 1 / -1; /* 푸터가 전체 가로를 차지하도록 설정 */
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        input[type="submit"] {
            padding: 10px 15px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
        }
        input[type="submit"]:hover {
            opacity: 0.8;
        }
    </style>
</head>
<header><%@ include file="../header.jsp" %></header>
<body>
<div class="container1">
    <div class="sidebar">
        <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
    </div>
    <main>
        <h2>학생 정보 : ${unqNum}</h2>
        <h2>학생 이름 : ${stdntName}</h2>
        <h2>강의명 정보 : ${lctr_num}</h2>
        <table>
            <thead>
                <tr>
                    <th>과목 이름</th>
                    <th>강의 평가 여부</th>
                    <th>성적 확인</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${studentList}">
                	<c:if test="${empty studentList}">
					    <p>학생 정보가 없습니다.</p>
					</c:if>
					
					  <c:if test="${empty studentList}">
                    <tr>
                        <td colspan="3">학생 정보가 없습니다.</td>
                    </tr>
                </c:if>

                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <!-- 과목 이름 출력 -->
                        <td>${student.lctr_num}</td>
                        <td><a href="evaluation.jsp?studentId=${student.unq_num}">평가하기</a></td>
						<td><input type="submit" value="성적 확인" onclick="location.href='detailResult?unq_num=${student.unq_num}&lctr_num=${student.lctr_num}'"></td>
                    </tr>
                </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </main>
</div>
</body>
<footer><%@ include file="../footer.jsp" %></footer>
</html>
