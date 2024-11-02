<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>평가 이후 확인 페이지</title>
<style>
    body {
        width: 1920px;
        margin: 10;
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
        margin: 50px;
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
                <tr>
                    <td>홍길동</td>
                    <td>Java 프로그래밍</td>
                    <td><a href="evaluation.jsp?studentId=1">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=1'"></td>
                </tr>
                <tr>
                    <td>김영희</td>
                    <td>웹 개발</td>
                    <td><a href="evaluation.jsp?studentId=2">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=2'"></td>
                </tr>
                <tr>
                    <td>이철수</td>
                    <td>데이터베이스</td>
                    <td><a href="evaluation.jsp?studentId=3">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=3'"></td>
                </tr>
                <tr>
                    <td>박영수</td>
                    <td>소프트웨어 공학</td>
                    <td><a href="evaluation.jsp?studentId=4">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=4'"></td>
                </tr>
                <tr>
                    <td>최지은</td>
                    <td>운영체제</td>
                    <td><a href="evaluation.jsp?studentId=5">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=5'"></td>
                </tr>
                <tr>
                    <td>장민호</td>
                    <td>네트워크</td>
                    <td><a href="evaluation.jsp?studentId=6">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=6'"></td>
                </tr>
                <tr>
                    <td>정수연</td>
                    <td>인공지능</td>
                    <td><a href="evaluation.jsp?studentId=7">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=7'"></td>
                </tr>
                <tr>
                    <td>이서연</td>
                    <td>빅데이터 분석</td>
                    <td><a href="evaluation.jsp?studentId=8">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=8'"></td>
                </tr>
                <tr>
                    <td>황찬호</td>
                    <td>프로그래밍 언어</td>
                    <td><a href="evaluation.jsp?studentId=9">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=9'"></td>
                </tr>
                <tr>
                    <td>오민지</td>
                    <td>컴퓨터 구조</td>
                    <td><a href="evaluation.jsp?studentId=10">평가하기</a></td>
                    <td><input type="submit" value="성적 확인" onclick="location.href='grade.jsp?studentId=10'"></td>
                </tr>
            </tbody>
        </table>
    </main>
</body>
<footer><%@ include file="../footer.jsp" %></footer>
</html>
