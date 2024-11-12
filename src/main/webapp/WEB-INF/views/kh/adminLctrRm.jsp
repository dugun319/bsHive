<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/kh_adminPstList.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

<style>
ul, ol, li { 
	list-style:none;
	padding:0;
	margin:0; 
}

table, th, td {
	border: 2px solid #f5f5f5;
	border-collapse: collapse;
}

.rolling { 
	width: 1130px; 
	height:600px;
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
	padding:0 20px; 
	float:left; 
	height:600px;  
	border-radius: 0; 
	box-shadow: none; 
	border:0; background-color: #999999;
	color:#fff;
	font-size: 20px;
	font-weight: 900;
}

.thCell{
	width: 90px;
	height: 45px;
	max-width: 90px;
	max-height: 45px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.tdCell{
	font-size: 12px;
	font-weight: 500;
	width: 90px;
	height: 45px;
	max-height: 45px;
	max-width: 90px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

#control1{
	position: absolute;
	width: 1260px;
	bottom: 60px;
	left: 550px;
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
        
        	<div id="searchDiv">
				<form action="/kh/admin/lctrRoom" method="post">
					<select name="year"		id="year">
						<option value="24">2024년도</option>
						<option value="23">2023년도</option>
						<option value="22">2022년도</option>
					</select>
					<select name="semester"	id="semester">
						<option value="1">1학기</option>
						<option value="2">2학기</option>
						<option value="3">3학기</option>
						<option value="4">4학기</option>
						<option value="5">5학기</option>
						<option value="6">6학기</option>
						<option value="7">7학기</option>
					</select>
					<button type="submit"		id="searchButton">SEARCH</button>
				</form>
			</div>
        
        	<div class="code-html">
            <div class="panel panel-primary">
                <div class="example">
                    <div id="rolling" class="rolling">
                    
                        <ul>
                            <li class="panel">
                            	월요일
                            	<br>
                            	<table>
	                            	<tr>
	                            		<th class="thCell">시간</th>
	                            		<th class="thCell">101</th>
	                            		<th class="thCell">102</th>
	                            		<th class="thCell">201</th>
	                            		<th class="thCell">202</th>
	                            		<th class="thCell">301</th>
	                            		<th class="thCell">302</th>
	                            		<th class="thCell">401</th>
	                            		<th class="thCell">402</th>
	                            		<th class="thCell">501</th>
	                            		<th class="thCell">502</th>
	                            	</tr>
	                            	<c:set var="time" value="8"/>
									<c:forEach var="row" items="${str01}">
									<tr>
										<td class="tdCell">
										<c:set var="time" value="${time + 1}" />
										${time}:00
										</td>
										<c:forEach var="item" items="${row}">
											<c:if test="${empty item}">
									            <td class="tdCell" style="background-color: #999999;">${item}</td>
									        </c:if>
									        <c:if test="${not empty item}">
									            <td class="tdCell" style="background-color: #323232;">${item}</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</table>
                            </li>
                            
                            <li class="panel">
                            	화요일
                            	<br>
                            	<table>
	                            	<tr>
	                            		<th class="thCell">시간</th>
	                            		<th class="thCell">101</th>
	                            		<th class="thCell">102</th>
	                            		<th class="thCell">201</th>
	                            		<th class="thCell">202</th>
	                            		<th class="thCell">301</th>
	                            		<th class="thCell">302</th>
	                            		<th class="thCell">401</th>
	                            		<th class="thCell">402</th>
	                            		<th class="thCell">501</th>
	                            		<th class="thCell">502</th>
	                            	</tr>
	                            	<c:set var="time" value="8"/>
									<c:forEach var="row" items="${str02}">
									<tr>
										<td class="tdCell">
										<c:set var="time" value="${time + 1}" />
										${time}:00
										</td>
										<c:forEach var="item" items="${row}">
											<c:if test="${empty item}">
									            <td class="tdCell" style="background-color: #999999;">${item}</td>
									        </c:if>
									        <c:if test="${not empty item}">
									            <td class="tdCell" style="background-color: #323232;">${item}</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</table>
                            </li>
                            
                            <li class="panel">
                            	수요일
                            	<br>
                            	<table>
	                            	<tr>
	                            		<th class="thCell">시간</th>
	                            		<th class="thCell">101</th>
	                            		<th class="thCell">102</th>
	                            		<th class="thCell">201</th>
	                            		<th class="thCell">202</th>
	                            		<th class="thCell">301</th>
	                            		<th class="thCell">302</th>
	                            		<th class="thCell">401</th>
	                            		<th class="thCell">402</th>
	                            		<th class="thCell">501</th>
	                            		<th class="thCell">502</th>
	                            	</tr>
	                            	<c:set var="time" value="8"/>
									<c:forEach var="row" items="${str03}">
									<tr>
										<td class="tdCell">
										<c:set var="time" value="${time + 1}" />
										${time}:00
										</td>
										<c:forEach var="item" items="${row}">
											<c:if test="${empty item}">
									            <td class="tdCell" style="background-color: #999999;">${item}</td>
									        </c:if>
									        <c:if test="${not empty item}">
									            <td class="tdCell" style="background-color: #323232;">${item}</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</table>
                            </li>
                            
                            <li class="panel">
                            	목요일
                            	<br>
                            	<table>
	                            	<tr>
	                            		<th class="thCell">시간</th>
	                            		<th class="thCell">101</th>
	                            		<th class="thCell">102</th>
	                            		<th class="thCell">201</th>
	                            		<th class="thCell">202</th>
	                            		<th class="thCell">301</th>
	                            		<th class="thCell">302</th>
	                            		<th class="thCell">401</th>
	                            		<th class="thCell">402</th>
	                            		<th class="thCell">501</th>
	                            		<th class="thCell">502</th>
	                            	</tr>
	                            	<c:set var="time" value="8"/>
									<c:forEach var="row" items="${str04}">
									<tr>
										<td class="tdCell">
										<c:set var="time" value="${time + 1}" />
										${time}:00
										</td>
										<c:forEach var="item" items="${row}">
											<c:if test="${empty item}">
									            <td class="tdCell" style="background-color: #999999;">${item}</td>
									        </c:if>
									        <c:if test="${not empty item}">
									            <td class="tdCell" style="background-color: #323232;">${item}</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</table>
                            </li>
                            
                            <li class="panel">
                            	금요일
                            	<br>
                            	<table>
	                            	<tr>
	                            		<th class="thCell">시간</th>
	                            		<th class="thCell">101</th>
	                            		<th class="thCell">102</th>
	                            		<th class="thCell">201</th>
	                            		<th class="thCell">202</th>
	                            		<th class="thCell">301</th>
	                            		<th class="thCell">302</th>
	                            		<th class="thCell">401</th>
	                            		<th class="thCell">402</th>
	                            		<th class="thCell">501</th>
	                            		<th class="thCell">502</th>
	                            	</tr>
	                            	<c:set var="time" value="8"/>
									<c:forEach var="row" items="${str05}">
									<tr>
										<td class="tdCell">
										<c:set var="time" value="${time + 1}" />
										${time}:00
										</td>
										<c:forEach var="item" items="${row}">
											<c:if test="${empty item}">
									            <td class="tdCell" style="background-color: #999999;">${item}</td>
									        </c:if>
									        <c:if test="${not empty item}">
									            <td class="tdCell" style="background-color: #323232;">${item}</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</table>
                            </li>
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
     	</div> 
     </div>
       
        
        
<%--       
	        

       	
			<div id="rolling" class="rolling">
                    <c:forEach	var="lctrList"	items="${lctrList}"	varStatus="status" >
                    <c:set var="dow_day"	value="${lctrmList.dow_day}" />
                        <ul>
                            <li class="panel">월요일</li>
                            <c:if test="${dow_day eq '1'}">
                            	${lctrList.lctr_num }
                            	${lctrList.lctr_name }
                            	${lctrList.bgng_tm }
                            	${lctrList.end_tm }
                            </c:if>
                            <li class="panel">화요일</li>
                            <c:if test="${dow_day eq '2'}">
                            	${lctrList.lctr_num }
                            	${lctrList.lctr_name }
                            	${lctrList.bgng_tm }
                            	${lctrList.end_tm }
                            </c:if>
                            <li class="panel">수요일</li>
                            <c:if test="${dow_day eq '3'}">
                            	${lctrList.lctr_num }
                            	${lctrList.lctr_name }
                            	${lctrList.bgng_tm }
                            	${lctrList.end_tm }
                            </c:if>
                            <li class="panel">목요일</li>
                            <c:if test="${dow_day eq '4'}">
                            	${lctrList.lctr_num }
                            	${lctrList.lctr_name }
                            	${lctrList.bgng_tm }
                            	${lctrList.end_tm }
                            </c:if>
                            <li class="panel">금요일</li>
                            <c:if test="${dow_day eq '5'}">
                            	${lctrList.lctr_num }
                            	${lctrList.lctr_name }
                            	${lctrList.bgng_tm }
                            	${lctrList.end_tm }
                            </c:if>
                        </ul>
                    </c:forEach>
                    </div>
 --%>        
    

	
	
</body>
</html>