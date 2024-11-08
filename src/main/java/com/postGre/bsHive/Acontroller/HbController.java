 package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.postGre.bsHive.Adto.Hb_Evl;
import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Evl_Sbmsn;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Lctr_Aply;
import com.postGre.bsHive.HbService.HbTableService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "hb")
public class HbController {
	private final HbTableService hbtableService;

	@GetMapping("/courseEval")
	public String courEvalStart(HttpServletRequest request, 
														HttpServletResponse response,
														HttpSession session, Model model) throws ServletException, IOException{
		System.out.println("hbController courseEval Start");
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
		Integer unqNum = (Integer) session.getAttribute("unq_num");	   
		model.addAttribute("unqNum", unqNum); // 모델에 추가
			if (user == null) {
				return "redirect:/jh/loginForm"; // 예시
			}
			
		Hb_Evl hb_evl = new Hb_Evl();
		 List<Hb_Evl> evalQuestions = hbtableService.getEvalQuestion(hb_evl); // 객체 초기화
		 request.setAttribute("evalQuestions", evalQuestions); // 평가 질문 리스트 설정
		 // 세션에서 UNQ_NUM 값을 가져와 모델에 추가
		return "hb/courseEval"; 
	}
	
	@PostMapping("/submitEvaluation")
	public String courseEvalSubmit(Model model, 
			@RequestParam Map<String, Integer> evaluationScores,
			RedirectAttributes redirectAttributes,
			HttpSession session) { // HttpSession을 추가
		Hb_Evl hb_Evl = new Hb_Evl();
		
		User_Table user = (User_Table) session.getAttribute("user"); // 세션에서 사용자 정보 가져오기
		if (user != null) {
			hb_Evl.setUnq_num(user.getUnq_num()); // UNQ_NUM 설정 (user 객체에서 UNQ_NUM 가져오기)
		} else {
			return "redirect:/login"; // 로그인 페이지로 리다이렉트
		}
		
		System.out.println(evaluationScores);
		hbtableService.addAndUpdateEval(hb_Evl,evaluationScores);
		redirectAttributes.addFlashAttribute("successMessage", "강의 평가가 제출되었습니다. 성적을 확인하세요.");
		
		return "redirect:/hb/showResult";
	}
	
	@GetMapping("/showResult")
	public String showResult(Model model, HttpSession session){
		System.out.println("getStartFinalclass");
		Integer unqNum = (Integer) session.getAttribute("unq_num"); // 세션에서 UNQ_NUM 가져오기
		String stdntName = (String)session.getAttribute("stdnt_nm");
		model.addAttribute("unqNum", unqNum); // 모델에 추가
		model.addAttribute("stdntName", stdntName); // 모델에 추가
		System.out.println("getStartFinalclass : " + unqNum + stdntName);

		    if (unqNum != null) {
		        System.out.println("로그인한 사용자 UNQ_NUM: " + unqNum);
		    } else {
		        System.out.println("사용자 UNQ_NUM이 세션에 없습니다.");
		    }
		List<Hb_Evl> studentClassList = hbtableService.getAllStudentsList();
		System.out.println(studentClassList.size());
		model.addAttribute("studentList",studentClassList);
		if (!studentClassList.isEmpty()) {
	        model.addAttribute("lctr_name", studentClassList.get(0).getLctr_name()); // 첫 번째 강의명
		}
		System.out.println(studentClassList);
		return "hb/showResult";
	}
	
	
	@GetMapping("/lecTestResult")
	public String submitGrades(Model model, Hb_Evl hb_Evl) {
		List<Hb_Evl> hbEvl = new ArrayList<>();
		System.out.println("submitGrades start...");
		
		hbEvl= hbtableService.getAllClass(hb_Evl);
		model.addAttribute("classlist",hbEvl);
		return "hb/lecTestResult";
	}
	@GetMapping("/showFinalResult")
	public String showFinalResult(Model model,Hb_Evl hb_Evl) {
		System.out.println("finalResultPage");
		List<Hb_Evl> hbEvl = new ArrayList<>();
		System.out.println("showFinalResult start...");
		
		hbEvl= hbtableService.getAllClass(hb_Evl);
		Map<Integer, String> evlMap = new HashMap<>();
		evlMap.put(0, "평가안함");
		evlMap.put(1, "평가완료");
		
		model.addAttribute("className","영상의 이해");
		model.addAttribute("evlMap",evlMap);
		model.addAttribute("studentList",hbEvl);
		return "hb/showFinalResult";
	}

	@GetMapping("/lecTestWrite")
	public String lecTestWrite(Model model) {
		System.out.println("submitGrades start...");
		
		return "hb/lecTestWrite";
	}
	@GetMapping("/lecTest")
	public String lecTest(Model model) {
		System.out.println("submitGrades start...");
		
		return "hb/lecTest";
	}
	@GetMapping("/detailResult")
	public String detailResult(@RequestParam("unq_num") int unqNum, 
            @RequestParam("lctr_num") int lctrNum, 
            Model model) {
				System.out.println("상세 성적 확인 페이지, unq_num: " + unqNum + " / " + lctrNum);
				
				// unq_num을 모델에 추가하여 JSP에서 사용 가능하도록 전달
				model.addAttribute("unqNum", unqNum);
				model.addAttribute("lctr_num", lctrNum);
				
				// 단일 객체를 가져옴
				Hb_Evl hb_evl = hbtableService.getDetailGrade(unqNum, lctrNum);
				
				// null 체크 후 리스트로 감싸기
				if (hb_evl != null) {
				    List<Hb_Evl> studentGradeList = new ArrayList<>();
				    studentGradeList.add(hb_evl);  // 단일 객체를 리스트에 추가
				    model.addAttribute("studentGrade", studentGradeList);  // 모델에 리스트 전달
				    System.out.println(studentGradeList);
				} else {
				    System.out.println("hb_evl 객체가 null입니다.");  // 디버깅
				}
				
				
				model.addAttribute("studentList", hb_evl);  // 학생 목록을 JSP로 전달
				
				return "hb/detailResult";  // 리스트를 JSP로 전달

}


	
	
}
