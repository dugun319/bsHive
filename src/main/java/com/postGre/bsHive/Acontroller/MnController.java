package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.Session;
import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Adto.Mn_LctrInfoPage;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Lctr_Week;
import com.postGre.bsHive.Amodel.Lctrm;
import com.postGre.bsHive.Amodel.Lgn;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.Amodel.Sort_Code;
import com.postGre.bsHive.Amodel.Stdnt;
import com.postGre.bsHive.MnService.MnService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MnController {
	private final MnService ms;
	
	@Value("${spring.file.upload.path}")
    private String UPLOAD_DIRECTORY;
	
	@GetMapping(value = "/")
	public String mainPage(Model model){
		System.out.println("MhController mainPage start...");
		
		List<Pst> pstList = ms.pstList();
		List<Pst> pstList5 = pstList.subList(0, Math.min(5, pstList.size()));
		
		List<Crans_Qitem> crQt = ms.selAll();
		
		System.out.println("crQt >>>>>> " + crQt);
		
		model.addAttribute("pstList", pstList5);
		model.addAttribute("crQt", crQt);
		
		return "main";
		
	}
	
	@GetMapping(value = "mn/lctListPage")
	public String lustLctPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, 
	                          Model model) {
	    System.out.println("MhController lustLctPage start...");
	    System.out.println("pageNum >>> "+pageNum);
	    int pageSize = 10; // 페이지당 항목 수
	    int totalLctrCount = ms.lctrListCount(); // 총 항목 수
	    
	    // 페이징을 위한 startIndex와 endIndex 계산
	    int startIndex = (pageNum - 1) * pageSize;
	    int endIndex = pageNum * pageSize;
	    
	    // 데이터 리스트 가져오기 (페이지네이션 적용)
	    List<Mn_LctrAplyOflWeek> lctrList = ms.joinLctrAplyAllList(startIndex, endIndex);
	    
	    // 총 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalLctrCount / pageSize);
	    // 모델에 데이터 추가
	    model.addAttribute("lctrList", lctrList);
	    model.addAttribute("listCot", totalLctrCount);
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", totalPages);
	    
	    return "mn/mn_of_lctrList";
	}
	
	@RequestMapping(value = "/mn/search_lctrList")
	public String searchList(@RequestParam(name = "keyword") String keyword, Model model) {
		System.out.println("MnController searchList start...");
		
		List<Mn_LctrAplyOflWeek> searchList = ms.searchLctrAplyList(keyword);
		
		model.addAttribute("lctrList",searchList);
		
		return "mn/mn_of_lctrList";
	}
	
	@GetMapping(value = "/mn/insertformlctr")
	public String insertLctrList() {
		System.out.println("MnController insertLctrList start...");
		return "mn/mn_insertPage";
	}
	
	@GetMapping(value = "mn/timePopup")
	public String timePopupPage(Model model) {
		System.out.println("MnController timePopupPage start...");
		
		List<Lctrm> lctrmList = ms.lctrmListAll();
		List<Sort_Code> codeList = ms.selectLctrm();
		System.out.println("codeList >>>>" + codeList);
		
		model.addAttribute("codeList",codeList);
		model.addAttribute("lctrm",lctrmList);
		
		return "mn/timePopup";
	}
	
	@GetMapping("/getReservations")
	@ResponseBody
	public List<Lctrm> getReservations(@RequestParam("roomNumber") String roomNumber) {
	    System.out.println("MnController getReservations start...");
	    System.out.println("roomNumber >>>>" +roomNumber);
	    return ms.getLcrtmRoomNumber(roomNumber);
	}
	
	@PostMapping("/mn/insertPage")
	public String insertPage(@ModelAttribute Mn_LctrAplyOflWeek lctrAplyOflWeek,
	                         @RequestParam("lctr_weeks") String[] lctr_weeks,
	                         @RequestParam("lctr_plan") String[] lctr_plans,
	                         @RequestParam(name = "files", required = false) List<MultipartFile> files,
	                         HttpSession session) {
	    System.out.println("MnController insertPage start...");
	    System.out.println("files >>>> " + files);
	    int userSess = (int) session.getAttribute("unq_num");
	    System.out.println("userSess >>>>" + userSess);
	    Stdnt stdUser = ms.getUserTable(userSess);
	    System.out.println("stdUser >>>>" + stdUser);
	    // 강의번호 생성
	    int currentYear = java.time.LocalDate.now().getYear() % 100; // 현재 연도의 마지막 두 자리
	    int quarter = (java.time.LocalDate.now().getMonthValue() - 1) / 3 + 1; // 현재 분기
	    Random random = new Random();
	    
	    int department = random.nextInt(9) + 1; // 1~9 랜덤
	    int major = random.nextInt(9) + 1; // 1~9 랜덤

	    // olineoff_type에 따라 랜덤값 생성
	    int onlineOfflineType = lctrAplyOflWeek.getOlineoff_type(); 
	    int olineoffRandom;
	    if (onlineOfflineType == 0) {
	        olineoffRandom = random.nextInt(5); // 0~4 사이의 랜덤
	    } else if (onlineOfflineType == 5) {
	        olineoffRandom = random.nextInt(5) + 5; // 5~9 사이의 랜덤
	    } else {
	        olineoffRandom = 0; // 기본값 (원하는 대로 설정)
	    }

	    int lectureCode = random.nextInt(99) + 1; // 01~99 랜덤

	    // 강의번호 조합
	    int lectureNum = Integer.parseInt(String.format("%02d%01d%01d%01d%01d%02d",
	        currentYear, quarter, department, major, olineoffRandom, lectureCode));
	    
	    
	    lctrAplyOflWeek.setLctr_num(lectureNum); // 생성한 번호를 DTO에 설정
	    lctrAplyOflWeek.setUnq_num2(stdUser.getUnq_num());
	    System.out.println("lectureNum >>>>>" +lectureNum);
	    System.out.println("lctrAplyOflWeek>>>>" + lctrAplyOflWeek);
	    
	    List<File> fileList = new ArrayList<>();
	    if (files != null) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String uuid = UUID.randomUUID().toString();
	                    String originalFileName = file.getOriginalFilename();
	                    int fileSize = (int) file.getSize();
	                    String uniqueFileName = uuid + "_" + originalFileName;
	                    Path filePath = Paths.get("C:/upload/" + uniqueFileName);
	                    Files.createDirectories(filePath.getParent());
	                    file.transferTo(filePath.toFile());

	                    // File 객체에 파일 정보 설정
	                    File fileRecord = new File();
	                    fileRecord.setFile_group(0); // 필요시 그룹 이름 설정
	                    fileRecord.setFile_no(0); // 파일 번호 설정
	                    fileRecord.setFile_unq(uuid);
	                    fileRecord.setDwnld_file_nm(originalFileName);
	                    fileRecord.setFile_size(fileSize);
	                    fileRecord.setFile_path_nm("C:/upload/" + uniqueFileName);
	               
	                    fileList.add(fileRecord);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    } else {
	        System.out.println("파일이 없습니다.");
	    }
	    
	    System.out.println("fileList >>> " + fileList);
	    List<File> insertFile = ms.imgInsertList(fileList);
	    
	 // Lctr_Week 리스트 생성
	    List<Lctr_Week> lctrWeeks = new ArrayList<>();
	    for (int i = 0; i < lctr_weeks.length; i++) {
	        Lctr_Week week = new Lctr_Week();
	        week.setLctr_weeks(lctr_weeks[i]);
	        week.setLctr_plan(lctr_plans[i]);
	        week.setLctr_num(lectureNum);
	        week.setLctrm_num(lctrAplyOflWeek.getLctrm_num());
	        week.setFile_group(insertFile.get(i).getFile_group());
	        lctrWeeks.add(week);
	    }

	    int insertList = ms.subminPageLctr(lctrAplyOflWeek, lctrWeeks);

	    return "jh/registeredClassProfessor";
	}
	
	@GetMapping(value = "/mn/mn_lctrInfo")
	public String lctrInfoPage(@RequestParam("lctr_num") String lctr_num,
							   Model model) {
		System.out.println("MnController lctrInfoPage start...");
		System.out.println("lctr_num >>>" + lctr_num);
		
		List<Mn_LctrInfoPage> lctrSelList = ms.selGetLctrList(lctr_num);
		List<Lctr_Week> weekList = ms.selWeekList(lctr_num); 
		
		System.out.println("controller lctrSelList >>" + lctrSelList);
		
		model.addAttribute("lctr",lctrSelList);
		model.addAttribute("week",weekList);
		
		return "mn/mn_lctrInfo";
	}
	
	@GetMapping(value = "/mn/mn_CourseregPage")
	public String CourseregPage(@RequestParam("lctr_num") Integer lctr_num, HttpSession session, Model model) {
	    System.out.println("MnController CourseregPage start..");

	    Object unq_numObj = session.getAttribute("unq_num");
	    String unq_num = unq_numObj != null ? unq_numObj.toString() : null;

	    User_Table user_table = (User_Table) session.getAttribute("user");
	    
	    int mbr_se = user_table.getMbr_se();

	    System.out.println("MnController CourseregPage unq_num >>>>" + unq_num);
	    System.out.println("MnController CourseregPage mbr_se >>>>" + mbr_se);
	    
	    model.addAttribute("lctr_num", lctr_num);

	    if (unq_num == null || unq_num.isEmpty()) {
	        return "jh/loginForm";
	    }

	    if (mbr_se == 1) {
	        return "mn/mn_Coursereg";
	    } else {
	        return "jh/loginForm";
	    }
	}

}