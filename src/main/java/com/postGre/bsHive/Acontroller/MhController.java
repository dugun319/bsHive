package com.postGre.bsHive.Acontroller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/gongList")
	public String GongGiList(Pst pst, Model model) {
		System.out.println("mhboard_GongList start");
		
		int totalGongList = ms.totalGongList();
		
		List<Pst> listGong = ms.listGong(pst);
		
		model.addAttribute("totalGongList", totalGongList);
		model.addAttribute("listGong", listGong);
		
		return "mh/gongList";
	}
	
	@GetMapping("/gongView")
	public String GongView(Pst pst, Model model) {
		 List<Pst> GongView = ms.GongView(pst);
		 
		 model.addAttribute("GongView",GongView);
		
		return "mh/gongView";
	}
	
	@GetMapping("/gongDelete")
	public String GongDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.GongDelete(pst_num);
		return "redirect:gongList";	
	}
	
	@GetMapping("/gongWrite")
	public String Gongwrite(Model model) {
		
		return "mh/gongWrite";	
	}
	
	@PostMapping("/gongInsert")
	public String gongInsert (Pst pst, Model model) {
		int insertResult = ms.gongInsert(pst); 
		return "redirect:gongList";
	}
	
	@GetMapping("/gongModify")
	public String gongModify (Pst pst, Model model) {
		 List<Pst> GongView = ms.GongView(pst);
		 
		 model.addAttribute("GongView",GongView);
		return "mh/gongModify";
	}
	
	@PostMapping("/gongModifyDB")
	public String gongModifyDB (Pst pst) {
		int updateCount = ms.updateGong(pst);
		System.out.println("updateCount->" +updateCount);
		return "redirect:gongList";
	}
	
	@GetMapping("/yakList")
	public String yakGiList(Pst pst, Model model) {
		int totalYakList = ms.totalYakList();
		
		List<Pst> listYak = ms.listYak(pst);
		
		model.addAttribute("totalYakList", totalYakList);
		model.addAttribute("listYak", listYak);
		
		return "mh/yakList";
	}
	
	@GetMapping("/yakView")
	public String yakView(Pst pst, Model model) {
		 List<Pst> yakView = ms.yakView(pst);
		 
		 model.addAttribute("yakView", yakView);
		 System.out.println("yakView->" +yakView);
		
		return "mh/yakView";
	}
	@GetMapping("/yakWrite")
	public String yakwrite(Model model) {
		
		return "mh/yakWrite";	
	}
	
	@PostMapping("/yakInsert")
	public String yakInsert (Pst pst, Model model) {
		int insertResult = ms.yakInsert(pst); 
		return "redirect:yakList";
	}
	
	@GetMapping("/yakModify")
	public String yakModify (Pst pst, Model model) {
		 List<Pst> yakView = ms.yakView(pst);
		 
		 model.addAttribute("yakView",yakView);
		return "mh/yakModify";
	}
	
	@PostMapping("/yakModifyDB")
	public String yakModifyDB (Pst pst) {
		int updateCount = ms.updateYak(pst);
		return "redirect:yakList";
	}
	
	@GetMapping("/yakDelete")
	public String yakDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.yakDelete(pst_num);
		return "redirect:yakList";	
	}
	
	@GetMapping("/fnqList")
	public String faqList(Pst pst, Model model) {
		
		int totalFaqList = ms.totalFaqList();
		
		List<Pst> listFaq = ms.listFaq(pst);
		
		model.addAttribute("totalFaqList", totalFaqList);
		model.addAttribute("listFaq", listFaq);
		
		return "mh/fnqList";
	}
	
	@GetMapping("/fnqView")
	public String fnqView(Pst pst, Model model) {
		 List<Pst> fnqView = ms.fnqView(pst);
		 
		 model.addAttribute("fnqView",fnqView);
		
		return "mh/fnqView";
	}
	
}
