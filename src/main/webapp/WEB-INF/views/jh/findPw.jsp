<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/css/jh_findPw.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/js/jh_findPw.js"></script> 
</head>
<body>
	<div class="body">
	    <div class="title">비밀번호 찾기</div>
	    <div class="main-container"> 
	        <div class="list">
	            <%@ include file="loginSideBar.jsp" %>
	        </div>
			<div class="content">
			<c:if test="${not empty userCheckError}">
		        <div class="alert">
		            ${userCheckError}
		        </div>
		    </c:if>
			    <form method="post" name="frm" action="/jh/realFindPw" onsubmit="return chkValue();">
			        <!-- 이름 -->
			        <div class="form-group">
			            <label for="user_name">이름</label>
			            <input type="text" id="user_name" name="nm" required>
			            <p class="check_font" id="nameCheckMessage"></p>
			        </div>
			    
			        <!-- 이메일 -->
			        <div class="form-group">
			            <label for="user_email">이메일</label>
			            <input type="text" id="user_email1" name="user_email1" style="width: 30px;" required>
			            &nbsp;@&nbsp;
			            <input type="text" id="user_email3" name="selboxDirect" />
			            <select id="user_email2" name="email_domain">
			                <option value="naver.com">naver.com</option>
			                <option value="daum.net">daum.net</option>
			                <option value="gmail.com">gmail.com</option>
			                <option value="hanmail.net">hanmail.net</option>
			                <option value="direct">직접입력</option>
			            </select>
						<button type="button" id="emailduplicate">이메일 확인</button>
			            <button type="button" id="emailCheckBtn">인증번호 전송</button>
			            <p class="check_font" id="emailCheckMessage"></p>
			            <input type="hidden" id="hidden_user_email" name="eml" />
			        </div>
			
			        <!-- 이메일 인증 번호 -->
					<div class="form-group" id="input_auth_code">
					    <label for="auth_code">인증번호</label>
					    <input type="text" id="auth_code" name="auth_code" maxlength="6" pattern="\d{6}" required>
					    <button type="button" id="verifyAuthCodeBtn">인증번호 확인</button>
					    <p class="check_font" id="verifyAuthCodeMessage"></p>
					</div>

			        <!-- 제출 버튼 -->
					<div class="form-group">
					    <div class="moveBtn">
					        <button type="submit" id="submitBtn" disabled style="background-color: gray; color: white; cursor: not-allowed;">비밀번호 찾기</button>
					        <button id="backBtn" onclick="history.back();">이전</button>
					    </div>
					</div>
			    </form>
			</div>
		</div>
		</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>