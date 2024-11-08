package com.postGre.bsHive.Acontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mysql.cj.Session;
import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_ScholarshipList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Adto.Kh_pstList;
import com.postGre.bsHive.Amodel.Lgn;
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
		lcList.setAply_type("3");

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
		lcList.setAply_type("1");
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
		lcList.setAply_type("5");
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
		lcList.setAply_type("2");
		khTableSerive.updateAplyType(lcList);
		Kh_LctrList lctrDetail = khTableSerive.getLctrDetail(lcList);

		String aplyType = lctrDetail.getAply_type();

		System.out.println("aplyType -> " + aplyType);

		return "redirect:/kh/admin/appLctrList?aply_type=" + aplyType + "&lectureType=" + lectureType;
	}
	
	//
	//LCTR ROOM
	//
	
	
	@GetMapping(value = "/lctrRoom")
	 public String getLctrRoom(Model model) { 
		
		log.info("KhController getLctrRoom() is called");
		
		return "kh/adminLctrRm"; 
	 }
	 
	
	
	

	//
	// prdoc
	//

	@GetMapping(value = "/prdocList")
	public String getPrdocList(Kh_PrdocList prList, Model model) {
		log.info("KhController getPrdocList() is called");

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
	
	@GetMapping(value = "/schList")
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
	
	@GetMapping(value = "/applyScholarship")
	public String aplyScholarship(@RequestParam("lctr_num") long lctr_num, HttpSession session, Model model) {
		log.info("KhController aplyScholarship() is called");
		
		Kh_ScholarshipList sList 		= new Kh_ScholarshipList();
		long unq_num 					= Long.parseLong(session.getAttribute("unq_num").toString());
		
		sList.setLctr_num(lctr_num);
		sList.setUnq_num(unq_num);
		
		Kh_ScholarshipList schDetail	= khTableSerive.getSchDetail(sList);
		model.addAttribute("schDetail", schDetail);
		
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
