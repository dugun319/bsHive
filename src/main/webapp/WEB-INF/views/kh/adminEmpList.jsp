<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/kh_adminStdntList.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function delLgnId(param, mbr_se) {
		console.log(param);
		console.log(mbr_se);
		confirm("로그인 ID를 삭제합니다");		
		location.href = "/kh/admin/delLgnId?mbr_se=" + mbr_se + "&eml=" + encodeURIComponent(param);
	}
	
	$(function(){
		const sBox 			= $("#search");
		const len 			= sBox.find("option").length;
		const rawSearch		= '${rawList.search}';
		
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
				<form action="/kh/admin/empList" method="post">
					<select name="search"	id="search">
						<option value="UNQ_NUM">고유번호</option>
						<option value="EML">로그인ID</option>
						<option value="EMP_NM">이름</option>
						<option value="EMP_TELNO">연락처</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
					<input	type="hidden"		name="mbr_se"	id="mbr_se"		value="${mbr_se}" />
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>

       	
			<table>
				<tr>
					<th class="cell1">고유번호</th>
					<th class="cell2">이름</th>
					<th class="cell3">연락처</th>
					<th class="cell4">주소</th>
					<th class="cell5">상세주소</th>
					<th class="cell6">우편번호</th>
					<th class="cell7">로그인 ID</th>
					<th class="cell8">삭제</th>			
				</tr>
				
				<c:forEach	var="empList"	items="${empList}"	varStatus="status" >
					<tr>
						<td class="cell1">${empList.unq_num}</td>						
						<td class="cell2">${empList.emp_nm}</td>
						<td class="cell3">${empList.emp_telno}</td>						
						<td class="cell4">${empList.emp_addr}</td>
						<td class="cell5">${empList.emp_daddr}</td>						
						<td class="cell6">${empList.emp_zip}</td>
						<td class="cell7">${empList.eml}</td>
						<td class="cell8">
							<button type="button" onclick="delLgnId('${empList.eml}', '${mbr_se}')">로그인 ID 삭제</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			
		<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/empList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&mbr_se=${mbr_se}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/empList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}&mbr_se=${mbr_se}">[${i }]</a>				</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/empList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&mbr_se=${mbr_se}">[Next]</a>
			</c:if>
		</div>
		  
        </div>
        
        
        
    </div>
	
</body>
</html>