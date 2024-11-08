<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>시험 제출 페이지</title>
</head>
<body>
    <header><%@ include file="../header.jsp" %></header>
    <div class="container1">
        <div class="sidebar">
            <%@ include file="../sidebarLctr.jsp" %> <!-- 사이드바 포함 -->
        </div>
        <div class="content">
            <h2>시험 제출 페이지</h2>
            <form action="submitExam" method="post">
                <!-- 이곳에 시험 제출 관련 필드 추가 가능 -->
                <div>
                    <label for="exam_link">시험 제출 링크:</label>
                    <input type="url" id="exam_link" name="exam_link" placeholder="시험 제출 링크를 입력하세요" required>
                </div>
                <div>
                    <label for="exam_comments">시험 제출 코멘트 (선택 사항):</label>
                    <textarea id="exam_comments" name="exam_comments" rows="4" placeholder="시험에 대한 코멘트를 남겨주세요"></textarea>
                </div>
                <button type="submit">시험 제출</button>
            </form>
        </div>
    </div>
    <footer>
        <%@ include file="../footer.jsp" %>
    </footer>
</body>
</html>
