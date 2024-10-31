<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="jh_jstl.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/jh_mypage.css">
<link rel="stylesheet" type="text/css" href="/css/jh_joinAgree.css">
<script type="text/javascript" src="/js/jh_joinAgree.js"></script>	
</head>
<body>

<header class="header">
    <h1>헤더</h1>
</header>
	
<div class="body">
    <div class="title">회원가입</div> <!-- 제목 추가 -->

    <div class="main-container"> <!-- 리스트와 콘텐츠를 감싸는 컨테이너 -->
        <div class="list">
            <div class="active">회원가입</div>
            <a href="/loginForm"><div>로그인</div></a>
            <a href="/loginForm"><div>아이디 찾기</div></a>
            <a href="/loginForm"><div>비밀번호 찾기</div></a>
        </div>
        
		<div class="content">
			<div class="form-group">
				<label class="agree">
					<Strong>1. 이용약관에 동의</Strong>
				</label>
				<div class="text">
					<strong>제 1 장 총 칙</strong>
					
					<strong>제 1 조 (목 적)</strong>
					이 약관은 부산광역시 HiVE 센터 고등직업교육거점지구(이하 '부산광역시 HiVE 센터 고등직업교육거점지구')이 제공하는 교육강좌서비스(이하 '서비스')의 이용조건 및 절차에 관한 사항을 규정함을 목적으로 합니다.
					
					<strong>제 2 조 (용어 정의)</strong>
					1. 이 약관에서 사용하는 용어는 다음과 같습니다.
					가. '회원'이라 함은 서비스를 제공받기 위해 부산광역시 HiVE 센터 고등직업교육거점지구가 인정하는 절차를 통해 가입하여 이용자번호 (ID)를 부여 받은 자를 말합니다.
					나. '이용자번호(ID)'라 함은 가입회원의 식별과 회원의 서비스 이용을 위해 고객이 선정하고 부산광역시 HiVE 센터 고등직업교육거점지구가 부여하는 문자와 숫자의 조합을 말합니다.
					다. '비밀번호'라 함은 회원이 부여받은 이용자번호(ID)와 일치된 회원임을 확인하고 회원의 권익보호를 위하여 회원이 선정한 문자와 숫자의 조합을 말합니다.
					2. 이 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스 이용안내에서 정하는 바에 따릅니다.
					
					<strong>제 3 조 (약관 외 적용범위)</strong>
					1. 이 약관은 부산광역시 HiVE 센터 고등직업교육거점지구가 제공하는 서비스 이용에 관한 안내(이하 '서비스 이용안내')와 함께 적용 합니다.
					2. 이 약관에 명시되지 않은 사항에 대해서는 관계법령 및 서비스 이용안내에 따릅니다.
					
					<strong>제 2 장 이용계약</strong>
					
					<strong>제 4 조 (서비스의 종류)</strong>
					서비스의 종류와 내용은 서비스 이용안내에서 정하는 바에 따릅니다.
					
					<strong>제 5조 (이용계약의 성립)</strong>
					1. 이용계약은 본 약관에 동의한 고객이 부산광역시 HiVE 센터 고등직업교육거점지구 소정의 이용신청서를 온라인 또는 부산광역시 HiVE 센터 고등직업교육거점지구가 정하는 방법으로 신청, 부산광역시 HiVE 센터 고등직업교육거점지구의 승낙에 의하여 성립합니다.
					2. 이용계약은 이용자번호(ID) 단위로 체결됩니다.
					3. 제 1항의 규정에 의해 고객이 이용신청을 할 때에는 부산광역시 HiVE 센터 고등직업교육거점지구가 정한 필수입력 사항을 입력하여야 합니다.
					4. 부산광역시 HiVE 센터 고등직업교육거점지구는 고객의 이용신청이 다음 각 호에 해당하는 경우 그 신청에 대한 승낙을 제한 또는 유보할 수 있습니다.
					가. 이름이 실명이 아닐 경우
					나. 허위 주민등록번호를 기재했을 경우
					다. 선량한 풍속, 기타 사회질서를 해 할 목적으로 신청한 경우
					라. 이용신청서의 내용을 허위로 기재하였거나 허위서류를 첨부하였을 경우
					마. 부산광역시 HiVE 센터 고등직업교육거점지구의 업무수행상 또는 기술상 지장이 있는 경우
					바. 기타 이용신청고객의 귀책사유로 이용승낙이 곤란한 경우
					
					<strong>제 6조 (약관의 동의)</strong>
					회원이 등록절차를 거처 동의 버튼을 누름으로써 본 약관에 동의한 것으로 간주합니다.
				</div>
				<div class="check">
					<input type="checkbox" class="checkBtn" id="agree1" required="required" 
						   onclick="toggleButton()">
					<span>동의합니다.</span>
				</div>
			</div>

			<div class="form-group">
				<label class="agree">
					<Strong>2. 개인정보 약관동의</Strong> 
				</label>
				<div class="text">
					<strong>개인정보의 수집ㆍ이용에 관한 사항</Strong> 
					
					<strong>개인정보의 수집ㆍ이용 목적</Strong> 
					부산광역시 HiVE 센터 고등직업교육거점지구에서는 프로그램 운영에 있어 귀하의 개인정보를 아래와 같이 수집·이용을 하고자 합니다. 다음의 사항에 대해 충분히 읽어보신 후, 동의여부를 체크, 서명하여 주시기 바랍니다.
					
					<strong>개인정보 수집항목</Strong> 
					필수항목 : 성명, 생년월일, 성별, 아이디, 비밀번호, 주소, 휴대폰번호, 전화번호, 이메일, 최종학력, 가입경로
					선택사항 : 정보수신여부(이메일/SMS)
					개인정보의 이용 및 보유기간
					위 개인정보는 수집ㆍ이용에 관한 동의일로부터 이용목적을 위한 보유ㆍ이용됩니다. 단, 지원자의 지원정보는 본 사업 종료 시까지 활용 후 폐기되며, 교육지원을 받아 교육을 받은 경우 교육관계법에 따른 정보관리를 위해서 개인정보를 활용할 수 있음
					
					<strong>동의 거부 권리 안내</Strong> 
					개인정보의 수집 및 이용 동의에 대해 거부 할 수 있으나, 거부하는 경우 교육지원이 제한 될 수 있습니다.
					
					<strong>개인정보의 제3자 제공에 관한 사항</Strong> 
					제공받는자의 개인정보 이용 목적
					지원자와 관련된 전력조사
					
					<strong>개인정보를 제공 받는자</Strong> 
					지원자의 전력관련 기관
				</div>
				<div class="check">
					<input type="checkbox" class="checkBtn" id="agree2" required="required" 
						   onclick="toggleButton()">
					<span>동의합니다.</span>
				</div>
			</div>

<div class="check2">
    <input type="checkbox" id="agree3" required="required" onclick="toggleAgreeCheckboxes()">
    <span>전체 약관에 모두 동의합니다.</span>
</div>



			<div class="form-group">
				<div class="moveBtn">
					<a href="/"><button id="backBtn">취소</button></a>
					<a href="/sign_up_form"><button id="submitBtn">다음</button></a>
				</div>
			</div>
		</div>
    </div>
</div>

<footer class="footer">
        &copy; 2024 MyPage Corp. All rights reserved.
</footer>




	
</body>
</html>