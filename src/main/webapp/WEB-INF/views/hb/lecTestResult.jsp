<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>교수의 성적 입력창</title>
    <style type="text/css">
        body {
            margin: 0;
            display: grid;
            grid-template-columns: 410px 1fr; /* 사이드바와 메인의 비율 설정 */
            grid-template-rows: auto 1fr auto; /* 헤더, 메인 콘텐츠, 푸터의 비율 설정 */
            height: 100vh; /* 전체 화면 높이 사용 */
            width:1920px;
            
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
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        }
        .content {
            padding: 15px; /* 메인 콘텐츠 패딩 */
            display: flex;
            flex-direction: column; /* 세로 방향으로 정렬 */
        }
        h2 {
            color: #333;
            margin-top: 0; /* 제목의 상단 여백 제거 */
        }
        form {
            display: flex;
            flex-direction: column; /* 세로 방향으로 정렬 */
            padding: 15px; /* 패딩 추가 */
            background-color: #f9f9f9; /* 배경색 추가 */
            border-radius: 8px; /* 모서리 둥글게 */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        }
        label {
            margin-bottom: 5px; /* 레이블과 입력 필드 간의 간격 */
            font-weight: bold; /* 레이블 글씨 두껍게 */
        }
        input[type="text"],
        input[type="number"],
        button {
            width: 100%; /* 입력 요소와 버튼을 가득 채움 */
            padding: 10px; /* 패딩 추가 */
            margin-bottom: 15px; /* 요소 간 간격 */
            border: 1px solid #ccc; /* 테두리 색상 */
            border-radius: 5px; /* 모서리 둥글게 */
            font-size: 16px; /* 글씨 크기 */
        }
        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #4CAF50; /* 포커스 시 테두리 색상 */
            outline: none; /* 기본 아웃라인 제거 */
        }
        button {
            background-color: #4CAF50; /* 버튼 배경색 */
            color: white; /* 글자색 */
            border: none; /* 테두리 없음 */
            cursor: pointer; /* 커서 포인터로 변경 */
            font-size: 16px; /* 글씨 크기 */
        }
        button:hover {
            opacity: 0.8; /* 버튼 hover 효과 */
        }
    </style>
</head>
<body>
    <header><%@ include file="../header.jsp" %></header>
    <div class="sidebar">
        <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
    </div>
    <div class="content">
	<h2>교수의 성적 입력창</h2>
        <form action="submitGrades" method="post">
            <c:forEach var="lctr_aply" items="${lctr_aplyList}">
                <label for="lctr_num_${lctr_aply.lctr_num}">강의번호:</label>
                <input type="text" id="lctr_num_${lctr_aply.lctr_num}" name="lctr_num_${lctr_aply.unq_num}" value="${lctr_aply.lctr_num}" readonly required>

                <label for="unq_num_${lctr_aply.unq_num}">학생번호:</label>
                <input type="text" id="unq_num_${lctr_aply.unq_num}" name="unq_num_${lctr_aply.unq_num}" value="${lctr_aply.unq_num}" readonly required>
                
                <label for="atndc_scr_${lctr_aply.unq_num}">출석점수:</label>
                <input type="number" id="atndc_scr_${lctr_aply.unq_num}" name="atndc_scr_${lctr_aply.unq_num}" required>
                
                <label for="asmt_scr_${lctr_aply.unq_num}">과제점수:</label>
                <input type="number" id="asmt_scr_${lctr_aply.unq_num}" name="asmt_scr_${lctr_aply.unq_num}" required>
            </c:forEach>
            
            <button type="submit">성적 제출</button>
        </form>
    </div>
    <footer>
        <%@ include file="../footer.jsp" %></footer>
</body>
</html>
