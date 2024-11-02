		function toggleButton() {
		    const checkBox1 = document.getElementById('agree1');
		    const checkBox2 = document.getElementById('agree2');
		    const checkBox3 = document.getElementById('agree3');
		    const submitBtn = document.getElementById('submitBtn');
		
		    // agree3 체크 여부 결정
		    checkBox3.checked = checkBox1.checked && checkBox2.checked;
		
		    // 체크박스 상태에 따라 버튼 활성화/비활성화 및 CSS 클래스 변경
		    if (checkBox1.checked && checkBox2.checked) {
		        submitBtn.disabled = false; // 활성화
		        submitBtn.classList.add('active'); // 활성화 스타일 추가
		    } else {
		        submitBtn.disabled = true; // 비활성화
		        submitBtn.classList.remove('active'); // 활성화 스타일 제거
		    }
		}
		
		function toggleAgreeCheckboxes() {
		    const checkBox1 = document.getElementById('agree1');
		    const checkBox2 = document.getElementById('agree2');
		    const checkBox3 = document.getElementById('agree3');
		
		    // agree3 체크 시 agree1과 agree2 체크
		    if (checkBox3.checked) {
		        checkBox1.checked = true;
		        checkBox2.checked = true;
		    } else {
		        // agree3 체크 해제 시 agree1과 agree2 체크 해제
		        checkBox1.checked = false;
		        checkBox2.checked = false;
		    }
		
		    // 상태 업데이트
		    toggleButton();
		}
		
		// 페이지 로드 시 버튼 비활성화 상태로 시작
		window.onload = function() {
		    const submitBtn = document.getElementById('submitBtn');
		    submitBtn.disabled = true; // 기본 비활성화
		    submitBtn.classList.remove('active'); // 기본 스타일 제거
		};
