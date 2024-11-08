<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>평가 이후 확인 페이지</title>
  <style type="text/css">
  table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
            color: #333;
            font-weight: bold;
        }

        td {
            background-color: #fafafa;
        }

        tr:nth-child(even) td {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        input[type="button"] {
            padding: 8px 15px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }

        input[type="button"]:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
    <header><%@ include file="../header.jsp" %></header>
    <div class="container1">
        <div class="sidebar">
            <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
        </div>
        <main>
            <h2>학생 정보</h2>
            
            <!-- 강의명 -->
            <table>
                <tr>
                    <th>강의명</th>
                </tr>
                <tr>
                    <td>${className}</td>
                </tr>
            </table>
            
        <table>
                <thead>
                    <tr>
                        <th>학생 이름</th>
                        <th>강의명</th>
                        <th>강의 평가</th>
                        <th>성적 확인</th>
                        <th>과제 확인</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 학생 목록 반복 -->
                    <c:forEach var="student" items="${studentList}">
                        <tr>
                            <td>${student.stdnt_nm}</td> <!-- 학생 이름 -->
                            <td>${student.lctr_name}</td> <!-- 강의명 -->
                            <td>${student.evl_total}</td> <!-- 강의 평가 상태 -->
                            <td><input type="button" value="성적 확인" onclick="location.href='showResult?studentId=${student.unq_num}'"></td> <!-- 성적 확인 버튼 -->
                            <td><input type="button" value="과제 확인" onclick="location.href='../hs/lecProfConfirmAssignt?studentId=${student.unq_num}'"></td> <!-- 과제 확인 버튼 -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
    <footer>
        <%@ include file="../footer.jsp" %>
    </footer>
</body>
</html>
