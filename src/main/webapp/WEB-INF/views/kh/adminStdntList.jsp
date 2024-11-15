<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminStdntList.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	function delLgnId(param) {
		confirm("로그인 ID를 삭제합니다");
		console.log(param);
		location.href = "/kh/admin/delLgnId?mbr_se=1&eml=" + encodeURIComponent(param);
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
				<form action="/kh/admin/stdntList" method="post">
					<select name="search"	id="search">
						<option value="UNQ_NUM">고유번호</option>
						<option value="EML">로그인ID</option>
						<option value="STDNT_NM">이름</option>
						<option value="STDNT_TELNO">연락처</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
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
				
				<c:forEach	var="stdntList"	items="${stdntList}"	varStatus="status" >
					<tr>
						<td class="cell1">${stdntList.unq_num}</td>						
						<td class="cell2">${stdntList.stdnt_nm}</td>
						<td class="cell3">${stdntList.stdnt_telno}</td>						
						<td class="cell4">${stdntList.stdnt_addr}</td>
						<td class="cell5">${stdntList.stdnt_daddr}</td>						
						<td class="cell6">${stdntList.stdnt_zip}</td>
						<td class="cell7">${stdntList.eml}</td>
						<td class="cell8">
							<button type="button" onclick="delLgnId('${stdntList.eml}')">로그인 ID 삭제</button>
						</td>
					</tr>
				</c:forEach>
			</table>
        
        
    	<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/stdntList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/stdntList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}">[${i }]</a>
			</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/stdntList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}">[Next]</a>
			</c:if>
		</div> 
		
		</div>         
    </div>

</body>
</html>