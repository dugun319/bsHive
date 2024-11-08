<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>시간설정</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <style type="text/css">
        .reserved { background-color: gray; pointer-events: none; }
        .selected { background-color: lightblue; }
    </style>
</head>
<body>
    <h2>시간설정</h2>

    <!-- 서버에서 예약된 시간대를 설정 (예제 데이터) -->
    <c:set var="timeList" value="${fn:split('09:00,10:00,11:00,13:00,14:00,15:00,17:00', ',')}" />
    <c:set var="dayList" value="${fn:split('1,2,3,4,5', ',')}" />

    <div class="time_container">
        <select id="roomSelect">
        	<option selected="selected" value="0">강의실을 선택해주세요.</option>
            <c:forEach var="content" items="${codeList}">
                <option value="${content.mcode}">${content.content}</option>
            </c:forEach>
        </select>

        <table class="week_time_table">
            <thead>
                <tr>
                    <th></th>
                    <th>월</th>
                    <th>화</th>
                    <th>수</th>
                    <th>목</th>
                    <th>금</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="time" items="${timeList}">
                    <tr>
                        <th>${time}</th>
                        <c:forEach var="day" items="${dayList}">
                            <td data-day="${day}" data-time="${time}"></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button id="completeButton">완료</button>
    </div>

    <script type="text/javascript">
    $(document).ready(function() {
        // 방 번호 선택 시 AJAX로 예약 정보를 불러옴
        $('#roomSelect').change(function() {
            const roomNumber = $(this).val();
            if (roomNumber) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/getReservations',
                    type: 'GET',
                    data: { roomNumber: roomNumber },
                    success: function(reservations) {
                        // 기존 예약 상태 초기화
                        $(".week_time_table td").removeClass("reserved");

                        // 응답으로 받은 예약 정보를 바탕으로 예약 상태 표시
                        reservations.forEach(function(reservation) {
                            const dow = reservation.dow_day;
                            const startTime = reservation.bgng_tm;
                            const endTime = reservation.end_tm;

                            // 예약 시간대에 해당하는 셀에 "reserved" 클래스 추가
                            $(".week_time_table td").each(function() {
                                const day = $(this).data("day");
                                const time = $(this).data("time");

                                if (day == dow && time >= startTime && time <= endTime) {
                                    $(this).addClass("reserved");
                                }
                            });
                        });
                    },
                    error: function() {
                        alert("예약 정보를 불러오는 데 실패했습니다.");
                    }
                });
            }
        });

        // 시간대 선택 코드 
        let selectedDay = null;

        $(".week_time_table td").click(function() {
            if ($(this).hasClass("reserved")) {
                return;
            }

            const day = $(this).data("day");
            $(this).toggleClass("selected");

            if ($(".week_time_table td.selected").length === 0) {
                selectedDay = null;
            } else {
                if (selectedDay === null) {
                    selectedDay = day;
                }
                if (day !== selectedDay) {
                    alert("같은 요일의 시간대만 선택할 수 있습니다.");
                    $(this).removeClass("selected");
                    return;
                }
            }
        });

        $("#completeButton").click(function() {
            let selectedTimes = [];
            $(".week_time_table td.selected").each(function() {
                const time = $(this).data("time");
                selectedTimes.push(time);
            });
            
            const lctrmNum = $("#roomSelect").val();

            if(lctrmNum == 0) {
        		alert("강의실을 선택해주세요.");
        		$("#roomSelect").focus();
        		return false;
        	}
            
            if (selectedTimes.length > 0) {
            	
                const startTime = selectedTimes[0];
                const endTime = selectedTimes[selectedTimes.length - 1];
                const lctrmNum = $("#roomSelect").val(); // 선택된 강의실 번호를 가져옵니다.
                
                // 부모 페이지의 입력 필드에 값 설정
                window.opener.document.getElementById("startTimeInput").value = startTime;
                window.opener.document.getElementById("endTimeInput").value = endTime;
                window.opener.document.getElementById("dayInput").value = selectedDay;
                window.opener.document.getElementById("lctrmNumInput").value = lctrmNum; // lctrm_num 값 설정
                
                window.close();
            } else {
                alert("시간을 선택해 주세요.");
            }
        });
    });
    </script>
</body>
</html>
