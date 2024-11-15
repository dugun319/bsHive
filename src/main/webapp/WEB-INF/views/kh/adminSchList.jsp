<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminSchList.css" rel="stylesheet" type="text/css">
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
	
	function loadImg(num, type) {
		var url = "/kh/admin/loadImg?num=" + num + "&type=" + type;
		window.open(url, "_blank", 'width=700,height=990,location=no,status=no,scrollbars=no,top=100,left=300');
	}
	
	$(function(){
		const sBox 			= $("#give_stts");
		const len 			= sBox.find("option").length;
		const giveStssVal 	= '${rawList.give_stts}';
		
		sBox.find("option").each(function() {
		    if ($(this).val() == giveStssVal) {
		        $(this).prop("selected", true);
		    }
		});
	});
	
	function updateGiveStss(num, gStts){
		location.href="/kh/admin/updateGiveStss?num=" + num + "&gStts=" + gStts;
	}

	function applyScholarship() {
		alert("장학금을 신청하겠습니까?");
		var url = "/kh/admin/applyScholarship?lctr_num=24122507";		
		window.open(url, "_blank", 'width=600,height=800,location=no,status=no,scrollbars=no,top=100,left=300');
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
        	<!-- <button type="button" onclick="applyScholarship()">
        		장학금신청
        	</button> -->
        </div>
        
        <div class="main-content">
        	 <div id="searchDiv">
				<form action="/kh/admin/schList" method="post">
					<select name="give_stts"	id="give_stts">
						<option value="100" selected="selected">신청</option>
						<option value="110">심사</option>
						<option value="120">지급</option>
						<option value="130">수령</option>
						<option value="140">기부</option>
						<option value="150">거절</option>
					</select>
					<select name="search"		id="search">
						<option value="LCTR_NUM">강의번호</option>
						<option value="UNQ_NUM">고유번호</option>
						<option value="STDNT_NM">이름</option>
					</select>
					<input	type="text"			name="keyword"	id="keyword" 	placeholder="keyword" />
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>
			
			<div>
			
			<table>
				<tr>
					<th class="cell1">장학금수여번호</th>
					<th class="cell2">고유번호</th>
					<th class="cell3">학생이름</th>
					<th class="cell4">강의번호</th>
					<th class="cell5">강의이름</th>
					<th class="cell6">참여유형</th>
					<th class="cell7">우대유형</th>
					<th class="cell8">지원비용</th>
					<th class="cell9">통장사본</th>
					<th class="cell10">출석점수</th>
					<th class="cell11">과제점수</th>
					<th class="cell12">수료여부</th>
					<th class="cell13">처리상태</th>
				</tr>
				
				<c:forEach	var="schList"	items="${schList}"	varStatus="status" >
					
					<tr>
						<td class="cell1">${schList.scholarship_num}</td>						
						<td class="cell2">${schList.unq_num}</td>
						<td class="cell3">${schList.stdnt_nm}</td>
						<td class="cell4">${schList.lctr_num}</td>						
						<td class="cell5">${schList.lctr_name}</td>
						<td class="cell6">
						<c:set var="ptcpType"	value="${schList.ptcp_type}" />
						<c:if test="${ptcpType ne '0' }">
							<button type="button" id="ptcpButton"	onclick="loadImg('${schList.scholarship_num}', 'participate')">
								${schList.ptcp_type}
							</button>
						</c:if>
						</td>
						
						
						<td class="cell7">
						<c:set var="priType"	value="${schList.priority_type}" />
						<c:if test="${priType ne '0' }">
							<button type="button" id="prioButton"	onclick="loadImg('${schList.scholarship_num}', 'priority')">
								${schList.priority_type}
							</button>
						</c:if>
							
						</td>
						
						<td class="cell8">${schList.sprt_cost}</td>
						<td class="cell9">
							<button type="button" id="bankButton"	onclick="loadImg('${schList.scholarship_num}', 'bank')">
								확인
							</button>
						</td>
						<td class="cell10">${schList.atndc_scr}</td>
						<td class="cell11">${schList.asmt_scr}</td>
						<td class="cell12">${schList.fnsh_yn}</td>
						<td class="cell13">
							<select name="giveStts"	id="giveStts">
								<option value="100" selected="selected">신청</option>
								<option value="110">심사</option>
								<option value="120">지급</option>
								<option value="130">수령</option>
								<option value="140">기부</option>
								<option value="150">거절</option>
							</select>
							<button type="button" id="bankButton"	onclick="updateGiveStss('${schList.scholarship_num}', document.getElementById('giveStts').value)" >
									확인
							</button>
						</td>
						
					</tr>
				</c:forEach>
				</table>
			
        	</div>
        
		<div id="paging">
			<c:if test="${page.startPage > page.pageBlock }">
				<a href="/kh/admin/schList?currentPage=${page.startPage - page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&give_stts=${rawList.give_stts}">[Previous]</a>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
				<a href="/kh/admin/schList?currentPage=${i }&search=${rawList.search}&keyword=${rawList.keyword}&give_stts=${rawList.give_stts}">[${i }]</a>
			</c:forEach>
			
			<c:if test="${page.startPage < page.pageBlock }">
				<a href="/kh/admin/schList?currentPage=${page.startPage + page.pageBlock }&search=${rawList.search}&keyword=${rawList.keyword}&give_stts=${rawList.give_stts}">[Next]</a>
			</c:if>
		</div> 
		
		</div> 
    </div>
	
</body>
</html>