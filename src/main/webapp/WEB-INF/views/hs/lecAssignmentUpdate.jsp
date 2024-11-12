<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/offLctrBanner.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제제출수정</title>
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
    
    ul {
    	list-style-type: none;
    }
    
    button {
		margin-left: 10px;
		cursor: pointer; /* 마우스 커서 변경 */
		border: none;
		color: red;
		background-color: white;
	}
</style>
<script type="text/javascript">
	function deleteFile(fileGroup, fileNo) {
		// 파일 삭제 요청을 서버로 보내는 AJAX (fetch가 현대식 ajax)
		
		// fetch를 사용해 비동기적으로 POST 요청을 보냄
		fetch('<%=request.getContextPath()%>/hs/deleteUpdFile', {
			method: 'POST',
			// 서버에 JSON 형식으로 데이터를 전송한다고 알려줌
			headers: {
					'Content-Type': 'application/json',
			},
			// 요청 본문(body) 데이터를 JSON 형태로 변환하여 전송
			body: JSON.stringify({
						//dto : 현재 jsp에서 쓰이는 parameter
						file_group: fileGroup,
						file_no: fileNo
			})
		})
		.then(response => {
			if (response.ok) {  // HTTP 상태 코드 200 OK일 경우
	            console.log('파일이 삭제되었습니다.');
	            location.reload();  // 페이지 새로고침하여 파일 목록 갱신
	        } else if (response.status === 400) {  // 400 상태 코드일 경우
	        	console.log('파일 삭제에 실패했습니다.');
	        } else if (response.status === 500) {  // 500 상태 코드일 경우
	        	console.log('서버 오류가 발생했습니다.');
	        } else {
	        	console.log('알 수 없는 오류가 발생했습니다.');
	        }
		})
		.catch(error => {
	     	console.error('Error:', error);
	     	console.log("파일 삭제에 실패했습니다.");
		});
	}
	
	// 페이지가 완전히 로드된 후 스크립트 실행
	document.addEventListener('DOMContentLoaded', function () {
		// 파일 추가 시 파일 목록에 추가
	    document.getElementById("file1").addEventListener("change", function(event) {
	        const files = event.target.files;  // 선택된 파일들
	        const newFilesList = document.getElementById("newFiles");
			
	    	// 기존 목록을 비우기
	        newFilesList.innerHTML = '';
	        
	        // 기존 목록을 비우고, 새로 추가된 파일들만 리스트에 추가
	        Array.from(files).forEach(file => {
	            const li = document.createElement("li");
	            li.textContent = file.name;  // 파일명만 추가
	            // 삭제 버튼 추가
	            const deleteButton = document.createElement("button");
	            deleteButton.textContent = "x";
	            deleteButton.style.color = "red";
	            deleteButton.addEventListener("click", function() {
	                // x 버튼 클릭 시 해당 파일만 목록에서 삭제
	                newFilesList.removeChild(li);
	            });
	            li.appendChild(deleteButton);
	            newFilesList.appendChild(li);
	        });
	    });
	});
	
	// 목록으로 이동 시 저장 안된다는 경고
	function confirmRedirect() {
	    // alert 메시지 표시 후 확인을 누르면 해당 URL로 이동
	    const isConfirmed = confirm("목록으로 이동 시 수정사항이 저장되지 않습니다. 이동하시겠습니까?");
	    
	    if (isConfirmed) {
			// 확인 버튼을 클릭하면 해당 경로로 이동
			location.href = '/hs/lecAssignmentList?lctr_num=${hsAssignWrite.lctr_num}';
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
	<form action="AssignStdUpd" method="post" enctype="multipart/form-data">
		<h1>과제제출수정</h1>
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
				<td><textarea class="form-control" aria-label="With textarea" name="crans_cnt" id="floatingTextarea" style="height: 100px;">${hsAssigStdUpd.crans_cnt  }</textarea> </td>
			</tr>
			<div class="form-group">
				<tr>
					<th><label for="file1">파일</label></th>
					<td><!-- 기존 첨부파일 목록 표시 -->
						<input type="hidden" name="file_group" value="${hsAssigStdUpd.file_group2}">
						<input type="file" class="form-control" id="file1" name="file" multiple>
                        <c:if test="${not empty filePath1}">
                        	<input type="hidden" name="file_group" value="${hsAssigStdUpd.file_group2}">
                            <ul>
                            	<!-- 새 파일 첨부 -->
                        		<br>
                                <c:forEach var="fileL" items="${filePath1}">
                                    <li>
                                        <!-- 기존 파일 이름 표시 -->
                                        ${fileL.dwnld_file_nm}
                                        <!-- 파일 삭제 버튼 -->
                                        <button type="button" onclick="deleteFile(${fileL.file_group}, '${fileL.file_no}')">  x</button>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <ul class="file-list" id="newFiles"></ul></td>
				</tr>
			</div>
			<tr>
				<td colspan="4">※ 제출 마감일 전 까지는 수정이 가능합니다.</td>
			</tr>
		</table>
		<button type="button" class="btn btn-outline-primary" style="text-align: center;" onclick="confirmRedirect()">목록</button>
        <button type="submit" class="btn btn-outline-primary" style="text-align: center;">제출</button>
	</form>
	</div>
	</div>
</body>
<footer>
	<%@ include file="../footer.jsp" %>
</footer>
</html>