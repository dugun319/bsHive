<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminAppLctrList.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
	table, th, td{
		border-bottom: 2px solid #999999;
		border-collapse: collapse;
		text-align: center;
		font-size: 16px;
		font-weight: 600;
	}
</style>
<script type="text/javascript">
	function goSyllabus(lctr_num) {
		var url = "/kh/admin/goSyllabus?lctr_num=" + lctr_num;
		// var url = "/hs/lecSchedule?lctr_num=" + lctr_num;
		window.open(url, "_blank", 'width=1050,height=1485,location=no,status=no,scrollbars=no,top=100,left=300');
	}
	
	function goRequest(lctr_num) {
		confirm("담당교수에게 강의계획에 대한 보완을 요청하겠습니까?");
		var url = "/kh/admin/goRequest?lctr_num=" + lctr_num;
		window.open(url, "_blank", 'width=500,height=500,location=no,status=no,scrollbars=no,top=100,left=300');
	}
	
	function openLctr(lctr_num) {
		confirm("강의개설을 승인하겠습니까?");
		location.href = "/kh/admin/openLctr?lctr_num=" + lctr_num;
	}
	
	function closeLctr(lctr_num) {
		confirm("강의를 폐강하겠습니까?");
		location.href = "/kh/admin/closeLctr?lctr_num=" + lctr_num;
	}
	
	function startLctr(lctr_num) {
		confirm("강의를 시작하겠습니까?");
		location.href = "/kh/admin/startLctr?lctr_num=" + lctr_num;
	}
	
	
	$(document).ready(function() {
		  $('#modalBtn').on('click', function() {		//modalOpen
		    											// 버튼의 data-*** 속성에서 값 가져오기
		    var lctrNum = $(this).data('lctrnum');  	// data-lctrNum 속성
		    var empNm = $(this).data('empnm');      	// data-empNm 속성

		    // 모달에 값 전달
		    $('#modalLctrNum').val(lctrNum);
		    $('#modalEmpName').text(empNm);

		    // 모달 열기 (Bootstrap 모달을 열기 위해 사용)
		    $('#requestModal').modal('show');
	  });
	});

	function sendRequest() {
		$('#modalForm').submit();
		window.close();
	}

	$(function(){
		const sBox 			= $("#search");
		const len 			= sBox.find("option").length;
		const rawSearch		= '${rawList.aply_type}';
		
		sBox.find("option").each(function() {
		    if ($(this).val() == rawSearch) {
		        $(this).prop("selected", true);
		    }
		});
	});
	
	
</script>
</head>
<body>
	<header>
		<%@ include file="adminHeader.jsp"%>
	</header>
	
	<div class="container">
        <div class="left-menu">
        	<%@ include file="tree.jsp"%>
        </div>
        <div class="main-content">
        
	        <div id="searchDiv">
				<form action="/kh/admin/appLctrList" method="post">
					<select name="aply_type"	id="aply_type">
						<option value="100" selected="selected">개설신청</option>
						<option value="110">모집중</option>
						<option value="120">진행중</option>
						<option value="130">보완요청</option>
						<option value="140">강의종료</option>
						<option value="150">폐강</option>
					</select>
					<select name="search"		id="search">
						<option value="LCTR_NUM">강의번호</option>
						<option value="UNQ_NUM2">교수고유번호</option>
						<option value="EMP_NM">교수이름</option>
						<option value="SBJCT_NM">학과이름</option>
						<option value="LCTR_NAME">강의이름</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
					<input	type="hidden"		name="lectureType"				value="${rawList.lectureType}" />
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>

       	
			<table>
				<tr>
					<th class="cell1">강의번호</th>
					<th class="cell2">담당교수</th>
					<th class="cell3">전화번호</th>
					<th class="cell4">개설일</th>
					<th class="cell5">강의정원</th>
					<th class="cell6">강의명</th>
					<th class="cell7">강의상태</th>
					<th class="cell8">강의계획서</th>
					<th class="cell9">
					<c:set var="status"	value="${aplyType}" />
						<c:choose>
							<c:when test="${status eq '0'}">
								보완요청
							</c:when>
							<c:when test="${status eq '100'}">
								보완요청
							</c:when>
							<c:when test="${status eq '1'}">
								강의진행
							</c:when>
							<c:when test="${status eq '110'}">
								강의진행
							</c:when>
							<c:when test="${status eq '3'}">
								폐강
							</c:when>
							<c:when test="${status eq '130'}">
								폐강
							</c:when>
							<c:otherwise>보완요청</c:otherwise>
						</c:choose>
					</th>
					<th class="cell10">
						<c:choose>
							<c:when test="${status eq '0'}">
								승인
							</c:when>
							<c:when test="${status eq '100'}">
								승인
							</c:when>
							<c:when test="${status eq '1'}">
								폐강
							</c:when>
							<c:when test="${status eq '110'}">
								폐강
							</c:when>
							<c:when test="${status eq '2'}">
								폐강
							</c:when>
							<c:when test="${status eq '120'}">
								폐강
							</c:when>
							<c:otherwise>승인</c:otherwise>
						</c:choose>
					</th>			
				</tr>
				
				<c:forEach	var="lctrList"	items="${lctrList}"	varStatus="status" >
					
					<tr>
						<td class="cell1">${lctrList.lctr_num}</td>						
						<td class="cell2">${lctrList.emp_nm}</td>
						<td class="cell3">${lctrList.emp_telno}</td>
						<td class="cell4">${lctrList.aply_ydm}</td>						
						<td class="cell5">${lctrList.pscp_nope}</td>
						<td class="cell6">${lctrList.lctr_name}</td>
						<td class="cell7">
							<c:set var="status"	value="${lctrList.aply_type}" />
							<c:choose>
								<c:when test="${status eq '0'}">
									개설신청
								</c:when>
								<c:when test="${status eq '100'}">
									개설신청
								</c:when>
								<c:when test="${status eq '1'}">
									모집중
								</c:when>
								<c:when test="${status eq '110'}">
									모집중
								</c:when>
								<c:when test="${status eq '2'}">
									진행중
								</c:when>
								<c:when test="${status eq '120'}">
									진행중
								</c:when>
								<c:when test="${status eq '3'}">
									보완요청
								</c:when>
								<c:when test="${status eq '130'}">
									보완요청
								</c:when>
								<c:when test="${status eq '4'}">
									강의종료
								</c:when>
								<c:when test="${status eq '140'}">
									강의종료
								</c:when>
								<c:otherwise>폐강</c:otherwise>
							</c:choose>
						</td>
						
						<td class="cell8">
							<button type="button" id="syllabus"	onclick="goSyllabus('${lctrList.lctr_num}')">강의계획서</button>
						</td>
						<td class="cell9">
						<c:set var="status"	value="${lctrList.aply_type}" />
							<c:choose>
								<c:when test="${status eq '0'}">
									<button type="button" class="btn btn-primary"	id="modalBtn"	data-lctrNum="${lctrList.lctr_num}" data-empNm="${lctrList.emp_nm}" style="font-size: 12px;">
										보완요청
									</button>
								</c:when>
								<c:when test="${status eq '100'}">
									<button type="button" class="btn btn-primary"	id="modalBtn"	data-lctrNum="${lctrList.lctr_num}" data-empNm="${lctrList.emp_nm}" style="font-size: 12px;">
										보완요청
									</button>
								</c:when>
								<c:when test="${status eq '1'}">
									<button	id="canButton"	onclick="startLctr('${lctrList.lctr_num}')">강의시작</button>
								</c:when>
								<c:when test="${status eq '110'}">
									<button	id="canButton"	onclick="startLctr('${lctrList.lctr_num}')">강의시작</button>
								</c:when>
								<c:when test="${status eq '3'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
								<c:when test="${status eq '130'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
							</c:choose>	
						
							
							
							    
						    <!-- Modal -->
							<div class="modal fade" id="requestModal" tabindex="-1" aria-labelledby="#requestModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-m">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							      	<form action="/kh/admin/sendRequest"	id="modalForm"	method="post">
							      		&nbsp;&nbsp;&nbsp;<label style="font-weight: bold">담당교수 : </label><span id="modalEmpName"></span>
							      		<br>
							      		<br>
							      		<input type="hidden" id="modalLctrNum" name="lctr_num">
							      		<textarea name="requestContent" rows="20" cols="50"></textarea>
									</form>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="font-size: 12px;">취소</button>
							        <button type="button" onclick="sendRequest()"	class="btn btn-primary" style="font-size: 12px;">요청</button>
							      </div>
							    </div>
							  </div>
							</div>
						
							<%-- <button type="button" id="request"	onclick="goRequest('${lctrList.lctr_num}')">보완요청</button> --%>						</td>
						<td class="cell10">
							<c:set	var="aply_type"	value="${lctrList.aply_type}"/>
							<c:choose>
								<c:when test="${aply_type eq '0'}">
									<button	id="appButton" 	onclick="openLctr('${lctrList.lctr_num}')">개설승인</button>
								</c:when>
								<c:when test="${aply_type eq '100'}">
									<button	id="appButton" 	onclick="openLctr('${lctrList.lctr_num}')">개설승인</button>
								</c:when>
								<c:when test="${aply_type eq '1'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
								<c:when test="${aply_type eq '110'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
								<c:when test="${aply_type eq '2'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
								<c:when test="${aply_type eq '120'}">
									<button	id="canButton"	onclick="closeLctr('${lctrList.lctr_num}')">강의폐강</button>
								</c:when>
								<c:when test="${aply_type eq '3'}">
									<button	id="appButton" 	onclick="openLctr('${lctrList.lctr_num}')">개설승인</button>
								</c:when>
								<c:when test="${aply_type eq '130'}">
									<button	id="appButton" 	onclick="openLctr('${lctrList.lctr_num}')">개설승인</button>
								</c:when>
								<c:otherwise>수정불가</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			
		
		<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/appLctrList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&lectureType=${rawList.lectureType}&aply_type=${rawList.aply_type}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/appLctrList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}&lectureType=${rawList.lectureType}&aply_type=${rawList.aply_type}">[${i }]</a>
			</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/appLctrList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&lectureType=${rawList.lectureType}&aply_type=${rawList.aply_type}">[Next]</a>
			</c:if>
		</div>
			
        </div>
        
    </div>

	
</body>
</html>