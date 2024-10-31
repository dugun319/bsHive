<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<style type="text/css">
	body {
		font-family: 'Pretendard';
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	
	.footer_container{
		display: flex;
		padding: 50px 110px;
		background-color: #fdfdfd;
	}
	
	.footer_main_logo {
		margin: auto 0;
	}
	
	.footer_box {
		margin: 0 80px;
	}
	
	.footer_container img {
		width: 100%;
		max-width: 250px;
		height: auto;
	}
	
	.footer_text_hd {
		display: flex;
		align-items: center;
		margin-bottom: 30px;
	}
	
	.footer_text_hd a {
		 text-decoration: none;
		 color: #323232;
		 font-size: 24px;
		 font-weight: 600;
	}
	
	.footer_text_hed_text1{
		font-size: 16px;
		color: #e2e8ee;
	}
	
	.footer_text_bd_text1_1 {
		display: flex;
		font-size: 18px;
	}
	
	.footer_text_bd_text1 {
		margin-bottom: 30px;
	}
	
	.footer_text_footer_text {
		font-size: 12px;
		font-weight: 600;
		color: gray;
	}
	
	.footer_text_hed {
		color: #134b84;
		font-size: 28px;
		font-weight: 800;
		margin-bottom: 20px;
	}
</style>
</head>
<body>
	<div class="footer_container">
		<div class="footer_main_logo">
			<img alt="로고_icon" src="<%=request.getContextPath()%>/images/main/Logo.png">
		</div>
		<div class="footer_box">
			<div class="footer_text_hed">부산시 HiVE교육센터</div>
			<div class="footer_text_hd">
				<a href="" class="footer_text_hd_td1">개인정보 처리 방침</a>
				<div class="footer_text_hed_text1">│</div>
				<a href="" class="footer_text_hd_td1">이용약관</a>
			</div>
			<div class="footer_text_bd">
				<div class="footer_text_bd_text1">
					<div class="footer_text_bd_text1_1"><div style="font-weight: 700; margin: 0 80px 10px 0; ">동아대학교</div>(49315) 부산 사하구 낙동대로550번길 37 (하단동) / TEL : 051-200-6114</div>
					<div class="footer_text_bd_text1_1"><div style="font-weight: 700; margin-right: 80px; ">고신대학교</div>(49104) 부산시 영도구 와치로 194(동삼동) / TEL : 051-990-2114</div>
				</div>
				
			</div>
			<div class="footer_text_footer">
				<div class="footer_text_footer_text">(c) Copyright 2024 BsHiVE University. All Rights Reserved</div>
			</div>
		</div>
		<div></div>
	</div>
</body>
</html>