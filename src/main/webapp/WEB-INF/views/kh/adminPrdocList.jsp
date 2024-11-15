<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminPrdocList.css" rel="stylesheet" type="text/css">
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
	function goCertCompletion(aply_num) {
		var url = "/kh/admin/goCertification?aply_num=" + aply_num + "&prdoc_type=100";
		window.open(url, "_blank", 'width=1050,height=1520,location=no,status=no,scrollbars=no,top=100,left=300');
	}
	
	function goCertScholarship(aply_num) {
		var url = "/kh/admin/goCertification?aply_num=" + aply_num + "&prdoc_type=110";;
		window.open(url, "_blank", 'width=1050,height=1520,location=no,status=no,scrollbars=no,top=100,left=300');
	}
	
	$(function(){
		const sBox 			= $("#prdoc_type");
		const len 			= sBox.find("option").length;
		const rawSearch		= '${rawList.prdoc_type}';
		
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
				<form action="/kh/admin/prdocList" method="post">
					<select name="prdoc_type"	id="prdoc_type">
						<option value="100" selected="selected">수료증명서</option>
						<option value="110">장학증명서</option>
					</select>
					<select name="search"		id="search">
						<option value="APLY_NUM">신청번호</option>
						<option value="UNQ_NUM">고유번호</option>
						<option value="ISSU_STTS">교수이름</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>

       	
			<table>
				<tr>
					<th class="cell1">신청번호</th>
					<th class="cell2">고유번호</th>
					<th class="cell3">이름</th>
					<th class="cell4">전화번호</th>
					<th class="cell5">강의번호</th>
					<th class="cell6">강의명</th>
					<th class="cell7">신청일</th>
					<th class="cell8">발급일</th>
					<th class="cell9">발급상태</th>
					<th class="cell10">확인</th>
					
				</tr>
				
				<c:forEach	var="prdocList"	items="${prdocList}"	varStatus="status" >
					
					<tr>
						<td class="cell1">${prdocList.aply_num}</td>						
						<td class="cell2">${prdocList.unq_num}</td>
						<td class="cell3">${prdocList.stdnt_nm}</td>
						<td class="cell4">${prdocList.stdnt_telno}</td>						
						<td class="cell5">${prdocList.lctr_num}</td>
						<td class="cell6">${prdocList.lctr_name}</td>
						<td class="cell7">${prdocList.aply_ymd}</td>
						<td class="cell8">${prdocList.issu_ymd}</td>
						<td class="cell9">
						<c:set var="status"	value="${prdocList.issu_stts}" />
							<c:choose>
								<c:when test="${status eq '100'}">
									신청
								</c:when>
								<c:when test="${status eq '110'}">
									승인
								</c:when>
								<c:when test="${status eq '120'}">
									미출력
								</c:when>
								<c:otherwise>출력</c:otherwise>
							</c:choose>
						</td>
						<td class="cell10">
						<c:set var="status"	value="${prdocList.prdoc_type}" />
							<c:choose>
								<c:when test="${status eq '100'}">
									<button	id="canButton"	onclick="goCertCompletion('${prdocList.aply_num}')">수료증명서</button>
								</c:when>
								<c:when test="${status eq '110'}">
									<button	id="canButton"	onclick="goCertScholarship('${prdocList.aply_num}')">장학증명서</button>
								</c:when>
							</c:choose>	
							
					</tr>
				</c:forEach>
			</table>
		
		<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/prdocList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&prdoc_type=${rawList.prdoc_type}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/prdocList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}&prdoc_type=${rawList.prdoc_type}">[${i }]</a>
			</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/prdocList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&prdoc_type=${rawList.prdoc_type}">[Next]</a>
			</c:if>
		</div>
			
        </div>
        
    </div>

	
</body>
</html>