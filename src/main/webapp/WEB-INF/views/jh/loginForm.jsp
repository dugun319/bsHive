<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>

<title>로그인 페이지</title>
	
	<link rel="stylesheet" type="text/css" href="/css/jh_loginForm.css">
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", () => {
            const userButtons = document.querySelectorAll(".user_button");
            const userTypeInput = document.getElementById("userType");

            userButtons.forEach(button => {
                button.addEventListener("click", () => {
                    userButtons.forEach(btn => btn.classList.remove("active"));
                    button.classList.add("active");
                    userTypeInput.value = button.getAttribute("data-type");
                });
            });

            const loginForm = document.getElementById("loginForm");
            loginForm.addEventListener("submit", (event) => {
                if (!userTypeInput.value) {
                    event.preventDefault();
                    alert("사용자 유형을 선택해주세요.");
                }
            });
        });
    </script>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
    <div class="container">
        <!-- 이미지 -->
        
    	<div class="background_image">
			<img src="/images/로그인배경.jpg" alt="배경 이미지" class="background_image_img">
    	</div>
    
        <!-- 로그인 폼 -->
        <div class="login_container">
        	<div class="image_container">
	            <img src="/images/로그인옆사진.jpg" alt="로그인 옆사진">
	        </div>
	        <div class="login_box">
	        	<div class="login_icon">
	           		<img src="/images/부산Hive로고.png" alt="로그인 아이콘">
	        	</div>
	                      

	            <div class="user_type_buttons">
	                <button class="user_button" id="student" data-type="1">학생</button>
	                <button class="user_button" id="professor" data-type="2">교수</button>
	                <button class="user_button" id="staff" data-type="3">직원</button>
	            </div>
	
	            <form action="/jh/login" method="post" id="loginForm">
	            	<c:if test="${not empty loginError}">
            		<p class="errMessage">${loginError}</p>
        			</c:if>
	                <input type="hidden" name="MBR_SE" id="userType" value="">
	                <input type="text" name="EML" value="${eml}" placeholder="이메일을 입력하세요." required="required">
	                <input type="password" name="PSWD" placeholder="비밀번호를 입력하세요." required="required">
	                <input type="submit" value="로그인" class="input_submit">
	            </form>
	
	            <ul class="sign_up_find_idpw">
	                <li><a href="/jh/signUpSelect">회원가입</a></li>
	                <li><span>&nbsp;|&nbsp;</span></li>
	                <li><a href="/jh/findPw">비밀번호 찾기</a></li>
	            </ul>
	        </div>
        </div>
    </div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>
