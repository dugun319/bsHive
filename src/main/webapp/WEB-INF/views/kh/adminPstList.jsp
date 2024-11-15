<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminPstList.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	function updateDelYnPst(pst_num, pst_clsf, del_yn) {
		console.log(pst_num);
		confirm("게시물을 삭제합니다");		
		location.href = "/kh/admin/updateDelYnPst?pst_num=" 
						+ pst_num 
						+ "&pst_clsf=" 
						+ pst_clsf
						+ "&del_yn=" 
						+ del_yn;
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
				<form action="/kh/admin/boardList" method="post">
					<select name="search"	id="search">
						<option value="PST_NUM">게시물번호</option>
						<option value="UNQ_NUM">작성자고유번호</option>
						<option value="PST_TTL">제목</option>
						<option value="PST_CN">내용</option>
						<option value="WRT_YMD">내용</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>

       	
			<table>
				<tr>
					<th class="cell1">게시물번호</th>
					<th class="cell2">작성자고유번호</th>
					<th class="cell3">이름</th>
					<th class="cell4">전화번호</th>
					<th class="cell5" style="text-align: center;">제목</th>
					<th class="cell6">작성일</th>
					<th class="cell7">삭제여부</th>
				</tr>
				
				<c:forEach	var="pstList"	items="${pstList}"	varStatus="status" >
					<tr>
						<td class="cell1">${pstList.pst_num}</td>						
						<td class="cell2">${pstList.unq_num}</td>
						<td class="cell3">
						<c:set var="status"	value="${pstList.pst_clsf}" />
							<c:choose>
								<c:when test="${status eq '1'}">
									${pstList.empName}
								</c:when>
								<c:when test="${status eq '2'}">
									${pstList.empName}
								</c:when>
								<c:when test="${status eq '3'}">
									${pstList.empName}
								</c:when>
								<c:otherwise>${pstList.studentName}</c:otherwise>
							</c:choose>
						</td>
						<td class="cell4">
						<c:set var="status"	value="${pstList.pst_clsf}" />
							<c:choose>
								<c:when test="${status eq '1'}">
									${pstList.empTelNo}
								</c:when>
								<c:when test="${status eq '2'}">
									${pstList.empTelNo}
								</c:when>
								<c:when test="${status eq '3'}">
									${pstList.empTelNo}
								</c:when>
								<c:otherwise>${pstList.studentTelNo}</c:otherwise>
							</c:choose>
						</td>
												
						<td class="cell5">${pstList.pst_ttl}</td>
						<td class="cell6">${pstList.wrt_ymd}</td>						
						<td class="cell7">
						<c:set var="status"	value="${pstList.del_yn}" />
							<c:choose>
								<c:when test="${status eq '0'}">
									<button type="button" id="del"		onclick="updateDelYnPst('${pstList.pst_num}', '${pstList.pst_clsf}', '1')">게시물삭제</button>
								</c:when>
								<c:otherwise>
									<button type="button" id="recover"	onclick="updateDelYnPst('${pstList.pst_num}', '${pstList.pst_clsf}', '0')">게시물복원</button>
								</c:otherwise>
							</c:choose>
							</td>
					</tr>
				</c:forEach>
			</table>
        </div>
        
		<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/boardList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&pst_clsf=${rawList.pst_clsf}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/boardList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}&pst_clsf=${rawList.pst_clsf}">[${i }]</a>
			</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/boardList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&pst_clsf=${rawList.pst_clsf}">[Next]</a>
			</c:if>
		</div> 
		
		</div>
        
    </div>
	
</body>
</html>