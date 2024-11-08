<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jh_changePassword.css">
</head>
<body>

<div class="body">
    <div class="title">비밀번호 변경</div>
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
		    <h1>현재 비밀번호를 입력 후 새로 사용할 비밀번호를 입력하세요.</h1>
			<div class="realPwChk">    
			    <form action="/jh/realchangePassword" method="post" id="loginForm">
			        <% String eml = (String) session.getAttribute("eml"); %>
			        <input type="hidden" name="eml" value="<%= eml != null ? eml : "" %>">
			        <div class="input-group" id="input-group2">
			            <label for="password">현재 비밀번호</label>
			            <input type="password" name="pswd" id="old_pswd" placeholder="비밀번호를 입력하세요." required="required">
			        </div>
			        <div class="input-group">
			            <label for="password">새로운 비밀번호</label>
			            <input type="password" name="new_pswd" id="new_pswd1" placeholder="비밀번호를 입력하세요." required="required" oninput="checkNewPassword()">
			            <p id="passwordLengthMessage" style="margin-top: 5px; color: red; font-size: 0.9em;"></p> <!-- 길이 메시지 표시 -->
			        </div>
			        <div class="input-group">
			            <label for="password">비밀번호 확인</label>
			            <input type="password" id="new_pswd2" placeholder="비밀번호를 입력하세요." required="required" oninput="checkPasswordMatch()">
			            <p id="matchMessage" style="margin-top: 5px; color: red; font-size: 0.9em;"></p> <!-- 일치 메시지 표시 -->
			        </div>
			        <div class="button-container"> 
			            <input type="submit" value="확인" class="input_submit" id="submitBtn" disabled>
			            <input type="button" value="취소" class="input_cancel" onclick="window.location.href='/jh/myPage';">
			        </div>
			    </form>
			</div>   
		</div>
    </div> 
</div>	
<script type="text/javascript">
    function checkNewPassword() {
        const newPassword = document.getElementById('new_pswd1').value;
        const passwordLengthMessage = document.getElementById('passwordLengthMessage');
        const submitButton = document.getElementById('submitBtn');

        if (newPassword.length < 6 || newPassword.length > 12) {
            passwordLengthMessage.textContent = "비밀번호는 6자 이상 12자 이하로 입력해주세요.";
            passwordLengthMessage.style.color = "red";
            submitButton.disabled = true;
        } else {
            passwordLengthMessage.textContent = "사용 가능한 비밀번호입니다.";
            passwordLengthMessage.style.color = "green";
            submitButton.disabled = false; // 일단 비밀번호 길이 조건 충족 시 활성화
        }

        // 비밀번호 일치 여부도 확인합니다.
        checkPasswordMatch();
    }

    function checkPasswordMatch() {
        const newPassword = document.getElementById('new_pswd1').value;
        const confirmPassword = document.getElementById('new_pswd2').value;
        const matchMessage = document.getElementById('matchMessage');
        const submitButton = document.getElementById('submitBtn');

        if (newPassword && confirmPassword && newPassword === confirmPassword) {
            matchMessage.textContent = "비밀번호가 일치합니다.";
            matchMessage.style.color = "green";
            if (newPassword.length >= 6 && newPassword.length <= 12) {
                submitButton.disabled = false;
            }
        } else {
            matchMessage.textContent = "비밀번호가 일치하지 않습니다.";
            matchMessage.style.color = "red";
            submitButton.disabled = true;
        }
    }

    // 비밀번호 입력 시마다 이벤트 리스너 추가
    document.getElementById('new_pswd1').addEventListener('input', checkNewPassword);
    document.getElementById('new_pswd2').addEventListener('input', checkPasswordMatch);
</script>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>