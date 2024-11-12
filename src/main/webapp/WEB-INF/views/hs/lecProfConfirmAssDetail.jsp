<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생제출물상세확인</title>
<style type="text/css">
/* 메인 콘텐츠 영역 스타일 */
.main {
	margin-top: 10px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
    color: #134b84;
    font-size: 28px;
    margin-bottom: 20px;
}

/* 테이블 스타일 */
table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 30px;
    border: 1px solid white;
}

th, td {
    padding: 12px;
    border: 1px solid white;
    vertical-align: middle;
}

th {
    background-color: #134b84;
    color: white;
    font-weight: bold;
    width: 180px;
    padding: 15px;
    text-align: center;
}

td {
    background-color: #f9f9f9;
    text-align: left;
}

tr:nth-child(even) td {
    background-color: #f2f2f2;
}

/* 폼 그룹 스타일 */
.form-group {
    margin-bottom: 20px;
}

.form-floating {
    margin-bottom: 20px;
}

.form-floating input {
    padding: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
    width: 100%;
    font-size: 16px;
}

.form-floating label {
    font-size: 14px;
    color: #666;
}

/* 파일 다운로드 링크 스타일 */
.file-links a {
    color: #007bff;
    text-decoration: none;
    font-size: 14px;
}

.file-links a:hover {
    text-decoration: underline;
}


</style>
<script type="text/javascript">
	// 목록으로 이동 시 저장 안된다는 경고
	function confirmRedirect() {
	    // alert 메시지 표시 후 확인을 누르면 해당 URL로 이동
	    const isConfirmed = confirm("목록으로 이동 시 수정사항이 저장되지 않습니다. 이동하시겠습니까?");
	    
	    if (isConfirmed) {
			// 확인 버튼을 클릭하면 해당 경로로 이동
			location.href = '/hs/lecProfConfirmAssign?lctr_num=${hsAssignWrite.lctr_num}';
	    }
	}
	
	// 점수 입력 확인 경고
	function confirmSubmit() {
	    // 점수 입력 확인 메시지
	    const isConfirmed = confirm("점수를 입력하시겠습니까?");
	    
	    if (isConfirmed) {
	        // 확인 버튼 클릭 시 폼 제출
	        document.getElementById("scoreForm").submit();
	    }
	}
</script>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제확인</div>
		<img alt="메인배너" src="<%=request.getContextPath()%>/images/main/수강신청_banner.jpg" class="lctrList_main_banner_img">
	</div>
	<div class="container1">
	<div class="sideLeft">
			<%-- lctr_num 가져오기 --%>
			<c:set var="lctrNum" value="${lctr.lctr_num}" />
			
			<%-- 앞에서 6번째 자리를 추출 (인덱스 5) --%>
			<c:set var="sixthFromStart" value="${fn:substring(lctrNum, 5, 6)}" />
			
			<%-- 숫자로 변환 --%>
			<c:set var="sixthFromStartNum" value="${sixthFromStart}" />

			<%-- 조건에 따라 사이드바 파일을 동적으로 설정 --%>
			<c:choose>
			    <c:when test="${sixthFromStartNum >= 1 and sixthFromStartNum <= 4}">
			        <!-- 온라인 강의일 경우 -->
			        <%@ include file="../se/sidebarOn.jsp" %>
			    </c:when>
			    <c:when test="${sixthFromStartNum >= 5 and sixthFromStartNum <= 9}">
			        <!-- 오프라인 강의일 경우 -->
			        <%@ include file="../sidebarLctr.jsp" %>
			    </c:when>
			</c:choose>
	</div>
	<div class="main">
		<h1>학생 제출물 확인</h1>
		<table>
			<tr>
				<th>차수</th>
				<td>${hsAssignWrite.cycl }차</td>
				<th>강의명</th>
				<td>${hsAssignWrite.lctr_name }</td>
			</tr>
			<tr>
				<th>주제</th>
				<td colspan="3">${hsAssignWrite.asmt_tpc }</td>
			</tr>
			<tr>
				<th>상세내용</th>
				<td colspan="3">${hsAssignWrite.asmt_dtl_cn }</td>
			</tr>
		</table>
		<table>
			<tr>
				<th>이름</th>
				<td colspan="3">${hsAssignWrite.unq_num } ${hsAssignWrite.stdnt_nm }</td>
			</tr>
			<tr>
				<th>제출 내용</th>
				<td>${hsAssigStdUpd.crans_cnt  }</td>
			</tr>
			<div class="form-group">
				<tr>
					<th><label for="file">제출문서</label></th>
					<td>
						<div>
	            			<c:forEach var="filePath" items="${filePath1}">
	                			<a download="${filePath.dwnld_file_nm}" href="download?filePath=${filePath.file_path_nm}" type="media_type" style="text-decoration: underline;"">
	                   				${filePath.dwnld_file_nm}
	                			</a>
	                			<br>
	            			</c:forEach>
	        			</div>
					</td>
				</tr>
			</div>
		</table>
		<form action="scoreAsmt" method="post">
			<input type="hidden" name="lctr_num" value="${hsAssignWrite.lctr_num }">
			<input type="hidden" name="cycl" value="${hsAssignWrite.cycl }">
			<input type="hidden" name="unq_num" value="${hsAssignWrite.unq_num }">
			<h5 style="font-weight: bold; color: 134b84">과제 점수</h5>
			<div class="form-floating">
	  			<input type="text" name="cycl_scr" class="form-control" id="floatingInput" placeholder="숫자만 입력하세요" required="required" value="${hsAssigStdUpd.cycl_scr }">
	  			
	  			<label for="fvloatingInput" style="color: grey;">숫자만 입력하세요</label>
	  		</div>
			<button type="button" class="btn btn-outline-primary" onclick="confirmRedirect()" style="text-align: center;">목록</button>
		    <button type="submit" class="btn btn-outline-primary" onclick="confirmSubmit()" style="text-align: center;">입력</button>
		</form>
	</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>