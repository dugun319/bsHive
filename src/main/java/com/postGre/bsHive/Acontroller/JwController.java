package com.postGre.bsHive.Acontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Onln_Lctr;
import com.postGre.bsHive.JwService.JwService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/jw")
@Slf4j
public class JwController {
	private final JwService js;
	
	// 강의개설 폼으로 이동
	// 로그인한 교수의 정보에서 고유번호와 이름을 들고 감
	@RequestMapping("/writeFormOnlnLctr")
	public String writeFormOnlnLctr(HttpSession session, Model model) {
		System.out.println("JwController writeFormOnlnLctr Start...");		
		
		LgnEmp lgnEmp = (LgnEmp) session.getAttribute("user"); 
		System.out.println("JwController writeFormOnlnLctr lgnEmp->"+lgnEmp);
		
		// 교수이름
		model.addAttribute("professorName", lgnEmp.getEmp_nm());
		System.out.println("model lgnEmp.getEmp_nm()->"+lgnEmp.getEmp_nm());
		// 교수 고유번호
		model.addAttribute("professorId", lgnEmp.getUnq_num());	
		System.out.println("model lgnEmp.getUnq_num()->"+lgnEmp.getUnq_num());
		
		return "jw/writeFormOnlnLctr";
	}
	
	// 교수가 강의개설 폼에서 강의계획서 작성함
	@RequestMapping("/insertOnlnLctr")
	public String insertOnlnLctr(HttpSession session, LgnEmp lgnEmp2, Model model) {
		System.out.println("JwController insertOnlnLctr Start...");
		
		//1. 교수정보 불러오기
		LgnEmp lgnEmp = (LgnEmp) session.getAttribute("user"); 
		
		// 2. 교수 고유번호, 이름, 강의상태 설정
		Lctr lctr = new Lctr();
		lctr.setUnq_num2(lgnEmp2.getUnq_num()); // professor의 고유번호를 UNQ_NUM2에 설정
		lctr.setAply_type(Integer.toString(0)); // 강의 상태를 '개설 신청' 상태로 설정
		
		
		// 3. 강의계획서 작성 정보 insert 
		int insertLCTR = js.insertLCTR(lgnEmp2);
		System.out.println("JwController insertOnlnLctr insertLCTR->"+insertLCTR);
		
		if(insertLCTR > 0)
			return "jw/lctrInfo";
		
		else {
			model.addAttribute("msg", "입력 실패. 확인 바랍니다.");
			System.out.println("JwController insertOnlnLctr insertLCTR failure->"+insertLCTR);
		}
		
		return "forward:jw/writeFormOnlnLctr";
	}
	
	// 직원이 강의계획서 승인하는 절차

	
	
	// 승인된 강의를 온라인 강의 테이블에 저장하기
	
	
	
	// 온라인 강의정보, 차시정보 불러오기 
	@GetMapping("/lctrInfo")
	public String OnlnLctrInfo(Onln_Lctr_List ol,
							   //HttpSession session,
							   Model model) {
		System.out.println("JwController OnlnLctrInfo Start...");
		
		// 강의정보 목록으로 가져오기
		List<Onln_Lctr_List> listOnlnLct = js.listOnlnLct(ol);
		System.out.println("JwController OnlnLctrInfo listOnlnLct.size->"+listOnlnLct.size());
		
		// 차시정보 목록으로 가져오기
		List<Onln_Lctr_List> UnitOnlnList = js.UnitOnlnList(ol);
		System.out.println("JwController OnlnLctrInfo UnitOnlnList.size()->"+UnitOnlnList.size());
		
		model.addAttribute("listOnlnLct", listOnlnLct);
		System.out.println("JwController OnlnLctrInfo listOnlnLct->"+listOnlnLct);
		
		model.addAttribute("UnitOnlnList", UnitOnlnList);
		System.out.println("JwController OnlnLctrInfo UnitOnlnList->"+UnitOnlnList);
		
		return "jw/lctrInfo";
	}
	

}
