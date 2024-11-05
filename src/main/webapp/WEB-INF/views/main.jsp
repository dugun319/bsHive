<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BsHiVE</title>
</head>
<body>
    <header>
        <%@ include file="header_main.jsp" %>
    </header>

    <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        const images = document.querySelectorAll('.main_banner_image');
        const navigators = document.querySelectorAll('.main_navigator');
        const eventImages = document.querySelectorAll('.main_event_image');
        const eventNavigators = document.querySelectorAll('.event_navigator');
        let currentIndex = 0;
        let currentEventIndex = 0; // 이벤트 네비게이터의 현재 인덱스 추가

                                                  

        function showEventImage(index) {
            eventImages.forEach((img, i) => {
                img.classList.toggle('active', i === index);
            });
            eventNavigators.forEach((navigator, i) => {
                navigator.classList.toggle('active', i === index); // 활성화된 상태 추가
            });
        }

        navigators.forEach((navigator, index) => {
            navigator.addEventListener('click', () => {
                currentIndex = index;
                showImage(currentIndex);
            });
        });

        eventNavigators.forEach((navigator, index) => {
            navigator.addEventListener('click', () => {
                currentEventIndex = index; // 이벤트 인덱스 업데이트
                showEventImage(currentEventIndex); // 이벤트 이미지 표시
                updateEventNavigator(); // 이벤트 네비게이터 업데이트
            });
        });

        function updateBannerPosition() {
            const bannerBox = document.querySelector('.main_event_banner_box');
            const offset = currentIndex * -100; // 이미지 너비만큼 이동
            bannerBox.style.transform = `translateX(${offset}%)`; // 배너 이동
        }

        function updateEventNavigator() {
            eventNavigators.forEach((navigator, i) => {
                navigator.classList.toggle('active', i === currentEventIndex); // 선택된 네비게이터 활성화
            });
        }

        // 배너 이미지 자동 전환
        setInterval(() => {
            currentIndex = (currentIndex + 1) % images.length;
            showImage(currentIndex);
            updateBannerPosition(); // Ensure this function is also called here
        }, 5000);

        const sections = document.querySelectorAll('.main_header_banner, .main_body_container, .main_footer_container, footer');
        let currentSectionIndex = 0;

        window.addEventListener('wheel', function(event) {
            event.preventDefault(); // 기본 스크롤 방지
            if (event.deltaY > 0) { // 아래로 스크롤
                if (currentSectionIndex < sections.length - 1) {
                    currentSectionIndex++;
                    sections[currentSectionIndex].scrollIntoView({ behavior: 'smooth' });
                }
            } else { // 위로 스크롤
                if (currentSectionIndex > 0) {
                    currentSectionIndex--;
                    sections[currentSectionIndex].scrollIntoView({ behavior: 'smooth' });
                }
            }
        });
    });

    </script>

    <div class="main_container">
        <div class="main_header_banner">
        	<div class="main_header_img">
	            <img alt="" src="<%=request.getContextPath()%>/images/main/banner_1.jpg" class="main_banner_image active">
	            <img alt="" src="<%=request.getContextPath()%>/images/main/banner_2.jpg" class="main_banner_image">
	            <img alt="" src="<%=request.getContextPath()%>/images/main/banner_3.jpg" class="main_banner_image">
	            <img alt="" src="<%=request.getContextPath()%>/images/main/banner_4.jpg" class="main_banner_image">
	            <img alt="" src="<%=request.getContextPath()%>/images/main/banner_5.jpg" class="main_banner_image">
            </div>
            <div class="main_banner_navigetor">
                <div class="main_navigator active" data-index="0"></div>
                <div class="main_navigator" data-index="1"></div>
                <div class="main_navigator" data-index="2"></div>
                <div class="main_navigator" data-index="3"></div>
                <div class="main_navigator" data-index="4"></div>
            </div>
        </div>
        <div class="main_body_container">
            <div class="main_body_content">
                <table class="main_gogi_table">
                    <tr>
                        <td class="main_gogi_table_td1">공지사항</td>
                    </tr>
                    <c:forEach var="gogi" items="${pstList }">
	                    <tr>
	                        <td class="main_gogi_table_td3">공지사항</td>
	                        <td class="main_gogi_table_td2">${gogi.pst_ttl }</td>
	                        <td class="main_gogi_table_td2">
	                        	${gogi.wrt_ymd }
	                        </td>
	                        <td class="main_gogi_table_img">
	                        	<c:choose>
	                        		<c:when test="${gogi.file_group == null || gogi.file_group == ''}">
	                        			<div class="main_gogi_table_img_text">없음</div>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<img alt="파일icon" src="<%=request.getContextPath()%>/images/main/파일.png" class="main_gogi_table_img_images">
	                        		</c:otherwise>
	                        	</c:choose>
	                        </td>
	                    </tr>
                    </c:forEach>
                </table>
                <div class="main_but_img">
                    <a href=""><img alt="증명서발급" src="<%=request.getContextPath()%>/images/main/test_star2.png" class="main_but_img_src"><div class="main_but_img_text">증명서발급</div></a>
                    <a href=""><img alt="수료증발급" src="<%=request.getContextPath()%>/images/main/test_star2.png" class="main_but_img_src"><div class="main_but_img_text">수료증발급</div></a>
                    <a href=""><img alt="1:1상담" src="<%=request.getContextPath()%>/images/main/test_star2.png" class="main_but_img_src"><div class="main_but_img_text">1:1 상담</div></a>
                    <a href=""><img alt="1:1상담" src="<%=request.getContextPath()%>/images/main/test_star2.png" class="main_but_img_src"><div class="main_but_img_text">1:1 상담</div></a>
                </div>
                <div class="main_calendar">
                    <div class="main_calendar_header">강의일정</div>
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
                            <div class="event_navigator active" data-index="0"></div>
                            <div class="event_navigator" data-index="1"></div>
                            <div class="event_navigator" data-index="2"></div>
                        </div>
                        <div class="view_more">VIEW MORE</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="main_footer_container">
        	<div class="main_footer_content">
        		
        	</div>
        </div>
        <footer>
		    <%@ include file="footer.jsp" %>
		</footer>
    </div>
</body>

</html>
