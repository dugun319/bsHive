package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Onln_Lctr;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.Amodel.Syllabus_Unit;
import com.postGre.bsHive.JwService.JwService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping
@Slf4j

public class JwController {
	private final JwService js;
	// 파일첨부
	 @Value("${spring.file.upload.path}")
	    private String UPLOAD_DIRECTORY;
	
	// 1. 강의계획서 루틴(교수제출->직원승인->온라인강의에 등록됨)
	
	// 1-1. 교수제출
	
	// 강의개설 폼으로 이동
	// 로그인한 교수의 정보에서 고유번호와 이름을 들고 감
	@RequestMapping(value = "/jw/writeFormOnlnLctr")
	public String writeFormOnlnLctr() {
		System.out.println("JwController writeFormOnlnLctr Start...");	
		
//		User_Table user_Table1 = (User_Table) session.getAttribute("user");
//		User_Table user_Table3 = js.getUserTable(user_Table1.getUnq_num());
//		System.out.println("JwController writeFormOnlnLctr user_Table3->"+user_Table3);
//		
//		// session.setAttribute("user", user_Table);
//		model.addAttribute("user_Table3", user_Table3);
		
		return "jw/writeFormOnlnLctr";
	}
	

	// 온라인강의 TBL insert
	@RequestMapping(value = "/jw/insertOnlnLctr" , method = RequestMethod.POST)
	public String insertOnlnLctr(Onln_Lctr onln_lctr,
								 Model model, 
								 HttpSession session,
								 @RequestParam("lastTwoDigits") String lastTwoDigits, 
								 @RequestParam("file") MultipartFile[] files, 
								 Pst pst
								 ) {
		System.out.println("JwController insertOnlnLctr Start...");
		
		// Lctr_num 새 강의번호 뒤의 두 자리 뺴고 생성
		// 기본값 설정: lastTwoDigits가 빈 값인 경우 "00"으로 대체
        if (lastTwoDigits == null || lastTwoDigits.isEmpty()) {
            lastTwoDigits = "00";
        }
		// 연도 마지막 두 자리 추출
		String yearPrefix = String.valueOf(LocalDate.now().getYear()).substring(2);
		System.out.println("연도 마지막 두 자리->"+yearPrefix);
		
		// 차수 번호
		String unitNum = "1";
		
		// 학부 + 학과 번호 추출
		Integer unqNum = (Integer)session.getAttribute("unq_num");
		String unqNumStr = String.valueOf(unqNum);
		String fourFiveNumber = getFourFiveNumber(unqNumStr);
		System.out.println("교수번호에서 추출한 학부, 학과번호->"+fourFiveNumber);
		
		// 오프라인 숫자
		String offNum = "5";
		
		// 교수가 직접 입력하는 강의코드 두 자리
		String lectNum = lastTwoDigits;
		
		// 위의 숫자들 합해서 구성한 강의 번호 구성
		String lctr_num = yearPrefix + unitNum + fourFiveNumber + offNum + lectNum;
		System.out.println("생성된 강의번호->"+lctr_num);
		
		// 강의번호를 모델에 추가하여 뷰로 전달
		model.addAttribute("lctr_num", lctr_num);
		
		
		// 교수의 Unq_num2 가져와서 뷰로 전달
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		User_Table user_Table3 = js.getUserTable(user_Table1.getUnq_num());
		// user_Table3.unq_num=224110017 -->교수 
		System.out.println("JwController writeFormOnlnLctr user_Table3->"+user_Table3);
		model.addAttribute("user_Table3", user_Table3);
		
		
		// 파일첨부
		 StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성

	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    // 고유 파일 이름 생성 (UUID 사용)
	                    String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	                    Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueFileName);

	                    // 파일 저장
	                    Files.createDirectories(filePath.getParent());  // 디렉토리 생성
	                    file.transferTo(filePath.toFile());

	                    fileGroupPaths.append("C:/upload/").append(uniqueFileName).append(",");

	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        // 모든 파일 경로 설정 후 pst 객체에 저장
	        if (fileGroupPaths.length() > 0) {
	            fileGroupPaths.setLength(fileGroupPaths.length() - 1); // 마지막 콤마 제거
	        }
	        onln_lctr.setLctr_data(fileGroupPaths.toString()); // pst 객체에 file_group 경로 설정

	        
	    // Onln_Lctr의 PK인 Lctr_num을 Interger로 형전환
	    int lctr_numInt =  Integer.parseInt(lctr_num);
	    onln_lctr.setLctr_num(lctr_numInt);
	    
	    // user_Table3.unq_num에서 가져온 교수 고유번호 set
	    onln_lctr.setUnq_num2(user_Table3.getUnq_num());
		System.out.println("JwController insertOnlnLctr insertLCTR onln_lctr->"+onln_lctr);
	
		
		// 강의번호를 포함한 온라인 강의 TBL insert 
		int insertLCTR = js.insertLCTR(onln_lctr);
		System.out.println("JwController insertOnlnLctr insertLCTR->"+insertLCTR);
		
		if(insertLCTR > 0)
			// 다음 작성페이지로 이동
			return "jw/writeFormOnlnConts";
		
		else {
			model.addAttribute("msg", "입력 실패11. 확인 바랍니다.");
			System.out.println("JwController insertOnlnLctr insertLCTR failure->"+insertLCTR);
			
			return "forward:/writeFormOnlnLctr";
		}
	
	}
	
	// 학부 + 학과 번호 추출 보조 메소드
	private String getFourFiveNumber(String unqNumStr) {
		if (unqNumStr.length() >= 5) {
			return unqNumStr.substring(3,5);
		} else {
			return "Invalid unq_num";
		}
	}
	 
	// 온라인 콘텐츠 정보 입력 화면 이동
	@RequestMapping(value = "/jw/writeFormOnlnConts")
	public String writeFormOnlnConts() {
		System.out.println("JwController writeFormOnlnConts Start...");	
		
		return "jw/writeFormOnlnConts";
	}
	
	
	

	// 온라인 차시비디오 TBL insert
	@RequestMapping(value = "/jw/insertOnlnConts", method = RequestMethod.POST)
	public String insertOnlnCont(@RequestParam("vdo_id") String vdo_id,  // 폼에서 넘어온 비디오 ID
            					 @RequestParam("conts_nm") String conts_nm,  // 폼에서 넘어온 콘텐츠명
            					 @RequestParam("play_hr") String play_hr,  // 폼에서 넘어온 재생시간
								 HttpSession session,
								 Model model
								) {
		System.out.println("JwController insertOnlnCont Start...");

		// 1. 교수가 작성한 Onln_Lctr 테이블에서 차시번호가 없는 강의번호 가져오기
		int lctr_num = js.getLctrNum(); 
		System.out.println("가져온 강의번호->"+lctr_num);
		
		if (lctr_num == 0) {
            model.addAttribute("msg", "강의번호를 찾을 수 없습니다.");
            return "forward:/writeFormOnlnLctr"; // 강의번호가 DB에도 없을 경우 리턴
        }

		// 2. 위에서 가져온 강의번호 가져와서 차시번호로 활용하게 뷰로 보내기
		int unit_num = lctr_num;
		System.out.println("insertOnlnCont 만들어진 차시번호->" + unit_num);
		model.addAttribute("unit_num", unit_num);
		
		// 3. 차시비디오 TBL insert
	    Syllabus_Unit syllabus_Unit = new Syllabus_Unit();
		// 첫 번째 콘텐츠 ID
	    syllabus_Unit.setLctr_num(lctr_num);
	    syllabus_Unit.setUnit_num(unit_num);
	    syllabus_Unit.setVdo_id(vdo_id);
	    syllabus_Unit.setConts_nm(conts_nm);
	    syllabus_Unit.setPlay_hr(play_hr);
	    
		int insertOnlnsyllUnit = js.insertOnlnCont(syllabus_Unit);
		System.out.println("JwController insertOnlnCont insertOnlnCont1->"+insertOnlnsyllUnit);
		model.addAttribute("syllabus_Unit", syllabus_Unit);
		
		
		if(insertOnlnsyllUnit > 0)
			return "redirect:/";
		
		else {
			model.addAttribute("msg", "입력 실패11. 확인 바랍니다.");
			System.out.println("JwController insert failure->"+insertOnlnsyllUnit);
			
			return "forward:jw/writeFormOnlnLctr";
		}
	
	}
	
}
