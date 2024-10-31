<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>영상 시청</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <h1>영상 시청</h1>
    <div id="ytplayer"></div>

    <script>
    var tag = document.createElement('script');
    tag.src = "https://www.youtube.com/player_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    var player;
    let intervalId;
    let lastWatchedTime = 0; // 마지막 시청 시간
    let canRollback = false; // 슬라이더 이동 감지 플래그

    function fetchVideoId() {
        return fetch('/api/video-id')
            .then(response => response.json())
            .then(data => data.videoId)
            .catch(error => console.error('비디오 ID 가져오기 오류:', error));
    }

    function onYouTubePlayerAPIReady() {
        fetchVideoId().then(videoId => {
            player = new YT.Player('ytplayer', {
                height: '360',
                width: '640',
                videoId: videoId,
                playerVars: {
                    'controls': 1,
                    'showinfo': 0,
                    'modestbranding': 1,
                    'rel': 0
                },
                events: {
                    'onReady': onPlayerReady
                }
            });
        });
    }

    function onPlayerReady(event) {
        event.target.playVideo();

        // 3초마다 현재 재생 시간 업데이트
        intervalId = setInterval(() => {
            const currentTime = Math.floor(player.getCurrentTime()); // 현재 재생 시간을 초 단위로 저장

            console.log('현재 시청 시간:', currentTime); // 시청 시간 콘솔에 출력

            // 슬라이더가 조작된 후 현재 재생 시간보다 큰 값이 들어온 경우에만 롤백
            if (canRollback && currentTime > lastWatchedTime) {
                player.seekTo(lastWatchedTime); // 마지막 시청 시간으로 롤백
                player.playVideo(); // 롤백 후 재생
                console.log('영상이 되돌아갔습니다. 현재 시청 시간:', currentTime);
            }

            lastWatchedTime = currentTime; // 마지막 시청 시간 업데이트
        }, 3000); // 3초마다
    }

    // 슬라이더 변경 이벤트 추가
    document.getElementById('ytplayer').addEventListener('mousemove', () => {
        canRollback = true; // 슬라이더가 조작되면 롤백 가능
    });

    </script>
</body>
</html>
