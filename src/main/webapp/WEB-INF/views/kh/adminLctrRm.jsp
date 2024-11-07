<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminPstList.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/kh_tui-rolling-style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/kh_common_rolling.css" />
<script type="text/javascript">
	function updateDelYnPst(pst_num, pst_clsf, del_yn) {
		console.log(pst_num);
		alert("게시물을 삭제합니다");		
		location.href = "/kh/admin/updateDelYnPst?pst_num=" 
						+ pst_num 
						+ "&pst_clsf=" 
						+ pst_clsf
						+ "&del_yn=" 
						+ del_yn;
	}

</script>

<style>
ul, ol, li { 
	list-style:none;
	padding:0;
	margin:0; 
}

.rolling { 
	width: 1130px; 
	height:150px;
	margin:30px auto;
}

.rolling ul {
	width:1000000px; 
	float:left;
	position:absolute;
}

.example { 
	text-align: center; 
}

li.panel { 
	width: 1130px; 
	margin:0; 
	padding:0; 
	float:left; 
	height:150px;  
	border-radius: 0; 
	box-shadow: none; 
	border:0; background-color: #999999;
	color:#fff; 
}

</style>
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
        	<div class="code-html">
            <div class="panel panel-primary">
                <div class="example">
                    <div id="rolling" class="rolling">
                        <ul>
                            <li class="panel">월요일</li>
                            <li class="panel">화요일</li>
                            <li class="panel">수요일</li>
                            <li class="panel">목요일</li>
                            <li class="panel">금요일</li>
                        </ul>
                    </div>
                    <div class="btn-group" id="control1">
                        <button class="prev">left</button>
                        <button class="next">right</button>
                    </div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/css/dist/tui-rolling.js"></script>
        <script class="code-js">

            var rolling1 = new tui.Rolling({
                element: document.getElementById('rolling'),
                direction: 'horizontal',
                isVariable: false,
                isAuto: false,
                duration: 400,
                isCircular: true,
                isDrawn: true,
                initNum: 0,
                motion: 'linear',
                unit: 'page'
            });
            
            var control = document.getElementById('control1');

            control.onclick = function(e) {
                var e = e || window.event;
                var target = e.target || e.srcElement;
                var className;

                if (target.tagName.toLowerCase() !== 'button') {
                    return;
                }

                className = target.className;

                if (className.indexOf('prev') > -1) {
                    rolling1.roll(null, 'prev');
                } else if (className.indexOf('next') > -1) {
                    rolling1.roll(null, 'next');
                }
            };

        </script>
        
        
        
<%--       
	        <div id="searchDiv">
				<form action="/kh/admin/boardList" method="post">
					<select name="search"	id="search">
						<option value="PST_NUM">게시물번호</option>
						<option value="UNQ_NUM">작성자고유번호</option>
						<option value="PST_TTL">제목</option>
						<option value="PST_CN">내용</option>
						<option value="WRT_YMD">내용</option>
						<option value="">전체검색</option>
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
 --%>        
    </div>

	
	
</body>
</html>