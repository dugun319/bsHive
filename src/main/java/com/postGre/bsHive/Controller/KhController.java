package com.postGre.bsHive.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import com.postGre.bsHive.dto.Paper;
import com.postGre.bsHive.service.kh.KhTableSerive;


@Slf4j
@Controller
@RequiredArgsConstructor
public class KhController {
	
	private final KhTableSerive khTableSerive;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		
		log.info("KhController main() is called");
		
		// List<Paper> paperList = khTableSerive.getTestTableList();
		List<Paper> paperList	= khTableSerive.getPaperTableList();
		
		model.addAttribute("paperList", paperList);
		
		return "main";
	}
}
