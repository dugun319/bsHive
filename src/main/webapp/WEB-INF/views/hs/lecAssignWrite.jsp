<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제입력(Prof)</title>
<style type="text/css">

</style>

</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제입력</div>
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
		<form action="profAsmtWrite" method="post" enctype="multipart/form-data">
		<div class="main">
			<h1>과제입력</h1>
			<input type="hidden" name="lctr_num" value="${lctr.lctr_num }">
			<input type="hidden" name="cycl" value="${hsAssignWrite.cycl }">
			<table>
				<tr>
					<th>차수</th>
					<td>${hsAssignWrite.cycl }</td>
					<th>강의명</th>
					<td>${hsAssignWrite.lctr_name }</td>
				</tr>
				<tr>
					<th>교수명</th>
					<td colspan="3">${hsAssignWrite.emp_nm }</td>
				</tr>
				<tr>
					<th>주제</th>
					<td colspan="3">
						<div class="form-floating">
	  						<input type="text" name="asmt_tpc" class="form-control" id="floatingInput" placeholder="과제주제를 입력하세요" required="required">
	  						<label for="floatingInput" style="color: grey;">과제 주제</label>
	  					</div>
					</td>
				</tr>
				<tr>
					<th>상세내용</th>
					<td colspan="3">
						<div class="form-floating">
							<textarea class="form-control" aria-label="With textarea" name="asmt_dtl_cn" id="floatingTextarea" required="required" style="height: 100px;"></textarea>
							<label for="floatingTextarea">과제 관련해 자세히 적어주세요</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>제출마감일</th>
					<td colspan="3">
						<input type="date" name="asmt_ddln" class="form-control" required="required" id="asmt_ddln">
					</td>
				</tr>
				<tr>
					<th><label for="file">참고문서</label></th>
					<td colspan="3">
                		<input type="file" class="form-control" id="file" name="file" multiple>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<button type="submit" class="btn btn-outline-primary" id="submitBtn">등록</button>
					</td>
				</tr>
			</table>
		</div>
		</form>
	</div>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function () {
    // 폼 제출 버튼 클릭 시
    document.getElementById('submitBtn').addEventListener('click', function(event) {
        // "과제를 등록하시겠습니까?" 확인 메시지
        let confirmResult = confirm("과제를 등록하시겠습니까?");
        
        // 사용자가 "취소"를 클릭한 경우, 폼 제출을 취소
        if (!confirmResult) {
            event.preventDefault();  // 폼 제출을 막습니다.
            return;
        }

        // 마감일자 유효성 검사
        let asmt_ddln = document.getElementById('asmt_ddln').value;
        let today = new Date();
        let selectedDate = new Date(asmt_ddln);

        // 오늘 날짜보다 이전일 경우 경고 메시지 표시
        if (selectedDate < today) {
            alert("오늘 이후 날짜를 선택하세요.");
            event.preventDefault();  // 폼 제출을 막습니다.
        }
    });
});
</script>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>