<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 성적 확인 페이지</title>
</head>
<head>
    <meta charset="UTF-8">
    <title>상세 성적 확인 페이지</title>
    <style>
        /* CSS 스타일 추가 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #f9f9f9;
        }
        
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            text-align: left;
            padding: 12px 15px;
            border: 1px solid #ddd;
        }

        td {
            padding: 10px 15px;
            border: 1px solid #ddd;
            text-align: left;
        }

        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e0e0e0;
        }

        h2 {
            text-align: center;
            margin: 20px 0;
            font-size: 24px;
            color: #333;
        }

        tbody td {
            font-size: 16px;
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
hi
  <h2>상세 성적 확인</h2>
          <c:forEach var="student" items="${studentGrade}">
        <p>학생 ID: ${student.unq_num}</p>
          <p>${student.stdnt_nm}</p> <!-- 학생 이름 -->
 		 <td>${student.lctr_name}</td> <!-- 강의명 -->
		</c:forEach>
         <table>
        <thead>
            <tr>
                <th>항목</th>
                <th>내용</th>
            </tr>
        </thead>
        <tbody>
<c:forEach var="grade" items="${studentGrade}">
    <tr>
        <td>신청 상태</td>
        <td>${grade.aply_stts}</td>
    </tr>
    <tr>
        <td>신청일</td>
        <td>${grade.aply_ymd}</td>
    </tr>
    <tr>
        <td>출석 점수</td>
        <td>${grade.atndc_scr}</td>
    </tr>
    <tr>
        <td>과제 점수</td>
        <td>${grade.asmt_scr}</td>
    </tr>
    <tr>
        <td>수료 여부</td>
        <td>${grade.fnsh_yn}</td>
    </tr>
    <tr>
        <td>강의 진도율</td>
        <td>${grade.pace}%</td>
    </tr>
    <tr>
        <td>강의 평가</td>
        <td>${grade.evl_total}</td>
    </tr>
    <hr>
</c:forEach>

        </tbody>
    </table>
    
</main>
    </div>

</body>
<footer><%@ include file="../footer.jsp" %></footer>
</html>