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
 <form action="yakInsert" method="post">
        <table border="1">
            <tr>
                <td>제목</td>
                <td><input type="text" name="pst_ttl"></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea rows="10" name="pst_cn"></textarea></td>
            </tr>
            <tr>
            <td colspan="2">
                <div class="form-group">
                <label for="file">파일</label>
                <input class="form-control-file" id="file" name="file" type="file" multiple>
            </div>
            </td>

            <tr>
                <td colspan="2">
                    <input type="submit" value="작성">
                    &nbsp;&nbsp; 
                    <a href="yakList">목록보기</a>&nbsp;&nbsp; 
                </td>
            </tr>
        </table>
    </form>
    
</body>
</html>