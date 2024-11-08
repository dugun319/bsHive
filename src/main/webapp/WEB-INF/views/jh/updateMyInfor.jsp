<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/css/jh_updateMyInfor.css">
		<script type="text/javascript" src="/js/jh_signUpForm.js"></script> 
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

<div class="body">
    <div class="title">회원정보변경</div>
    <div class="main-container">
        <div class="list">
           <%@ include file="myPageSideBar.jsp" %>
        </div>
        
		<div class="content">
			    <form method="post" name="frm" action="/jh/updateMyInfor" onsubmit="return chkValue();">
			    	 <input type="hidden" id="user_addr2" name="unq_num" value="${user.unq_num}" required>
			        <!-- 이름 -->
			        <div class="form-group">
			            <label for="user_name">이름</label>
			            <input type="text" id="user_name" name="nm" value="${user.nm}" required>
			            <p class="check_font" id="nameCheckMessage"></p>
			        </div>
			
			        <!-- 연락처 -->
			        <div class="form-group">
			            <label for="user_tel">연락처</label>
			            <input type="text" id="user_tel" name="telno" value="${user.telno}" oninput="hypenTel(this)" maxlength="13" required>
			            <p class="check_font" id="telCheckMessage"></p>
			        </div>
			
			        <!-- 우편번호 -->
			        <div class="form-group">
			            <label for="user_zipcode">우편번호</label>
			            <input type="text" id="user_zipcode" name="zip" value="${user.zip}" required>
			            <button type="button" onclick="findAddr()">주소 검색</button>
			            <p class="check_font" id="zipcodeCheckMessage"></p>
			        </div>
			
			        <!-- 주소 -->
			        <div class="form-group">
			            <label for="user_addr1">주소</label>
			            <input type="text" id="user_addr1" name="addr" value="${user.addr}" required>
			            <p class="check_font" id="addr1CheckMessage"></p>
			        </div>
			
			        <!-- 상세 주소 -->
			        <div class="form-group">
			            <label for="user_addr2">상세주소</label>
			            <input type="text" id="user_addr2" name="daddr" value="${user.daddr}" required>
			            <p class="check_font" id="addr2CheckMessage"></p>
			        </div>
			        
			        <!-- 제출 버튼 -->
					<div class="form-group1">
					    <div class="moveBtn">
					        <button type="submit" id="submitBtn">정보변경</button>
					        <input type="button" value="취소" id="backBtn" onclick="window.location.href='/jh/myPage';">
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