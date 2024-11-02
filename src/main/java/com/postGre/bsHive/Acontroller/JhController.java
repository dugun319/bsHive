package com.postGre.bsHive.Acontroller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr_Aply;
import com.postGre.bsHive.Amodel.Lgn;
>>>>>>> origin/br_test
import com.postGre.bsHive.JhService.JhService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
<<<<<<< HEAD
/* @RequestMapping(value = "/view_jh") */
=======
@RequestMapping(value = "/jh")
>>>>>>> origin/br_test
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
<<<<<<< HEAD
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
	
	
=======
	public String login(@RequestParam("EML") String eml, 
						@RequestParam("PSWD") String pswd, 
						@RequestParam("MBR_SE") String mbr_se,
						Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("로그인 시도");
		User_Table user_table = new User_Table();
		user_table = js.login(eml, pswd, mbr_se);
		
		
		if (user_table == null) {
			System.out.println("로그인 실패");
			model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
			model.addAttribute("eml", eml); // 입력한 아이디를 다시 전달
			return "jh/loginForm"; 
		}
		
		if (user_table.getDel_yn() == 1) {
			System.out.println("탈퇴한 계정임");
			model.addAttribute("loginError", "탈퇴한 회원입니다.");
			model.addAttribute("eml", eml); // 입력한 아이디를 다시 전달
			return "jh/loginForm"; 
		}
		
		// 기존 세션을 무효화하고 새로운 세션 ID 발급
		session.invalidate(); // 기존 세션을 무효화
		session = request.getSession(true); // 새로운 세션 생성

		// 사용자 정보를 세션에 저장
		session.setAttribute("user", user_table); // 새로운 세션에 사용자 정보 저장

		int unq_num = user_table.getUnq_num();
		session.setAttribute("unq_num", unq_num);
		System.out.println("유니크 넘버 = "+unq_num);
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		// 세션 해제
		session.invalidate();
		return "redirect:/";
	}
	
	// 마이페이지로 이동
	@GetMapping(value = "/myPage")
	public String myPage() {
		System.out.println("로그인 화면으로 이동");
		return "jh/myPage";
	}
	
	//회원정보 변경전 비밀번호 체크
	@GetMapping(value = "/pwChk")
	public String pwChk() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/pwChk";
	}
	


	//회원가입 유저타입 선택
	@GetMapping(value = "/signUpSelect")
	public String signUpSelect() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/signUpSelect";
	}
	
	//회원가입 이용약관 동의 페이지로 이동
	@GetMapping(value = "/joinAgreeStdnt")
	public String joinAgree_Stdnt() {
		System.out.println("회원가입 페이지 화면으로 이동");
		return "jh/joinAgreeStdnt";
	}
	
	//회원가입 이용약관 동의 페이지로 이동
	@GetMapping(value = "/joinAgreeEmp")
	public String joinAgree_Emp() {
		System.out.println("회원가입 페이지 화면으로 이동");
		return "jh/joinAgreeEmp";
	}
	
	//회원가입 회원정보 입력 페이지로 이동
	@GetMapping(value = "/signUpForm")
	public String sign_up_form() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/signUpForm";
	}
	
	
	
	//회원가입 메일 인증번호 보내기
	@PostMapping("/sendAuthCode")
	@ResponseBody
	public Map<String, String> sendAuthCode(HttpServletRequest request, @RequestParam("user_email") String user_email) {
		Map<String, String> response = new HashMap<>();
		System.out.println("JmController.sendAuthCode() start....");
		System.out.println("JmController.sendAuthCode() user_email >>> " + user_email);
		try {
			String authCode = js.sendAuthCode(user_email);
			HttpSession session = request.getSession();
			System.out.println("JmController.sendAuthCode() 트라이 start....");
			session.setAttribute("authCode", authCode);
			session.setMaxInactiveInterval(300);
			response.put("message", "이메일로 인증번호가 전송되었습니다.");

		} catch (Exception e) {
			response.put("message", "이메일 전송에 실패했습니다.");
		}
		return response;
	}

	// 인증번호 확인 로직
	@PostMapping("/verifyAuthCode")
	public ResponseEntity<?> verifyAuthCode(HttpSession session, @RequestParam("auth_code") String inputCode) {
		// 세션에 저장된 인증번호 가져오기
		String sessionAuthCode = (String) session.getAttribute("authCode");

		// 입력한 인증번호와 세션의 인증번호를 비교
		if (sessionAuthCode != null && sessionAuthCode.equals(inputCode)) {
			return ResponseEntity.ok(Map.of("valid", true));
		} else {
			return ResponseEntity.ok(Map.of("valid", false));
		}
	}
	
	// 회원가입
	@PostMapping("/join")
	public String join(@RequestParam("mbr_se") String mbr_se, 
					   @ModelAttribute User_Table user, Model model) {
	    user.setMbr_se(mbr_se);
	    System.out.println("@@@@@@@@@@@@@@@@@@mbr_se =" + mbr_se);
	    int join = js.join(user);
	    if (join > 0) {
	        return "jh/joinSuccess";
	    } else {
	        model.addAttribute("msg", "회원가입에 실패하셨습니다");
	        return "/";
	    }
	}

	// 회원가입 완료 페이지
	@GetMapping(value = "/joinSuccess")
	public String joinSuccess() {
		System.out.println("회원가입 완료 화면으로 이동");
		return "jh/joinSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	//나의 강의실 페이지로 이동
	@GetMapping(value = "/myClassroom")
	public String myClassroom(Model model,HttpSession session) {
		System.out.println("나의 강의실로 이동");

		int unq_num = (int) session.getAttribute("unq_num");
		System.out.println("@@@나의강의실 유니크 넘버@@@"+unq_num);
		
		List<Lctr_Aply> myClass = js.myClass(unq_num);

		model.addAttribute("myClass", myClass);
		return "jh/myClassroom";
	}
	
	
	
>>>>>>> origin/br_test
}
