<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.jsp"%>
<%@ include file="sidebarPst.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<h2>공지사항 내용</h2>
	<table border="1">
		<c:forEach items="${oneView}" var="board">
			<tr>
				<td>제목</td>
				<td><input type="text" name="pst_ttl" value="${board.pst_ttl}"
					readonly></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="stdnt_nm" value="${board.stdnt_nm }"></td>
				
				<td>작성일</td>
				<td><input type="text" name="wrt_ymd" value="${board.wrt_ymd}"
					readonly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td style="width: 100%; padding: 10px; border: 1px solid #ddd;">
    <div style="width: 100%; height: 400px; overflow-y: auto; padding: 10px; border: 1px solid #ccc;">
        <span name="pst_cn" readonly="readonly" th:utext>${board.pst_cn}</span>
    </div>
</td>
			</tr>
			<tr>
				<td>첨부 파일</td>
				<td><c:if test="${not empty board.file_group}">
						<c:forEach var="filePath"
							items="${fn:split(board.file_group, ',')}">
							<c:set var="fileName" value="${fn:substringAfter(filePath, '_')}" />
							<a download="${fileName }" href="download?filePath=${filePath.trim()}" type="media_type">
								${fileName} 다운로드 </a>
							<br>
						</c:forEach>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach items="${oneView}" var="board">
	 <c:if test="${writer == board.unq_num }"><button
			onclick="location.href='/mh/oneModify?pst_num=${board.pst_num}'">수정</button></c:if>
		<a href="oneList">목록보기</a>
		 <c:if test="${writer == board.unq_num }"><a href="oneDelete?pst_num=${board.pst_num}" class="delete">삭제</a></c:if>
	</c:forEach>
</body>
</html>
