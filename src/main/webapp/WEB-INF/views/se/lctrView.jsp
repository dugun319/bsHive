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
    <c:set var="vdoId" value="${vdoId}"></c:set>
</head>
<body onload="fetchVideoData('${vdoId}')">
    <h1>영상 시청</h1>
    
    <p>비디오 ID: ${vdoId}</p>
	<p>컨텐츠 ID: ${contsId}</p>
    
    <div id="ytplayer"></div>

     <script>
        var tag = document.createElement('script');
        tag.src = "https://www.youtube.com/player_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
    
        var player;

        function fetchVideoData(vdoId) {
            return fetch(`/api/video-id?vdo_id=` + vdoId)
                .then(response => response.json())
                .then(data => {
                    return { videoId: data.videoId };
                })
                .catch(error => {
                    console.error('비디오 데이터 가져오기 오류:', error);
                    return { videoId: '' };
                });
        }
    
        function onYouTubePlayerAPIReady() {
            const vdoId = '${vdoId}'; // vdoId를 JSP에서 사용
            fetchVideoData(vdoId).then(data => {
                if (data.videoId) {
                    player = new YT.Player('ytplayer', {
                        height: '360',
                        width: '640',
                        videoId: data.videoId,
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
                } else {
                    console.error('비디오 ID를 가져올 수 없습니다.');
                }
            });
        }

        function onPlayerReady(event) {
            event.target.playVideo();
        }
    </script>
</body>
</html>
