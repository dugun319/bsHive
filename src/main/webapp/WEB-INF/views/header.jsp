<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
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
		background: rgba(50, 50, 50, 0.5);
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
	    color: #fdfdfd;
	    font-size: 19px;
	    font-weight: 600;
	    text-decoration: none;
	    transition: color 0.3s;
	    margin:75px; 
	}
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
		margin: 30px;
	}
	
	.header_box_1_img {
		width: 100%;
		max-width: 24px;
		height: auto;
	}
	
</style>
</head>
<body>
	<div class="header_container">
		<div class="header_menu_1">
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
		</div>
	</div>
</body>
</html>