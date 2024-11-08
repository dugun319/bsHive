<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>영상 시청</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <c:set var="vdoId" value="${vdoId}"></c:set>
    <c:set var="unqNum" value="${unqNum}"></c:set>
    <c:set var="unitNum" value="${unitNum}"></c:set>
    <c:set var="lctrNum" value="${lctrNum}"></c:set>
    <c:set var="contsNm" value="${contsNm}"></c:set>
    <c:set var="maxDtl" value="${maxDtl}"></c:set>
    <c:set var="lastDtl" value="${lastDtl}"></c:set>
	<c:set var="chnm" value="${chnm}"></c:set>
	<c:set var="playHr" value="${playHr}"></c:set>
</head>
<style>
    /* 전반적인 스타일 */
    body {
        font-family: 'Roboto', sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 0;
        display: flex;
        min-height: 100vh;
        justify-content: flex-start;
    }

    /* 사이드바 스타일 */
    .sidebar {
        width: 200px;
        background-color: #2c3e50;
        color: white;
        padding: 30px;
        box-shadow: 4px 0 15px rgba(0, 0, 0, 0.1);
        height: 100vh;
        position: fixed;
        top: 0;
        left: 0;
        overflow-y: auto;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

    .sidebar h2 {
        color: #ecf0f1;
        font-size: 22px;
        margin-bottom: 20px;
        font-weight: 600;
        text-transform: uppercase;
        letter-spacing: 1px;
    }

    .sidebar a {
        display: block;
        color: #ecf0f1;
        font-size: 16px;
        text-decoration: none;
        padding: 12px 18px;
        border-radius: 8px;
        margin-bottom: 12px;
        transition: background-color 0.3s, padding-left 0.2s ease;
    }

    .sidebar a:hover {
        background-color: #34495e;
        padding-left: 24px;
    }

    /* 메인 콘텐츠 영역 스타일 */
    .content {
        margin-left: 250px;
        padding: 15px;
        flex-grow: 1;
        background-color: #ffffff;
        border-radius: 12px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease-in-out;
        overflow: hidden;
    }

    h1 {
        font-size: 36px;
        color: #34495e;
        margin-bottom: 30px;
        text-align: center;
        font-weight: 600;
    }

    /* 비디오 컨테이너 */
    .video-container {
        display: flex;
        justify-content: center;
        margin-bottom: 30px;
        position: relative;
    }

    /* 비디오 플레이어 */
    #ytplayer {
        width: 100%;
        max-width: 1200px;
        height: 675px;
        border-radius: 12px;
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
        background-color: #000;
    }

    /* 진도율 Progress Bar */
    progress {
        width: 100%;
        height: 14px;
        border-radius: 10px;
        background-color: #f0f4f7;
        appearance: none;
        border: none;
    }

    progress::-webkit-progress-bar {
        background-color: #f0f4f7;
        border-radius: 10px;
    }

    progress::-webkit-progress-value {
        background-color: #4caf50;
        border-radius: 10px;
    }

    progress::-moz-progress-bar {
        background-color: #4caf50;
        border-radius: 10px;
    }

    /* 버튼 스타일 */
    .btn {
        display: inline-block;
        padding: 10px 20px;
        font-size: 16px;
        color: #fff;
        background-color: #2980b9;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s ease, transform 0.2s;
        margin-right: 10px;
    }

    .btn:hover {
        background-color: #3498db;
        transform: scale(1.05);
    }

    .btn:focus {
        outline: none;
    }

    /* 반응형 디자인 */
    @media (max-width: 768px) {
        .content {
            margin-left: 0;
            padding: 20px;
        }

        h1 {
            font-size: 30px;
        }

        .video-container {
            margin-bottom: 20px;
        }

        #ytplayer {
            height: 400px;
        }

        .sidebar {
            position: relative;
            width: 100%;
            height: auto;
            box-shadow: none;
            margin-bottom: 20px;
        }
    }
    </style>
<body>
    <!-- 사이드바 -->
    <div class="sidebar">
	    <h2>챕터 목록</h2>
	    <c:forEach var="chapter" items="${chapterList}">
	        <!-- 각 챕터에 대한 링크 -->
	        <a href="javascript:void(0);" 
	           class="chapter-link" 
	           data-playstart="${chapter.play_start}">
	           - ${chapter.ch_nm}
	        </a>
	    </c:forEach>
	</div>

    <!-- 메인 콘텐츠 영역 -->
    <div class="content">
        <h1>${contsNm}</h1>

        <!-- 비디오 플레이어 -->
        <div class="video-container">
            <div id="ytplayer"></div>
        </div>

        <div>
            <c:if test="${not empty View.filegroup}">
                <c:forEach var="filePath" items="${fn:split(View.file_group, ',')}">
                    <c:set var="fileName" value="${fn:substringAfter(filePath, '')}" />
                    <a download="${fileName}" href="download?filePath=${filePath.trim()}" class="btn">
                        ${fileName} 다운로드
                    </a>
                    <br>
                </c:forEach>
            </c:if>
        </div>
    </div>

<script>
    var tag = document.createElement('script');
    tag.src = "https://www.youtube.com/player_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    let player;
    let maxDtl = '${maxDtl}';  
    let lastDtl = '${lastDtl}';  
    const playStart = parseFloat('${playstart}');
    let unqNum = parseInt('${unqNum}', 10) || 0;
    let unitNum = '${unitNum}';
    let lctrNum = parseInt('${lctrNum}', 10) || 0;
    let playHr = parseInt('${playHr}', 10) || 0;  // playHr가 없으면 0으로 기본값 설정

    function fetchVideoData(vdoId) {
        return fetch(`/api/video-id?vdo_id=` + vdoId)
            .then(response => {
                if (!response.ok) {
                    throw new Error('비디오 데이터 가져오기 오류');
                }
                return response.json();
            })
            .then(data => {
                return { videoId: data.videoId };
            })
            .catch(error => {
                alert('비디오 데이터 로드 실패: ' + error.message);
                return { videoId: '' };
            });
    }

    let updateMaxDtlInterval;
    let totUpdate;
    let videoPlaying = false;

    // 현재 시청 위치 체크 (2초 간격)
    function checkAndUpdateMaxDtl() {
	    // player가 준비되고, getCurrentTime 메소드가 존재하는 경우에만 처리
	    if (player && player.getCurrentTime) {
	        const currentTime = player.getCurrentTime();
	
	        // 비디오 시간이 정상적으로 나오면
	        let newLastDtl = Math.round(currentTime);  // 현재 비디오 시간을 정수로 변환
	        console.log('newLastDtl:', newLastDtl);

	        // 롤백 조건 체크: 현재 비디오 시간이 maxDtl보다 4초 이상 차이날 때
	        console.log('newLastDtl:', newLastDtl, 'maxDtl:', maxDtl);
	        if (newLastDtl - maxDtl >= 5) {
	            console.log('롤백 조건 만족, 롤백 시작');
	            if (player.getPlayerState() === YT.PlayerState.PLAYING) {
	                player.seekTo(maxDtl, true);
	                console.log('재생위치를 maxDtl로 롤백');
	            } else {
	                console.log('비디오가 재생 중이 아니므로 롤백을 수행하지 않았습니다.');
	            }
	        }
	
	        // 서버로 데이터 전송
	        fetch('/api/update-last-dtl', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify({
	                lastDtl: newLastDtl,
	                maxDtl: maxDtl,
	                unqNum: unqNum,
	                unitNum: unitNum,
	                lctrNum: lctrNum
	            })
	        })
	        .then(response => response.json())
	        .catch(error => {
	            console.error('Error updating maxDtl:', error);
	        });
	    }
	}


    // 진행 중인 비디오 정보 업데이트 (3초 주기) -> 명칭을 totalUpdate로 변경
    function totalUpdate() {
        const currentTime = player.getCurrentTime();
        let newMaxDtl = Math.floor(currentTime);  // 비디오의 현재 시간 (초 단위)

        // playStart가 설정되어 있을 경우, 그 값보다 작은 경우 playStart로 설정
        if (newMaxDtl < playStart) {
            newMaxDtl = Math.floor(playStart);
        }

        // maxDtl을 최신 값으로 갱신
        maxDtl = Math.max(newMaxDtl, maxDtl);  // 서버에서 가져온 값과 현재 플레이 중인 비디오 시간 중 큰 값 사용

        const playHr = parseInt('${playHr}', 10) || 0;
        let lctrPace = 0;

        // play_hr가 0보다 클 경우에만 진행률을 계산
        if (playHr > 0) {
            lctrPace = Math.min(Math.floor((maxDtl / playHr) * 100), 100);
        }

        // 서버로 진행률 및 관련 데이터를 전송
        fetch('/api/update-max-dtl', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                lastDtl: maxDtl,  // 최신 maxDtl을 lastDtl로 설정
                maxDtl: maxDtl,    // 최신 maxDtl을 갱신된 값으로 전송
                unqNum: unqNum,
                unitNum: unitNum,
                lctrNum: lctrNum,
                lctrPace: lctrPace
            })
        })
        .catch(error => {
            console.error('Error updating other info:', error);
        });
    }

    function startUpdating() {
        // maxDtl 갱신 및 체크는 3초 간격으로
        updateMaxDtlInterval = setInterval(checkAndUpdateMaxDtl, 1000);  // 3초마다 전체 업데이트
        // 진행 중인 정보는 3초마다 갱신
        totUpdate = setInterval(totalUpdate, 3000);  // 3초마다 진행률 업데이트 (명칭 변경됨)
    }

    function stopUpdating() {
        clearInterval(updateMaxDtlInterval);
        updateMaxDtlInterval = null;
        clearInterval(totUpdate);
        totUpdate = null;
    }

    // 비디오 플레이어의 속도 체크 (1초마다)
    let updateSpeedCheckInterval;

    function checkAndCorrectPlaybackSpeed() {
        if (player && player.getPlaybackRate) {
            const currentSpeed = player.getPlaybackRate();

            if (currentSpeed > 1.01) {
                player.setPlaybackRate(1.0);
            }
        }
    }

    function startSpeedCheck() {
        updateSpeedCheckInterval = setInterval(checkAndCorrectPlaybackSpeed, 1000);  // 1초마다 속도 체크
    }

    function onYouTubePlayerAPIReady() {
        const vdoId = '${vdoId}';

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
                        'onReady': function(event) {
                            event.target.setPlaybackRate(1.0);
                            // 초기 비디오 시작 시간 설정
                            const startTime = lastDtl > playStart ? lastDtl : playStart;
                            event.target.seekTo(startTime, true);
                            event.target.playVideo();
                        },
                        'onStateChange': function(event) {
                            if (event.data === YT.PlayerState.PAUSED || event.data === YT.PlayerState.ENDED) {
                                videoPlaying = false;
                                stopUpdating();
                                clearInterval(updateSpeedCheckInterval);
                            } else if (event.data === YT.PlayerState.PLAYING) {
                                if (!updateMaxDtlInterval && !totUpdate) {
                                    videoPlaying = true;
                                    startUpdating();
                                    startSpeedCheck();
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    // 챕터 링크 클릭 시 비디오의 재생 위치를 변경하는 코드
    document.querySelectorAll('.chapter-link').forEach(function(link) {
        link.addEventListener('click', function() {
            // 클릭한 챕터의 play_start 값을 가져옴
            const playStart = parseFloat(link.getAttribute('data-playstart'));

            // 유튜브 플레이어가 준비되었으면 해당 시간으로 seek
            if (player && player.seekTo) {
                player.seekTo(playStart, true);
                player.playVideo(); // 비디오 재생
            }
        });
    });
</script>

</body>
</html>