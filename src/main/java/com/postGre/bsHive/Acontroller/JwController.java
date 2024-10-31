package com.postGre.bsHive.Acontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.postGre.bsHive.Adto.All_Lctr;
import com.postGre.bsHive.JwService.JwService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JwController {
	private final JwService js;
	
	// 온라인 강의정보 불러오기 
	@GetMapping(value = "/jw/")
	public String OnlnLctrInfo(All_Lctr al,
							   //HttpSession session,
							   Model model) {
		System.out.println("JwController OnlnLctrInfo Start...");
		
		List<All_Lctr> listOnlnLct = js.listOnlnLct(al);
		System.out.println("JwController OnlnLctrInfo listOnlnLct.size->"+listOnlnLct.size());
		
		model.addAttribute("listOnlnLct", listOnlnLct);
		System.out.println("JwController OnlnLctrInfo listOnlnLct->"+listOnlnLct);
		
		return "jw/lctr_Info";
	}
	

}
