<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/jh_myPage.css">
<link rel="stylesheet" type="text/css" href="/css/jh_signUpForm.css">
<script type="text/javascript" src="/js/jquery.js"></script>
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script type="text/javascript">


	// 이벤트 핸들러
	$(document).ready(function () {
		$("#user_id").on("input", function () {
			validateId();
		});
		$("#user_pw").on("input", function () {
			validatePw();
			// 비밀번호를 변경하면 비밀번호 확인 로직도 다시 실행
			validatePwConfirm(); 
		});
		$("#user_pw_confirm").on("input", function () {
			validatePwConfirm();
		});
		$("#user_name").on("input", function () {
			validateName();
		});
		$("#user_email1, #user_email2, #user_email3").on("input", function () {
			validateEmail();
		});

		$("#verifyAuthCodeBtn").on("click", verifyAuthCode);

		$("#emailCheckBtn").on("click", emailCheck);


	})

	// 주소찾기 다음 api
	function findAddr() {
		new daum.Postcode({
			oncomplete: function (data) {
				var roadAddr = data.roadAddress;
				var jibunAddr = data.jibunAddress;
				document.getElementById('user_zipcode').value = data.zonecode;
				if (roadAddr !== '') {
					document.getElementById("user_addr1").value = roadAddr;
				} else if (jibunAddr !== '') {
					document.getElementById("user_addr2").value = jibunAddr;
				}
				// 상세 주소에 자동 포커스
				document.getElementById("user_addr2").focus();
			}
		}).open();
	}

	let isValid = true; // 제출 확인 여부
	let isIdConfirmed = false; // 아이디 중복 확인 여부
	let isAuthCodeVerified = false // 이메일 전송 여부


	// 비밀번호 유효성 확인
	function validatePw() {
		const pwRegex = /^[A-Za-z0-9_@!]{6,12}$/; // 비밀번호: 대소문자, 숫자, _, @, !, 6~12글자
		var password = $("#user_pw").val().trim();
		var pwCheckMessage = $("#pwCheckMessage"); // 비밀번호 입력 메시지

		// 초기화
		isValid = true;
		// 에러 메시지 초기화
		pwCheckMessage.text("");

		// 비밀번호가 빈 값인 경우
		if (password === "") {
			pwCheckMessage.text("비밀번호를 입력해주세요.").css("color", "red");
			$("#user_pw_confirm").attr("disabled", true); // 비밀번호 확인 비활성화
			$("#user_pw").focus();
			isValid = false;
			return isValid; // 비밀번호가 비어있으면 바로 반환
		}

		// 비밀번호 길이 확인
		if (password.length < 6 || password.length > 12) {
			pwCheckMessage.text("비밀번호는 6자 이상 12자 이하로 입력해주세요.").css("color", "red");
			$("#user_pw_confirm").attr("disabled", true); // 비밀번호 확인 비활성화
			isValid = false;
			return isValid; // 길이가 잘못된 경우 반환
		}

		// 비밀번호 정규식 확인
		if (!pwRegex.test(password)) {
			pwCheckMessage.text("비밀번호는 대소문자, 숫자, !, _, @ 만 입력해주세요.").css("color", "red");
			$("#user_pw_confirm").attr("disabled", true); // 비밀번호 확인 비활성화
			isValid = false;
			return isValid; // 정규식이 맞지 않으면 반환
		}

		// 비밀번호가 유효하면 비밀번호 확인 활성화 및 성공 메시지 표시
		$("#user_pw_confirm").attr("disabled", false);
		pwCheckMessage.text("사용 가능한 비밀번호입니다.").css("color", "green");

		return isValid;
	}


	// 비밀번호 확인 유효성 확인
	function validatePwConfirm() {
		var password = $("#user_pw").val().trim();
		var passwordConfirm = $("#user_pw_confirm").val().trim();
		var pwConfirmMessage = $("#pwConfirmMessage"); // 비밀번호 확인 메시지

		isValid = true; // 초기화
		pwConfirmMessage.text(""); // 에러 메시지 초기화

		if (passwordConfirm === "") {
			pwConfirmMessage.text("비밀번호 확인을 위해 입력해주세요.").css("color", "red");
			isValid = false
			return isValid;
		}
		if (password !== passwordConfirm) {
			pwConfirmMessage.text("비밀번호가 일치하지 않습니다.").css("color", "red");
			isValid = false;
			return isValid;
		} else {
			pwConfirmMessage.text("비밀번호가 일치합니다.").css("color", "green");
		}

		return isValid;
	}


	// 이름 유효성 확인 
	function validateName() {
		// 정규 표현식 : 한글만 입력 가능
		const nameRegex = /^[가-힣]+$/;

		var name = $("#user_name").val().trim();
		var nameCheckMessage = $("#nameCheckMessage");

		// 초기화
		isValid = true;
		nameCheckMessage.text("");

		if (name === "") {
			nameCheckMessage.text("이름을 입력해주세요").css("color", "red");
			$("#user_name").focus();  // 포커스를 이동
			isValid = false;
			return false; // 유효성 검사 결과 반환
		}
		if (!nameRegex.test(name)) {
			nameCheckMessage.text("한글 이름만 입력 가능합니다.").css("color", "red");
			$("#user_name").focus();  // 포커스를 이동
			isValid = false;
			return false; // 유효성 검사 결과 반환
		}

		return isValid;

	}

	// 전화번호 유효성 확인
	function validateTel() {
		// 전화번호 정규 표현식 : XXX-XXXX-XXXX 형식
		const telRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;

		var tel = $("#user_tel").val().trim().replace(/\s+/g, ''); // 공백 제거
		var telCheckMessage = $("#telCheckMessage");

		// 초기화
		let isValid = true;
		// 에러 메시지 초기화
		telCheckMessage.text("");

		if (tel === "") {
			telCheckMessage.text("전화번호를 입력해주세요").css("color", "red");
			$("#user_tel").focus();
			isValid = false;
			return false;
		}
		if (!telRegex.test(tel)) {
			telCheckMessage.text("전화번호는 XXX-XXXX-XXXX 형식으로 입력해주세요").css("color", "red");
			$("#user_tel").focus();
			isValid = false;
			return false;
		}

		return isValid;
	}

	const hypenTel = (target) => {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	}

	function validateAddr() {
    var zipcode = $("#user_zipcode").val().trim();
    var addr1 = $("#user_addr1").val().trim();
    var addr2 = $("#user_addr2").val().trim();
    var zipcodeCheckMessage = $("#zipcodeCheckMessage");
    var addr1CheckMessage = $("#addr1CheckMessage");
    var addr2CheckMessage = $("#addr2CheckMessage");

    isValid = true;

    if (zipcode === "") {
        zipcodeCheckMessage.text("우편번호를 입력해주세요").css("color", "red");
        $("#user_zipcode").focus();
        isValid = false;
        return false;
    }
    if (addr1 === "") {
        addr1CheckMessage.text("주소를 입력해주세요").css("color", "red");
        $("#user_addr1").focus();
        isValid = false;
        return false;
    }
    if (addr2 === "") {
        addr2CheckMessage.text("상세주소를 입력해주세요").css("color", "red");
        $("#user_addr2").focus();
        isValid = false;
        return false;
    }

    return true;
}


	// 이메일 직접입력 
	$(function () {
		//직접입력 인풋박스 기존에는 숨어있다가
		$("#user_email3").hide();
		$("#user_email2").change(function () {
			//직접입력을 누를 때 나타남
			if ($("#user_email2").val() == "direct") {
				$("#user_email3").show();
				$("#user_email2").css("width", "25px")
			} else {
				$("#user_email3").hide();
				$("#user_email2").css("width", ""); // 기본 크기로 되돌림
			}
		})
	});

	function validateEmail() {
    var email = $("#user_email1").val().trim();
    var emailDomain = $("#user_email2").val(); // 지정된 도메인 값
    var emailDirect = $("#user_email3").val().trim(); // 직접 입력한 도메인 값
    var emailCheckMessage = $("#emailCheckMessage");

    // 이메일 도메인이 직접 입력인 경우 처리
    if (emailDomain == "direct") {
        emailDomain = emailDirect;
    }

    isValid = true;

    // 이메일이 비어있는지 확인
    if (email === "" || (!emailDomain && emailDomain !== "direct")) {
        emailCheckMessage.text("이메일을 입력해주세요.").css("color", "red");
        $("#user_email1").focus();
        isValid = false;
        return false;
    }

    // 이메일 전체 주소 합치기
    var fullEmail = email + "@" + emailDomain;

    // 정규식으로 이메일 유효성 검사
    var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    if (!emailRegex.test(fullEmail)) {
        emailCheckMessage.text("유효한 이메일 주소를 입력해주세요.").css("color", "red");
        isValid = false;
        return false;
    }

    emailCheckMessage.text("유효한 이메일입니다.").css("color", "green");
	$("#hidden_user_email").val(fullEmail);
    return true;
}



	// 이메일 인증 
	function emailCheck() {

		if(!validateEmail()) return;

		var emailCheckMessage = $("#emailCheckMessage");

		$.ajax({
			url: '/jh/sendAuthCode',
			type: 'POST',
			data: { 'user_email': $("#hidden_user_email").val() },
			success: function (response) {
				emailCheckMessage.text("인증번호가 전송 되었습니다").css("color", "green");
			},
			error: function () {
				emailCheckMessage.text("인증번호가  전송 실패하였습니다.").css("color", "red");
			}
		});
	}

	// 인증번호 확인
	function verifyAuthCode() {
		var authCode = $("#auth_code").val().trim();
		$.ajax({
			url: '/jh/verifyAuthCode',
			type: 'POST',
			data: { 'auth_code': authCode },
			success: function (response) {
				if (response.valid) {
					$("#verifyAuthCodeMessage").text("인증번호가 확인되었습니다.");
					$("#verifyAuthCodeMessage").css("color", "green");
					isAuthCodeVerified = true;
				} else {
					$("#verifyAuthCodeMessage").text("인증번호가 틀렸습니다.");
					$("#verifyAuthCodeMessage").css("color", "red");
					isAuthCodeVerified = false;
				}
			},
			error: function () {
				alert("인증번호 확인 중 오류가 발생했습니다.");
			}
		});
	}


	function chkValue() {
		var auth_code = $("#auth_code").val().trim();

		var verifyAuthCodeMessage = $("#verifyAuthCodeMessage");


		isValid = true;

		if (!validateId() || !validatePw() || !validatePwConfirm() || !validateName() || !validateTel() || !validateEmail() || !validateAddr()) {
			return false; // 유효하지 않으면 폼 제출 중단
		}

		// 중복 확인 상태 체크
		if (!isIdConfirmed) {
			$("#idCheckMessage").text("아이디 중복 확인을 해주세요.").css("color", "red");
			$("#user_id").focus();
			return false; // 중복 확인이 안 되었으므로 제출 금지
		}

		if (!isAuthCodeVerified) {
			$("#verifyAuthCodeMessage").text("인증번호를 확인 해주세요.").css("color", "red");
			$("#auth_code").focus();
			return false;
		}

		return isValid;

	}

			</script>
</head>
<body>

<header class="header">
    <h1>헤더</h1>
</header>
	
<div class="body">
    <div class="title">회원가입</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <div class="active">회원가입</div>
            <a href="/loginForm"><div>로그인</div></a>
            <a href="/loginForm"><div>아이디 찾기</div></a>
            <a href="/loginForm"><div>비밀번호 찾기</div></a>
        </div>
        
        
        
        
     <div class="content">
    <form method="post" name="frm" action="/jh/join" onsubmit="return chkValue();">
        <!-- 이메일 -->
        <div class="form-group">
            <label for="user_email">이메일</label>
            <input type="text" id="user_email1" name="user_email1" style="width: 30px;">
            &nbsp;@&nbsp;
            <input type="text" id="user_email3" name="selboxDirect" />
            <select id="user_email2" name="email_domain">
                <option value="naver.com">naver.com</option>
                <option value="daum.net">daum.net</option>
                <option value="gmail.com">gmail.com</option>
                <option value="hanmail.net">hanmail.net</option>
                <option value="direct">직접입력</option>
            </select>
            <button type="button" id="emailCheckBtn">인증번호 전송</button>
            <p class="check_font" id="emailCheckMessage"></p>
            <input type="hidden" id="hidden_user_email" name="eml" />
        </div>

        <!-- 이메일 인증 번호 -->
        <div class="form-group">
            <label for="auth_code">인증번호</label>
            <input type="text" id="auth_code" name="auth_code">
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
                <button type="submit" id="submitBtn">회원가입</button>
            </div>
        </div>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // URL에서 mbr_se 매개변수 가져오기
        const urlParams = new URLSearchParams(window.location.search);
        const mbr_se = urlParams.get('mbr_se');

        // mbr_se가 존재할 경우 hidden input에 설정
        if (mbr_se) {
            const hiddenInput = document.createElement("input");
            hiddenInput.type = "hidden";
            hiddenInput.name = "mbr_se"; // 이름을 mbr_se로 설정
            hiddenInput.value = mbr_se; // URL에서 가져온 값으로 설정
            document.forms["frm"].appendChild(hiddenInput); // 폼에 추가
        }
    });
</script>


					
					
					
					
					
					
					
					
    </div>
</div>

<footer class="footer">
        &copy; 2024 MyPage Corp. All rights reserved.
</footer>




	
</body>
</html>