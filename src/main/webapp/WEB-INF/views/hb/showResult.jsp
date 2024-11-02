<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>평가 이후 확인 페이지</title>
    <style>
        body {
            width: 1920px;
            margin: 0;
            display: grid;
            grid-template-rows: auto 1fr auto; /* 헤더, 메인 콘텐츠, 푸터의 비율 설정 */
            grid-template-columns: 410px 1fr; /* 사이드바와 메인의 비율 설정 */
            height: 100vh; /* 전체 화면 높이 사용 */
        }
        header {
            grid-column: 1 / -1; /* 헤더가 전체 가로를 차지하도록 설정 */
        }
        footer {
            grid-column: 1 / -1; /* 푸터가 전체 가로를 차지하도록 설정 */
        }
        .sidebar {
            width: 410px; /* 사이드바 너비 */
            padding: 15px;
            background-color: #f9f9f9; /* 사이드바 배경색 */
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 (선택 사항) */
        }
        main {
            width: 1180px; /* 메인 콘텐츠 너비 */
            padding: 15px;
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
    <div class="sidebar">
        <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
    </div>
    <main>
        <h2>학생 정보</h2>
        <table>
            <thead>
                <tr>
                    <th>학생 이름</th>
                    <th>강의명</th>
                    <th>강의 평가</th>
                    <th>성적 확인</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.course}</td>
                        <td><a href="evaluation.jsp?studentId=${student.id}">평가하기</a></td>
                        <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=${student.id}'"></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
<footer><%@ include file="../footer.jsp" %></footer>
</html>
