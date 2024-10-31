<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
        width: 1902px; /* body 폭 설정 */
        margin: 0; /* 기본 여백 제거 */
        padding: 0; /* 기본 패딩 제거 */
        box-sizing: border-box; /* 박스 모델 설정 */
    }
    
	.container {
        display: grid;
        grid-template-columns: 410px 1180px; /* 왼쪽 메뉴와 오른쪽 콘텐츠 영역 비율 */
        grid-gap: 15px; /* 좌우 간격 */
        width: 1700px; /* 콘텐츠 영역 폭 */
        margin: 50px auto; /* 가운데 정렬 및 상단 여백 */
    }

    .sideLeft {
        min-width: 410px; /* 왼쪽 메뉴 폭 */
        margin-right: 80px; /* 오른쪽 콘텐츠와의 간격 설정 */
    }
    
    .sideLeft .sideTbl {
        width: 70%; /* 테이블 폭 */
        border-collapse: collapse; /* 테이블 경계 설정 */
        text-align: center;
    }
    
    .sideLeft .sideTbl td {
        border: none; /* 테두리 설정 */
        height:40px;
    }
    
    .main {
        width: 1180px; /* 오른쪽 콘텐츠 영역 폭 */
    }
	
	a {
		color: black;	/* 글자 색상 설정 */
		text-decoration: none;	/* 밑줄제거 */
	}
	
	a:hover {
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="container">
	<div class="sideLeft">
		<table class="sideTbl">
			<tr>
				<td style="border-top: 5px solid grey;"></td>
			</tr>
			<tr>
				<td><a href="/hs/lecSchedule">강의계획서</a></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-decoration: underline;"><a href="/hs/lecAttendance">출석확인</a></td>
			</tr>
			<tr>
				<td><a href="/hs/lecAssignment">과제</a></td>
			</tr>
			<tr>
				<td><a href="/hs/lecTest">시험</a></td>
			</tr>
			<tr>
				<td><a href="">성적확인</a></td>
			</tr>
		</table>
	</div>
	<div class="main">
		<h2>출석확인</h2>
	</div>
	</div>
</body>
</html>