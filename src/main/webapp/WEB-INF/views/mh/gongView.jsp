<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ include file="sidebarPst.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
    공지사항 내용
        <table border="1">
        <c:forEach items="${GongView }" var="board">
            <tr>
                <td>제목</td>
                <td><input type="text" name="pst_ttl" value="${board.pst_ttl }" readonly></td>
            </tr>
            <tr>
                <td>작성일</td>
                <td><input type="text" name="wrt_ymd" value="${board.wrt_ymd }" readonly></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="pst_cn" readonly>${board.pst_cn }</textarea></td>
            </tr>
             </c:forEach>
        </table>
        <c:forEach items="${GongView }" var="board">
        <button onclick="location.href='/mh/gongModify?pst_num=${board.pst_num}'">수정</button>
        <a href="gongList">목록보기</a>
        <a href="gongDelete?pst_num=${board.pst_num }" class="delete">삭제</a>
    </c:forEach>

</body>
</html>