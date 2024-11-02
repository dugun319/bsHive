<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<title>header</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
=======
<title>BsHiVE</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous">
</script>
<!-- CSS -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
<!-- 테마 -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<style type="text/css">
>>>>>>> origin/br_test
<style type="text/css">
	body {
		font-family: 'Pretendard';
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}

	.header_container {
		display: flex;
		justify-content: space-between;
<<<<<<< HEAD
		background: rgba(50, 50, 50, 0.5);
=======
		background: rgba(253, 253, 253, 1);
>>>>>>> origin/br_test
		align-items: center;
		max-height: 100px;
	}
	
	.header_menu_2 {
		display: flex;
		justify-content: space-around;
		list-style: none;
	    padding: 0;
	    margin: 0;
	}
	.header_menu_2 a {
		text-align: center;
<<<<<<< HEAD
	    color: #fdfdfd;
=======
	    color: #323232;
>>>>>>> origin/br_test
	    font-size: 19px;
	    font-weight: 600;
	    text-decoration: none;
	    transition: color 0.3s;
	    margin:75px; 
	}
<<<<<<< HEAD
=======
	
	.header_menu_2 a:hover {
		color: #134b84;
	}
>>>>>>> origin/br_test
	.header_box_1 {
		display: flex;
		border: 0.5px solid #e2e8ee;
		margin: 30px;
		padding: 10px;
		border-radius: 20px;
		align-items: center;
	}
	
	.header_input_1 {
		background: none;
		border: none;
	}
	
	.header_menu_1_img{
<<<<<<< HEAD
		margin: 30px;
=======
		margin: 15px 50px;
		width: 200px;
		height: auto;
>>>>>>> origin/br_test
	}
	
	.header_box_1_img {
		width: 100%;
		max-width: 24px;
		height: auto;
<<<<<<< HEAD
=======
	} 
	
	.header_menu_3 {
		display: flex;
		margin-right: 50px; 
	}
	
	.header_menu_3 a {
		text-decoration: none;
	}
	
	.lpgin_mypage_but{
		font-size: 18px;
		font-weight: 400;
		color: #323232;
		padding-right: 40px; 
	}
	
	.lpgin_but {
		font-size: 18px;
		font-weight: 400;
		color: #323232;
	}
	
	.lpgin_mypage_but:hover {
		color: #134b84;
	}
	
	.lpgin_but:hover {
		color: #134b84;
	}
	
	.lpgin_but_img{
		width: 100%;
		max-width: 12px;
		height: auto;
>>>>>>> origin/br_test
	}
	
</style>
</head>
<body>
	<div class="header_container">
		<div class="header_menu_1">
<<<<<<< HEAD
			<a><img alt="메뉴_icon" src="<%=request.getContextPath()%>/images/main/메뉴_icon.png" class="header_menu_1_img"></a>
		</div>
		<nav>
			<ul class="header_menu_2">
				<li><a href="">헤더 메뉴1</a></li>
				<li><a href="">헤더 메뉴2</a></li>
				<li><a href="">헤더 메뉴3</a></li>
				<li><a href="">헤더 메뉴4</a></li>
				<li><a href="">헤더 메뉴5</a></li>
			</ul>
		</nav>
		<div class="header_menu_3">
			<div class="header_box_1">
				<input type="text" class="header_input_1">
				<img alt="검색_icon" src="<%=request.getContextPath()%>/images/main/검색_icon.png" class="header_box_1_img">
			</div>
=======
			<a href="/"><img alt="메뉴_icon" src="<%=request.getContextPath()%>/images/main/Logo.png" class="header_menu_1_img"></a>
		</div>
		<nav>
			<ul class="header_menu_2">
				<li><a href="">센터소개</a></li>
				<li><a href="">오프라인수강신청</a></li>
				<li><a href="se/onlnList">LMS</a></li>
				<li><a href="mh/gongList">고객센터</a></li>
			</ul>
		</nav>
		<div class="header_menu_3">
			<%-- <div class="header_box_1">
				<input type="text" class="header_input_1">
				<img alt="검색_icon" src="<%=request.getContextPath()%>/images/main/검색_icon.png" class="header_box_1_img">
			</div> --%>
			<c:choose>
				<c:when test="${not empty sessionScope.user }">
					<!-- 세션에 유저정보가 있을시 -->
					<c:choose>
						<c:when test="${sessionScope.user.mbr_se == 3 }">
							<a class="lpgin_mypage_but"><img alt="관리자페이지" src="<%=request.getContextPath()%>/images/main/관리자.png" class="lpgin_but_img">&nbsp;관리자페이지</a>
						</c:when>
						<c:otherwise>
							<a class="lpgin_but"><img alt="마이페이지" src="<%=request.getContextPath()%>/images/main/마이페이지.png" class="lpgin_but_img">&nbsp;마이페이지</a>
						</c:otherwise>
					</c:choose>
					<a class="lpgin_mypage_but"><img alt="로그아웃" src="<%=request.getContextPath()%>/images/main/로그아웃.png" class="lpgin_but_img">&nbsp;로그아웃</a>
				</c:when>
				<c:otherwise>
					<!-- 세션에 저장된 user정보가 없을시 -->
					<a class="lpgin_mypage_but"><img alt="회원가입" src="<%=request.getContextPath()%>/images/main/회원.png" class="lpgin_but_img">&nbsp;회원가입</a>
					<a class="lpgin_mypage_but"><img alt="자물쇠" src="<%=request.getContextPath()%>/images/main/자물쇠.png" class="lpgin_but_img">&nbsp;로그인</a>
				</c:otherwise>
			</c:choose>
>>>>>>> origin/br_test
		</div>
	</div>
</body>
</html>