package com.postGre.bsHive.Acontroller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.MhService.MhService;



@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value="/mh")
public class MhController {
	
	@Autowired
	private final MhService ms;
	
	@GetMapping("/mhboard_GongList")
	public String GongGiList(Pst pst, Model model) {
		System.out.println("mhboard_GongList start");
		
		int totalGongList = ms.totalGongList();
		
		List<Pst> listGong = ms.listGong(pst);
		
		model.addAttribute("totalGongList", totalGongList);
		model.addAttribute("listGong", listGong);
		System.out.println("listGong->" +listGong);
		
		return "mh/mhboard_GongList";
	}
	
	@GetMapping("/GongView")
	public String GongView(Pst pst, Model model) {
		 List<Pst> GongView = ms.GongView(pst);
		 
		 model.addAttribute("GongView",GongView);
		 System.out.println("GongView Controller : " +GongView);
		
		return "mh/GongView";
	}
	
	@GetMapping("/GongDelete")
	public String GongDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.GongDelete(pst_num);
		return "redirect:mhboard_GongList";	
	}
	
	@GetMapping("/Gongwrite")
	public String Gongwrite() {
		return "redirect:mhboard_GongList";	
	}
	
	
	

}
