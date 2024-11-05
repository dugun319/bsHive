package com.postGre.bsHive.Acontroller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.HsService.HsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/hs")
public class HsController {

	private final HsService hss;
	
	// 내 강의실 메인 page
	@GetMapping(value = "/hsClassMain")
	public String LecMain () {
		System.out.println("HsController LecMain Start...");
		System.out.println("HsController LecMain End...");
		return "hs/hsClassMain";
	}
	
	// 내 강의실 강의계획서 page
	@GetMapping(value = "/lecSchedule")
	public String LecSchedule (Hs_Lec lec1, Model model) {
		System.out.println("HsController LecSchedule Start...");
		
		// 강의계획서 조회
		lec1.setLctr_num(24151513);
		Hs_Lec lec_TotSche = hss.lecTotSche(lec1.getLctr_num());
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
	public String LecProfAttend (Hs_Attend attend, Model model) {
		System.out.println("HsController LecProfAttend Start...");
		
		//강의 주차 option 불러오는 list
		attend.setLctr_num(24151513);
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
		
		
		//강의 주차 별 학생 출석입력 list
		attend.setLctr_num(24151513);
		attend.setLctr_weeks(attend.getLctr_weeks());
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
	        	attendL.setLctr_num(24151513);
	            
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
	public String LecAttendance () {
		System.out.println("HsController LecAttendance Start...");
		
		
		
		System.out.println("HsController LecAttendance End...");
		return "hs/lecAttendance";
	}
	
	// 강의실 과제입력 (교수) page
	@GetMapping(value = "/lecAssignWrite")
	public String LecProfAssign () {
		System.out.println("HsController LecProfAssign Start...");
		System.out.println("HsController LecProfAssign End...");
		return "hs/lecAssignWrite";
	}
	
	// 내 강의실 과제제출 (학생) page
	@GetMapping(value = "/lecAssignment")
	public String LecAssignment () {
		System.out.println("HsController LecAssignment Start...");
		System.out.println("HsController LecAssignment End...");
		return "hs/lecAssignment";
	}
	
	// 강의실 학생과제제출물 (교수) page
	@GetMapping(value = "/lecProfConfirmAssign")
	public String LecProfConfirmAssign () {
		System.out.println("HsController LecProfConfirmAssign Start...");
		System.out.println("HsController LecProfConfirmAssign End...");
		return "hs/lecProfConfirmAssign";
	}
	
	
	// 강의실 시험입력(교수) page
	@GetMapping(value = "/lecTestWrite")
	public String LecTestWrite () {
		System.out.println("HsController LecTest Start...");
		System.out.println("HsController LecTest End...");
		return "hs/lecTestWrite";
	}
	
	// 내 강의실 시험제출 (학생) page
	@GetMapping(value = "/lecTest")
	public String LecTest () {
		System.out.println("HsController LecTest Start...");
		System.out.println("HsController LecTest End...");
		return "hs/lecTest";
	}
	
	// 강의실 시험입력(교수) page
	@GetMapping(value = "/lecTestRead")
	public String LecTestRead () {
		System.out.println("HsController LecTest Start...");
		System.out.println("HsController LecTest End...");
		return "hs/lecTestRead";
	}
	
	

}