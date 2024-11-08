package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.HsService.HsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hs")
public class HsController {
	@Value("${spring.file.upload.path}")
    private String UPLOAD_DIRECTORY;
	private final HsService hss;
	
	// 내 강의실 메인 page
	@GetMapping(value = "/hsClassMain")
	public String LecMain (HttpServletRequest request, HttpSession session, Hs_Lec hl ,Model model) {
		System.out.println("HsController LecMain Start...");
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		System.out.println("HsController LecMain hl-> "+hl);
		
		// 강의번호/ 강의명 불러오기
		Lctr lctr = hss.callLctr_num(hl.getLctr_num());
		model.addAttribute("lctr", lctr);

		System.out.println("HsController LecMain End...");
		return "hs/hsClassMain";
	}
	
	// 내 강의실 강의계획서 page
	@GetMapping(value = "/lecSchedule")
	public String LecSchedule (HttpServletRequest request, Hs_Lec lec1, Model model) {
		System.out.println("HsController LecSchedule Start...");
		//User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
//        if (user == null) {
//            // 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/jh/loginForm"; // 예시
//        }
//		
		// 강의번호/ 강의명 불러오기
		Lctr lctr = hss.callLctr_num(lec1.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		System.out.println("HsController LecSchedule lec1-> "+lec1);
		// 강의계획서 조회
		List<Hs_Lec> lec_TotSche = hss.lecTotSche(lec1.getLctr_num());
		System.out.println("lec_TotSche->" +lec_TotSche);
		model.addAttribute("hsLec", lec_TotSche);
		
		// 주차별 강의 계획서 List
		List<Hs_Lec> lctrWeek = hss.lecWeekSchedule(lec1.getLctr_num());
		System.out.println("lctrWeek->" +lctrWeek);
		model.addAttribute("lctrWeek", lctrWeek);
		
		System.out.println("HsController LecSchedule End...");
		return "hs/lecSchedule";
	}
	
	// 강의실 교수 출석 입력 page
	@GetMapping(value = "/lecProfAttend")
	public String LecProfAttend (HttpServletRequest request, HttpSession session, Hs_Attend attend, Model model) {
		System.out.println("HsController LecProfAttend Start...");
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAttend attend-> "+attend);
		Lctr lctr = hss.callLctr_num(attend.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//강의 주차 option 불러오는 list
		List<Hs_Attend> weekList = hss.WeekList(attend.getLctr_num());
		
		//현재 날짜 가져오기
		LocalDate today = LocalDate.now();
		Optional<Hs_Attend> todayWeek = weekList.stream()
												.filter(w -> w.getLctr_ymd().equals(today.toString()))
												.findFirst();
		
		// 기본값: 오늘 해당하는 주차에 없으면 가장 가까운 과거 주차를 찾음
//		String defaultWeek = "01";	//기본값을 1주차로 설정
//		if (todayWeek.isPresent()) {
//			defaultWeek = todayWeek.get().getLctr_weeks();
//		} else {
//			//가장 가까운 과거 날짜 찾기
//			weekList.sort(Comparator.comparing(Hs_Attend::getLctr_ymd).reversed());
//			for (Hs_Attend week : weekList) {
//				if (LocalDate.parse(week.getLctr_ymd()).isBefore(today)) {
//					defaultWeek = week.getLctr_weeks();
//					break;
//				}
//			}
//		}
//		
//		model.addAttribute("defaultWeek", defaultWeek);
		model.addAttribute("weekList", weekList);
		
		System.out.println("HsController LecProfAttend End...");
		return "hs/lecProfAttend";
	}
	
	// 교수 출석체크 주차별 list ajax 불러오기
	@ResponseBody
	@PostMapping(value = "/lecWeekProf")
	public List<Hs_Attend> LecWeekProf (Hs_Attend attend, Model model) {
		System.out.println("HsController LecWeekProf Ajax Start...");
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAttend attend-> "+attend);
		Lctr lctr = hss.callLctr_num(attend.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//강의 주차 별 학생 출석입력 list
		List<Hs_Attend> lecWeekAttend = hss.lecWeekProf(attend);
		
		System.out.println("HsController LecWeekProf Ajax End...");
		return lecWeekAttend;
	}
	
	// 교수 출석체크 update Form
	@RequestMapping(value = "/AttendUpdate")
	public String lecProfAttUpd (Hs_Attend attend, Model model) {
		System.out.println("HsController lecProfAttUpd Start...");
		System.out.println("HsController lecProfAttUpd attend(list로 불러온 값)->"+attend);
		
		String attendanceData = attend.getAttendanceData();
		
		try {
	        // JSON 문자열을 List<Hs_Attend>로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Hs_Attend> attendList = objectMapper.readValue(attendanceData, new TypeReference<List<Hs_Attend>>() {});
	        
	        // 각 Hs_Attend 객체의 unq_num과 atndc_type을 출력
	        for (Hs_Attend attendL : attendList) {
	        	attendL.setLctr_weeks(attend.getLctr_weeks());
	        	attendL.setLctr_num(attend.getLctr_num());
	            
	            // 출결 상태가 null이 아닐 경우에만 로그 출력 및 DB 업데이트
	            if (attendL.getAtndc_type() != null) {
	                System.out.println("학번: " + attendL.getUnq_num() + ", 출결상태: " + attendL.getAtndc_type() + ", 주차: " + attendL.getLctr_weeks());
	                // 교수가 강의 출결 등록
	                int updateCount = hss.updateAttProf(attendL);
	            } else {
	                System.out.println("학번: " + attendL.getUnq_num() + "의 출결상태가 null입니다. 업데이트 생략.");
	            }
	        }
	    } catch (Exception e) {
	    	System.out.println("HsController lecProfAttUpd e.getMessage()-> "+e.getMessage());
	        e.printStackTrace();
	    }
		
		System.out.println("HsController lecProfAttUpd End...");
		return "forward:/hs/lecProfAttend";
	}
	
	
	// 내 강의실 출석확인 (학생) page
	@GetMapping(value = "/lecAttendance")
	public String LecAttendance (HttpServletRequest request, HttpSession session, Hs_Attend attend, Model model) {
		System.out.println("HsController LecAttendance Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		System.out.println("HsController LecAttendance user-> "+user);
		attend.setUnq_num(user.getUnq_num());
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecAttendance attend-> "+attend);
		Lctr lctr = hss.callLctr_num(attend.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		
		// 학생 주차별 출결상태 불러오기
		List<Hs_Attend> atndc_state = hss.lecStdAttend(attend);
		model.addAttribute("atndc_state", atndc_state);

		System.out.println("HsController LecAttendance End...");
		return "hs/lecAttendance";
	}
	
	// 강의실 과제목록 (교수) page
	@GetMapping(value = "/lecAssignList")
	public String LecProfAssign (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecProfAssign Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssign hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 목록에 대한 data 불러오기
		List<Hs_Assignment> assProfList = hss.assProfList(hsAss.getLctr_num());
		model.addAttribute("asmtList", assProfList);
		
		System.out.println("HsController LecProfAssign End...");
		return "hs/lecAssignList";
	}
	
	// 강의실 과제입력 (교수) page
	@GetMapping(value = "/lecAssignWrite")
	public String LecProfAssignWrite (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecProfAssignWrite Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignWrite hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignInfoProf(hsAss);
		
		model.addAttribute("lctr", lctr);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		System.out.println("HsController LecProfAssignWrite End...");
		return "hs/lecAssignWrite";
	}
	
	// 내 강의실 과제입력 (교수) form (insert)
	@PostMapping("profAsmtWrite")
    public String profAsmtWrite(@RequestParam("file") MultipartFile[] files, Hs_Assignment assign , Model model) {
		StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 고유 파일 이름 생성 (UUID 사용)
                    String file_unq = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path file_path_nm = Paths.get(UPLOAD_DIRECTORY + file_unq);
                    System.out.println("file_unq -> "+ file_unq);
                    System.out.println("file_path_nm -> "+ file_path_nm);
                    // 파일 저장
                    Files.createDirectories(file_path_nm.getParent());  // 디렉토리 생성
                    file.transferTo(file_path_nm.toFile());

                    fileGroupPaths.append("C:/upload/").append(file_unq).append(",");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 모든 파일 경로 설정 후 assign 객체에 저장
        if (fileGroupPaths.length() > 0) {
            fileGroupPaths.setLength(fileGroupPaths.length() - 1); // 마지막 콤마 제거
        }
        assign.setFile_groupc(fileGroupPaths.toString()); // assign 객체에 file_group 경로 설정

        // 데이터베이스에 pst 객체 저장
        int insertResult = hss.ProfAsmtInsert(assign); 
        if (insertResult <= 0) {
            model.addAttribute("message", "데이터베이스 저장 중 오류가 발생했습니다.");
            return "error"; // 오류 페이지로 이동
        }

        return "hs/lecAssignList"; // 업로드 후 목록 페이지로 이동
	}

	
	// 강의실 과제상세 (교수) page
	@GetMapping(value = "/lecAssignDetail")
	public String LecProfAssignDetail (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecProfAssignDetail Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignDetail hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignInfoProf(hsAss);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		
		System.out.println("HsController LecProfAssignDetail End...");
		return "hs/lecAssignDetail";
	}
	
	// 강의실 과제수정 (교수) page
	@GetMapping(value = "/lecAssignUpdate")
	public String LecProfAssignUpdate (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecProfAssignUpdate Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignUpdate hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignInfoProf(hsAss);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		System.out.println("HsController LecProfAssignUpdate End...");
		return "hs/lecAssignUpdate";
	}
	
	// 내 강의실 과제 수정 form (교수)
	@PostMapping(value = "profAsmtUpdate")
	public String profAsmtUpdate (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController profAsmtUpdate Start...");
		
		int updateCount = hss.asmtUpdate(hsAss);
		
		int lctr_num = hsAss.getLctr_num();
		String cycl = hsAss.getCycl();
		System.out.println("HsController LecProfAssignUpdate End...");
		return "hs/lecAssignDetail?lctr_num="+lctr_num+"&cycl="+cycl;
	}
	
	// 내 강의실 과제리스트 (학생) page
	@GetMapping(value = "/lecAssignmentList")
	public String LecAssignmentList (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecAssignmentList Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecAssignmentList hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 목록에 대한 data 불러오기
		List<Hs_Assignment> assProfList = hss.assProfList(hsAss.getLctr_num());
		model.addAttribute("asmtList", assProfList);
		
		//학생본인의 과제 제출여부 확인해보기
		hsAss.setUnq_num2(user.getUnq_num());
		boolean dataPresent = hss.checkData(hsAss);
		model.addAttribute("dataPresent", dataPresent);
		
		System.out.println("HsController LecAssignmentList End...");
		return "hs/lecAssignmentList";
	}
	
	// 내 강의실 과제제출 (학생) page
	@GetMapping(value = "/lecAssignment")
	public String LecAssignment (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecAssignment Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecAssignment hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//과제제출 시 필요한 기본정보 info 불러오기
		hsAss.setUnq_num(user.getUnq_num());
		Hs_Assignment hsAssignWrite = hss.stdAssignment(hsAss);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		System.out.println("HsController LecAssignment End...");
		return "hs/lecAssignment";
	}
	
	
	// 내 강의실 과제제출 (학생) form (insert)
	@PostMapping("/AssignInsert")
    public String gongInsert(@RequestParam("file") MultipartFile[] files, Hs_Assignment hsAss , Model model) {
        StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 고유 파일 이름 생성 (UUID 사용)
                    String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueFileName);
                    System.out.println("uniqueFileName -> "+ uniqueFileName);
                    System.out.println("filePath -> "+ filePath);
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
        hsAss.setFile_groupc(fileGroupPaths.toString()); // hsAss 객체에 file_group 경로 설정

        // 데이터베이스에 hsAss 객체 저장
        int insertResult = hss.ProfAsmtInsert(hsAss); 
        if (insertResult <= 0) {
            model.addAttribute("message", "데이터베이스 저장 중 오류가 발생했습니다.");
            return "error"; // 오류 페이지로 이동
        }

        return "hs/lecSchedule"; // 업로드 후 목록 페이지로 이동
    }
	
	// 강의실 학생과제제출물 (교수) page
	@GetMapping(value = "/lecProfConfirmAssign")
	public String LecProfConfirmAssign (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController LecProfConfirmAssign Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfConfirmAssign hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
				
		System.out.println("HsController LecProfConfirmAssign End...");
		return "hs/lecProfConfirmAssign";
	}
	
	
	// 강의실 시험입력(교수) page
	@GetMapping(value = "/lecTestWrite")
	public String LecTestWrite (HttpServletRequest request, HttpSession session) {
		System.out.println("HsController LecTest Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		System.out.println("HsController LecTest End...");
		return "hs/lecTestWrite";
	}
	
	// 내 강의실 시험제출 (학생) page
	@GetMapping(value = "/lecTest")
	public String LecTest (HttpServletRequest request, HttpSession session) {
		System.out.println("HsController LecTest Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		System.out.println("HsController LecTest End...");
		return "hs/lecTest";
	}
	
	// 강의실 시험입력(교수) page
	@GetMapping(value = "/lecTestRead")
	public String LecTestRead (HttpServletRequest request, HttpSession session) {
		System.out.println("HsController LecTest Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		System.out.println("HsController LecTest End...");
		return "hs/lecTestRead";
	}
	
	//첨부파일 download
	 @GetMapping("/download")
     public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) {
          System.out.println(filePath);
         try {
             Path fullPath = Paths.get(filePath).toAbsolutePath();
             Resource resource = new UrlResource(fullPath.toUri());
             System.out.println(fullPath);
             System.out.println(resource);

             if (resource.exists() && resource.isReadable()) {
                 String fileName = fullPath.getFileName().toString();
                 return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                         .body(resource);
             } else {
                 return ResponseEntity.notFound().build();
             }
         } catch (IOException e) {
             e.printStackTrace();
             return ResponseEntity.badRequest().build();
         }
     }
	

}