package com.postGre.bsHive.Acontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_Lctrm;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_ScholarshipList;
import com.postGre.bsHive.Adto.Kh_SortCode;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Adto.Kh_pstList;
import com.postGre.bsHive.Amodel.Lgn;
import com.postGre.bsHive.HsService.HsService;
import com.postGre.bsHive.KhService.KhTableSerive;
import com.postGre.bsHive.SeService.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/kh/admin")
public class KhController {
	@Value("${spring.file.upload.path}")
	private String uploadPath;
	
	private final KhTableSerive khTableSerive;
	private final HsService hss; 

	//
	// adminMain
	//

	@GetMapping(value = "/adminMain")
	public String adminMain(HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("unq_num") == null) {
			session.invalidate(); // 기존 세션을 무효화
			session = request.getSession(true); // 새로운 세션 생성
			session.setMaxInactiveInterval(30 * 60); // 30분 동안 활동이 없으면 세션 만료 설정
			session.setAttribute("sessionTime", session.getMaxInactiveInterval());
		} else {
			session.setMaxInactiveInterval(30 * 60); // 30분 동안 활동이 없으면 세션 만료 설정
			session.setAttribute("sessionTime", session.getMaxInactiveInterval());
		}
		return "kh/adminMain";
	}

	//
	// sessionTimer
	//

	@ResponseBody
	@PostMapping(value = "/sessionExtension")
	public void sessionExtension(HttpSession session) {
		System.out.println("sessionExtension(HttpSession session) is called");

		session.setAttribute("extensionTime", System.currentTimeMillis());
	}

	@ResponseBody
	@GetMapping("/getSessionRemain")
	public Integer getSessionRemain(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long remianTime = session.getAttribute("extensionTime") == null ? session.getCreationTime()
				: (long) session.getAttribute("extensionTime");
		// long jakarta.servlet.http.HttpSession.getCreationTime()
		// Returns the time when this session was created, measured in milliseconds
		// since midnight January 1, 1970 GMT.

		return (int) Math.max(0, session.getMaxInactiveInterval() - (System.currentTimeMillis() - remianTime) / 1000);
	}

	@GetMapping("/goLogOut")
	public String goLogOut(HttpSession session) {

		session.invalidate();

		return "main";
	}

	//
	// student
	//
	@RequestMapping(value = "/stdntList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getStdntList(Kh_StdntList stList, Model model) {
		log.info("KhController getStdntList() is called");

		String rawKeyword = stList.getKeyword();
		if (rawKeyword != null && rawKeyword.length() == 0) {
			stList.setKeyword(null);
			stList.setSearch(null);
		}

		int totStdntList = khTableSerive.getTotStdntList(stList);
		Paging paging = new Paging(totStdntList, stList.getCurrentPage());

		System.out.println(paging);
		stList.setStart(paging.getStart());
		stList.setEnd(paging.getEnd());

		List<Kh_StdntList> stdntList = khTableSerive.getStdntList(stList);

		model.addAttribute("rawList", stList);
		model.addAttribute("page", paging);
		model.addAttribute("currentPage", stList.getCurrentPage());
		model.addAttribute("stdntList", stdntList);

		return "kh/adminStdntList";
	}

	//
	// professor
	//
	@RequestMapping(value = "/empList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getEmpList(Kh_EmpList eList, Model model) {
		log.info("KhController getEmpList() is called");

		String rawKeyword = eList.getKeyword();
		if (rawKeyword != null && rawKeyword.length() == 0) {
			eList.setKeyword(null);
			eList.setSearch(null);
		}

		int totProfList = khTableSerive.getTotEmpList(eList);
		Paging paging = new Paging(totProfList, eList.getCurrentPage());

		System.out.println(paging);
		eList.setStart(paging.getStart());
		eList.setEnd(paging.getEnd());

		List<Kh_EmpList> empList = khTableSerive.getEmpList(eList);

		model.addAttribute("rawList", eList);
		model.addAttribute("page", paging);
		model.addAttribute("currentPage", eList.getCurrentPage());
		model.addAttribute("empList", empList);
		model.addAttribute("mbr_se", eList.getMbr_se());

		return "kh/adminEmpList";
	}

	@GetMapping(value = "/delLgnId")
	public String updateLgnDelYn(Lgn lgn) {
		int result = 0;

		System.out.println("updateLgnDelYn(String eml) eml -> " + lgn.getEml());
		System.out.println("updateLgnDelYn(String eml) mbr_se -> " + lgn.getMbr_se());
		result = khTableSerive.updateLgnDelYn(lgn);

		if (result == 1) {
			System.out.println("DELETE IS COMPLETED");
		}

		if (lgn.getMbr_se() == 1) {
			return "redirect:/kh/admin/stdntList";
		} else if (lgn.getMbr_se() == 2) {
			return "redirect:/kh/admin/empList?mbr_se=2";
		} else {
			return "redirect:/kh/admin/empList?mbr_se=3";
		}
	}

	//
	// Approve Lecture
	//
	@RequestMapping(value = "/appLctrList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getLctrList(Kh_LctrList lcList, Model model) {
		log.info("KhController getAppLctrList() is called");
		System.out.println("getAppLctrList lectureType -> " + lcList.getLectureType());

		String rawKeyword = lcList.getKeyword();
		if (rawKeyword != null && rawKeyword.length() == 0) {
			lcList.setKeyword(null);
			lcList.setSearch(null);
		}

		int totLctrList = khTableSerive.getTotLctrList(lcList);
		Paging paging = new Paging(totLctrList, lcList.getCurrentPage());
		String aplyType = "0";

		if (lcList.getAply_type() != null) {
			aplyType = lcList.getAply_type();
		}

		System.out.println(paging);
		lcList.setStart(paging.getStart());
		lcList.setEnd(paging.getEnd());
		lcList.setLectureTypeEnd(lcList.getLectureType() + 4);

		List<Kh_LctrList> lctrList = khTableSerive.getLctrList(lcList);

		model.addAttribute("rawList", lcList);
		model.addAttribute("page", paging);
		model.addAttribute("lctrList", lctrList);
		model.addAttribute("aplyType", aplyType);

		System.out.println(lcList);

		return "kh/adminAppLctrList";
	}

	@GetMapping(value = "/goSyllabus")
	public String goSyllabus(Kh_LctrList lcList, Model model) {
		log.info("KhController goSyllabus() is called");

		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);
		model.addAttribute("lctrDetail", lctrDetail);
		
		System.out.println(lctrDetail);

		return "kh/syllabusPage";
	}

	@PostMapping(value = "/sendRequest")
	public String requestModification(@RequestParam("lctr_num") String lctr_num,
			@RequestParam("requestContent") String requestContent, Model model) {
		log.info("KhController requestModification() is called");
		System.out.println("requestModification lctr_num -> " + lctr_num);
		System.out.println("requestModification requestContent -> " + requestContent);
		int lectureType = Integer.parseInt(lctr_num.substring(5, 6));
		if (lectureType < 5) {
			lectureType = 0;
		} else {
			lectureType = 5;
		}
		int lctrNum = Integer.parseInt(lctr_num);
		Kh_LctrList lcList = new Kh_LctrList();
		lcList.setLctr_num(lctrNum);
		lcList.setAply_type("130");

		khTableSerive.updateAplyType(lcList);
		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);
		lctrDetail.setEmlContent(requestContent);

		khTableSerive.sendRequest(lctrDetail);
		String aplyType = lctrDetail.getAply_type();

		System.out.println("aplyType -> " + aplyType);

		return "redirect:/kh/admin/appLctrList?aply_type=" + aplyType + "&lectureType=" + lectureType;
	}

	@GetMapping(value = "/openLctr")
	public String openLctr(@RequestParam("lctr_num") String lctr_num, HttpSession session) {
		log.info("KhController openLctr() is called");
		System.out.println("KhController openLctr() lctr_num -> " + lctr_num);

		int lectureType = Integer.parseInt(lctr_num.substring(5, 6));
		if (lectureType < 5) {
			lectureType = 0;
		} else {
			lectureType = 5;
		}
		String aplyYdm = "2024-06-15";
		String endDate = "2024-06-30";
		int lctrNum = Integer.parseInt(lctr_num);
		Kh_LctrList lcList = new Kh_LctrList();
		// int unq_num = Integer.parseInt(session.getAttribute("unq_num").toString());
		
		int unq_num = 317000012;
		String crtr_cnt = "총 42점/ 32점 미만 과락( 출석 +3 지각 +2 결석 0 )";

		lcList.setUnq_num(unq_num);
		lcList.setLctr_num(lctrNum);
		lcList.setAply_type("110");
		lcList.setAply_ydm(aplyYdm);
		lcList.setEnd_date(endDate);
		lcList.setCrtr_cnt(crtr_cnt);
		lcList.setFnsh_cost(300000);

		khTableSerive.openLecture(lcList);
		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);

		String aplyType = lctrDetail.getAply_type();
		System.out.println("aplyType -> " + aplyType);

		return "redirect:/kh/admin/appLctrList?aply_type=" + aplyType + "&lectureType=" + lectureType;
	}

	@GetMapping(value = "/closeLctr")
	public String closeLctr(@RequestParam("lctr_num") String lctr_num) {
		log.info("KhController closeLctr() is called");
		System.out.println("KhController closeLctr() lctr_num -> " + lctr_num);

		int lectureType = Integer.parseInt(lctr_num.substring(5, 6));
		if (lectureType < 5) {
			lectureType = 0;
		} else {
			lectureType = 5;
		}
		int lctrNum = Integer.parseInt(lctr_num);
		Kh_LctrList lcList = new Kh_LctrList();
		lcList.setLctr_num(lctrNum);
		lcList.setAply_type("150");
		khTableSerive.updateAplyType(lcList);
		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);

		String aplyType = lctrDetail.getAply_type();

		System.out.println("aplyType -> " + aplyType);

		return "redirect:/kh/admin/appLctrList?aply_type=" + aplyType + "&lectureType=" + lectureType;
	}

	@GetMapping(value = "/startLctr")
	public String startLctr(@RequestParam("lctr_num") String lctr_num) {
		log.info("KhController startLctr() is called");
		System.out.println("KhController startLctr() lctr_num -> " + lctr_num);

		int lectureType = Integer.parseInt(lctr_num.substring(5, 6));
		if (lectureType < 5) {
			lectureType = 0;
		} else {
			lectureType = 5;
		}
		int lctrNum = Integer.parseInt(lctr_num);
		Kh_LctrList lcList = new Kh_LctrList();
		lcList.setLctr_num(lctrNum);
		lcList.setAply_type("120");
		khTableSerive.updateAplyType(lcList);
		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);

		String aplyType = lctrDetail.getAply_type();

		hss.atndcTblInsert(Integer.parseInt(lctr_num));

		System.out.println("aplyType -> " + aplyType);

		return "redirect:/kh/admin/appLctrList?aply_type=" + aplyType + "&lectureType=" + lectureType;
	}
	
	//
	//LCTR ROOM
	//
	
	
	@RequestMapping(value = "/lctrRoom", method = { RequestMethod.GET, RequestMethod.POST })
	 public String getLctrmList(HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException { 
		
		log.info("KhController getLctrRoom() is called");
		
		String yearAndSemester 	= "";
		
		if(request.getContentLength() > 0) {
			yearAndSemester		= request.getParameter("year") + request.getParameter("semester");
			System.out.println("yearAndSemester -> " + yearAndSemester);
		}
		
		List<Kh_Lctrm> lctrmList 	= khTableSerive.getLctrmList(yearAndSemester);
		
		int[][] int01		= new int[10][10];			//Monday
		int[][] int02		= new int[10][10];			//Tuesday
		int[][] int03		= new int[10][10];			//Wednesday
		int[][] int04		= new int[10][10];			//Thursday
		int[][] int05		= new int[10][10];			//Friday
		
		String[][] str01		= new String[10][10];	//Monday
		String[][] str02		= new String[10][10];	//Tuesday
		String[][] str03		= new String[10][10];	//Wednesday
		String[][] str04		= new String[10][10];	//Thursday
		String[][] str05		= new String[10][10];	//Friday
		
		for(int i = 0 ; i < lctrmList.size() ; i++) {
			Kh_Lctrm dummy	= lctrmList.get(i);
			if(dummy.getDow_day().equals("1")) {
				String start 	= dummy.getBgng_tm();
				String end 		= dummy.getEnd_tm();
				start			= start.substring(0, 2);
				end				= end.substring(0, 2);
				int startNum	= Integer.parseInt(start);
				int endNum		= Integer.parseInt(end);
				startNum		= startNum - 9;
				endNum			= endNum - 8;	
				
				int	lctrmNum	= dummy.getLctrm_num();
				String lctrName	= dummy.getLctr_name();
				
				if(lctrmNum == 101) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][0] = 1;
						str01[j][0] = lctrName;
					}
				} else if(lctrmNum == 102) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][1] = 1;
						str01[j][1] = lctrName;
					}
				} else if(lctrmNum == 201) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][2] = 1;
						str01[j][2] = lctrName;
					}
				} else if(lctrmNum == 202) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][3] = 1;
						str01[j][3] = lctrName;
					}
				} else if(lctrmNum == 301) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][4] = 1;
						str01[j][4] = lctrName;
					}
				} else if(lctrmNum == 302) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][5] = 1;
						str01[j][5] = lctrName;
					}
				} else if(lctrmNum == 401) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][6] = 1;
						str01[j][6] = lctrName;
					}
				} else if(lctrmNum == 402) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][7] = 1;
						str01[j][7] = lctrName;
					}
				} else if(lctrmNum == 501) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][8] = 1;
						str01[j][8] = lctrName;
					}
				} else if(lctrmNum == 502) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int01[j][9] = 1;
						str01[j][9] = lctrName;
					}
				}
			} else if(dummy.getDow_day().equals("2")) {
				String start 	= dummy.getBgng_tm();
				String end 		= dummy.getEnd_tm();
				start			= start.substring(0, 2);
				end				= end.substring(0, 2);
				int startNum	= Integer.parseInt(start);
				int endNum		= Integer.parseInt(end);
				startNum		= startNum - 9;
				endNum			= endNum - 8;	
				
				int	lctrmNum	= dummy.getLctrm_num();
				String lctrName	= dummy.getLctr_name();
				
				if(lctrmNum == 101) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][0] = 1;
						str02[j][0] = lctrName;
					}
				} else if(lctrmNum == 102) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][1] = 1;
						str02[j][1] = lctrName;
					}
				} else if(lctrmNum == 201) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][2] = 1;
						str02[j][2] = lctrName;
					}
				} else if(lctrmNum == 202) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][3] = 1;
						str02[j][3] = lctrName;
					}
				} else if(lctrmNum == 301) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][4] = 1;
						str02[j][4] = lctrName;
					}
				} else if(lctrmNum == 302) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][5] = 1;
						str02[j][5] = lctrName;
					}
				} else if(lctrmNum == 401) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][6] = 1;
						str02[j][6] = lctrName;
					}
				} else if(lctrmNum == 402) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][7] = 1;
						str02[j][7] = lctrName;
					}
				} else if(lctrmNum == 501) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][8] = 1;
						str02[j][8] = lctrName;
					}
				} else if(lctrmNum == 502) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int02[j][9] = 1;
						str02[j][9] = lctrName;
					}
				}
			
			} else if(dummy.getDow_day().equals("3")) {
				String start 	= dummy.getBgng_tm();
				String end 		= dummy.getEnd_tm();
				start			= start.substring(0, 2);
				end				= end.substring(0, 2);
				int startNum	= Integer.parseInt(start);
				int endNum		= Integer.parseInt(end);
				startNum		= startNum - 9;
				endNum			= endNum - 8;	
				
				int	lctrmNum	= dummy.getLctrm_num();
				String lctrName	= dummy.getLctr_name();
				
				if(lctrmNum == 101) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][0] = 1;
						str03[j][0] = lctrName;
					}
				} else if(lctrmNum == 102) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][1] = 1;
						str03[j][1] = lctrName;
					}
				} else if(lctrmNum == 201) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][2] = 1;
						str03[j][2] = lctrName;
					}
				} else if(lctrmNum == 202) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][3] = 1;
						str03[j][3] = lctrName;
					}
				} else if(lctrmNum == 301) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][4] = 1;
						str03[j][4] = lctrName;
					}
				} else if(lctrmNum == 302) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][5] = 1;
						str03[j][5] = lctrName;
					}
				} else if(lctrmNum == 401) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][6] = 1;
						str03[j][6] = lctrName;
					}
				} else if(lctrmNum == 402) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][7] = 1;
						str03[j][7] = lctrName;
					}
				} else if(lctrmNum == 501) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][8] = 1;
						str03[j][8] = lctrName;
					}
				} else if(lctrmNum == 502) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int03[j][9] = 1;
						str03[j][9] = lctrName;
					}
				}
				
			} else if(dummy.getDow_day().equals("4")) {
				String start 	= dummy.getBgng_tm();
				String end 		= dummy.getEnd_tm();
				start			= start.substring(0, 2);
				end				= end.substring(0, 2);
				int startNum	= Integer.parseInt(start);
				int endNum		= Integer.parseInt(end);
				startNum		= startNum - 9;
				endNum			= endNum - 8;	
				
				int	lctrmNum	= dummy.getLctrm_num();
				String lctrName	= dummy.getLctr_name();
				
				if(lctrmNum == 101) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][0] = 1;
						str04[j][0] = lctrName;
					}
				} else if(lctrmNum == 102) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][1] = 1;
						str04[j][1] = lctrName;
					}
				} else if(lctrmNum == 201) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][2] = 1;
						str04[j][2] = lctrName;
					}
				} else if(lctrmNum == 202) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][3] = 1;
						str04[j][3] = lctrName;
					}
				} else if(lctrmNum == 301) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][4] = 1;
						str04[j][4] = lctrName;
					}
				} else if(lctrmNum == 302) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][5] = 1;
						str04[j][5] = lctrName;
					}
				} else if(lctrmNum == 401) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][6] = 1;
						str04[j][6] = lctrName;
					}
				} else if(lctrmNum == 402) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][7] = 1;
						str04[j][7] = lctrName;
					}
				} else if(lctrmNum == 501) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][8] = 1;
						str04[j][8] = lctrName;
					}
				} else if(lctrmNum == 502) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int04[j][9] = 1;
						str04[j][9] = lctrName;
					}
				}
				
			} else {
				String start 	= dummy.getBgng_tm();
				String end 		= dummy.getEnd_tm();
				start			= start.substring(0, 2);
				end				= end.substring(0, 2);
				int startNum	= Integer.parseInt(start);
				int endNum		= Integer.parseInt(end);
				startNum		= startNum - 9;
				endNum			= endNum - 8;	
				
				int	lctrmNum	= dummy.getLctrm_num();
				String lctrName	= dummy.getLctr_name();
				
				if(lctrmNum == 101) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][0] = 1;
						str05[j][0] = lctrName;
					}
				} else if(lctrmNum == 102) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][1] = 1;
						str05[j][1] = lctrName;
					}
				} else if(lctrmNum == 201) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][2] = 1;
						str05[j][2] = lctrName;
					}
				} else if(lctrmNum == 202) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][3] = 1;
						str05[j][3] = lctrName;
					}
				} else if(lctrmNum == 301) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][4] = 1;
						str05[j][4] = lctrName;
					}
				} else if(lctrmNum == 302) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][5] = 1;
						str05[j][5] = lctrName;
					}
				} else if(lctrmNum == 401) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][6] = 1;
						str05[j][6] = lctrName;
					}
				} else if(lctrmNum == 402) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][7] = 1;
						str05[j][7] = lctrName;
					}
				} else if(lctrmNum == 501) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][8] = 1;
						str05[j][8] = lctrName;
					}
				} else if(lctrmNum == 502) {
					for(int j = startNum ; j < endNum ; j ++ ) {
						int05[j][9] = 1;
						str05[j][9] = lctrName;
					}
				}
				
			}
		} 
			
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				System.out.print(int02[j][i] + "  ");
			}
			System.out.println("");
		}
		
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				System.out.print(str02[j][i] + "  ");
			}
			System.out.println("");
		}
		
		model.addAttribute("int01", int01);
		model.addAttribute("int02", int02);
		model.addAttribute("int03", int03);
		model.addAttribute("int04", int04);
		model.addAttribute("int05", int05);
		model.addAttribute("str01", str01);
		model.addAttribute("str02", str02);
		model.addAttribute("str03", str03);
		model.addAttribute("str04", str04);
		model.addAttribute("str05", str05);
		
		model.addAttribute("year", request.getParameter("year"));
		model.addAttribute("semester", request.getParameter("semester"));
		
		return "kh/adminLctrRm"; 
	 }
	 
	
	
	

	//
	// scholarship
	//

	
	@RequestMapping(value = "/schList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getSchList(Kh_ScholarshipList sList, Model model) {
		log.info("KhController getSchList() is called");
		
		String rawKeyword = sList.getKeyword();
		if (rawKeyword != null && rawKeyword.length() == 0) {
			sList.setKeyword(null);
			sList.setSearch(null);
		}
		
		int totSchList = khTableSerive.getTotSchList(sList);
		Paging paging = new Paging(totSchList, sList.getCurrentPage());

		System.out.println(paging);
		sList.setStart(paging.getStart());
		sList.setEnd(paging.getEnd());
		
		List<Kh_ScholarshipList> schList = khTableSerive.getSchList(sList);
		
		model.addAttribute("rawList", sList);
		model.addAttribute("page", paging);
		model.addAttribute("currentPage", sList.getCurrentPage());
		model.addAttribute("schList", schList);
		
		return "kh/adminSchList";
	}
	
	
	@GetMapping(value = "/loadImg")
	public String getScholarahipImgPath(HttpServletRequest request, Model model) {	
		log.info("KhController loadImg() is called");
		
		
		
		int scholarship_num = Integer.parseInt(request.getParameter("num").toString());
		String imgType		= request.getParameter("type"); 
		
		System.out.println("KhController loadImg() scholarship_num -> " + scholarship_num);
		System.out.println("KhController loadImg() imgType -> " + imgType);
		
		if(imgType.equals("participate")) {
			imgType	= "PTCP_IMG";
		} else if(imgType.equals("priority")) {
			imgType	= "PRIORITY_IMG";
		} else {
			imgType	= "BANK_IMG";
		}
		
		Kh_ScholarshipList schList = new Kh_ScholarshipList();
		schList.setScholarship_num(scholarship_num);
		schList.setImgType(imgType);
		
		String filePath = khTableSerive.getScholarahipImgPath(schList);
		
		model.addAttribute("filePath", filePath);
		
		return "kh/loadImg";
	}
	
	

	@GetMapping(value = "/updateGiveStss")
	public String updateGiveStss(HttpServletRequest request, Model model) {	
		log.info("KhController updateGiveStss() is called");
		
		int scholarship_num = Integer.parseInt(request.getParameter("num"));
		int gStts		= Integer.parseInt(request.getParameter("gStts"));
		
		Kh_ScholarshipList schList = new Kh_ScholarshipList();
		schList.setScholarship_num(scholarship_num);
		schList.setGive_stts(gStts);		
		
		khTableSerive.updateGiveStss(schList);
		
		System.out.println("KhController updateGiveStss() scholarship_num -> " + scholarship_num);
		System.out.println("KhController updateGiveStss() gStts -> " + gStts);
		
		
		
		return "redirect:/kh/admin/schList?give_stts=" + gStts;
	}
	
	
	
	@GetMapping(value = "/applyScholarship")
	public String aplyScholarship(@RequestParam("lctr_num") long lctr_num, HttpSession session, Model model) {
		log.info("KhController aplyScholarship() is called");
		
		Kh_ScholarshipList sList 		= new Kh_ScholarshipList();
		long unq_num 					= Long.parseLong(session.getAttribute("unq_num").toString());
		
		sList.setLctr_num(lctr_num);
		sList.setUnq_num(unq_num);
		
		Kh_ScholarshipList schList	= khTableSerive.getSchDetail(sList);
		model.addAttribute("schList", schList);
		
		return "kh/applyScholarshipForm";
	}

	
	@PostMapping(value = "/inputInfo")
	public String inputScholarshipInfo( @RequestParam(name = "proofFile") MultipartFile proofFile,
									  	@RequestParam(name = "bankFile") MultipartFile bankFile,
									  	Kh_ScholarshipList schDetail,
									  	Model model) {
		
		log.info("KhController inputScholarshipInfo() is called");
		
		String path 			= uploadPath + "proofFile/";
		File Folder 			= new File(path);
		// 해당 디렉토리가 없다면 디렉토리를 생성.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); 								//새폴더생성
				System.out.println("New Directory is created");
			} catch(Exception e){
				e.getStackTrace();
			} 
		}
		
		path 					= uploadPath + "bankFile/";
		Folder 					= new File(path);
		// 해당 디렉토리가 없다면 디렉토리를 생성.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); 								//새폴더생성
				System.out.println("New Directory is created");
			} catch(Exception e){
				e.getStackTrace();
			} 
		}
		
		String uuid = UUID.randomUUID().toString();
		String proofOriginalFileName = proofFile.getOriginalFilename();
		String proofExtension 		= proofOriginalFileName.substring(proofOriginalFileName.lastIndexOf("."));
		String proofSavePath 		= uploadPath + "proofFile/" + uuid + proofExtension;

		if (!proofFile.isEmpty()) {
			try {
				proofFile.transferTo(new File(proofSavePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		uuid = UUID.randomUUID().toString();
		String bankOriginalFileName = bankFile.getOriginalFilename();
		String bankExtension 		= bankOriginalFileName.substring(bankOriginalFileName.lastIndexOf("."));
		String bankSavePath 		= uploadPath + "bankFile/" + uuid + bankExtension;

		if (!bankFile.isEmpty()) {
			try {
				bankFile.transferTo(new File(bankSavePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int sprtCost = 0;
		if(schDetail.getPtcp_type() != 0) {
			schDetail.setPtcp_img(proofSavePath);
			sprtCost = (int) (schDetail.getFnsh_cost() * 0.5);	//참여 50% 감면
		} else {
			schDetail.setPriority_img(proofSavePath);			//우대 80% 감면
			sprtCost = (int) (schDetail.getFnsh_cost() * 0.8);
		}
		schDetail.setBank_img(bankSavePath);
		schDetail.setSprt_cost(sprtCost);
		
		System.out.println("schDetail -> " + schDetail);
		
		model.addAttribute("schDetail", schDetail);
		
		khTableSerive.insertSchDetail(schDetail);
		
		return "kh/applyScholarshipClosing";
	}
	
	
	

	//
	// prdoc
	//
	
	@RequestMapping(value = "/prdocList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getPrdocList(Kh_PrdocList prList, Model model) {
		log.info("KhController getPrdocList() is called");
		
		System.out.println("KhController getPrdocList() -> " + prList);
		
		String rawKeyword = prList.getKeyword();
		if (rawKeyword != null && rawKeyword.length() == 0) {
			prList.setKeyword(null);
			prList.setSearch(null);
		}

		int totPrdocList = khTableSerive.getTotPrdocList(prList);
		Paging paging = new Paging(totPrdocList, prList.getCurrentPage());

		System.out.println(paging);
		prList.setStart(paging.getStart());
		prList.setEnd(paging.getEnd());

		List<Kh_PrdocList> prdocList = khTableSerive.getPrdocList(prList);

		model.addAttribute("rawList", prList);
		model.addAttribute("page", paging);
		model.addAttribute("currentPage", prList.getCurrentPage());
		model.addAttribute("prdocList", prdocList);

		return "kh/adminPrdocList";
	}
	
	
	@GetMapping(value = "/goCertification")
	 public String goCertification(Kh_PrdocList prList, Model model) {
		
		log.info("KhController goCertification() is called");
		
		int prdocType			= prList.getPrdoc_type();
		Kh_PrdocList prDetail	= khTableSerive.getPrdocDetail(prList);
		
		LocalDate today			= LocalDate.now();
		prDetail.setIssu_ymd(today.toString());
		int yy					= today.getYear();
		int month				= today.getMonthValue();
		int day					= today.getDayOfMonth();
		prDetail.setYy(yy);
		prDetail.setMonth(month);
		prDetail.setDay(day);
		
		String lecNum			= prDetail.getLctr_num() + "";	 
		String year	 			= "20" + lecNum.substring(0, 2);
		String date	 			= lecNum.substring(2, 3);
		if(date.equals("1")) {
			year += "-03-02";
		} else {
			year += "-09-02";
		}
		prDetail.setStart_date(year);
		
		String unqNum			= prDetail.getUnq_num() + "";	 
		String bCode	 		= "3" + unqNum.substring(3, 4) + "0";
		String mCode	 		= unqNum.substring(3, 5);
		
		Kh_SortCode	sCode		= new Kh_SortCode();
		sCode.setBcode(Integer.parseInt(bCode));
		sCode.setMcode(Integer.parseInt(mCode));		
		String stdntDepart		= khTableSerive.getSortContent(sCode);
		prDetail.setStdntDepart(stdntDepart);
		
		if(prDetail.getPtcp_type() != 0) {
			sCode.setBcode(150);
			sCode.setMcode(prDetail.getPtcp_type());
		} else {
			sCode.setBcode(160);
			sCode.setMcode(prDetail.getPriority_type());
		}
		
		String scType			= khTableSerive.getSortContent(sCode);
		prDetail.setScType(scType);
		
		model.addAttribute("prDetail", prDetail);
		
		if(prdocType == 100) {
			return "kh/certCompletion"; 
		} else {
			return "kh/certAward"; 
		}
	 }
	
	
	
	@PostMapping(value = "/issueCertification")
	public ResponseEntity<String> saveImage(ModelMap modelMap, HttpServletRequest request, Kh_PrdocList prList) {
		log.info("KhController saveImage is called");
		
		int aply_num = Integer.parseInt(request.getParameter("aply_num"));
		
		FileOutputStream outStream	= null;
		String fileName				= aply_num + "_certification.png";
		String savePath 			= "c:" + File.separator + uploadPath + File.separator + fileName;
				
		try {
			String imgData 			= request.getParameter("imgData");
			// 디코딩을 위해서 string data의 "data:image/png;base64," 제거
			imgData					= imgData.replaceAll("data:image/png;base64,", "");
			
			byte[] file				= Base64.getDecoder().decode(imgData);
			outStream				= new FileOutputStream(savePath);
			
			outStream.write(file);
			outStream.close();
			
			LocalDate today	= LocalDate.now();
			prList.setIssu_ymd(today.toString());
			prList.setAply_num(aply_num);
			khTableSerive.updateIsuueDate(prList);   	
			
			System.out.println("KhController saveImage is completed");
			return ResponseEntity.ok("Image saved successfully");										//  JSP 응답이 필요 없으므로, @ResponseBody나 ResponseEntity를 사용해 클라이언트에 JSON 형식의 응답을 반환
			
			/*	IOUtils.copy를 이용하여 download 폴더에 바로 저장
			ByteArrayInputStream is	= new ByteArrayInputStream(file);
			response.setContentType("image/png");
			response.setHeader("Content-Disposition", "attachment; filename=report.png");
			
			System.out.println("response.getOutputStream() -> " + response.getOutputStream().toString());
			
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
			*/
		} catch (IOException e) {
			log.error("Error saving image", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");	// JSP 응답이 필요 없으므로, @ResponseBody나 ResponseEntity를 사용해 클라이언트에 JSON 형식의 응답을 반환
		}
		
	}
	

	
	//
	// Board
	//
	
	 
	 @GetMapping(value = "/boardList")
	 public String getoardList(Kh_pstList pList, Model model) { log.info("KhController getoardList() is called");
	 
		String rawKeyword = pList.getKeyword(); if(rawKeyword != null &&
		rawKeyword.length()==0) { pList.setKeyword(null); pList.setSearch(null); }
		 
		int totBoardList = khTableSerive.getTotBoardList(pList); Paging paging = new
		Paging(totBoardList, pList.getCurrentPage());
		
		System.out.println(paging); pList.setStart(paging.getStart());
		pList.setEnd(paging.getEnd());
		
		List<Kh_pstList> pstList = khTableSerive.getBoardList(pList);
		
		model.addAttribute("rawList", pList); model.addAttribute("page", paging);
		model.addAttribute("currentPage", pList.getCurrentPage());
		model.addAttribute("pstList", pstList);
		
		return "kh/adminPstList"; 
	 }
	 
	 
	 @GetMapping(value = "/updateDelYnPst")
		public String updateDelYnPst(Kh_pstList pList) {
			log.info("KhController updateDelYnPst() is called");
			System.out.println("updateDelYnPst pList -> " + pList);

			khTableSerive.updateDelYnPst(pList);

			return "redirect:/kh/admin/boardList?pst_clsf=" + pList.getPst_clsf();
		}

}
