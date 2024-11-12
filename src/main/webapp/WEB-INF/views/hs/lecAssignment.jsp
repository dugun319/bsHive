<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제제출</title>
<style type="text/css">
	
    /* 메인 콘텐츠 영역 */
    .main {
        width: 70%;
        background-color: #fff;
        padding: 30px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    /* 과제제출 제목 스타일 */
    h1 {
        font-size: 28px;
        color: #134b84;
        border-bottom: 2px solid #134b84;
        padding-bottom: 15px;
        margin-bottom: 20px;
    }

    /* 테이블 스타일 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th {
        background-color: #134b84;
        color: white;
        font-weight: normal;
        text-align: center !important;
        padding: 12px;
        font-size: 16px;
        width: 180px;
    }

    td {
        padding: 20px;
        text-align: left;
        background-color: #fff;
        color: #333;
        font-size: 14px;
    }

    /* 텍스트 입력 영역 스타일 */
    .form-control {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        line-height: 1.6;
    }

    /* 텍스트영역 */
    textarea.form-control {
        height: 100px;
    }

    /* 파일 업로드 입력 필드 */
    .form-control-file {
        width: 100%;
        padding: 8px;
        margin: 5px 0;
        font-size: 14px;
    }
    
    /* 제출 안내 스타일 */
    td[colspan="4"] {
        font-size: 12px;
        color: #F15F5F;
        text-align: center;
        padding-left: 10px;
        padding-top: 10px;
    }
</style>
</head>
<header>
	<%@ include file="../header.jsp" %>
</header>
<body>
	<div class="lctrList_main_banner">
		<div class="lctrList_main_banner_text3"><div class="lctrList_main_banner_do"></div>${lctr.lctr_name }</div>
		<div class="lctrList_main_banner_text">offline</div><div class="lctrList_main_banner_text2">과제</div>
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
	<form action="AssignInsert" method="post" enctype="multipart/form-data">
		<h1>과제제출</h1>
		<input type="hidden" name="lctr_num" value="${hsAssignWrite.lctr_num }">
		<input type="hidden" name="cycl" value="${hsAssignWrite.cycl }">
		<input type="hidden" name="unq_num" value="${hsAssignWrite.unq_num }">
		<table>
			<tr>
				<th>차수</th>
				<td>${hsAssignWrite.cycl }차</td>
				<th>강의명</th>
				<td>${hsAssignWrite.lctr_name }</td>
			</tr>
			<tr>
				<th>교수명</th>
				<td>${hsAssignWrite.emp_nm }</td>
				<th>제출마감일</th>
				<td>${hsAssignWrite.asmt_ddln }</td>
			</tr>
			<tr>
				<th>주제</th>
				<td colspan="3">${hsAssignWrite.asmt_tpc }</td>
			</tr>
			<tr>
				<th>상세내용</th>
				<td colspan="3">${hsAssignWrite.asmt_dtl_cn }</td>
			</tr>
			<tr>
				<th><label for="file">참고문서</label></th>
				<td colspan="3">
					<div>
            			<c:forEach var="filePath" items="${filePath}">
                			<a download="${filePath.dwnld_file_nm}" href="download?filePath=${filePath.file_path_nm}" type="media_type">
                   				${filePath.dwnld_file_nm}
                			</a>
                			<br>
            			</c:forEach>
        			</div>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th>이름</th>
				<td colspan="3">${hsAssignWrite.unq_num } ${hsAssignWrite.stdnt_nm }</td>
			</tr>
			<tr>
				<th>제출 내용</th>
				<td><textarea class="form-control" aria-label="With textarea" name="crans_cnt" id="floatingTextarea" style="height: 100px;"></textarea> </td>
			</tr>
			<div class="form-group">
				<tr>
					<th><label for="file">파일</label></th>
					<td><input class="form-control-file" id="file" name="file" type="file" multiple></td>
				</tr>
			</div>
			<tr>
				<td colspan="4">※ 제출 마감일 전 까지는 수정이 가능합니다.</td>
			</tr>
		</table>
        <button type="submit" class="btn btn-outline-primary" style="text-align: center;">제출</button>
	</form>
	</div>
	</div>
	
<script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            // 제출 버튼에 click 이벤트 리스너 추가
            document.querySelector('button[type="submit"]').addEventListener('click', function(event) {
                // "제출하시겠습니까?" 확인 메시지
                let confirmResult = confirm("제출하시겠습니까?");
                
                // 사용자가 "취소"를 클릭한 경우, 폼 제출을 취소
                if (!confirmResult) {
                    event.preventDefault();  // 폼 제출을 막습니다.
                    return;
                }
            });
        });
    </script>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>