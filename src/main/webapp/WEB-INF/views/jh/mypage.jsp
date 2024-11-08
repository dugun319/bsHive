<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jh_myPage.css">
</head>
<body>

<div class="body">
    <div class="title">마이페이지</div>
    <div class="main-container">
        <div class="list">
           <%@ include file="myPageSideBar.jsp" %>
        </div>

        <div class="content">
        	<form action="/jh/pwChk" method="get">
	            <div class="content-box" onclick="this.closest('form').submit();">
	        		<img src="/images/회원정보변경.png" alt="회원정보변경" class="content-image">
	        		<p>회원정보변경</p>
	    		</div>
    		</form>
    		
    		<form action="/jh/changePassword" method=get>
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/비밀번호변경.png" alt="비밀번호변경" class="content-image">
        			<p>비밀번호 변경</p>
    			</div>
    		</form>
    		
			<% if (mbr_se != null && mbr_se == 1) { %>
			    <!-- mbr_se가 1 (학생)일 때 표시 -->
        		<form action="/jh/registeredClassStudent" method="get">
		            <div class="content-box" onclick="this.closest('form').submit();">
	        			<img src="/images/수강신청현황.png" alt="수강신청현황" class="content-image">
	        			<p>수강신청현황</p>
	    			</div>   
	    		</form>		
	    		
			    <form action="/jh/myClassroom" method="get">
			        <div class="content-box" onclick="this.closest('form').submit();">
			            <img src="/images/나의 강의실.png" alt="학생의 강의실" class="content-image">
			            <p>나의 강의실</p>
			        </div> 
			    </form>
			<% } else if (mbr_se != null && mbr_se == 2) { %>
			    <!-- mbr_se가 2 (교수)일 때 표시 -->
	    		<form action="/jh/registeredClassProfessor" method="get">
		            <div class="content-box" onclick="this.closest('form').submit();">
	        			<img src="/images/수강신청현황.png" alt="강의신청현황" class="content-image">
	        			<p>강의신청현황</p>
	    			</div>   
	    		</form>
	    		
			    <form action="/jh/myProfClassroom" method="get">
			        <div class="content-box" onclick="this.closest('form').submit();">
			            <img src="/images/나의 강의실.png" alt="교수의 강의실" class="content-image">
			            <p>나의 강의실</p>
			        </div> 
			    </form>
			<% } %>

    		<form action="/your-controller-url" method="get">
	            <div class="content-box" onclick="this.closest('form').submit();">
        			<img src="/images/내가 등록한 글.png" alt="내가 등록한 글" class="content-image">
        			<p>내가 등록한 글</p>
    			</div> 	
    		</form>	
        </div>
    </div>
</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>