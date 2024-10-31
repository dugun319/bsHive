package com.postGre.bsHive.Acontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.MnService.MnService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MnController {
	private final MnService ms;
	
	@GetMapping(value = "/")
	public String listReviewcount(Model model){
		System.out.println("MhController listReviewcount start...");
		
		List<Crans_Qitem> crQt = ms.selAll();
		
		System.out.println("crQt >>>>>> " + crQt);
		model.addAttribute("crQt", crQt);
		
		return "main";
		
	}
}
