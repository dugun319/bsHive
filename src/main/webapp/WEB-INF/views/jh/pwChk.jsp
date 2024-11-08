<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jh_pwChk.css">
</head>
<body>

<div class="body">
    <div class="title">회원정보변경</div>
    <div class="main-container">
        <div class="list">
           <%@ include file="myPageSideBar.jsp" %>
        </div>
		<div class="content">
		  	<c:if test="${not empty loginError}">
		        <div class="alert">
		            ${loginError}
		        </div>
		    </c:if> 
		    <h1>본인확인을 위해 비밀번호를 입력해 주세요.</h1>
		    <div class="realPwChk">    
		        <form action="/jh/realPwChk" method="post" id="loginForm">
		            <div class="input-group" id="input-group2">
		                <label for="username" id ="email_label">이메일</label>
						<%String eml = (String) session.getAttribute("eml");%>
						<input type="text" name="ex_eml" id="eml" value="<%= eml != null ? eml : "" %>" readonly disabled>
						<input type="hidden" name="eml" value="<%= eml != null ? eml : "" %>">
		            </div>
		            <div class="input-group">
		                <label for="password">비밀번호</label>
		                <input type="password" name="pswd" id="pswd" placeholder="비밀번호를 입력하세요." required="required">
		            </div>
		            <div class="button-container"> 
		                <input type="submit" value="확인" class="input_submit">
		                <input type="button" value="취소" class="input_cancel" onclick="window.location.href='/jh/myPage';">
		            </div>
		        </form>
		    </div>           
		</div>
    </div> 
</div>	
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>