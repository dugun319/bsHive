<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 15px;
	background-color: #aaaaaa;
	font-size: 18px;
}

select {
	width: 200px;
	height: 30px;
	font-size: 16px;
	font-weight: 900;
	margin: 10px;
}

table{
	margin: 30px;
}
.leftCell{
	width: 200px;
	height: 40px;
	font-weight: 700;
}

.rightCell{
	width: 400px;
}

#proofFile {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

#proofFileUploadName{
	width: 200px;
    height: 30px;
    font-size: 12px;
}

#bankFile {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

#bankFileUploadName{
	width: 200px;
    height: 30px;
    font-size: 12px;
}

.fileLabel {
    display: inline-block;
    padding-top: 5px;
    text-align:center;
    color: #fff;
    vertical-align: middle;
    background-color: #134b84;
    cursor: pointer;
    width: 100px;
    height: 30px;
    margin-left: 10px;
    font-size: 12px;
}

.fileCaption {
	font-size: 12px;
	font-weight: bold;
	margin: 0px;
	line-height: 120%;
}

.bankInput{
	width: 300px;
    height: 30px;
    font-size: 12px;
}

#submitButton{
	text-align: center;
}

</style>
<script type="text/javascript">
	$(function(){
	    $("#ptcp_type").on('change', function(){
	    	
	    	var value = $("#ptcp_type option:selected").val();
	    	
	    	if(value > 100) {
	    		$('#priority_type').prop('disabled', true);		    		
	    	} else {
	    		$('#priority_type').prop('disabled', false);
	    	}
	    });
	    
		$("#priority_type").on('change', function(){
	    	
	    	var value = $("#priority_type option:selected").val();
	    	
	    	if(value > 100) {
	    		$('#ptcp_type').prop('disabled', true);		    		
	    	} else {
	    		$('#ptcp_type').prop('disabled', false);
	    	}
	    });
	});


	$(function() {
		$("#proofFile").on('change', function() {
			var fileName 	= $("#proofFile").val();
			
			var extension 	= fileName.split('.').pop().toLowerCase();
			
			if($.inArray(extension, ['jpg','jpeg','gif','png']) == -1){
				alert("jpg, jpeg, gif, png 파일만 첨부가능합니다.");
				$("#proofFile").val("");
				return;
			} else{
				$("#proofFileUploadName").val(fileName);
			}
			
			var maxSize = 10 * 1024 * 1024; // 10MB
		    var fileSize = $("#proofFile")[0].files[0].size;
		    if(fileSize > maxSize){
		    alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
		    $("#proofFile").val("");
		    return;
		    }
			
		});
		
		$("#bankFile").on('change', function() {
			var fileName 	= $("#bankFile").val();
			
			var extension 	= fileName.split('.').pop().toLowerCase();
			
			if($.inArray(extension, ['jpg','jpeg','gif','png']) == -1){
				alert("jpg, jpeg, gif, png 파일만 첨부가능합니다.");
				$("#bankFile").val("");
				return;
			} else{
				$("#bankFileUploadName").val(fileName);
			}
			
			var maxSize = 10 * 1024 * 1024; // 10MB
		    var fileSize = $("#bankFile")[0].files[0].size;
		    if(fileSize > maxSize){
		    alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
		    $("#bankFile").val("");
		    return;
		    }
			
		});
	});

	

	function applyScholarship() {
		
		var pFileName 	= $("#proofFile").val();
		var bFileName 	= $("#bankFile").val();
		var bNum		= $("#bank_num").val();
		var bName		= $("#bank_name").val();
		
		
		if(pFileName.length == 0) {
			alert("증명서류를 업로드하세요");
			$("#proofFile").focus();
			return false;
		} else if(bFileName.length == 0 ) {
			alert("통장사본을 업로드하세요");
			$("#bankFile").focus();
			return false;
		} else if(bNum.length == 0 ) {
			alert("계좌번호를 입력하세요");
			$("#bank_num").focus();
			return false; 
		} else if(bName.length == 0 ) {
			alert("은행이름 입력하세요");
			$("#bank_name").focus();
			return false; 
		} else {		
			confirm("입력하시겠습니까?");
			$("#applyScholarshipForm").submit();
		}
		
		
		
		
		
		
		
	}

</script>
</head>
<body>
		<img	src="<%=request.getContextPath()%>/images/main/kh_Logo_.png" />
		
		<div id="basicInformation">
		<table>
			<tr>
				<td colspan="2" style="font-size: 24px; font-weight: 900; text-align: center;">
					장학금신청
				</td>
			</tr>
			<tr>
				<th class="leftCell">강의번호</th>
				<td class="rightCell">${schDetail.lctr_num }  
				</td>
			</tr>
			<tr>
				<th class="leftCell">강의명</th>
				<td class="rightCell">${schDetail.lctr_name }  
				</td>
			</tr>
			<tr>
				<th class="leftCell">고유번호</th>
				<td class="rightCell">${schDetail.unq_num }  
				</td>
			</tr>
			<tr>
				<th class="leftCell">이름</th>
				<td class="rightCell">${schDetail.stdnt_nm }  
				</td>
			</tr>
			
		</table>
        </div>
        
       	<form action="/kh/admin/inputInfo" 	id="applyScholarshipForm"	method="post" enctype="multipart/form-data">
       	<input type="hidden"	name="lctr_num"		value="${schDetail.lctr_num }  ">
       	<input type="hidden"	name="lctr_name"	value="${schDetail.lctr_name }  ">
       	<input type="hidden"	name="unq_num"		value="${schDetail.unq_num }  ">
       	<input type="hidden"	name="stdnt_nm"		value="${schDetail.stdnt_nm }  ">
       	<input type="hidden"	name="atndc_scr"	value="${schDetail.atndc_scr }  ">
       	<input type="hidden"	name="asmt_scr"		value="${schDetail.asmt_scr }  ">
       	<input type="hidden"	name="fnsh_yn"		value="${schDetail.fnsh_yn }  ">
       	<input type="hidden"	name="pace"			value="${schDetail.pace }  ">
       	<input type="hidden"	name="fnsh_cost"	value="${schDetail.fnsh_cost }  ">
       	
       	<table>
	       	<tr>
	       		<th class="leftCell">참여유형</th>
				<td class="rightCell">
					<select name="ptcp_type"		id="ptcp_type">
						<option value="100" selected="selected">해당없음</option>
						<option value="110">구직자</option>
						<option value="120">재직자</option>
						<option value="130">소상공인</option>
					</select> 
				</td>
	       	</tr>
	       	<tr>
	       		<th class="leftCell">우대유형</th>
				<td class="rightCell">
					<select name="priority_type"	id="priority_type">
						<option value="100" selected="selected">해당없음</option>
						<option value="110">장애인</option>
						<option value="120">경력단절자</option>
						<option value="130">탈북자</option>
						<option value="140">다문화가정</option>
						<option value="150">기초생활대상자</option>
					</select> 
				</td>
	       	</tr>
       		<tr>	
				<th class="leftCell">증명서류</th>
				<td>					
					<input	id="proofFileUploadName"
							placeholder="첨부파일"
							readonly="readonly" 
							required="required" />
					<label 	for="proofFile" class="fileLabel">파일찾기</label>
   					<input 	type="file" 
   							id="proofFile"
   							name="proofFile"
   							accept=".jpg, .gif, .png" 
   							required="required" 
   							readonly="readonly"
   							style="font-size: 12px;" />
   					<br>
   					<p class="fileCaption">.jpg, .gif, .png 파일만 첨부가능합니다.</p>
					<p class="fileCaption">파일크기는 10MB 이하만 가능합니다.</p>
				</td>
			</tr>
			<tr>
				<th class="leftCell">은행이름</th>
				<td class="rightCell">
					<input type="text" name="bank_name"  class="bankInput"   id="bank_name" required="required">
				</td>
			</tr>
			<tr>
				<th class="leftCell">계좌번호</th>
				<td class="rightCell">
					<input type="text" name="bank_num"  class="bankInput"    id="bank_num" required="required">
				</td>
			</tr>
			<tr>	
				<th class="leftCell">통장사본</th>
				<td>					
					<input	id="bankFileUploadName"
							placeholder="첨부파일"
							readonly="readonly" 
							required="required" />
					<label 	for="bankFile"  class="fileLabel">파일찾기</label>
   					<input 	type="file" 
   							id="bankFile"
   							name="bankFile"
   							accept=".jpg, .gif, .png" 
   							required="required" 
   							readonly="readonly"
   							style="font-size: 12px;" />
   					<br>
   					<p class="fileCaption">.jpg, .gif, .png 파일만 첨부가능합니다.</p>
					<p class="fileCaption">파일크기는 10MB 이하만 가능합니다.</p>
				</td>
			</tr>
		</table>
		</form>
		
		<div id="submitButton">
		<button type="button" class="btn btn-primary"	onclick="applyScholarship()">
			장학금신청
		</button>	
		</div>	

</body>
</html>