package com.postGre.bsHive.Acontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postGre.bsHive.SeService.SeService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SeVideoController {

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


}
