<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석입력(교수용)</title>

<style type="text/css">
/* 메인 콘텐츠 영역 */
    .main {
        background-color: #fff;

        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    /* 섹션 제목 스타일 */
    h1 {
        font-size: 28px;
        color: #134b84;
        border-bottom: 2px solid #134b84;
        padding-bottom: 15px;
        margin-bottom: 20px;
        text-align: center;
    }

    /* 출결 입력 테이블 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th {
        background-color: #134b84;
        color: white;
        font-weight: normal;
        text-align: center !important;
        padding: 12px;
        font-size: 16px;
    }

    td {
        padding: 12px;
        text-align: center;
        background-color: #fff;
        color: #333;
        font-size: 14px;
    }

    /* 출결 상태 라디오 버튼 스타일 */
    .btn-check {
        display: none;
    }

    label {
        cursor: pointer;
        padding: 8px 12px;
        border-radius: 5px;
        font-size: 14px;
    }

    /* 라디오 버튼 선택 시 색상 변화 */
    .btn-outline-primary:checked + label {
        background-color: #134b84;
        color: white;
    }

    /* 라디오 버튼 상태에 따른 색상 */
    .btn-outline-primary {
        border-color: #134b84;
        color: #134b84;
    }

    .btn-outline-primary:hover {
        background-color: #e5e5e5;
    }

    .btn-outline-primary:checked {
        background-color: #134b84;
        color: white;
    }
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<script type="text/javascript">
	// 아작스로 주차 별 list 불러오기
	$(document).ready(function () {
		$('#lctrWeekSelect').change(function() {
			let lctr_weeks = $(this).val();
			let lctr_num = $(this).data('lctr-num');  // 이미 HTML에서 data-lctr-num으로 서버값을 전달받음
			$('#lctrWeeksHidden').val(lctr_weeks); // 선택된 주차를 hidden input에 설정
			
			$.ajax({
				url: "<%=request.getContextPath()%>/hs/lecWeekProf",
				type: "POST",
				dataType: "JSON",
				data: {lctr_weeks : lctr_weeks, lctr_num: lctr_num},
				success:function(lecWeekAttend) {
					console.log('AJAX 호출 성공:', lecWeekAttend);
					
					// 기존 출결 데이터 초기화
					$('tbody#attendList').empty();
					
					// 서버에서 받은 데이터로 새로운 행 추가
					$.each(lecWeekAttend, function(index, hs_attend) {
						console.log('현재 처리 중인 학생: ',hs_attend);	//속성 확인
						console.log('학번:', hs_attend.unq_num, '이름:', hs_attend.stdnt_nm);
						console.log('학번 타입:', typeof hs_attend.unq_num); // 타입 확인
						
						let studentId = hs_attend.unq_num;
			            let studentName = hs_attend.stdnt_nm;
			            let atndc_type = hs_attend.atndc_type; // 출결 상태
			            let index1 = index+1;
			            console.log('each 학번:', studentId, 'each 이름:', studentName);
						console.log('index', index);
						let row = `
							<tr>
								<td>`+index1+`</td>
								<td>`+studentId+`</td>
								<td>`+studentName+`</td>
								<td>
									<input type="radio" class="btn-check" name="atndc_type_`+studentId+`" id="option100_`+studentId+`" autocomplete="off" value="100" ` + (atndc_type === "100" ? 'checked' : '') + `>
									<label class="btn btn-outline-primary btn-sm" for="option100_`+studentId+`">출석</label>
									
									<input type="radio" class="btn-check" name="atndc_type_`+studentId+`" id="option110_`+studentId+`" autocomplete="off" value="110" ` + (atndc_type === "110" ? 'checked' : '') + `>
									<label class="btn btn-outline-primary btn-sm" for="option110_`+studentId+`">지각</label>
									
									<input type="radio" class="btn-check" name="atndc_type_`+studentId+`" id="option120_`+studentId+`" autocomplete="off" value="120" ` + (atndc_type === "120" ? 'checked' : '') + `>
									<label class="btn btn-outline-primary btn-sm" for="option120_`+studentId+`">결석</label>
								</td>
							</tr>
						`;
						
						// 130이면 100 체크
				        /* if (atndc_type === "130") {
				            row = row.replace(`value="100"`, `value="100" checked`);
				        } */
						
						console.log('추가된 행 :', row);	//추가된 행 확인
						$('tbody#attendList').append(row);
					});
				}, 
				error: function(xhr, status, error) {
					console.error('Error fetching data:', error);
				}
			});
		});
		
		let attendanceData = [];
		
		// 출석 상태 라디오 버튼 클릭 시 자동 업데이트
        $(document).on('change', 'input[type="radio"][name^="atndc_type"]', function() {
            let lctr_num = $('#lctrNumHidden').val(); // 강의 번호
            let lctr_weeks = $('#lctrWeeksHidden').val(); // 주차
            let row = $(this).closest('tr');  // 현재 클릭한 라디오 버튼을 포함하는 <tr> 찾기
            let studentId = row.find('td:nth-child(2)').text();
            let attendanceType = $(this).val();
            console.log('studentId->',studentId);
            console.log('lctr_num->',lctr_num);
            console.log('lctr_weeks->',lctr_weeks);
            console.log('attendanceType->',attendanceType);
            

         	/* // 출석 정보를 객체 형태로 배열에 추가
            let attendance = {
                unq_num: studentId,
                atndc_type: attendanceType,
            };
            
         	// 배열에 추가
            attendanceData.push(attendance);
         	
            console.log('현재 출석 데이터 배열:', attendanceData); */
            
            // AJAX로 출석 상태 업데이트
            $.ajax({
                url: "<%=request.getContextPath()%>/hs/AttendUpdate",
                type: "POST",
                contentType: "application/json", // JSON 형식으로 전송
                data: JSON.stringify({
                    lctr_weeks: lctr_weeks,
                    lctr_num: lctr_num,
                    unq_num: studentId,
                    atndc_type: attendanceType
                }),	// JSON 형식으로 데이터 전송
                success: function(response) {
                    console.log('출석 업데이트 성공:', response);
                },
                error: function(xhr, status, error) {
                    console.error('출석 업데이트 실패:', error);
                }
            });
        });
		

        
        $('#markAllPresent').click(function() {
            // 사용자에게 확인 메시지 표시
            let confirmResult = confirm("전체 출석 처리하시겠습니까?");
            
            // 사용자가 '확인'을 누른 경우
            if (confirmResult) {
                let lctr_num = $('#lctrNumHidden').val(); // 강의 번호
                let lctr_weeks = $('#lctrWeeksHidden').val(); // 주차

                let attendanceData = [];  // 출석 데이터를 담을 배열

                // 모든 학생에 대해 출석 상태를 '100'으로 설정
                $('tbody#attendList tr').each(function() {
                    let studentId = $(this).find('td:nth-child(2)').text(); // 학번 추출

                    // 출석 상태 '100'으로 설정
                    $(this).find('input[type="radio"][value="100"]').prop('checked', true);

                    // 출석 정보를 객체 형태로 배열에 추가
                    let attendance = {
                        unq_num: studentId,
                        atndc_type: '100',  // 출석 상태는 '100'
                        lctr_num: lctr_num,
                        lctr_weeks: lctr_weeks
                    };

                    // 배열에 추가
                    attendanceData.push(attendance);
                });

                console.log('전체 출석 처리 후 출석 데이터 배열:', attendanceData);

                // 출석 정보를 JSON으로 서버에 전송
                $.ajax({
                    url: "<%=request.getContextPath()%>/hs/AttendUpdate1",
                    type: "POST",
                    contentType: "application/json",  // JSON 형식으로 전송
                    data: JSON.stringify(attendanceData),  // JSON 배열로 변환하여 전송
                    success: function(response) {
                        console.log('출석 업데이트 성공:', response);
                    },
                    error: function(xhr, status, error) {
                        console.error('출석 업데이트 실패:', error);
                    }
                });
            } else {
                console.log('전체 출석 처리를 취소했습니다.');
            }
        });


	})
</script>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">출결입력</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>출결관리</h1>
			<table>
				<tr>
					<td colspan="2">
						<select class="form-select" id="lctrWeekSelect" aria-label="Default select example" data-lctr-num="${lctr.lctr_num }">
							<option>주차 선택</option>
							<c:forEach var="hs_attend" items="${weekList }">
								<option value="${hs_attend.lctr_weeks }"
								<c:if test="${hs_attend.lctr_weeks == defaultWeek}">selected</c:if>>
									${hs_attend.lctr_weeks } 주차 (${hs_attend.lctr_ymd })
								</option>
							</c:forEach>
						</select>
					</td>
					<td><button id="markAllPresent" class="btn btn-outline-primary">전체 출석처리</button></td>
				</tr>
				<tr list="lctr_weeks"></tr>
				<tr>
					<th></th>
					<th>학번</th>
					<th>이름</th>
					<th>출결상태</th>
				</tr>
				<tbody id="attendList"><!-- 출결 목록이 여기에 동적으로 추가됩니다. --></tbody>
				<input type="hidden" id="lctrNumHidden" value="${lctr.lctr_num}">
				<input type="hidden" id="lctrWeeksHidden" value="">
			</table>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>