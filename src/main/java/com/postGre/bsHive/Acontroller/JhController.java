package com.postGre.bsHive.Acontroller;

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

import com.postGre.bsHive.Adto.Jh_myClassroomList;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.JhService.JhService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/jh")
public class JhController {

	private final JhService js;

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ로그인ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//로그인 페이지로 이동
	@GetMapping(value = "/loginForm")
	public String loginForm() {
		System.out.println("로그인 화면으로 이동");
		return "jh/loginForm";
	}
	
	//로그인 로직
	@PostMapping(value = "/login")
	public String login(@RequestParam("EML") String eml, 
						@RequestParam("PSWD") String pswd, 
						@RequestParam("MBR_SE") int mbr_se,
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
			
		
		// 임시 비밀번호로 로그인한 경우
		String tempPswd = (String) session.getAttribute("tempPswd");
		System.out.println("@@@@" + tempPswd);
	    if (pswd.equals(tempPswd)) {
	    	session.setAttribute("isTempPswd", true);
	    	session.setAttribute("user", user_table);
	    	int unq_num = user_table.getUnq_num();
			session.setAttribute("eml", eml);
			session.setAttribute("unq_num", unq_num);
			return "redirect:/";
	    }
	    
	    
		// 기존 세션을 무효화하고 새로운 세션 ID 발급
		session.invalidate(); // 기존 세션을 무효화
		session = request.getSession(true); // 새로운 세션 생성

		// 사용자 정보를 세션에 저장
		session.setAttribute("user", user_table); // 새로운 세션에 사용자 정보 저장
		
		User_Table user = (User_Table) session.getAttribute("user"); // 올바른 키로 수정
	    if (user == null) {
	        // 세션에 사용자 정보가 없으면 적절한 처리를 수행 (예: 로그인 페이지로 리다이렉트)
	        return "redirect:/jh/loginForm"; // 예시
	    }
	    
		int unq_num = user_table.getUnq_num();
		session.setAttribute("eml", eml);
		session.setAttribute("unq_num", unq_num);
		System.out.println("나의 고유번호 = "+unq_num);
		
		int mbr_se1  = user_table.getMbr_se();
		session.setAttribute("mbr_se", mbr_se1);
		
		switch (mbr_se1) {
	    case 1:
	        System.out.println("사용자 유형: 학생");
	        break;
	    case 2:
	        System.out.println("사용자 유형: 교수");
	        break;
	    case 3:
	        System.out.println("사용자 유형: 직원");
	        break;
		}
	    	return "redirect:/";
	}

	// 로그아웃
	@GetMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		// 세션 해제
		session.invalidate();
		return "redirect:/";
	}
	
	
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ마이페이지ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	
	// 마이페이지로 이동
	@GetMapping(value = "/myPage")
	public String myPage() {
		System.out.println("로그인 화면으로 이동");
		return "jh/myPage";
	}
	
	//회원정보 변경전 비밀번호 확인하는 페이지로 이동
	@GetMapping(value = "/pwChk")
	public String pwChk() {
		System.out.println("회원정보 변경전 비밀번호 체크");
		return "jh/pwChk";
	}
	
	// 회원정보변경전 비밀번호 확인하는 로직
	@PostMapping(value = "/realPwChk")
	public String realPwChk(@RequestParam("eml") String eml, @RequestParam("pswd") String pswd, Model model) {
		System.out.println("회원정보 변경전 비밀번호 확인");
		User_Table user = new User_Table();
		user = js.realPwChk(eml, pswd);
		
		if (user == null) {
			System.out.println("@@로그인 실패@@");
			model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
			return "jh/pwChk"; 
		}
		System.out.println("@@로그인 성공@@");
		System.out.println("@@유저정보"+ user);
		model.addAttribute("user", user);
		return "jh/updateMyInfor";
	}
	
	// 회원정보변경 로직
	@PostMapping(value = "/updateMyInfor")
	public String updateMyInfor(@RequestParam("nm") String nm, @RequestParam("telno") String telno, 
								@RequestParam("zip") String zip, @RequestParam("addr") String addr, 
								@RequestParam("daddr") String daddr, @RequestParam("unq_num") int unq_num) {
		System.out.println("회원정보변경 실행");
		User_Table user = new User_Table();
		user.setUnq_num(unq_num);
		user.setNm(nm);
		user.setTelno(telno);
		user.setZip(zip);
		user.setAddr(addr);
		user.setDaddr(daddr);
		js.updateMyInfor(user);
		return "jh/myPage";
	}
	
	// 비밀번호 변경하는 페이지로 이동
	@GetMapping(value = "/changePassword")
	public String changePassword() {
		System.out.println("비밀번호 변경하는 페이지로 이동");
		return "jh/changePassword";
	}
	
	//비밀번호 변경하는 로직
	@PostMapping(value = "/realchangePassword")
	public String realchangePassword(@RequestParam("eml") String eml, @RequestParam("pswd") String pswd, 
			                         @RequestParam("new_pswd") String new_pswd, Model model) {
		System.out.println("비밀번호 변경하기");
		User_Table user = new User_Table();
		user = js.realPwChk(eml, pswd);
		
		if (user == null) {
			System.out.println("@@로그인 실패@@");
			model.addAttribute("loginError", "비밀번호가 일치하지 않습니다.");
			return "jh/changePassword"; 
		}
		js.realchangePassword(eml, new_pswd);
		System.out.println("비밀번호 변경완료");
		return "jh/myPage";
	}
	
	// (학생)수강신청현황 페이지로 이동
	@GetMapping(value = "/registeredClassStudent")
	public String classRegistrationStatus(HttpSession session, Model model) {
		System.out.println("수강신청현황 페이지로 이동");
		int unq_num = (int) session.getAttribute("unq_num");
		List<Map<String, Object>> registeredClassStudent = js.classRegistrationStatus(unq_num);
		
		System.out.println("수강신청현황 갯수 = " + registeredClassStudent.size());
		model.addAttribute("regClaStdt", registeredClassStudent);
		return "jh/registeredClassStudent";
	}
	
	// (교수)강의신청현황 페이지로 이동
	@GetMapping(value = "/registeredClassProfessor")
	public String lectureRegistrationStatus(HttpSession session, Model model) {
		System.out.println("강의신청현황 페이지로 이동");
		int unq_num = (int) session.getAttribute("unq_num");
		List<Map<String, Object>> registeredClassProfessor = js.lectureRegistrationStatus(unq_num);
		
		System.out.println("강의신청현황 갯수 = " + registeredClassProfessor.size());
		model.addAttribute("regClaProf", registeredClassProfessor);
		return "jh/registeredClassProfessor";
	}
	
	//학생의 온라인 강의 리스트 실행
	@GetMapping(value = "/myClassroom")
	public String myOnlineClass(Model model,HttpSession session) {
		System.out.println("나의 온라인 리스트 실행");

		int unq_num = (int) session.getAttribute("unq_num");
		System.out.println("@@@@나의 고유번호@@@@"+unq_num);
		
		List<Jh_myClassroomList> myClass = js.myOnlineClass(unq_num);
		System.out.println("나의 온라인 강의목록 = "+ myClass);
		
		boolean myOnlineClass = true;
		model.addAttribute("myOnlineClass", myOnlineClass);
		model.addAttribute("myClass", myClass);
		return "jh/myClassroom";
	}
	
	//학생의 오프라인 강의 리스트 실행
	@GetMapping(value = "/myOfflineClass")
	public String myOfflineClass(Model model,HttpSession session) {
		System.out.println("나의 오프라인 리스트 실행");

		int unq_num = (int) session.getAttribute("unq_num");
		System.out.println("@@@@나의 고유번호@@@@"+unq_num);
		
		List<Jh_myClassroomList> myClass = js.myOfflineClass(unq_num);
		System.out.println("나의 오프라인 강의목록 = "+ myClass);
		
		boolean myOfflineClass = true;
		model.addAttribute("myOfflineClass", myOfflineClass);
		model.addAttribute("myClass", myClass);
		return "jh/myClassroom";
	}
	
	//교수의 온라인 강의 리스트 실행
	@GetMapping(value = "/myProfClassroom")
	public String myProfClassroom(Model model,HttpSession session) {
		System.out.println("나의 온라인 리스트 실행");
		
		int unq_num = (int) session.getAttribute("unq_num");
		System.out.println("@@@@나의 고유번호@@@@"+unq_num);
		
		List<Jh_myClassroomList> myClass = js.myProfOnlineClass(unq_num);
		System.out.println("나의 온라인 강의목록 = "+ myClass);
		
		boolean myOnlineClass = true;
		model.addAttribute("myOnlineClass", myOnlineClass);
		model.addAttribute("myClass", myClass);
		return "jh/myProfClassroom";
	}
	
	//교수의 오프라인 강의 리스트 실행
	@GetMapping(value = "/myProfOfflineClass")
	public String myProfOfflineClass(Model model,HttpSession session) {
		System.out.println("나의 오프라인 리스트 실행");

		int unq_num = (int) session.getAttribute("unq_num");
		System.out.println("@@@@나의 고유번호@@@@"+unq_num);
		
		List<Jh_myClassroomList> myClass = js.myProfOfflineClass(unq_num);
		System.out.println("나의 오프라인 강의목록 = "+ myClass);
		
		boolean myOfflineClass = true;
		model.addAttribute("myOfflineClass", myOfflineClass);
		model.addAttribute("myClass", myClass);
		return "jh/myProfClassroom";
	}
	
	
	
	
	
	
	/* 내가 등록한 글 로직 */
	
	
	
	
	
	
	
	
	// 회원탈퇴 페이지로 이동
	@GetMapping(value = "/deleteAccount")
	public String deleteAccount() {
		System.out.println("회원탈퇴하는 페이지로 이동");
		return "jh/deleteAccount";
	}

	// 회원탈퇴하기 
	@PostMapping(value = "/realDeleteAccount")
	public String realDeleteAccount(@RequestParam("eml") String eml, @RequestParam("pswd") String pswd, 
									HttpSession session, Model model) {
		System.out.println("회원탈퇴하기");
		User_Table user = new User_Table();
		user = js.realPwChk(eml, pswd);
		
		if (user == null) {
			System.out.println("@@로그인 실패@@");
			model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
			return "jh/pwChk"; 
		}
		js.realDeleteAccount(eml);
		System.out.println("탈퇴완료");
		session.invalidate();
		return "jh/deleteSuccess";
	}
	
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ회원가입ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//회원가입 유저타입 선택하는 페이지로 이동
	@GetMapping(value = "/signUpSelect")
	public String signUpSelect() {
		System.out.println("회원가입 유저타입 선택");
		return "jh/signUpSelect";
	}
	
	//회원가입 이용약관 동의 페이지로 이동
	@GetMapping(value = "/joinAgree")
	public String joinAgree_Emp() {
		System.out.println("회원가입 페이지 화면으로 이동");
		return "jh/joinAgree";
	}
	
	//회원가입 회원정보 입력 페이지로 이동
	@GetMapping(value = "/signUpForm")
	public String sign_up_form() {
		System.out.println("회원가입 회원정보 입력 페이지로 이동");
		return "jh/signUpForm";
	}
		
	//회원가입 이메일 중복체크 로직
	@PostMapping("/checkEmailDuplicate")
	@ResponseBody
	public Map<String, Boolean> checkEmailDuplicate(@RequestParam("email") String email) {
	    Map<String, Boolean> response = new HashMap<>();
	    boolean isDuplicate = js.checkEmailDuplicate(email);
	    response.put("isDuplicate", isDuplicate);
	    return response;
	}
	
	//회원가입 메일 인증번호 보내기
	@PostMapping("/sendAuthCode")
	@ResponseBody
	public Map<String, String> sendAuthCode(HttpServletRequest request, @RequestParam("user_email") String user_email) {
		Map<String, String> response = new HashMap<>();
		System.out.println("이메일 인증번호 발송시작");
		try {
			String authCode = js.sendAuthCode(user_email);
			HttpSession session = request.getSession();
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
	public String join(@RequestParam("mbr_se") int mbr_se, 
					   @ModelAttribute User_Table user, Model model) {
	    user.setMbr_se(mbr_se);
	    System.out.println("@@@mbr_se@@@ =" + mbr_se);
	    int join = js.join(user);
	    if (join > 0) {
	        return "jh/joinSuccess";
	    } else {
	        model.addAttribute("msg", "회원가입 실패");
	        return "/";
	    }
	}

	// 회원가입 완료 페이지로 이동
	@GetMapping(value = "/joinSuccess")
	public String joinSuccess() {
		System.out.println("회원가입 완료 화면으로 이동");
		return "jh/joinSuccess";
	}
	
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ비밀번호 찾기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// 비밀번호 찾기 페이지로 이동
	@GetMapping(value = "/findPw")
	public String findId() {
		System.out.println("비밀번호 찾기 페이지로 이동");
		return "jh/findPw";
	}
		
	// 비밀번호 찾기 로직
	@PostMapping(value = "/realFindPw")
	public String realFindPw(@RequestParam("eml") String eml, @RequestParam("nm") String nm, Model model, HttpSession session) {
		User_Table user = new User_Table();
		user.setEml(eml);
		user.setNm(nm);
		int result = js.findAccount(user);
		System.out.println("찾은 계정 갯수 = " + result);

		if (result == 1) {
			//임시 비밀번호 생성 후 업데이트 
			String tempPswd = js.createtempPswd(eml);
			session.setAttribute("tempPswd", tempPswd);
			System.out.println("생성된 임시 비밀번호 = " + tempPswd);
			
			//임시 비밀번호 이메일로 발송
			int result1 = js.sendTempPswd(eml, tempPswd);
			System.out.println("임시 비밀번호 이메일 발송 결과 = " + result1);
			if (result1 == 1) {
				return "jh/findPwResult";
			} else {
				return "jh/findPw";
			}	
		} else {
			model.addAttribute("userCheckError", "존재하지 않는 계정입니다. 다시 입력해주세요");
			return "jh/findPw";
		}
	}
	
	@PostMapping("/clearTempPswdFlag")
	@ResponseBody
	public void clearTempPswdFlag(HttpSession session) {
	    session.removeAttribute("isTempPswd");
	}

	
}
