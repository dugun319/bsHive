<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>

<div class=""><a href="se/onln_form">영상시청</a></div>

<h1>MAIN</h1>
	<table>
		<c:forEach	var="paperList"	items="${paperList}"	varStatus="status" >
					<tr>
						<td class="cell">${paperList.aply_num}</td>						
						<td class="cell">${paperList.lctr_num}</td>
						<td class="cell">${paperList.unq_num}</td>						
						<td class="cell">${paperList.prdoc_type}</td>
						<td class="cell">${paperList.aply_ymd}</td>						
						<td class="cell">${paperList.issu_ymd}</td>
						<td class="cell">${paperList.issu_stts}</td>	
					</tr>
		</c:forEach>
	</table>

</body>
</html>

</head>
<header>
	<%@ include file="header.jsp" %>
</header>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll('.main_event_image');
    const navigators = document.querySelectorAll('.navigator');
    let currentIndex = 0;

    function showImage(index) {
        images.forEach((img, i) => {
            img.classList.toggle('active', i === index);
        });
        navigators.forEach((nav, i) => {
            nav.classList.toggle('active', i === index);
        });
    }

    navigators.forEach((navigator, index) => {
        navigator.addEventListener('click', () => {
            currentIndex = index;
            showImage(currentIndex);
        });
    });

    setInterval(() => {
        currentIndex = (currentIndex + 1) % images.length;
        showImage(currentIndex);
    }, 3000);
});
</script>
<body>
	<div class="main_container">
	    <div class="main_header_banner">
	        <img alt="" src="<%=request.getContextPath()%>/images/main/banner_1.jpg" class="main_header_banner_img">
	        <img alt="" src="<%=request.getContextPath()%>/images/main/banner_2.jpg" class="main_header_banner_img">
	        <img alt="" src="<%=request.getContextPath()%>/images/main/banner_3.jpg" class="main_header_banner_img">
	        <img alt="" src="<%=request.getContextPath()%>/images/main/banner_4.jpg" class="main_header_banner_img">
	        <img alt="" src="<%=request.getContextPath()%>/images/main/banner_5.jpg" class="main_header_banner_img">
	    </div>
	    <div class="main_body_container">
	    	<div class="main_body_content">
		    	<table class="main_gogi_table">
		    		<tr>
		    			<td class="main_gogi_table_td1">공지사항</td>
		    		</tr>
		    		<tr>
		    			<td class="main_gogi_table_img">분류</td>
		    			<td class="main_gogi_table_td2">제목</td>
		    			<td class="main_gogi_table_td2">날짜</td>
		    			<td class="main_gogi_table_img">첨부파일유무</td>
		    		</tr>
		    		
		    		
		    		
		    	</table>
		    	<div class="main_but_img">
		    		<a href="/kh/admin/prdocList?currentPage=1"><img alt="수료증발급" src=""><div class="main_but_img_text">수료증발급</div></a>
		    		<a href="/kh/admin/adminMain"><img alt="장학증명서발급" src=""><div class="main_but_img_text">장학증명서발급</div></a>
		    		<a href=""><img alt="1:1상담" src=""><div class="main_but_img_text">1:1 상담</div></a>
		    		<a href=""><img alt="1:1상담" src=""><div class="main_but_img_text">1:1 상담</div></a>
		    	</div>
		    	<div class="main_calendar">
		    		<div class="main_calendar_header">
		    			강의일정
		    		</div>
		    		<div class="main_calendar_card_list">
		    			<div class="main_calender_card">
		    				<div class="main_calender_card_date">
		    					<div class="main_calender_card_week">금요일</div>
		    					<div class="main_calender_card_day">23</div>
		    				</div>
		    				<div class="main_calender_card_content">
		    					<div class="main_calender_card_content_img">강의</div>
		    					<div class="main_calender_card_content_title">제목</div>
		    					<div class="main_calender_card_content_date">날짜~날짜</div>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    	<div class="main_event_banner">
		    		<div class="main_event_banner_box">
			    		<img src="<%=request.getContextPath()%>/images/main/banner_가로_001.png" class="main_event_image active" alt="Banner 1">
				        <img src="<%=request.getContextPath()%>/images/main/banner_가로_002.png" class="main_event_image" alt="Banner 2">
				        <img src="<%=request.getContextPath()%>/images/main/banner_가로_003.png" class="main_event_image" alt="Banner 3">
		    		</div>
		    		<div class="main_event_banner_text">
		    			<div class="main_event_banner_navigetor">
				    		<div class="navigator" data-index="0"></div>
					        <div class="navigator" data-index="1"></div>
					        <div class="navigator" data-index="2"></div>
				        </div>
				        <div class="view_more">VIEW MORE</div>
			    	</div>
		    	</div>
	    	</div>
	    </div>
	</div>
</body>
<footer>
	<%@ include file="footer.jsp" %>
</footer>
</html>
