package com.postGre.bsHive.Acontroller;

import org.springframework.stereotype.Controller;


import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postGre.bsHive.HbService.HbTableService;
import com.postGre.bsHive.SeService.SeService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "hb")
public class HbController {
	private final HbTableService hbtableService;
	
	@GetMapping("/hb")
	public String afterclass() {
		System.out.println("hbController sucessfully start");
		return "hb/hbmain";
	}
	
	@GetMapping("/courseEval")
	public String courEvalStart(Model model){
		System.out.println("hbController courseEval Start");
		return "hb/courseEval";
	}
	
	@GetMapping("/courseEvalSubmit")
	public String courseEvalSubmit(Model model) {
		hbtableService.addCourseEval();
		return "hb/showresult";
	}
	
}
