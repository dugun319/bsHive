package com.postGre.bsHive.Acontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postGre.bsHive.Amodel.Lctr_View;
import com.postGre.bsHive.SeService.SeService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SeVideoController {
	
	@Value("${spring.file.upload.path}")
    private String UPLOAD_DIRECTORY;
	
    @Value("${youtube.api.key}")
    private String youtubeApiKey;
    
    @Autowired
    private SeService ss;
    
    @GetMapping("/api/video-id")
    public ResponseEntity<Map<String, String>> getVideoId(@RequestParam(name = "vdo_id") String vdoId) {
        System.out.println("Received vdoId: " + vdoId); // vdoId 로그 추가
        String videoId = vdoId;

        Map<String, String> response = new HashMap<>();
        response.put("videoId", videoId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/get-max-dtl")
    public ResponseEntity<Map<String, Object>> getMaxDtl(
            @RequestParam("unqNum") int unqNum,
            @RequestParam("lctrNum") int lctrNum) {

        // Lctr_View 객체 생성
        Lctr_View lctr_View = new Lctr_View();

        // 받아온 파라미터 값으로 Lctr_View 객체의 속성 설정
        lctr_View.setUnq_num(unqNum);
        lctr_View.setLctr_num(lctrNum);

        // SeService에서 maxDtl 값을 가져오는 로직
        int maxDtl = ss.getMaxDtl(lctr_View);  // 설정된 lctr_View 객체를 사용

        Map<String, Object> response = new HashMap<>();
        response.put("maxDtl", maxDtl);

        return ResponseEntity.ok(response);
    }

    // maxDtl만 업데이트하는 엔드포인트
    @PostMapping("/api/update-last-dtl")
    public ResponseEntity<Map<String, String>> updateMaxDtl(@RequestBody Map<String, String> payload) {

        int lastDtl = Integer.parseInt(payload.get("lastDtl"));
        int unqNum = Integer.parseInt(payload.get("unqNum"));
        int unitNum = Integer.parseInt(payload.get("unitNum"));
        int lctrNum = Integer.parseInt(payload.get("lctrNum"));

        // 여기서 maxDtl만 업데이트 처리
        ss.updateMaxDtl(lastDtl, unqNum, unitNum, lctrNum);

        Map<String, String> response = new HashMap<>();
        response.put("message", "maxDtl 업데이트 성공");

        return ResponseEntity.ok(response);
    }

    // 나머지 정보를 업데이트하는 엔드포인트
    @PostMapping("/api/update-max-dtl")
    public ResponseEntity<Map<String, String>> updateLastDtl(@RequestBody Map<String, String> payload) {
        
        int lastDtl = Integer.parseInt(payload.get("lastDtl"));
        int maxDtl = Integer.parseInt(payload.get("maxDtl"));
        int unqNum = Integer.parseInt(payload.get("unqNum"));
        int unitNum = Integer.parseInt(payload.get("unitNum"));
        int lctrNum = Integer.parseInt(payload.get("lctrNum"));
        int lctrPace = Integer.parseInt(payload.get("lctrPace"));
        // 여기서 나머지 정보 업데이트 처리
        ss.updateView(maxDtl, lastDtl, unqNum, unitNum, lctrNum, lctrPace);

        Map<String, String> response = new HashMap<>();
        response.put("message", "나머지 정보 업데이트 성공");

        return ResponseEntity.ok(response);
    }


}
