<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/css/jh_signUpForm.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript" src="/js/jh_signUpForm.js"></script> 
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div class="body">
	    <div class="title">회원가입</div>
	    <div class="main-container"> 
	        <div class="list">
	            <%@ include file="loginSideBar.jsp" %>
	        </div>
			<div class="content">
			    <form method="post" name="frm" action="/jh/join" onsubmit="return chkValue();">
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
						<button type="button" id="emailduplicate">중복확인</button>
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

			
			        <!-- 비밀번호 -->
			        <div class="form-group">
			            <label for="user_pw">비밀번호</label>
			            <input type="password" id="user_pw" name="pswd" required>
			            <p class="check_font" id="pwCheckMessage"></p>
			        </div>
			
			        <!-- 비밀번호 확인 -->
			        <div class="form-group">
			            <label for="user_pw_confirm">비밀번호 확인</label>
			            <input type="password" id="user_pw_confirm" name="pswd_confirm" required>
			            <p class="check_font" id="pwConfirmMessage"></p>
			        </div>
			
			        <!-- 이름 -->
			        <div class="form-group">
			            <label for="user_name">이름</label>
			            <input type="text" id="user_name" name="nm" required>
			            <p class="check_font" id="nameCheckMessage"></p>
			        </div>
			
			        <!-- 휴대폰 번호 -->
			        <div class="form-group">
			            <label for="user_tel">연락처</label>
			            <input type="text" id="user_tel" name="telno" oninput="hypenTel(this)" maxlength="13" required>
			            <p class="check_font" id="telCheckMessage"></p>
			        </div>
			
			        <!-- 우편 번호 -->
			        <div class="form-group">
			            <label for="user_zipcode">우편번호</label>
			            <input type="text" id="user_zipcode" name="zip" required>
			            <button type="button" onclick="findAddr()">주소 검색</button>
			            <p class="check_font" id="zipcodeCheckMessage"></p>
			        </div>
			
			        <!-- 주소 -->
			        <div class="form-group">
			            <label for="user_addr1">주소</label>
			            <input type="text" id="user_addr1" name="addr" required>
			            <p class="check_font" id="addr1CheckMessage"></p>
			        </div>
			
			        <!-- 상세 주소 -->
			        <div class="form-group">
			            <label for="user_addr2">상세주소</label>
			            <input type="text" id="user_addr2" name="daddr" required>
			            <p class="check_font" id="addr2CheckMessage"></p>
			        </div>
			
			        <!-- 제출 버튼 -->
					<div class="form-group">
					    <div class="moveBtn">
					        <button id="backBtn" onclick="history.back();">이전</button>
					        <button type="submit" id="submitBtn" disabled style="background-color: gray; color: white; cursor: not-allowed;">회원가입</button>
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