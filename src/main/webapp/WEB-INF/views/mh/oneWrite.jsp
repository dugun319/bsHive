<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ include file="sidebarPst.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	$('#summernote').summernote({
	toolbar:[
		['custom', ['examplePlugin']],
		['style', ['style', 'examplePlugin']]
		 // 글꼴 설정
	    ['fontname', ['fontname']],
	    // 글자 크기 설정
	    ['fontsize', ['fontsize']],
	    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
	    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	    // 글자색
	    ['color', ['forecolor','color']],
	    // 표만들기
	    ['table', ['table']],
	    // 글머리 기호, 번호매기기, 문단정렬
	    ['para', ['ul', 'ol', 'paragraph']],
	    // 줄간격
	    ['height', ['height']],
		
	]
	});	
});
$.extend($.summernote.options, {
	examplePlugin: {
		icon: '<i class="note-icon-pencil"/>',
		tooltip: 'Example Plugin Tooltip'
	}
});
$('.dropdown-toggle').dropdown();

</script>
<style type="text/css">

	
      table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 100%;
            border-radius: 8px;
            overflow: hidden;
        }
             th, td {
            padding: 12px;
            border: 2px solid #ddd;
            word-wrap: break-word; /* Allow word wrap */
            min-width: 0px; /* Minimum width */
        }

        th {
            background-color: #034EA2; /* Header background */
            color: white;
            text-align: center;
        }

        td {
            text-align: left;
        }

        textarea {
            width: 100%;
            height: 200px;
            resize: vertical; /* Allow vertical resize */
        }
        input {
        	outline: none;
        	border-width: 0;
        }

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="oneInsert" method="post" enctype="multipart/form-data">
 <input type="hidden" name="writer" value="${writer }">
        <table border="1">
            <tr>
                <td>제목</td>
                <td><input type="text" name="pst_ttl"></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea id="summernote" rows="10" name="pst_cn"></textarea></td>
            </tr>
            <tr>
            <td colspan="2">
                <div class="form-group">
                <label for="file">파일</label>
                <input class="form-control-file" id="file" name="file" type="file" multiple>
            </div>
            </td>
            <tr>
                <td colspan="2">
                    <input type="submit" value="작성">
                    &nbsp;&nbsp; 
                    <a href="gongList">목록보기</a>&nbsp;&nbsp; 
                </td>
            </tr>
        </table>
    </form>
    
</body>
</html>