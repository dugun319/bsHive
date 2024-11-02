<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
body{
	margin: 0px;
	padding: 0px;
}

header{
	padding: 0px;
	width: 1700px;
	height: 106px;
	margin: auto;
	background-color: #999999;
}


footer {
	text-align: center;
	margin-top: 15px;
	font-size: 18px;
	font-weight: 700;
	color: blue;
}

.container {
    display: grid;
    grid-template-columns: 410px 1fr; 		/* 왼쪽 메뉴 410px, 나머지 공간은 주 콘텐츠 */
    gap: 30px; 								/* 좌우 여백(15px + 15px) -> 메뉴와 콘텐츠 간격 */
    max-width: 1700px; 						/* 콘텐츠 영역 전체 크기 410px + 30px + 1260px */
    height: 750px;							/* 높이 고정*/
    margin: 0 auto; 						/* 중앙 정렬 */
    padding: 0px; 							/* 좌우 패딩 15px */
}

.left-menu {
    background-color: #f0f0f0;
    padding: 0px 15px;
}

.main-content {
    background-color: #e0e0e0;
    padding: 0px 15px;
}

#timerDiv{
	position: absolute;
	right: 150px;
	top: 70px;
	justify-content: center;
	font-size: 18px;
	font-weight: 700;
}

</style>

<script type="text/javascript">

fetchAndUpdateRemainingTime = function () {
    $.get("/kh/admin/getSessionRemain", 
    		function(response) {
						        if (response == 0) {
						            goLogOut();
						        } else {
						            updateTimerDisplay(response);
						        }
    });
}

updateTimerDisplay = function (countdown) {
    var minutes = Math.floor(countdown / 60);
    var seconds = countdown % 60;
    var formattedTime = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
    $("#sessionTimer").text(formattedTime);
    
    // 0을 시작으로 하는 추출 시작점에 대한 인덱스를 의미
    // 음수 인덱스는 배열의 끝에서부터의 길이
    // slice(-2) 는 배열에서 마지막 두 개의 엘리먼트를 추출
}

sessionExtension = function () {
    $.ajax({
        type:"POST", 
        dataType:'json', 
        data:{}, 
        url:"/kh/admin/sessionExtension",
    });
}

function goLogOut() {
	alert("LogOut 되었습니다")
	location.href="/kh/admin/goLogOut";
}

// 함수실행
fetchAndUpdateRemainingTime();
setInterval(fetchAndUpdateRemainingTime, 1000);

</script>
</head>
<body>
	<header>
		<img	src="<%=request.getContextPath()%>/images/main/kh_Logo_.png" />
		<div id="timerDiv">
			<span id="userId">userId@gmail.com</span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img	src="<%=request.getContextPath()%>/images/main/kh_timer.png" 
					width="18px"
					height="18px"/>
			<span id="sessionTimer"></span>
			<button type="button" onclick="sessionExtension();">시간연장</button>
		</div>
	</header>
</body>
</html>