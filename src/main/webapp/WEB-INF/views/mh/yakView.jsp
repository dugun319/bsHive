<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ include file="sidebarPst.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <table border="1">
        <c:forEach items="${yakView }" var="board">
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

            <tr>
                <td colspan="2">
                    <button onclick="location.href='/mh/yakModify?pst_num=${board.pst_num}'">수정</button>
                    &nbsp;&nbsp; 
                    <a href="yakList">목록보기</a>&nbsp;&nbsp; 
                    <a href="yakDelete?pst_num=${board.pst_num }">삭제</a>&nbsp;&nbsp;
                </td>
            </tr>
                        </c:forEach>
        </table>
</body>
</html>