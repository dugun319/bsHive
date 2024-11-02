 package com.postGre.bsHive.Acontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_Evl;
import com.postGre.bsHive.HbService.HbTableService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "hb")
public class HbController {
	private final HbTableService hbtableService;

	@GetMapping("/courseEval")
	public String courEvalStart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("hbController courseEval Start");
		List<String> evalQuestions = hbtableService.getEvalQuestion();
		request.setAttribute("questions", evalQuestions);
		return "hb/courseEval"; 
	}
	@GetMapping("/showResult")
	public String showResult(){
		System.out.println("getStartFinalImages");
		return "hb/showResult";
	}
	
	@PostMapping("/submitEvaluation")
	public String courseEvalSubmit(Model model, Lctr_Evl lctr_Evl,  @RequestParam(name = "scores",  required=false)  Integer [] scores) {
		System.out.println("i'm start...");
		  int eval_total = 0;

		    // 점수 합산
		    if (scores != null) {
		        for (Integer score : scores) {
		            if (score != null) {
		                eval_total += score; // 점수를 합산
		            }
		        }
		    }
		hbtableService.addAndUpdateEval(lctr_Evl, eval_total);
		return "hb/showResult";
	}
	
	@GetMapping("/lecTestResult")
	public String lecTestResult(Model model) {
		System.out.println("startLecTest");
		Onln_Lctr_List onln_Lctr_List = new Onln_Lctr_List();
		onln_Lctr_List.setUnq_num(123); // 예시로 고유번호를 세팅
		onln_Lctr_List.setLctr_num(456); // 예시로 강의번호를 세팅
		System.out.println("startLecTest");
	    List<Onln_Lctr_List> results = hbtableService.getStntNum(onln_Lctr_List);
	    model.addAttribute("lctr_aplyList",results);
		return"hb/lecTestResult";
	}
	
	@GetMapping("/showFinalResult")
	public String showFinalResult(Model model) {
		System.out.println("finalResultPage");
		return "hb/showFinalResult";
	}
	
	@GetMapping("/submitGrades")
	public String submitGrades(Model model) {
		System.out.println("submitGrades start...");
		return "hb/lecTestResult";
	}
}
