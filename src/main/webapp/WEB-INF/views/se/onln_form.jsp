<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상시청</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
	<h1>영상시청</h1>
	<div id="ytplayer"></div>
<script>
  // IFrame Player API 코드를 비동기로 로드합니다.
  var tag = document.createElement('script');
  tag.src = "https://www.youtube.com/player_api";
  var firstScriptTag = document.getElementsByTagName('script')[0];
  firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

  // YouTube 플레이어를 저장할 변수
  var player;

  // 데이터베이스에서 비디오 ID를 가져오는 함수
  function fetchVideoId() {
    // 'your_api_endpoint'를 실제 API 엔드포인트로 변경하세요
    return fetch('your_api_endpoint')
      .then(response => response.json())
      .then(data => {
        return data.videoId; // API 응답 구조에 맞게 조정하세요
      })
      .catch(error => {
        console.error('비디오 ID 가져오기 오류:', error);
      });
  }

  // API 코드 다운로드 후 'ytplayer' 요소를 <iframe>과 YouTube 플레이어로 교체합니다.
  function onYouTubePlayerAPIReady() {
    fetchVideoId().then(videoId => {
      player = new YT.Player('ytplayer', {
        height: '360',
        width: '640',
        videoId: SMjFxZXLoeQ // 여기에서 변수 사용
      });
    });
  }
</script>


</body>
</html>

