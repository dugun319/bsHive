<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생용 강의 평가 페이지입니다</title>
    <style>
        body {
        	width:1920px;
            margin: 0;
            display: flex;
            flex-direction: column; /* 세로 방향 정렬 */
            height: 100vh; /* 전체 화면 높이 사용 */
        }
        .content {
            display: flex; /* 사이드바와 메인 콘텐츠를 나란히 배치 */
            flex: 1; /* 남은 공간을 차지 */
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
        input[type="submit"], input[type="reset"] {
            padding: 10px 15px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }
        input[type="reset"] {
            background-color: #f44336;
            color: white;
        }
        input[type="submit"]:hover, input[type="reset"]:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <header> <%@ include file="../header.jsp" %> </header>
    <div class="content">
        <div class="sidebar">
            <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
        </div>
        <main>
            <h2>bsHive 강의 중간 평가</h2>
            <hr>
            <div>
                강의 평가는 익명성을 보장하며 행정업무상 참여인원만을 확인하고 수업개선을 위한 소중한 자료로 활용됩니다.
                <p>강의평가서는 수업의 질을 향상시키기 위한 목적으로 제작되었으며,</p>
                <p>성적평가에는 영향을 받지 않습니다.</p>
                <p>한번 저장된 강의평가는 변경이 불가하니 입력 시 유의하시기 바랍니다.</p>
                <hr>
            </div>
            <p>
            <form action="submitEvaluation" method="post">
                <table>
                    <tr>
                        <th>문항</th>
                        <th>점수</th>
                    </tr>
                    <c:forEach var="question" items="${questions}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}. ${question}</td>
                            <td>
                                <c:forEach var="score" begin="1" end="5">
                                    <label>
                                        <input type="radio" name="scores[${status.index}]" value="${score}" required>
                                      ${score}점
                                    	</label>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit"  value="제출">
                <input type="reset" value="초기화">
            </form>
        </main>
    </div>
    <footer> <%@ include file="../footer.jsp" %> </footer>
</body>
</html>
