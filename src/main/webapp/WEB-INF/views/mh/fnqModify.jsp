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
    <form action="faqModifyDB" method="post">
        <table border="1">
        <c:forEach items="${fnqView }" var="board">
            <input type="hidden" name="pst_num" value="${board.pst_num }">
            <tr>
                <td>제목</td>
                <td><input type="text" name="pst_ttl" value="${board.pst_ttl }"></td>
            </tr>
            <tr>
                <td>작성일</td>
                <td><input type="text" name="wrt_ymd" value="${board.wrt_ymd }" readonly></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="pst_cn">${board.pst_cn }</textarea></td>
                <td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="작성">
                    &nbsp;&nbsp; 
                    <a href="fnqList">목록보기</a>&nbsp;&nbsp; 
                </td>
            </tr>
            
                        </c:forEach>
        </table>
    </form>
</body>
</html>