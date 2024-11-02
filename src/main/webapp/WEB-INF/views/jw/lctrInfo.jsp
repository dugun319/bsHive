<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="header"><%@ include file="../header.jsp"%></div>
	<div class="lctrList">
		<h3>강의목록</h3>
		<table class="listOnln">
			<tbody>
				<tr class="lctrTitle">
						<th>강좌번호</th>
						<th>강좌명</th>
						<th>강좌시작일</th>
						<th>강좌종료일</th>
						<th>인원수</th>
						
				</tr>
				<c:forEach var="lctr" items="${ listOnlnLct }">
					<tr class="lctrContent">
						<td>${ lctr.lctr_num}</td>
						<td>${ lctr.lctr_name}</td>
						<td>${ lctr.bgng_ymd }</td>
						<td>${ lctr.end_ymd }</td>
						<td>${ lctr.rcrt_nope } / >${ lctr.pscp_nope }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			
		<h3>차시정보</h3>
			<table>
				<tbody>
					<tr class="unitTitle">
						<th>차시번호</th>
						<th>차시명</th>
						<th>차시재생시간</th>
						<th>차시자료</th>
					</tr>
					<c:forEach var="unit" items="${ UnitOnlnList }">
						<tr>
							<td>${ unit.unit_num }</td>
							<td>${ unit.conts_nm }</td>
							<td>
								<span class="learning-time" data-seconds="${unit.play_hr}"></span>
							</td>
							<td>
								다운로드
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
	</div>
	
</body>
<script>
    // 학습 시간을 변환하고 표시하는 함수
    function formatTime(seconds) {
        var minutes = Math.floor((seconds % 3600) / 60);
        var secs = seconds % 60;
        return minutes + "분 " + secs + "초";
    }

    // 모든 학습 시간을 변환하여 출력
    document.querySelectorAll(".learning-time").forEach(function(element) {
        var seconds = element.getAttribute("data-seconds");
        element.innerText = formatTime(seconds);
    });
</script>
</html>