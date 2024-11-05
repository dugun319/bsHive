<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생시험결과(교수)</title>
<style type="text/css">
	body {
        width: 1920px !important; /* body 폭 설정 */
        margin: 0; /* 기본 여백 제거 */
        padding: 0; /* 기본 패딩 제거 */
        box-sizing: border-box; /* 박스 모델 설정 */
    }
    
	.container1 {
        display: grid;
        grid-template-columns: 410px 1180px; /* 왼쪽 메뉴와 오른쪽 콘텐츠 영역 비율 */
        grid-gap: 15px; /* 좌우 간격 */
        width: 1700px; /* 콘텐츠 영역 폭 */
        margin: 50px auto; /* 가운데 정렬 및 상단 여백 */
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
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="container1">
		<div class="sideLeft">
			<%@ include file="../sidebarLctr.jsp" %>
		</div>
		<div class="main">
			<h1>학생 시험제출목록</h1>
		</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>