<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
</head>
<body>
	<!-- Header -->
	<div class="header"><%@ include file="../header.jsp"%></div>
	<div class="header"><%@ include file="../se/sidebarOn.jsp"%></div>
	
	<h1>강의계획서 폼</h1>
		<c:if test="${msg!=null}">${msg}</c:if>
		<form action="/jw/insertOnlnLctr" method="post" name="frm" enctype="multipart/form-data" onsubmit="return chk();">
			<table>
				
		        <!-- 교수 정보 불러오기 -->
		    
		
		        <!-- Onln_Lctr TBL 입력 필드 -->
		        <tr><th>강의번호</th>
		        <td><span>${ lctr_num.substring(0,7) }</span>
		        <input type="text" name="lastTwoDigits" maxlength="2" size="2" required pattern="[0-9]{2}" ></td></tr>
		        
		        <tr><th>강의설명</th>
		        <td><input type="text" name="lctr_expln" required></td></tr>
		        
		        <tr><th>강좌자료</th>
		        <td><input type="file" name="file" multiple></td></tr>
		
		       	<tr><th>시작일</th>
		        <td><input type="date" name="bgng_ymd" required></td></tr>
		
		        <tr><th>종료일</th>
		        <td><input type="date" name="end_ymd" required></td><tr>
		        
		        <tr><th>모집인원수</th>
		        <td><input type="number" name="rcrt_nope" required></td></tr>
		        
		        <tr><th>수료기준</th>
		        <td><input type="text" name="fnsh_crtr" required></td></tr>
		        
		        <tr><td><button type="submit">강의계획서 제출</button></td></tr>
			
			</table>
	    </form>

<script type="text/javascript">
	function chk() {
		if (!frm.lctrName.value) {
            alert("강좌 번호를 입력해주세요");
            frm.lctrName.focus();
            return false;
        }
        if (!frm.sbjectName.value) {
            alert("학과명을 입력해주세요");
            frm.sbjectName.focus();
            return false;
        }
        if (!frm.lctrNum.value) {
            alert("강좌명을 입력해주세요");
            frm.lctrNum.focus();
            return false;
        }
        if (!frm.pscpNope.value) {
            alert("모집인원을 입력해주세요");
            frm.pscpNope.focus();
            return false;
        }
        if (!frm.openDate.value) {
            alert("개설일을 입력해주세요");
            frm.openDate.focus();
            return false;
        }
        if (!frm.closeDate.value) {
            alert("마감일을 입력해주세요");
            frm.closeDate.focus();
            return false;
        }
        return true; // 모든 검증 통과
    }
</script>			
</body>
</html>