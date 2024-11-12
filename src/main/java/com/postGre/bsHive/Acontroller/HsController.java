package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Adto.ResponseMessage;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Asmt;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Lgn;
import com.postGre.bsHive.HsService.HsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import oracle.net.aso.c;

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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		
		System.out.println("HsController LecMain hl-> "+hl);
		
		// 강의번호/ 강의명 불러오기
		Lctr lctr = hss.callLctr_num(hl.getLctr_num());
		model.addAttribute("lctr", lctr);

		System.out.println("HsController LecMain End...");
		return "hs/hsClassMain";
	}
	
	// 내 강의실 강의계획서 page
	@GetMapping(value = "/lecSchedule")
	public String LecSchedule (HttpServletRequest request,HttpSession session, Hs_Lec lec1, Model model) {
		System.out.println("HsController LecSchedule Start...");
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
//        if (user == null) {
//            // 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/jh/loginForm"; // 예시
//        }
//		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
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
		
		// 주차별 강의계획서에서 file_group을 확인하고 파일 경로 불러오기
	    List<File> allFilePaths = new ArrayList<>(); // 모든 파일 경로를 담을 리스트

	    // lctrWeek의 각 항목을 순회하면서 file_group 확인 후 파일 경로 불러오기
	    for (Hs_Lec weekLec : lctrWeek) {
	    	int fileGroup = weekLec.getFile_group();  // 주차별 강의계획서의 file_group을 가져옴

	    	if (fileGroup != 0) {
	    		// file_group이 있으면 해당 file_group에 해당하는 파일 경로를 불러옴
	        	List<File> filePath = hss.filePath(fileGroup);
	        	if (filePath != null && !filePath.isEmpty()) {
	            	allFilePaths.addAll(filePath); // 파일 경로들을 모두 리스트에 추가
	        	}
	    	}
	    }

	    // 모든 파일 경로를 모델에 추가
	    model.addAttribute("filePaths", allFilePaths);
		
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		
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
	
	// 교수 출석체크 update Form (radio 한 개씩)
	@ResponseBody
	@RequestMapping(value = "/AttendUpdate")
	public  ResponseEntity<?> lecProfAtt1Upd (@RequestBody Hs_Attend attend, Model model) {
		System.out.println("HsController lecProfAtt1Upd Start...");
		System.out.println("HsController lecProfAtt1Upd attend(list로 불러온 값)->"+attend);
		
		try {
            // attend update ajax
			int updateCount = hss.updateAttProf(attend);
            
            // 삭제 성공 시
            if (updateCount > 0) {
                ResponseMessage responseMessage = new ResponseMessage(true, "출석 상태변경 완료되었습니다.");
                System.out.println("HsController lecProfAtt1Upd ajax END...");
                return ResponseEntity.ok(responseMessage);  // 성공 메시지와 함께 반환
            } else {
                // 삭제 실패 시
                ResponseMessage responseMessage = new ResponseMessage(false, "출석 상태변경 실패했습니다.");
                return ResponseEntity.status(400).body(responseMessage);  // 실패 메시지와 함께 반환
            }
        } catch (Exception e) {
            // 예외 발생 시
            ResponseMessage responseMessage = new ResponseMessage(false, "서버 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(responseMessage);  // 서버 오류 메시지와 함께 반환
        }
	}
	
	
	// 교수 출석체크 update Form
	@ResponseBody
	@RequestMapping(value = "/AttendUpdate1")
	public ResponseEntity<?> lecProfAttUpd (@RequestBody List<Hs_Attend> attend, Model model) {
		System.out.println("HsController lecProfAttUpd Start...");
		System.out.println("HsController lecProfAttUpd attend(list로 불러온 값)->"+attend);
		
//		List<Hs_Attend> attendanceData = attend.getAttendanceData();	// "attendanceData"는 JSON 문자열로 배열을 받음
//		System.out.println(attendanceData);
		
		boolean allUpdatesSuccessful = true;  // 모든 출석 업데이트가 성공적인지 확인하는 플래그
		
		try {
	        
	        // 각 Hs_Attend 객체의 unq_num과 atndc_type을 출력
	        for (Hs_Attend attendL : attend) {
	            // 출결 상태가 null이 아닐 경우에만 로그 출력 및 DB 업데이트
	            System.out.println("학번: " + attendL.getUnq_num() + ", 출결상태: " + attendL.getAtndc_type() + ", 주차: " + attendL.getLctr_weeks());
	            // 교수가 강의 출결 등록
	            int updateCount = hss.updateAttProf(attendL);
	            
	            if (updateCount > 0) {
                    System.out.println("출석 상태 변경 완료: 학번 " + attendL.getUnq_num());
                } else {
                    System.out.println("출석 상태 변경 실패: 학번 " + attendL.getUnq_num());
                }
	        }
	        
	        // 모든 출석 업데이트가 성공했으면 성공 메시지 반환
	        if (allUpdatesSuccessful) {
	            ResponseMessage responseMessage = new ResponseMessage(true, "출석 상태변경 완료되었습니다.");
	            return ResponseEntity.ok(responseMessage);
	        } else {
	            // 하나라도 실패하면 실패 메시지 반환
	            ResponseMessage responseMessage = new ResponseMessage(false, "일부 출석 상태변경에 실패했습니다.");
	            return ResponseEntity.status(400).body(responseMessage);  // 실패 메시지와 함께 반환
	        }
	        
	    } catch (Exception e) {
	    	System.out.println("HsController lecProfAttUpd e.getMessage()-> "+e.getMessage());
	        e.printStackTrace();
	        
	        // 예외 처리 및 실패 메시지 반환
	        ResponseMessage responseMessage = new ResponseMessage(false, "서버 오류가 발생했습니다.");
	        return ResponseEntity.status(500).body(responseMessage);
	    }
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssign hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 목록에 대한 data 불러오기
		hsAss.setUnq_num(user.getUnq_num());
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignWrite hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignWriteProf(hsAss);
		
		model.addAttribute("lctr", lctr);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		System.out.println("HsController LecProfAssignWrite End...");
		return "hs/lecAssignWrite";
	}
	
	// 내 강의실 과제입력 (교수) form (insert)
	@PostMapping("profAsmtWrite")
    public String profAsmtWrite(Hs_Assignment assign , Model model) {
		System.out.println("HsController profAsmtWrite Start...");
		
		List<MultipartFile> files = assign.getFile();
		List<File> fileList = new ArrayList<>();
		if (files!=null) {
			for (MultipartFile file:files) {
				if(!file.isEmpty()) {
					try {
						// UUID 생성
						String uuid = UUID.randomUUID().toString();
                        String originalFileName = file.getOriginalFilename();
                        int fileSize = (int) file.getSize();
                        // UUID와 원본 파일명을 합쳐서 새로운 파일명 생성
                        String uniqueFileName = uuid + "_" + originalFileName;
                        // 업로드 디렉토리 경로
                        Path uploadPath  = Paths.get("src/main/resources/static/", UPLOAD_DIRECTORY).toAbsolutePath().normalize();
                        // 파일 경로 (저장할 실제 경로)
                        Path filePath = uploadPath.resolve(uniqueFileName);	// UUID + 파일명 결합된 경로
                        Files.createDirectories(filePath.getParent());	// 디렉토리 생성
                        file.transferTo(filePath.toFile());	// 파일 업로드
                        System.out.println("파일 경로: " + filePath.toString());
                        
                        // File 객체에 파일 정보 설정
                        File fileupload = new File();
                        fileupload.setFile_no(0); // 파일 번호 설정
                        fileupload.setFile_unq(uuid);
                        fileupload.setDwnld_file_nm(originalFileName);
                        fileupload.setFile_size(fileSize);
                        fileupload.setFile_path_nm("/uploads/" + uniqueFileName);
                        
                        fileList.add(fileupload);
                        System.out.println("HsController profAsmtWrite fileList-> "+fileList);
                        
					} catch (Exception e) {
						System.out.println("HsController profAsmtWrite e.getMessage()-> "+e.getMessage());
	                    e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("파일이 없습니다.");
		}
		int insertResult = hss.ProfAsmtInsert(assign, fileList);
		System.out.println("HsController profAsmtWrite End...");
		
		return "redirect:/hs/lecAssignList?lctr_num="+assign.getLctr_num(); // 업로드 후 목록 페이지로 이동
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignDetail hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignInfoProf(hsAss);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		//강의번호, 차수로 파일그룹 불러오기
		int fileGroup = hss.CallFileGroup(hsAss);
		hsAss.setFile_group(fileGroup);
		
		//파일그룹으로 파일 TBL 정보 불러오기
		System.out.println("HsController LecProfAssignDetail fileGroup 첨부 뒤-> "+hsAss);
		List<File> filePath = hss.filePath(hsAss.getFile_group());
        model.addAttribute("filePath", filePath);
		
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignUpdate hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 과제 입력 시 기본 정보 불러오기 (교수)
		hsAss.setUnq_num2(user.getUnq_num());
		System.out.println("unq_num2 ->" + hsAss.getUnq_num2());
		Hs_Assignment hsAssignWrite = hss.AssignInfoProf(hsAss);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		int fileGroup = hsAssignWrite.getFile_group();
		System.out.println("UpdateForm fileGroup-> "+fileGroup);
		
		//파일그룹에 해당하는 file목록 불러오기
		List<File> fileList = hss.filePath(fileGroup);
		System.out.println("UpdateForm fileList-> "+fileList);
		model.addAttribute("fileList", fileList);
		
		System.out.println("HsController LecProfAssignUpdate End...");
		return "hs/lecAssignUpdate";
	}
	
	// 교수 과제 수정 시 파일 삭제 ajax fetch
	@ResponseBody
	@PostMapping(value = "/deleteUpdFile")
	public ResponseEntity<?> deleteFile(@RequestBody Hs_Assignment assign) {
		System.out.println("HsController deleteFile ajax Start...");
		System.out.println("HsController deleteFile ajax assign-> "+assign);
		try {
            // update form에서 파일 삭제 ajax
            int isDeleted = hss.deleteFile(assign);
            
            // 삭제 성공 시
            if (isDeleted > 0) {
                ResponseMessage responseMessage = new ResponseMessage(true, "파일이 삭제되었습니다.");
                System.out.println("HsController deleteFile ajax END...");
                return ResponseEntity.ok(responseMessage);  // 성공 메시지와 함께 반환
            } else {
                // 삭제 실패 시
                ResponseMessage responseMessage = new ResponseMessage(false, "파일 삭제에 실패했습니다.");
                return ResponseEntity.status(400).body(responseMessage);  // 실패 메시지와 함께 반환
            }
        } catch (Exception e) {
            // 예외 발생 시
            ResponseMessage responseMessage = new ResponseMessage(false, "서버 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(responseMessage);  // 서버 오류 메시지와 함께 반환
        }
	}
	
	// 내 강의실 과제 수정 form (교수)
	@PostMapping(value = "profAsmtUpdate")
	public String profAsmtUpdate (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController profAsmtUpdate Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfAssignUpdate hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		List<MultipartFile> files = hsAss.getFile();
		List<File> fileList = new ArrayList<>();
		if (files!=null) {
			for (MultipartFile file:files) {
				if(!file.isEmpty()) {
					try {
						// UUID 생성
						String uuid = UUID.randomUUID().toString();
                        String originalFileName = file.getOriginalFilename();
                        int fileSize = (int) file.getSize();
                        // UUID와 원본 파일명을 합쳐서 새로운 파일명 생성
                        String uniqueFileName = uuid + "_" + originalFileName;
                        // 업로드 디렉토리 경로
                        Path uploadPath  = Paths.get("src/main/resources/static/", UPLOAD_DIRECTORY).toAbsolutePath().normalize();
                        // 파일 경로 (저장할 실제 경로)
                        Path filePath = uploadPath.resolve(uniqueFileName);	// UUID + 파일명 결합된 경로
                        Files.createDirectories(filePath.getParent());	// 디렉토리 생성
                        file.transferTo(filePath.toFile());	// 파일 업로드
                        System.out.println("파일 경로: " + filePath.toString());
                        
                        // File 객체에 파일 정보 설정
                        File fileupload = new File();
                        fileupload.setFile_no(0); // 파일 번호 설정
                        fileupload.setFile_unq(uuid);
                        fileupload.setDwnld_file_nm(originalFileName);
                        fileupload.setFile_size(fileSize);
                        fileupload.setFile_path_nm("/uploads/" + uniqueFileName);
                        
                        fileList.add(fileupload);
                        System.out.println("HsController profAsmtWrite fileList-> "+fileList);
                        
					} catch (Exception e) {
						System.out.println("HsController profAsmtWrite e.getMessage()-> "+e.getMessage());
	                    e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		int updateCount = hss.asmtUpdate(hsAss, fileList);
		
		int lctr_num = hsAss.getLctr_num();
		String cycl = hsAss.getCycl();
		System.out.println("HsController LecProfAssignUpdate End...");
		return "redirect:/hs/lecAssignDetail?lctr_num="+lctr_num+"&cycl="+cycl;
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecAssignmentList hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 목록에 대한 data 불러오기
		List<Hs_Assignment> assProfList = hss.assProfList(hsAss.getLctr_num());
		model.addAttribute("asmtList", assProfList);
		
		for (Hs_Assignment asmt : assProfList) {
			//학생본인의 과제 제출여부 확인해보기
			hsAss.setUnq_num(user.getUnq_num());
			hsAss.setCycl(asmt.getCycl());  // 해당 과제의 cycl을 설정
			System.out.println("HsController LecAssignmentList hsAss-> "+hsAss);
			boolean dataPresent = hss.checkData(hsAss);
			asmt.setDataPresent(dataPresent);
			asmt.setUnq_num(user.getUnq_num());
		}
		
		model.addAttribute("asmtList", assProfList);
		
		// 오늘 날짜 가져오기
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String today = year + "-" + month + "-" +day;
		request.setAttribute("today", today);
		
		//학생 unq_num 전달
		
		
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecAssignment hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//과제제출 시 필요한 기본정보 info 불러오기
		hsAss.setUnq_num(user.getUnq_num());
		Hs_Assignment hsAssignWrite = hss.stdAssignment(hsAss);
		System.out.println("HsController LecAssignment hsAssignWrite-> "+hsAssignWrite);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		//강의번호, 차수, 학번으로 파일그룹 불러오기 (학생)
		int fileGroup = hss.CallFileGroup(hsAss);
		hsAss.setFile_group(fileGroup);
				
		//파일그룹으로 파일 TBL 정보 불러오기
		System.out.println("HsController LecProfAssignDetail fileGroup 첨부 뒤-> "+hsAss);
		List<File> filePath = hss.filePath(fileGroup);
		model.addAttribute("filePath", filePath);
		
		System.out.println("HsController LecAssignment End...");
		return "hs/lecAssignment";
	}
	
	
	// 내 강의실 과제제출 (학생) form (insert)
	@PostMapping("/AssignInsert")
    public String stdntAssign(Hs_Assignment hsAss , Model model) {
		System.out.println("HsController stdntAssign Start...");
		List<MultipartFile> files = hsAss.getFile();
		List<File> fileList = new ArrayList<>();
		if (files!=null) {
			for (MultipartFile file:files) {
				if(!file.isEmpty()) {
					try {
						// UUID 생성
						String uuid = UUID.randomUUID().toString();
                        String originalFileName = file.getOriginalFilename();
                        int fileSize = (int) file.getSize();
                        // UUID와 원본 파일명을 합쳐서 새로운 파일명 생성
                        String uniqueFileName = uuid + "_" + originalFileName;
                        // 업로드 디렉토리 경로
                        Path uploadPath  = Paths.get("src/main/resources/static/", UPLOAD_DIRECTORY).toAbsolutePath().normalize();
                        // 파일 경로 (저장할 실제 경로)
                        Path filePath = uploadPath.resolve(uniqueFileName);	// UUID + 파일명 결합된 경로
                        Files.createDirectories(filePath.getParent());	// 디렉토리 생성
                        file.transferTo(filePath.toFile());	// 파일 업로드
                        System.out.println("파일 경로: " + filePath.toString());
                        
                        // File 객체에 파일 정보 설정
                        File fileupload = new File();
                        fileupload.setFile_no(0); // 파일 번호 설정
                        fileupload.setFile_unq(uuid);
                        fileupload.setDwnld_file_nm(originalFileName);
                        fileupload.setFile_size(fileSize);
                        fileupload.setFile_path_nm("/uploads/" + uniqueFileName);
                        
                        fileList.add(fileupload);
                        System.out.println("HsController stdntAssign fileList-> "+fileList);
                        
					} catch (Exception e) {
						System.out.println("HsController stdntAssign e.getMessage()-> "+e.getMessage());
	                    e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		int insertResult = hss.StdntAsmtInsert(hsAss, fileList);
		System.out.println("HsController stdntAssign End...");
		
		return "redirect:/hs/lecAssignmentList?lctr_num="+hsAss.getLctr_num(); // 업로드 후 목록 페이지로 이동
    }
	
	
	
	// 내 강의실 과제제출 수정 (학생) page
	@GetMapping(value = "/lecAssignmentUpdate")
	public String lecAssignmentUpdate (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController lecAssignmentUpdate Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController lecAssignmentUpdate hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//과제제출 시 필요한 기본정보 info 불러오기
		hsAss.setUnq_num(user.getUnq_num());
		Hs_Assignment hsAssignWrite = hss.stdAssignment(hsAss);
		System.out.println("HsController lecAssignmentUpdate hsAssignWrite-> "+hsAssignWrite);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
		
		//강의번호, 차수로 파일그룹 불러오기
		int fileGroup = hss.CallFileGroup(hsAss);
		hsAss.setFile_group(fileGroup);
				
		//파일그룹으로 파일 TBL 정보 불러오기
		System.out.println("HsController lecAssignmentUpdate fileGroup 첨부 뒤-> "+hsAss);
		List<File> filePath = hss.filePath(hsAss.getFile_group());
		model.addAttribute("filePath", filePath);
		
		
		
		// 강의번호, 차수, 학번으로 과제제출 tbl 불러오기
		Hs_Assignment hsAssign_stdUpd = hss.stdAssignmentUpd(hsAss);
		System.out.println("HsController lecAssignmentUpdate hsAssign_stdUpd-> "+hsAssign_stdUpd);
		model.addAttribute("hsAssigStdUpd", hsAssign_stdUpd);
		
		//강의번호, 차수, 학번으로 파일그룹 불러오기
		int fileGroup1 = hss.CallFileGroupStd(hsAss);
		hsAss.setFile_group2(fileGroup1);
		hsAssign_stdUpd.setFile_group2(fileGroup1);
		System.out.println("HsController lecAssignmentUpdate hsAssign_stdUpd.setFile_group2(fileGroup1)-> "+hsAssign_stdUpd.getFile_group2());
						
		//파일그룹으로 파일 TBL 정보 불러오기
		System.out.println("HsController lecAssignmentUpdate fileGroup2 첨부 뒤-> "+hsAss);
		List<File> filePath1 = hss.filePath(fileGroup1);
		System.out.println("HsController lecAssignmentUpdate filePath1-> "+filePath1);
		model.addAttribute("filePath1", filePath1);
		
		System.out.println("HsController lecAssignmentUpdate End...");
		return "hs/lecAssignmentUpdate";
	}
	
	// 학생 과제제출 수정 form
	@PostMapping(value = "AssignStdUpd")
	public String stdntAsmtUpdate (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController stdntAsmtUpdate Start...");
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController stdntAsmtUpdate hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		List<MultipartFile> files = hsAss.getFile();
		List<File> fileList = new ArrayList<>();
		if (files!=null) {
			for (MultipartFile file:files) {
				if(!file.isEmpty()) {
					try {
						// UUID 생성
						String uuid = UUID.randomUUID().toString();
                        String originalFileName = file.getOriginalFilename();
                        int fileSize = (int) file.getSize();
                        // UUID와 원본 파일명을 합쳐서 새로운 파일명 생성
                        String uniqueFileName = uuid + "_" + originalFileName;
                        // 업로드 디렉토리 경로
                        Path uploadPath  = Paths.get("src/main/resources/static/", UPLOAD_DIRECTORY).toAbsolutePath().normalize();
                        // 파일 경로 (저장할 실제 경로)
                        Path filePath = uploadPath.resolve(uniqueFileName);	// UUID + 파일명 결합된 경로
                        Files.createDirectories(filePath.getParent());	// 디렉토리 생성
                        file.transferTo(filePath.toFile());	// 파일 업로드
                        System.out.println("파일 경로: " + filePath.toString());
                        
                        // File 객체에 파일 정보 설정
                        File fileupload = new File();
                        fileupload.setFile_no(0); // 파일 번호 설정
                        fileupload.setFile_unq(uuid);
                        fileupload.setDwnld_file_nm(originalFileName);
                        fileupload.setFile_size(fileSize);
                        fileupload.setFile_path_nm("/uploads/" + uniqueFileName);
                        
                        fileList.add(fileupload);
                        System.out.println("HsController stdntAsmtUpdate fileList-> "+fileList);
                        
					} catch (Exception e) {
						System.out.println("HsController stdntAsmtUpdate e.getMessage()-> "+e.getMessage());
	                    e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		int updateCount = hss.asmtSbmsnUpdate(hsAss, fileList);
		
		int lctr_num = hsAss.getLctr_num();
		System.out.println("HsController stdntAsmtUpdate End...");
		return "redirect:/hs/lecAssignmentList?lctr_num="+lctr_num;
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
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController LecProfConfirmAssign hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		// 학생과제제출물 list 불러오기
		//1. 해당 강의의 과제 차수 list 불러오기
		List<Hs_Assignment> lecAssignCycl = hss.assCycl(hsAss.getLctr_num());

		// 2. 학생 목록을 저장할 리스트
		List<Hs_Assignment> allAssignSubmitList = new ArrayList<>();

		// 3. 강의 차수마다 학생 목록을 조회
		for (Hs_Assignment assignment : lecAssignCycl) {
		    // 각 강의 차수에 대해 학생 목록을 조회 (WHERE 조건: lctr_num, cycl)
		    hsAss.setCycl(assignment.getCycl()); // 강의 차수 설정
		    List<Hs_Assignment> assignSubmitList = hss.assignSubmitList(hsAss); // 학생 목록 조회
		    
		    // 제출 학생 수 조회 count
		    int submitCount = hss.getSubmitCount(hsAss);
		    
		    Hs_Assignment assignSubList = new Hs_Assignment();
		    assignSubList.setCycl(assignment.getCycl());
		    assignSubList.setAsmtSubCount(submitCount);
		    assignSubList.setStudentList(assignSubmitList);
		    
		    allAssignSubmitList.add(assignSubList);
		}
		// 4. 조회한 모든 학생 목록을 모델에 추가
		model.addAttribute("AssignAbmsn", allAssignSubmitList); // 서버로 전달할 전체 학생 목록
		
		System.out.println("HsController LecProfConfirmAssign End...");
		return "hs/lecProfConfirmAssign";
	}
	
	// 교수 학생 과제 상세보기 page
	@GetMapping(value = "/lecProfConfirmAssDetail")
	public String stdAsmtDetail (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController stdAsmtDetail Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController stdAsmtDetail hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		//과제제출 시 필요한 기본정보 info 불러오기
		Hs_Assignment hsAssignWrite = hss.stdAssignment(hsAss);
		System.out.println("HsController stdAsmtDetail hsAssignWrite-> "+hsAssignWrite);
		model.addAttribute("hsAssignWrite", hsAssignWrite);
			
		// 강의번호, 차수, 학번으로 과제제출 tbl 불러오기
		Hs_Assignment hsAssign_stdUpd = hss.stdAssignmentUpd(hsAss);
		System.out.println("HsController stdAsmtDetail hsAssign_stdUpd-> "+hsAssign_stdUpd);
		model.addAttribute("hsAssigStdUpd", hsAssign_stdUpd);
		
		//강의번호, 차수, 학번으로 파일그룹 불러오기
		int fileGroup1 = hss.CallFileGroupStd(hsAss);
		hsAss.setFile_group2(fileGroup1);
		hsAssign_stdUpd.setFile_group2(fileGroup1);
		System.out.println("HsController stdAsmtDetail hsAssign_stdUpd.setFile_group2(fileGroup1)-> "+hsAssign_stdUpd.getFile_group2());
						
		//파일그룹으로 파일 TBL 정보 불러오기
		System.out.println("HsController stdAsmtDetail fileGroup2 첨부 뒤-> "+hsAss);
		List<File> filePath1 = hss.filePath(fileGroup1);
		System.out.println("HsController stdAsmtDetail filePath1-> "+filePath1);
		model.addAttribute("filePath1", filePath1);
		
		System.out.println("HsController stdAsmtDetail End...");
		return "hs/lecProfConfirmAssDetail";
	}
	
	//과제 점수부여 update form (교수 -> 학생)
	@PostMapping(value = "scoreAsmt")
	public String AsmtScoreProf (HttpServletRequest request, HttpSession session, Hs_Assignment hsAss, Model model) {
		System.out.println("HsController AsmtScoreProf Start...");
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		if (user == null) {
			// 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
			return "redirect:/jh/loginForm";
	    }
		
		// 로그인 된 사번 들고다니기
		Lgn lgn = new Lgn();
		lgn.setUnq_num(user.getUnq_num());
		model.addAttribute("lgn", lgn);
		
		// 강의번호/ 강의명 불러오기
		System.out.println("HsController AsmtScoreProf hsAss-> "+hsAss);
		Lctr lctr = hss.callLctr_num(hsAss.getLctr_num());
		model.addAttribute("lctr", lctr);
		
		hss.asmtScoreUpd(hsAss);
		
		System.out.println("HsController AsmtScoreProf End...");
		return "redirect:/hs/lecProfConfirmAssign?lctr_num="+hsAss.getLctr_num();
	}

	//첨부파일 다운로드 로직
	@GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) {
		System.out.println("HsController downloadFile Start...");
        try {
        	System.out.println("HsController downloadFile filePath-> "+filePath);
        	
        	// 'filePath'가 '/uploads/'로 시작하는 경우, 이를 무시하고 'static/uploads/' 경로에 포함
        	if (filePath.startsWith("/uploads/")) {
                filePath = filePath.substring("/uploads/".length());  // /uploads/를 제거
            }
        	
        	// 앞에 static 경로를 붙여서 실제 파일 경로를 지정
            Path fullPath = Paths.get("src/main/resources/static/uploads/").normalize().resolve(filePath);
            System.out.println("fullPath-> "+fullPath);
            // 실제 파일을 Resource로 변환
            Resource resource = new UrlResource(fullPath.toUri());
            
            
            
            // 파일이 존재하고 읽을 수 있으면 다운로드 처리
            if (resource.exists() && resource.isReadable()) {
            	String originalFileName = filePath.substring(filePath.indexOf('_') + 1);  // UUID 이후의 파일명 추출
                String fileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
                System.out.println("HsController downloadFile End...");
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
	 
	 
	

}