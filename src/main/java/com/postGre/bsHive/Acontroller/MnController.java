package com.postGre.bsHive.Acontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.MnService.MnService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MnController {
	private final MnService ms;
	
	@GetMapping(value = "/")
	public String mainPage(Model model){
		System.out.println("MnController mainPage start...");
		
		List<Pst> pstList = ms.pstList();
		List<Pst> pstList5 = pstList.subList(0, Math.min(5, pstList.size()));
		
		List<Crans_Qitem> crQt = ms.selAll();
		
		System.out.println("crQt >>>>>> " + crQt);
		
		model.addAttribute("pstList", pstList5);
		model.addAttribute("crQt", crQt);
		
		return "main";
		
	}
	
	@GetMapping(value = "mn/lctListPage")
	public String lustLctPage(Model model) {
		System.out.println("MnController lustLctPage start...");
		
		List<Mn_LctrAplyOflWeek> ListAll = ms.joinLctrAplyAllList();
		
		model.addAttribute("lctrList", ListAll);
		
		
		return "mn/mn_of_lctrList";
	}
}
