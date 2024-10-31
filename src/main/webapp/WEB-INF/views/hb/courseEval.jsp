<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생용 강의 평가 페이지입니다</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
    }
    h2 {
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #fff;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        color: #333;
    }
    tr:hover {
        background-color: #f5f5f5;
    }
    input[type="submit"], input[type="reset"] {
        padding: 10px 15px;
        margin: 5px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
    }
    input[type="reset"] {
        background-color: #f44336;
        color: white;
    }
    input[type="submit"]:hover, input[type="reset"]:hover {
        opacity: 0.8;
    }
    textarea {
        width: 100%;
        height: 100px; /* 원하는 높이로 조절 가능 */
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
</style>
</head>
<body>
	<main>
	<h2> bsHive 강의 중간 평가</h2>
	<hr>
	<div>
		강의 평가는 익명성을 보장하며 행정업무상 참여인원만을 확인하고 수업개선을 위한 소중한 자료로 활용됩니다.
		<p>
		강의평가서는 수업의 질을 향상시키기 위한 목적으로 제작되었으며,
		<p>
		 성적평가에는 영향을 받지 않습니다.
		 <p>
		 한번 저장된 강의평가는 변경이 불가하니 입력 시 유의하시기 바랍니다.
	<hr>
	</div>
		<table>
			<th>과정구분</th>
		</table>
		<table border="2">
			<tr>
			 	<th>문항레벨</th>
			 	<th>과정구분</th>
			 	<th>문항분류</th>
			 	<th>문항명</th>
			 	<th>평가 (1-5)</th>
			 </tr>
			 <tr>
				<td>1</td>
				<td>이론</td>
				<td>내용의 이해</td>
				<td>강의 내용이 명확하고 이해하기 쉬웠습니까?</td>
				<td>
					<input type="radio" name="q1" value="1"> 1
					<input type="radio" name="q1" value="2"> 2
					<input type="radio" name="q1" value="3"> 3
					<input type="radio" name="q1" value="4"> 4
					<input type="radio" name="q1" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>2</td>
				<td>이론</td>
				<td>자료의 유용성</td>
				<td>강의 자료는 학습에 도움이 되었습니까?</td>
				<td>
					<input type="radio" name="q2" value="1"> 1
					<input type="radio" name="q2" value="2"> 2
					<input type="radio" name="q2" value="3"> 3
					<input type="radio" name="q2" value="4"> 4
					<input type="radio" name="q2" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>3</td>
				<td>이론</td>
				<td>교수님의 설명</td>
				<td>교수님의 설명 방식이 효과적이었습니까?</td>
				<td>
					<input type="radio" name="q3" value="1"> 1
					<input type="radio" name="q3" value="2"> 2
					<input type="radio" name="q3" value="3"> 3
					<input type="radio" name="q3" value="4"> 4
					<input type="radio" name="q3" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>4</td>
				<td>이론</td>
				<td>수업 진행 속도</td>
				<td>수업의 진행 속도가 적절하다고 느꼈습니까?</td>
				<td>
					<input type="radio" name="q4" value="1"> 1
					<input type="radio" name="q4" value="2"> 2
					<input type="radio" name="q4" value="3"> 3
					<input type="radio" name="q4" value="4"> 4
					<input type="radio" name="q4" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>5</td>
				<td>이론</td>
				<td>질문 응답</td>
				<td>교수님이 질문에 대해 충분히 설명해 주셨습니까?</td>
				<td>
					<input type="radio" name="q5" value="1"> 1
					<input type="radio" name="q5" value="2"> 2
					<input type="radio" name="q5" value="3"> 3
					<input type="radio" name="q5" value="4"> 4
					<input type="radio" name="q5" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>6</td>
				<td>이론</td>
				<td>수업 만족도</td>
				<td>전체적인 수업 만족도는 얼마나 됩니까?</td>
				<td>
					<input type="radio" name="q6" value="1"> 1
					<input type="radio" name="q6" value="2"> 2
					<input type="radio" name="q6" value="3"> 3
					<input type="radio" name="q6" value="4"> 4
					<input type="radio" name="q6" value="5"> 5
				</td>
			</tr>
			<tr>
				<td>7</td>
				<td>이론</td>
				<td>추천 의향</td>
				<td>이 수업을 다른 친구에게 추천할 의향이 있습니까?</td>
				<td>
					<input type="radio" name="q7" value="1"> 1
					<input type="radio" name="q7" value="2"> 2
					<input type="radio" name="q7" value="3"> 3
					<input type="radio" name="q7" value="4"> 4
					<input type="radio" name="q7" value="5"> 5
				</td>
			</tr>
			<tr>
			<td>8</td>
			<td>이론</td>
			<td>주관식</td>
				<td colspan="2">자유롭게 의견을 적어주세요</td>
				</tr>
				<tr>
				<td colspan="5" >
					<textarea name="feedback"  ></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" name="courseEvalSubmit" value="제출">
		<input type="reset" value="다시하기">
	</main>
</body>
</html>
