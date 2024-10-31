package com.postGre.bsHive.Acontroller;

import java.util.List;

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

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
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
	
	private final KhTableSerive khTableSerive;
	
	//
	//adminMain
	//
	
	@GetMapping(value = "/adminMain")
	public String adminMain(HttpServletRequest request, HttpSession session) {
		
		if(session.getId() == null) {
			session.invalidate(); 						// 기존 세션을 무효화
			session = request.getSession(true); 		// 새로운 세션 생성
			session.setMaxInactiveInterval(30*60); 	// 30분 동안 활동이 없으면 세션 만료 설정
			session.setAttribute("sessionTime", session.getMaxInactiveInterval());
		}
		return "kh/adminMain";
	}
	
	
	
	//
	//sessionTimer
	//
	
	@PostMapping(value = "/sessionExtension")
	public void sessionExtension(HttpSession session) {
		System.out.println("sessionExtension(HttpSession session) is called");
		
		session.setAttribute( "extensionTime", System.currentTimeMillis());
	}
	
	@ResponseBody
	@GetMapping("/getSessionRemain")
	public Integer getSessionRemain(HttpServletRequest request) {
	    HttpSession session 	= request.getSession();
	    Long remianTime 		= session.getAttribute( "extensionTime" ) == null ? session.getCreationTime() : (long) session.getAttribute( "extensionTime" );
	    // long jakarta.servlet.http.HttpSession.getCreationTime() 
	    // Returns the time when this session was created, measured in milliseconds since midnight January 1, 1970 GMT.

	    return (int) Math.max(0, session.getMaxInactiveInterval() - (System.currentTimeMillis() - remianTime) / 1000);
	}
	
	
	@GetMapping("/goLogOut")
	public String goLogOut(HttpSession session) {
		
		session.invalidate();
		
	    return "main";
	}
	
	
	
	//
	//student
	//
	@RequestMapping(value="/stdntList" , method = {RequestMethod.GET, RequestMethod.POST})
    public String getStdntList(Kh_StdntList stList, Model model) {
		log.info("KhController getStdntList() is called");
		
		String rawKeyword 				= stList.getKeyword();		
		if(rawKeyword != null && rawKeyword.length()==0) { 
			stList.setKeyword(null);
			stList.setSearch(null); 
		}
		
		int totStdntList				= khTableSerive.getTotStdntList(stList);
		Paging paging					= new Paging(totStdntList, stList.getCurrentPage());
		
		System.out.println(paging);
		stList.setStart(paging.getStart());
		stList.setEnd(paging.getEnd());
		
		List<Kh_StdntList> stdntList	= khTableSerive.getStdntList(stList);
		
		model.addAttribute("rawList", 		stList);
		model.addAttribute("page", 			paging);
		model.addAttribute("currentPage", 	stList.getCurrentPage());
		model.addAttribute("stdntList", 	stdntList);
		
        return "kh/adminStdntList";
    }
	

	
	//
	//professor
	//
	@RequestMapping(value="/profList" , method = {RequestMethod.GET, RequestMethod.POST})
    public String getProfList(Kh_EmpList eList, Model model) {
		log.info("KhController getStdntList() is called");
		
		String rawKeyword 			= eList.getKeyword();		
		if(rawKeyword != null && rawKeyword.length()==0) { 
			eList.setKeyword(null);
			eList.setSearch(null); 
		}
		
		int totProfList				= khTableSerive.getTotProfList(eList);
		Paging paging				= new Paging(totProfList, eList.getCurrentPage());
		
		System.out.println(paging);
		eList.setStart(paging.getStart());
		eList.setEnd(paging.getEnd());
		
		List<Kh_EmpList> ProfList	= khTableSerive.getStdntList(eList);
		
		model.addAttribute("rawList", 		eList);
		model.addAttribute("page", 			paging);
		model.addAttribute("currentPage", 	eList.getCurrentPage());
		model.addAttribute("ProfList", 		ProfList);
		
        return "kh/adminStdntList";
    }
	
	@GetMapping(value = "/delLgnId")
	public String updateLgnDelYn(Lgn lgn) {
		int result = 0;
		
		System.out.println("updateLgnDelYn(String eml) eml -> " + lgn.getEml());
		result					= khTableSerive.updateLgnDelYn(lgn);
		
		if(result == 1) {
			System.out.println("DELETE IS COMPLETED");
		}
		
		if(lgn.getMbr_se().equals("1")) {
			return "redirect:/kh/admin/stdntList";
		} else if(lgn.getMbr_se().equals("2")) {
			return "redirect:/kh/admin/profList";
		} else {
			return "redirect:/kh/admin/empList";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//
	//prdoc
	//
		
	@GetMapping(value = "/prdocList")
    public String getPrdocList(Kh_PrdocList prList, Model model) {
		log.info("KhController getPrdocList() is called");
		
		String rawKeyword 				= prList.getKeyword();		
		if(rawKeyword != null && rawKeyword.length()==0) { 
			prList.setKeyword(null);
			prList.setSearch(null); 
		}
		
		int totPrdocList				= khTableSerive.getTotPrdocList(prList);
		Paging paging					= new Paging(totPrdocList, prList.getCurrentPage());
		
		System.out.println(paging);
		prList.setStart(paging.getStart());
		prList.setEnd(paging.getEnd());
		
		List<Kh_PrdocList> prdocList	= khTableSerive.getPrdocList(prList);
		
		model.addAttribute("rawList", 		prList);
		model.addAttribute("page", 			paging);
		model.addAttribute("currentPage", 	prList.getCurrentPage());
		model.addAttribute("prdocList", 	prdocList);
		
        return "kh/adminPrdocList";
    }

}
