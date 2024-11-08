<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/css/mn_insertPage.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의작성페이지</title>
<header>
		<%@ include file="../header.jsp" %>
</header>
<script type="text/javascript">
	// input 꾸미기용
	document.addEventListener('DOMContentLoaded', () => {
    // insert_page_box 및 insert_page_input_3 내의 모든 텍스트 입력 필드를 선택
    const inputs = document.querySelectorAll('.insert_page_box .insert_page_input_text, .insert_page_input_3 .insert_page_input_text');

    inputs.forEach(input => {
        const label = input.nextElementSibling;

        // 포커스 시 label 텍스트 변경
        input.addEventListener('focus', () => {
            label.textContent = label.getAttribute('for') === 'summary' ? '강의개요' :
                                label.getAttribute('for') === 'purpose' ? '교육목적' :
                                label.getAttribute('for') === 'content' ? '교육내용' :
                                label.getAttribute('for') === 'reference' ? '참고자료' : 
                                label.getAttribute('for') === 'notes' ? '특이사항' : 
                                label.getAttribute('for') === 'referenceInfo' ? '참고자료내용' :
                                label.getAttribute('for') === 'specialNotes' ? '수료기준' :
                                label.getAttribute('for') === 'evaluationMethod' ? '평가방법' : 
                                '기타'; // 조건에 따라 맞는 내용 설정
        });

        // 블러 시 입력값이 없으면 label을 원래 상태로 복원
        input.addEventListener('blur', () => {
            if (input.value.trim() === '') {
                label.textContent = label.getAttribute('for') === 'summary' ? '강의개요를 입력해주세요.' :
                                    label.getAttribute('for') === 'purpose' ? '교육목적을 입력해주세요.' :
                                    label.getAttribute('for') === 'content' ? '교육내용을 입력해주세요.' :
                                    label.getAttribute('for') === 'reference' ? '참고자료내용에 대해 입력해주세요.' :
                                    label.getAttribute('for') === 'notes' ? '특이사항에 대해서 설명해주세요.' :
                                    label.getAttribute('for') === 'referenceInfo' ? '참고자료내용을 입력해주세요.' :
                                    label.getAttribute('for') === 'specialNotes' ? '수료기준을 입력해주세요.' :
                                    label.getAttribute('for') === 'evaluationMethod' ? '평가방법을 입력해주세요.' :
                                    '기타'; // 원래 텍스트로 복원
		            }
		        });
		    });
		});

	document.addEventListener('DOMContentLoaded', () => {
	    const inputs = document.querySelectorAll('.insert_page_input_2 .input-container input');

	    inputs.forEach(input => {
	        const label = input.previousElementSibling;
	        
	        input.addEventListener('focus', () => {
	            label.classList.add('focused'); // 라벨에 focused 클래스 추가
	        });

	        input.addEventListener('blur', () => {
	            if (input.value === '') {
	                label.classList.remove('focused'); // 필드가 비어 있으면 focused 클래스 제거
	            }
	        });
	    });
	});
	//날짜 중복&과거선택 불가능
	$(document).ready(function() {
        // 오늘 날짜를 기준으로 minDate 설정
        const today = new Date();

        // Datepicker 초기화
        $("#startDate").datepicker({
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            changeYear: true,
            minDate: today, // 시작 날짜는 오늘 이후로만 선택 가능
            onClose: function(selectedDate) {
                // 시작 날짜가 선택되면, 종료 날짜의 최소값을 시작 날짜로 설정
                $("#endDate").datepicker("option", "minDate", selectedDate);
            }
        });

        $("#endDate").datepicker({
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            changeYear: true,
            minDate: today, // 종료 날짜도 오늘 이후로만 선택 가능
            onClose: function(selectedDate) {
                // 종료 날짜가 선택되면, 시작 날짜의 최대값을 종료 날짜로 설정
                $("#startDate").datepicker("option", "maxDate", selectedDate);
            }
        });
    });
	
	//시간 새창열기
	function openTimePopup() {
    // 팝업 창을 열어 시간 값을 입력받음 (여기서는 모의 코드)
    const popupWindow = window.open('<c:url value="/mn/timePopup"/>', 'timePopup', 'width=600,height=400');

    // 팝업에서 선택된 값을 받아오는 함수
    popupWindow.onunload = function() {
        // 값이 입력되었을 때만 .focused 클래스를 추가하여 라벨이 위로 올라가게 함
        const startTimeInput = document.getElementById('startTimeInput');
        const endTimeInput = document.getElementById('endTimeInput');
        const dayInput = document.getElementById('dayInput');
        

        if (startTime) {
            startTimeInput.value = startTime;
            startTimeInput.previousElementSibling.classList.add('focused');
        }
        if (endTime) {
            endTimeInput.value = endTime;
            endTimeInput.previousElementSibling.classList.add('focused');
        }
        if (day) {
            dayInput.value = day;
            dayInput.previousElementSibling.classList.add('focused');
        }
    };
}
	
	let containerCount = 1; // 항상 1번은 고정
	let availableWeeks = []; // 재사용 가능한 주차 번호를 저장할 배열
	let containersData = []; // 각 컨테이너의 데이터를 저장할 배열

	function addContainer() {
	    const originalContainer = document.querySelector('.insert_page_lctr_container1');

	    if (!originalContainer) {
	        console.error('원본 컨테이너를 찾을 수 없습니다.');
	        return;
	    }

	    const newContainer = document.createElement('div');
	    newContainer.classList.add('insert_page_lctr_container1', 'insert_page_lctr_container');

	    let weeksNumber;
	    if (availableWeeks.length > 0) {
	        weeksNumber = availableWeeks.pop(); // 삭제된 주차 번호 재사용
	    } else {
	        weeksNumber = containerCount + 1; // 2번부터 시작
	        containerCount++;
	    }

	    const contentDiv = originalContainer.querySelector('.insert_page_lctr_content').cloneNode(true);

	    const weeksInput = contentDiv.querySelector('#lcrt_weeks');
	    weeksInput.id = 'lcrt_weeks_' + weeksNumber;
	    weeksInput.value = weeksNumber;

	    const fileInputContainer = contentDiv.querySelector('.form-group');
	    const fileInput = fileInputContainer.querySelector('input[type="file"]');
	    fileInput.id = 'fileUpload_' + weeksNumber;
	    /* fileInput.name = 'file_' + weeksNumber; // 파일 이름을 고유하게 설정 */

	    let fileNameDisplay = contentDiv.querySelector('p.check_font');
	    if (!fileNameDisplay) {
	        fileNameDisplay = document.createElement('p');
	        fileNameDisplay.classList.add('check_font');
	        fileInputContainer.appendChild(fileNameDisplay);
	    }
	    fileNameDisplay.id = 'buzFileCheckMessage_' + weeksNumber;

	    fileInput.addEventListener('change', function () {
	        fileNameDisplay.textContent = fileInput.files.length > 0 ? fileInput.files[0].name : '';
	    });

	    const planTextarea = originalContainer.querySelector('#lcrt_plan').cloneNode(true);
	    planTextarea.id = 'lcrt_plan_' + weeksNumber;

	    const deleteButton = document.createElement('button');
	    deleteButton.textContent = '취소';
	    deleteButton.classList.add('delete-button');
	    deleteButton.onclick = function () {
	        newContainer.remove();
	        if (weeksNumber > 1) {
	            availableWeeks.push(weeksNumber); // 삭제된 주차 번호 재사용
	        }
	        reorderContainers(); // 남은 컨테이너들 재정렬
	        // 삭제할 때 컨테이너 데이터도 삭제
	        containersData = containersData.filter(data => data.lctr_weeks !== weeksNumber);
	    };

	    const saveButton = document.createElement('button');
	    saveButton.textContent = '저장';
	    saveButton.classList.add('save-button');
	    saveButton.onclick = function () {
	        const planValue = planTextarea.value;
	        const fileValue = fileInput.files.length > 0 ? fileInput.files[0].name : null;

	        // 컨테이너 데이터 배열에 추가
	        containersData.push({
	            lctr_weeks: weeksNumber,
	            lctr_num: 0,
	            lctr_ymd: null,
	            lctr_plan: planValue,
	            lctr_data: fileValue,
	            lctrm_num: 501
	        });

	        console.log(containersData); // 배열의 내용을 콘솔에 출력
	    };

	    newContainer.appendChild(contentDiv);
	    newContainer.appendChild(planTextarea);
	    newContainer.appendChild(deleteButton);

	    document.getElementById('lctr-containers').appendChild(newContainer);
	}

	function reorderContainers() {
	    const containers = document.querySelectorAll('.insert_page_lctr_container1');
	    let index = 1; // 1번은 고정

	    containers.forEach(container => {
	        const weeksInput = container.querySelector('input#lcrt_weeks');
	        if (weeksInput) {
	            if (index > 1) {
	                weeksInput.value = index;
	                weeksInput.id = 'lcrt_weeks_' + index;
	                const fileInput = container.querySelector('input[type="file"]');
	                fileInput.id = 'fileUpload_' + index;
	                fileInput.name = 'file_' + index; // 파일 이름 속성 고유하게 설정
	                const fileNameDisplay = container.querySelector('p.check_font');
	                fileNameDisplay.id = 'buzFileCheckMessage_' + index;
	            }
	            index++;
	        }
	    });
	}

	// 데이터 저장용 함수
	function saveData() {
	    const allWeeks = containersData.map(data => data.lctr_weeks);
	    const allPlans = containersData.map(data => data.lctr_plan);
	    const allFiles = containersData.map(data => data.lctr_data);

	    // AJAX 요청을 통해 데이터 전송
	    fetch('/mn/insertPage', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	            'Accept': 'application/json'
	        },
	        body: JSON.stringify({
	            lctr_weeks: allWeeks,
	            lctr_plan: allPlans,
	            file: allFiles // 파일은 Blob으로 변환해서 보내는 추가적인 로직이 필요할 수 있습니다.
	        })
	    })
	    .then(response => {
	        if (response.ok) {
	            return response.json();
	        }
	        throw new Error('네트워크 응답이 올바르지 않습니다.');
	    })
	    .then(data => {
	        console.log('성공:', data);
	    })
	    .catch(error => {
	        console.error('문제가 발생했습니다:', error);
	    });
	}


	// 초기 컨테이너에서 파일 선택 이벤트 리스너 설정
	document.querySelector('#fileUpload').addEventListener('change', function() {
	    const fileNameInput = document.querySelector('.check_font'); // 초기 컨테이너의 p 태그 선택
	    fileNameInput.textContent = this.files.length > 0 ? this.files[0].name : '';
	});



	function updateFileName() {
        const fileInput = document.getElementById('fileUpload');
        const fileNameField = document.getElementById('fileUploadName');

        if (fileInput.files.length > 0) {
            const fileName = fileInput.files[0].name;
            fileNameField.value = fileName; // 파일 이름을 표시
        } else {
            fileNameField.value = ''; // 선택된 파일이 없으면 비우기
        }
    }

	function chkValue() {
		if (frm.olineoff_type.value == -1) {
			alert("강의 온/오프라인을 선택해주세요.");
			frm.olineoff_type.focus();
			return false;
		}
		if (!frm.lctr_name.value) {
			alert("강의명을 작성해주세요.");
			frm.lctr_name.focus();
			return false;
		}
		if (!frm.lctr_otln.value) {
			alert("강의개요를 작성해주세요.");
			frm.lctr_otln.focus();
			return false;
		}
		if (!frm.edu_prps.value) {
			alert("교육목적을 작성해주세요.");
			frm.edu_prps.focus();
			return false;
		}
		if (!frm.edu_cn.value) {
			alert("교육내용을 작성해주세요.");
			frm.edu_cn.focus();
			return false;
		}
		if (!frm.ref_data_cn.value) {
			alert("참고자료내용을 작성해주세요.");
			frm.ref_data_cn.focus();
			return false;
		}
		if (!frm.excptn_mttr.value) {
			alert("특이사항을 작성해주세요.");
			frm.excptn_mttr.focus();
			return false;
		}
		if (!frm.bgng_ymd.value) {
			alert("시작날짜를 설정해주세요.");
			frm.bgng_ymd.focus();
			return false;
		}
		if (!frm.end_ymd.value) {
			alert("종료날짜를 설정해주세요.");
			frm.end_ymd.focus();
			return false;
		}
		if (!frm.fnsh_scr.value) {
			alert("수료기준을 작성해주세요.");
			frm.fnsh_scr.focus();
			return false;
		}
		if (!frm.evl_mthd.value) {
			alert("평가방법을 작성해주세요.");
			frm.evl_mthd.focus();
			return false;
		}
		if (frm.pscp_nope.value == 0) {
			alert("인원수를 선택해주세요.");
			frm.pscp_nope.focus();
			return false;
		}
		
		return true;
	}
	
</script>
</head>
<body>
	
	
	<div class="insert_page_container">
		<div class="insert_page_grid_container">
			<div class="insert_page_main_sidebar">
				<%@ include file="../sidebarLctr.jsp" %>
			</div>
			<form action="/mn/insertPage" method="post" name="frm" onsubmit="return chkValue()" enctype="multipart/form-data">
				<div class="insert_page_content">
					<div class="insert_page_select">
						<select name="olineoff_type">
							<option value="-1">온/오프라인선택</option>
							<option value="5">온라인</option>
							<option value="0">오프라인</option>
						</select>
					</div>
					<div class="insert_page_input_container">
						<input type="text" class="insert_page_input_title" id="title" placeholder="" name="lctr_name"/>
	   					<label for="title" class="input-label">강의명을 입력해주세요.</label>
					</div>
					<div class="insert_page_input_1">
						<div class="insert_page_title">강의정보</div>
						<div class="insert_page_box">
						    <div class="insert_page_input_container">
						        <input type="text" class="insert_page_input_text" id="summary" placeholder="" name="lctr_otln"/>
						        <label for="summary" class="input-label">강의개요를 입력해주세요.</label>
						    </div>
						    <div class="insert_page_input_container">
						        <input type="text" class="insert_page_input_text" id="purpose" placeholder="" name="edu_prps"/>
						        <label for="purpose" class="input-label">교육목적을 입력해주세요.</label>
						    </div>
						    <div class="insert_page_input_container">
						        <input type="text" class="insert_page_input_text" id="content" placeholder="" name="edu_cn"/>
						        <label for="content" class="input-label">교육내용을 입력해주세요.</label>
						    </div>
						    <div class="insert_page_input_container">
						        <input type="text" class="insert_page_input_text" id="reference" placeholder="" name="ref_data_cn"/>
						        <label for="reference" class="input-label">참고자료내용에 대해 입력해주세요.</label>
						    </div>
						    <div class="insert_page_input_container">
						        <input type="text" class="insert_page_input_text" id="notes" placeholder="" name="excptn_mttr"/>
						        <label for="notes" class="input-label">특이사항에 대해서 설명해주세요.</label>
						    </div>
						</div>
	
					</div>
					<div class="insert_page_input_2">
					    <div class="insert_page_title">강의기간</div>
					    <!-- 날짜 입력 필드 -->
					    <div class="insert_page_date_box">
					        <div class="input-container">
					            <label for="startDate" class="input-label">시작 날짜</label>
					            <input type="text" id="startDate" name="bgng_ymd" class="insert_page_input_date_text">
					        </div>
					        <div class="input-container">
					            <label for="endDate" class="input-label">종료 날짜</label>
					            <input type="text" id="endDate" name="end_ymd" class="insert_page_input_date_text">
					        </div>
					    </div>
					    
					    <!-- 시간 및 요일 입력 필드 -->
					    <div class="insert_page_time_box">
						    <div class="input-container">
						        <label for="startTimeInput" class="input-label">시작 시간</label>
						        <input type="text" id="startTimeInput" name="bgng_tm" readonly>
						    </div>
						    <div class="input-container">
						        <label for="endTimeInput" class="input-label">종료 시간</label>
						        <input type="text" id="endTimeInput" name="end_tm" readonly>
						    </div>
						    <div class="input-container">
						        <label for="dayInput" class="input-label">요일</label>
						        <input type="text" id="dayInput" name="dow_day" readonly>
						    </div>
						    <input type="text" id="lctrmNumInput" name="lctrm_num" />
						    <button type="button" class="insert_page_time_but" onclick="openTimePopup()">시간검색</button>
						</div>
					</div>
					<div class="insert_page_input_3">
					    <div class="insert_page_title">기타사항</div>
					    <!-- <div class="insert_page_input_container">
					        <input type="text" class="insert_page_input_text" id="referenceInfo" placeholder="" />
					        <label for="referenceInfo" class="input-label">참고자료내용을 입력해주세요.</label>
					    </div> -->
					    <div class="insert_page_input_container">
					        <input type="text" class="insert_page_input_text" id="specialNotes" placeholder="" name="fnsh_scr"/>
					        <label for="specialNotes" class="input-label">수료기준을 설명해주세요.</label>
					    </div>
					    <div class="insert_page_input_container">
					        <input type="text" class="insert_page_input_text" id="evaluationMethod" placeholder="" name="evl_mthd"/>
					        <label for="evaluationMethod" class="input-label">평가방법을 입력해주세요.</label>
					    </div>
					    <div class="insert_numpeople">
						    <select name="pscp_nope">
						   		<option selected="selected" value="0">인원수를 선택해주세요.</option>
						        <c:forEach var="people" begin="1" end="25">
						            <option value="${people}">${people}</option>
						        </c:forEach>
						    </select>
						</div>
					</div>
					<div class="insert_page_input_4">
					    <div class="insert_page_title">주차별 계획</div>
					    <div class="insert_page_lctr_containers" id="lctr-containers">
						    <div class="insert_page_lctr_container1 insert_page_lctr_container">
						        <div class="insert_page_lctr_content">
						            <input type="text" id="lcrt_weeks" class="insert_page_lctr_input1" value="1" name="lctr_weeks[]" readonly>
						            <div class="form-group">
						                <label for="file">파일</label>
						                <input class="form-control-file" id="files" name="files" type="file" multiple>
						            </div>
						        </div>
						        <textarea type="text" id="lcrt_plan" class="insert_page_input_text" name="lctr_plan[]" placeholder="강의계획을 작성해주세요."></textarea>
						    </div>
						</div>
					    <button type="button" class="insert_page_lctr_plus" onclick="addContainer()">추가하기</button>
					</div>
					<div class="insert_page_but_end">
						<button type="submit">완료하기</button>
						<button type="button">목록으로</button>
					</div>
				</div>
			</form>	
		</div>
	
	</div>
	
	<footer>
		<%@ include file="../footer.jsp" %>
	</footer>
</body>
</html>