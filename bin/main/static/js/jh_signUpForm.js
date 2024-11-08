

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

  
}



//인증번호 중복확인하는 로직
$(document).ready(function() {
    var isEmailChecked = false;  // 이메일 중복 확인 여부

    // 이메일 설정 함수
    function setEmail() {
        var email1 = $("#user_email1").val();
        var emailDomain = $("#user_email2").val();
        var email3 = $("#user_email3").val();

        if (emailDomain === "direct") {
            $("#hidden_user_email").val(email1 + "@" + email3);
        } else {
            $("#hidden_user_email").val(email1 + "@" + emailDomain);
        }
    }

    // 중복 확인 버튼 클릭 시 이메일 설정 및 AJAX 호출
    $("#emailduplicate").click(function() {
        var email1 = $("#user_email1").val().trim();
        var email3 = $("#user_email3").val().trim();
        var emailDomain = $("#user_email2").val();

        // 1. user_email1이 5글자 이상인지 확인
        if (email1.length < 5) {
            alert("올바른 이메일 형식이 아닙니다.");
            $("#user_email1").focus();  // user_email1에 포커스
            return; // 함수 종료
        }

        // 2. user_email3이 이메일 형식인지 확인 (직접 입력일 경우)
        var validDomains = ["naver.com", "daum.net", "gmail.com", "hanmail.net"];
        if (emailDomain === "direct" && validDomains.indexOf(email3) === -1) {
            alert("올바른 이메일 주소를 입력해주세요.");
            $("#user_email3").val(""); // user_email3의 값을 지움
            $("#user_email1").focus(); // user_email1에 포커스
            isEmailChecked = false; // 중복 확인 상태 유지
            return; // 함수 종료
        }

        // 이메일 설정 및 AJAX 호출
        setEmail();

        $.ajax({
            url: '/jh/checkEmailDuplicate',
            type: 'POST',
            data: { 'email': $("#hidden_user_email").val() },
            success: function(response) {
                if (response.isDuplicate) {
                    $("#emailCheckMessage").text("이미 사용 중인 이메일입니다.").css("color", "red");
                    disableEmailCheckBtn();  // 버튼 비활성화
                    isEmailChecked = false;
                } else {
                    $("#emailCheckMessage").text("사용할 수 있는 이메일입니다.").css("color", "green");
                    enableEmailCheckBtn();  // 버튼 활성화
                    isEmailChecked = true;
                }
            },
            error: function() {
                $("#emailCheckMessage").text("이메일 중복 확인에 실패했습니다.").css("color", "red");
                disableEmailCheckBtn();  // 비활성화
                isEmailChecked = false;
            }
        });
    });

    // 인증번호 전송 버튼 클릭 시 중복 확인 여부 체크
    $("#emailCheckBtn").click(function() {
        if (!isEmailChecked) {
            alert("이메일 중복 확인을 먼저 해주세요.");
        } else {
            // 인증번호 전송 로직 여기에 추가
            alert("인증번호가 전송되었습니다.");
        }
    });

    // emailCheckBtn을 비활성화하고 스타일 적용
    function disableEmailCheckBtn() {
        $("#emailCheckBtn").prop("disabled", true).css({
            "background-color": "gray",
            "color": "white",
            "cursor": "not-allowed"
        });
    }

    // emailCheckBtn을 활성화하고 스타일 초기화
    function enableEmailCheckBtn() {
        $("#emailCheckBtn").prop("disabled", false).css({
            "background-color": "",
            "color": "",
            "cursor": "pointer"
        });
    }

    // 초기 상태에서 emailCheckBtn 비활성화
    disableEmailCheckBtn();
});


//인증번호 전송하지는 로직
$(document).ready(function() {
    // 초기 상태 설정
    $("#verifyAuthCodeBtn").prop("disabled", true)
        .css({
            "background-color": "gray",
            "color": "white",
            "cursor": "not-allowed"
        });

    // 이메일 인증 요청 함수
    function emailCheck() {
        if (!validateEmail()) return;

        var emailCheckMessage = $("#emailCheckMessage");

        $.ajax({
            url: '/jh/sendAuthCode',
            type: 'POST',
            data: { 'user_email': $("#hidden_user_email").val() },
            success: function(response) {
                emailCheckMessage.text("인증번호가 전송되었습니다.").css("color", "green");

                // "emailCheckBtn" 클릭 후 "verifyAuthCodeBtn" 활성화
                $("#verifyAuthCodeBtn").prop("disabled", false)
                    .css({
                        "background-color": "",
                        "color": "",
                        "cursor": ""
                    });
            },
            error: function() {
                emailCheckMessage.text("인증번호 전송실패하였습니다.").css("color", "red");
            }
        });
    }

    // 이메일 인증 버튼 클릭 이벤트
    $("#emailCheckBtn").click(function() {
        emailCheck();
    });

    // 중복 확인 함수 예제
    function validateEmail() {
        // 이메일 유효성 검사 로직을 추가하세요.
        return true; // 예시: 실제 유효성 검사 후에 true/false를 반환합니다.
    }
});


//인증번호 확인하는 로직
$(document).ready(function() {
    var isAuthCodeVerified = false;

    // 인증번호 확인 함수
    function verifyAuthCode() {
        var authCode = $("#auth_code").val().trim();

        $.ajax({
            url: '/jh/verifyAuthCode',
            type: 'POST',
            data: { 'auth_code': authCode },
            success: function(response) {
                if (response.valid) {
                    $("#verifyAuthCodeMessage").text("인증번호가 확인되었습니다.").css("color", "green");
                    isAuthCodeVerified = true;

                    // 제출 버튼 활성화
                    $("#submitBtn").prop("disabled", false).css({
                        "background-color": "", // 원래 배경색으로 복원
                        "color": "", // 원래 글씨색으로 복원
                        "cursor": "pointer" // 커서 스타일 복원
                    });

                    // 이메일 입력 필드 및 버튼 비활성화 및 밝기 감소
                    $("#user_email1, #user_email2, #user_email3, #emailduplicate, #emailCheckBtn, #verifyAuthCodeBtn, #auth_code").prop("disabled", true).css("opacity", "0.5");
                } else {
                    $("#verifyAuthCodeMessage").text("인증번호가 틀렸습니다. 다시 입력해주세요").css("color", "red");
                    isAuthCodeVerified = false;

                    // 제출 버튼 비활성화 및 스타일 변경
                    $("#submitBtn").prop("disabled", true).css({
                        "background-color": "gray", // 회색 배경
                        "color": "white", // 흰색 글씨
                        "cursor": "not-allowed" // 금지 아이콘
                    });
                }
            },
            error: function() {
                $("#verifyAuthCodeMessage").text("인증번호 확인에 실패했습니다.").css("color", "red");
            }
        });
    }

    // 인증번호 확인 버튼 클릭 이벤트
    $("#verifyAuthCodeBtn").click(function() {
        verifyAuthCode();
    });
});

	
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
    

 