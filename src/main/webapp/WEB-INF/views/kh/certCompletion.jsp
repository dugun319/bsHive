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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/html2canvas.min.js"></script>
<style type="text/css">
@media print {
    #screenShot { 
        -webkit-print-color-adjust: exact;
        width: 210mm;
        height: 297mm;
    }
    #buttonDiv { display: none; } 		/* 인쇄 시 버튼 숨기기 */
    table { page-break-inside: auto; }
    tr { page-break-inside: avoid; page-break-after: auto; }
    thead { display: table-header-group; }
    tfoot { display: table-footer-group; }
}


body{
	margin: 0;
	padding: 0;
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
}

/*
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
*/

#aplyNum{
	position: absolute;
	top: 25px;
	left: 100px;
	font-size: 32px;
	font-weight: 700;
}

#stdntInfo{
	position: absolute;
	top: 390px;
	left: 790px;
	font-size: 20px;
	font-weight: 700;
}

.stdntInfoCell{
	width: 180px;
	height: 55px;
	text-align: right;
	vertical-align: middle;
	
}

#subjectInfo{
	position: absolute;
	top: 630px;
	left: 350px;
	font-size: 20px;
	font-weight: 700;
}

.subjectInfoCell{
	width: 500px;
	height: 55px;
	vertical-align: middle;
	
}

#issueDate{
	position: absolute;
	top: 1070px;
	left: 320px;
	width: 600px;
	height: 55px;
	font-size: 24px;
	font-weight: 700;
	padding-left: 20px;
	vertical-align: middle;
	justify-content: center;
}

#buttonDiv{
	text-align: center;
}

.btn{
	width: 200px;
	height: 40px;
	background-color: #134b84;
	color: #FDFDFD;
	font-size: 16px;
	font-weight: 900;
	border: none;
	cursor: pointer;
	margin: 5px;
	margin-top: 30px;
}

</style>

<script type="text/javascript">

	$(function(){
		$('#btn_comfirm').click(function() {
		    html2canvas(document.getElementById("screenShot")).then(function(canvas) {
		        alert("이미지를 저장합니다");
	
		        var image = canvas.toDataURL("image/png"); 
		        $("#imgData").val(image);
	
		        $.ajax({
		            url: '/kh/admin/issueCertification', 		// 컨트롤러 URL에 맞게 설정
		            type: 'POST',
		            data: $("#imgForm").serialize(),
		            success: function(response) {
		                alert("이미지 저장 완료");
		                window.close();
		            },
		            error: function(error) {
		                alert("전송 실패: " + error.responseText);
		            }
		        });
		    });
		});
	});
		
	$(function(){
	    $("#btn_download").click(function(e){
	        html2canvas(document.getElementById("screenShot")).then(function(canvas) {
	            var imageDiv 		= document.createElement("a");
				var imageName		= $('#sellNumVal').val() + "_contract.jpg";
				
	            imageDiv.href 		= canvas.toDataURL("image/jpeg");
	            imageDiv.download 	= imageName;						 //다운로드 할 파일명 설정
	            imageDiv.click();
	        })
	    })
	});
	
	
	$(function(){
		    $("#btn_print").click(function(e){
		        var printArea = document.getElementById("screenShot");
		    	window.print(printArea);
		    })
	});
	
</script>


</head>
<body>

	<form id="imgForm" name="imgForm" action="/kh/admin/issueCertification" method="post">
		<input type="hidden" name="imgData"  	id="imgData" >	
		<input type="hidden" name="aply_num" 	id="aplyNum"	value="${prDetail.aply_num }">
	</form>

	<div id="screenShot">
	
		<img	src="<%=request.getContextPath()%>/images/main/kh_completion.png"
				width="1050px" 
				height="1485px">
				
		<div id="aplyNum">
			${prDetail.aply_num }
		</div>
	
		<div id="stdntInfo">
			<table>
				<tr>
					<td class="stdntInfoCell">${prDetail.unq_num }</td>
				</tr>
				<tr>
					<td class="stdntInfoCell">${prDetail.stdnt_nm }</td>
				</tr>
				<tr>
					<td class="stdntInfoCell">${prDetail.stdntDepart }</td>
				</tr>
			</table>		
		</div>
		
		<div id="subjectInfo">
			<table>
				<tr>
					<td class="subjectInfoCell"></td>
				</tr>
				<tr>
					<td class="subjectInfoCell">${prDetail.lctr_name }</td>
				</tr>
				<tr>
					<td class="subjectInfoCell">${prDetail.start_date } ~ ${prDetail.end_date }</td>
				</tr>
				<tr>
					<td class="subjectInfoCell">${prDetail.end_date }</td>
				</tr>
			</table>		
		</div>
		
		<div id="issueDate">
			${prDetail.yy } 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${prDetail.month } 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${prDetail.day }	
		</div>
	
		
	</div>
	
	<div id="buttonDiv">
		<button type="button" id="btn_comfirm"	class="btn">확인</button>
		<button type="button" id="btn_download"	class="btn">파일다운로드</button>
		<button type="button" id="btn_print"	class="btn">인쇄하기</button>
	</div>
</body>
</html>