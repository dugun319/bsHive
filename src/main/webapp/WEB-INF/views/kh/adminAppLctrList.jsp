<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminAppLctrList.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function delLgnId(param, mbr_se) {
		console.log(param);
		console.log(mbr_se);
		alert("로그인 ID를 삭제합니다");		
		location.href = "/kh/admin/delLgnId?mbr_se=" + mbr_se + "&eml=" + encodeURIComponent(param);
	}

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
						<option value="0" selected="selected">개설신청</option>
						<option value="1">모집중</option>
						<option value="2">진행중</option>
						<option value="3">보완요청</option>
						<option value="4">강의종료</option>
						<option value="5">폐강</option>
					</select>
					<select name="search"		id="search">
						<option value="LCTR_NUM">강의번호</option>
						<option value="UNQ_NUM2">교수고유번호</option>
						<option value="EMP_NM">교수이름</option>
						<option value="SBJCT_NM">학과이름</option>
						<option value="LCTR_NAME">강의이름</option>
						<option value="">전체검색</option>
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
					<th class="cell9">보완요청</th>
					<th class="cell10">승인</th>			
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
								<c:when test="${status eq '1'}">
									모집중
								</c:when>
								<c:when test="${status eq '2'}">
									진행중
								</c:when>
								<c:when test="${status eq '3'}">
									보완요청
								</c:when>
								<c:when test="${status eq '4'}">
									강의종료
								</c:when>
								<c:otherwise>폐강</c:otherwise>
							</c:choose>
						</td>
						
						<td class="cell8">
							<button type="button" onclick="delLgnId('${lctrList.lctr_num}')">강의계획서</button>
						</td>
						<td class="cell9">
							<button type="button" onclick="delLgnId('${lctrList.lctr_num}')">보완요청</button>
						</td>
						<td class="cell10">
							<button type="button" onclick="delLgnId('${lctrList.lctr_num}')">강의승인</button>
						</td>
					</tr>
				</c:forEach>
			</table>
        </div>
        
    </div>
    <footer id="pagingDiv">
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
    </footer>
	
	
</body>
</html>