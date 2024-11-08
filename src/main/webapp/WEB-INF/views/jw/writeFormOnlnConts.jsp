<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Header -->
	<div class="header"><%@ include file="../header.jsp"%></div>
	<div class="header"><%@ include file="../se/sidebarOn.jsp"%></div>
	
	<h1>강의계획서 폼</h1>
		<c:if test="${msg!=null}">${msg}</c:if>
		<form action="/jw/insertOnlnConts" method="post" name="frm" onsubmit="return chk();">
		
			<!-- 첫 번째 콘텐츠 입력 -->
			<table class="content">
				<tr><!-- <th>강의번호</th> -->
				<td><span>${lctr_num}</span>
					<!-- 강의번호(Lctr_num)를 서버에 전송하기 위한 hidden input -->
	        		<input type="hidden" name="Lctr_num" value="${lctr_num}"/>
				</td></tr>
				
				<tr><!-- <th>차시번호</th> -->
				<td><span>${unit_num}</span>
					<!-- 차시번호(unit_num)를 서버에 전송하기 위한 hidden input -->
	        		<input type="hidden" name="unit_num" value="${unit_num}"/>
				</td></tr>
				
				<tr><th>비디오ID</th>
		        <td><input type="text" name="vdo_id" required></td></tr>
		        					
		        <tr><th>콘텐츠명</th>
		        <td><input type="text" name="conts_nm" required></td></tr>
		        		        
		        <tr><th>재생시간</th>
		        <td><input type="number" name="play_hr" required> 단위: 초</td></tr>
		        
			</table>
			
			<br><br><br><br><br>
			
			<button type="submit">강의 콘텐츠 계획서 제출</button>
	
	    </form>

<script type="text/javascript">
	function chk() {
		
		// content1 필드만 필수 입력 확인
	    if (document.frm.conts_nm1.value === "" || 
	        document.frm.vdo_id1.value === "" || 
	        document.frm.play_hr1.value === "") {
	        alert("content1의 모든 필드를 입력해 주세요.");
	        return false;
	    }
		
	 // content2 필드를 입력했으면, content2의 필수 입력 필드도 확인
	    if ( document.frm.conts_nm2.value !== "" ||
	        document.frm.vdo_id2.value !== "" || 
	        document.frm.play_hr2.value !== "") {
	        
	        if (document.frm.conts_nm2.value === "" || 
	            document.frm.vdo_id2.value === "" || 
	            document.frm.play_hr2.value === "") {
	            alert("content2의 모든 필드를 입력해 주세요.");
	            return false;
	        }
	    }
	 
	    return true;
	}
</script>			
</body>
</html>