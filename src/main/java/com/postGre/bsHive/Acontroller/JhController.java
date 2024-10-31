package com.postGre.bsHive.Acontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postGre.bsHive.JhService.JhService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
/* @RequestMapping(value = "/view_jh") */
public class JhController {

	private final JhService js;

	//로그인 페이지로 이동
	@GetMapping(value = "/loginForm")
	public String loginForm() {
		System.out.println("로그인 화면으로 이동");
		return "jh/loginForm";
	}
	
	//로그인 로직
	@PostMapping(value = "/login")
	public String login(@RequestParam("user_id") String user_id, 
						@RequestParam("user_pw") String user_pw, 
						Model model, HttpSession session) {
		System.out.println("로그인 실행");
		
		return "jh/loginForm";
	}
	
	//회원정보 변경전 비밀번호 체크
	@GetMapping(value = "/pw_chk")
	public String pw_chk() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/pw_chk";
	}
	
	//회원가입 이용약관 동의 페이지로 이동
	@GetMapping(value = "/joinAgree")
	public String joinAgree() {
		System.out.println("회원가입 페이지 화면으로 이동");
		return "jh/joinAgree";
	}

	//회원가입 회원정보 입력 페이지로 이동
	@GetMapping(value = "/sign_up_form")
	public String sign_up_form() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/sign_up_form";
	}
	
	
}
