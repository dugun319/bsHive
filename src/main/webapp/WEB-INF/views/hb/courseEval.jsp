<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생용 강의 평가 페이지입니다</title>
    
    <style>
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
    <script type="text/javascript">
        // 강의 평가 제출 완료 후 알림 띄우기
        function showSubmitConfirmation() {
            // 평가 제출 후 성공 메시지
            const submitConfirmation = confirm("강의 평가가 제출되었습니다. 성적을 확인하시겠습니까?");
            
            // 확인을 누르면 성적 확인 페이지로 이동
            if (submitEvaluation) {
                window.location.href = "showResult.jsp";  // 성적 확인 페이지로 리디렉션
            }
        }
    </script>
</head>
<body>
    <header> <%@ include file="../header.jsp" %> </header>
    <div class="container1">
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
            당신의 학번 :  <strong>${unqNum}</strong>
            
              <form action="submitEvaluation" method="post" onsubmit="showSubmitConfirmation()">
             <table>
                    <tr>
                        <th>문항</th>
                        <th>점수</th>
                    </tr>
                    <c:forEach var="question" items="${evalQuestions}">
				    <tr>
				        <td>${question.evl_detnum}: ${question.evl_detail}</td>
				        <td>
				            <c:forEach var="score" begin="1" end="5">
				                <label>
								<input type="radio" name="evaluationScores[${question.evl_detnum}]" value="${score}" required>
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
