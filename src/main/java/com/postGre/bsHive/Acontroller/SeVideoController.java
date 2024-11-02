package com.postGre.bsHive.Acontroller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postGre.bsHive.SeService.SeService;

import java.util.Collections;
>>>>>>> origin/br_test
import java.util.HashMap;
import java.util.Map;

@RestController
public class SeVideoController {
<<<<<<< HEAD
	/*
	 * @Value("${youtube.api.key}") private String youtubeApiKey;
	 * 
	 * // @Autowired // private SeVideoService sevideoService;
	 * 
	 * 
	 * @GetMapping("/api/video-id") public ResponseEntity<Map<String, String>>
	 * getVideoId() { // 비디오 ID를 동적으로 가져오는 로직을 여기에 추가 // String videoId =
	 * sevideoService.getLatestVideoId(); // 서비스에서 비디오 ID 가져오기 String videoId =
	 * "MtiHSv1iwXs"; // 위에 코드 사용예정 Map<String, String> response = new HashMap<>();
	 * response.put("videoId", videoId); return ResponseEntity.ok(response); }
	 */    
=======

//    @Value("${youtube.api.key}")
//    private String youtubeApiKey;
//    
    @Autowired
    private SeService ss;
    
    @GetMapping("/api/video-id")
    public ResponseEntity<Map<String, String>> getVideoId(@RequestParam(name = "vdo_id") String vdoId) {
        System.out.println("Received vdoId: " + vdoId); // vdoId 로그 추가
     //   String videoId = "pisdBj08LqA";
        String videoId = vdoId;

        Map<String, String> response = new HashMap<>();
        response.put("videoId", videoId);
        return ResponseEntity.ok(response);
    }


>>>>>>> origin/br_test
}
