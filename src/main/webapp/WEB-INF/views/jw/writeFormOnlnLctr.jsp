<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chk() {
		if (!frm.lctr_num.value) {
			alert("강좌 번호를 입력해주세요")
			frm.lctr_num.focus();
			return false;
		} else location.href="confirm?lctr_num="+frm.lctr_num.value;
		
		if (!frm.lctr_name.value) {
			alert("강좌명을 입력해주세요")
			frm.lctr_name.focus();
			return false;
		} else location.href="confirm?lctr_name="+frm.lctr_name.value;
		
		if (!frm.pscp_nope.value) {
			alert("모집인원을 입력해주세요")
			frm.pscp_nope.focus();
			return false;
		} else location.href="confirm?pscp_nope="+frm.pscp_nope.value;
		
		if (!frm.aply_ymd.value) {
			alert("개설일을 입력해주세요")
			frm.aply_ymd.focus();
			return false;
		} else location.href="confirm?aply_ymd="+frm.aply_ymd.value;
		
		if (!frm.end_date.value) {
			alert("마감일을 입력해주세요")
			frm.end_date.focus();
			return false;
		} else location.href="confirm?end_date="+frm.end_date.value;
		
		if (!frm.bgng_ymd.value) {
			alert("강좌시작날짜를 입력해주세요")
			frm.bgng_ymd.focus();
			return false;
		} else location.href="confirm?bgng_ymd="+frm.bgng_ymd.value;
		
		if (!frm.end_ymd.value) {
			alert("강좌종료날짜 입력해주세요")
			frm.end_ymd.focus();
			return false;
		} else location.href="confirm?end_ymd="+frm.end_ymd.value;
	}
</script>
</head>
<body>
	<!-- Header -->
	<div class="header"><%@ include file="../header.jsp"%></div>
	
	<h1>강의계획서 폼</h1>
		<c:if test="${msg!=null}">${msg}</c:if>
		<form action="insertOnlnLctr" method="post" name="frm">
			<table>
				<tbody>
					<tr>
						<th>강의상태
							<select>
								<option value="0">개설신청</option>
							</select>
						</th>
					</tr>
					<tr><th>강좌번호</th>		<td><input type="text"></td></tr>
					<tr><th>학과명</th>			<td><input type="text"></td></tr>
					<tr><th>강좌명</th>			<td><input type="text"></td></tr>		
					<tr><th>모집인원</th>		<td><input type="text"></td></tr>
					
					<tr><th>강사번호</th>		<td><input type="text"></td></tr>
					<tr><th>강사명</th>			<td><input type="text"></td></tr>
					
					<tr>
						<th>개설일</th>			<td><input type="date"></td>
						<th>마감일</th>			<td><input type="date"></td>
					</tr>
					
					<tr><td colspan="2"><input type="submit" value="확인"></td></tr>
				</tbody>
			</table>
		</form>
</body>
</html>