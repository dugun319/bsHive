package com.postGre.bsHive.Acontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postGre.bsHive.SeService.SeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SeController {
	
	private final SeService ss;
	
	@GetMapping(value = "/main") public String all() {
	 
	return "se/OnlnForm"; }	
	
}
