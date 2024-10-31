<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
    공지사항 내용
    <form action="modify" method="post">
        <table border="1">
        <c:forEach items="${GongView }" var="board">
            <tr>
                <td>제목</td>
                <td><input type="text" name="bTitle" value="${board.pst_ttl }" readonly></td>
            </tr>
            <tr>
                <td>작성일</td>
                <td><input type="text" name="bDate" value="${board.wrt_ymd }" readonly></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="bContent" readonly>${board.pst_cn }</textarea></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="수정">
                    &nbsp;&nbsp; 
                    <a href="mhboard_GongList">목록보기</a>&nbsp;&nbsp; 
                    <a href="GongDelete?pst_num=${board.pst_num }">삭제</a>&nbsp;&nbsp;
                </td>
            </tr>
                        </c:forEach>
        </table>
    </form>
    <a href="Gongwrite">글작성</a>
</body>
</html>
