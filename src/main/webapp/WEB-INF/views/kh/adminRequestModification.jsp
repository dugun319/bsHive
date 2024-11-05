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

<script type="text/javascript">
$(document).ready(function() {
	  // "모달 열기" 버튼 클릭 시
	  $('.openModalBtn').on('click', function() {
	    // 버튼의 data-* 속성에서 값 가져오기
	    var lctrNum = $(this).data('lctrnum');  // data-lctrNum 속성
	    var empNm = $(this).data('empnm');      // data-empNm 속성

	    // 모달에 값 전달
	    $('#modalLctrNum').text(lctrNum);
	    $('#modalEmpNm').text(empNm);

	    // 모달 열기 (Bootstrap 모달을 열기 위해 사용)
	    $('#myModal').modal('show');
	  });
	});

</script>
</head>
<body>
<table class="table">
  <thead>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>모달 열기</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td class="cell1">12345</td>
      <td class="cell2">홍길동</td>
      <td><button class="btn btn-primary openModalBtn" data-lctrNum="12345" data-empNm="홍길동">모달 열기</button></td>
    </tr>
    <tr>
      <td class="cell1">67890</td>
      <td class="cell2">김철수</td>
      <td><button class="btn btn-primary openModalBtn" data-lctrNum="67890" data-empNm="김철수">모달 열기</button></td>
    </tr>
  </tbody>
</table>

<!-- 모달 -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="myModalLabel">선택된 데이터</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><strong>번호:</strong> <span id="modalLctrNum"></span></p>
        <p><strong>이름:</strong> <span id="modalEmpNm"></span></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

	
</body>
</html>