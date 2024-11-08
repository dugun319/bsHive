package com.postGre.bsHive.HsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.Asmt_Sbmsn;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.HsDao.HsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HsServiceImpl implements HsService {
	
	@Autowired
	private final HsDao hsd;
	
	// 강의번호/ 강의명 불러오기
	@Override
	public Lctr callLctr_num(int lctr_num) {
		System.out.println("HsServiceImpl callLctr_num Start...");
		Lctr lctr = hsd.callLctr_num(lctr_num);
		System.out.println("HsServiceImpl callLctr_num lctr -> " +lctr);
		System.out.println("HsServiceImpl callLctr_num After...");
		return lctr;
	}
	
	// 강의계획서 조회
	@Override
	public List<Hs_Lec> lecTotSche(int lctr_num) {
		System.out.println("HsServiceImpl lecTotSche Start...");
		List<Hs_Lec> lec_TotSche = hsd.lecTotSche(lctr_num);
		System.out.println("HsServiceImpl lecTotSche lec_TotSche -> " +lec_TotSche);
		System.out.println("HsServiceImpl lecTotSche After...");
		return lec_TotSche;
	}
	
	// 주차별 강의 계획서 List
	@Override
	public List<Hs_Lec> lecWeekSchedule(int lctr_num) {
		System.out.println("HsServiceImpl lecWeekSchedule Start...");
		List<Hs_Lec> lctrWeek = hsd.lecWeekSchedule(lctr_num);
		System.out.println("HsServiceImpl lecWeekSchedule lctrWeek -> " +lctrWeek);
		System.out.println("HsServiceImpl lecWeekSchedule After...");
		return lctrWeek;
	}
	
	//강의 주차 option 불러오는 list
	@Override
	public List<Hs_Attend> WeekList(int lctr_num) {
		System.out.println("HsServiceImpl WeekList Start...");
		List<Hs_Attend> weekList = hsd.WeekList(lctr_num);
		System.out.println("HsServiceImpl WeekList weekList -> " +weekList);
		System.out.println("HsServiceImpl WeekList After...");
		return weekList;
	}
	
	//강의 주차 별 학생 출석입력 list
	@Override
	public List<Hs_Attend> lecWeekProf(Hs_Attend attend) {
		System.out.println("HsServiceImpl lecWeekProf Start...");
		List<Hs_Attend> weekList = hsd.lecWeekProf(attend);
		System.out.println("HsServiceImpl lecWeekProf weekList -> " +weekList);
		System.out.println("HsServiceImpl lecWeekProf After...");
		return weekList;
	}
	
	//교수가 강의 출결 등록
	@Override
	public int updateAttProf(Hs_Attend attendL) {
		System.out.println("HsServiceImpl updateAttProf Start...");
		int updateCount = hsd.updateAttProf(attendL);
		System.out.println("HsServiceImpl updateAttProf attendL -> " +attendL);
		System.out.println("HsServiceImpl updateAttProf After...");
		return updateCount;
	}
	
	// 학생 주차별 출결상태 불러오기
	@Override
	public List<Hs_Attend> lecStdAttend(Hs_Attend attend) {
		System.out.println("HsServiceImpl lecStdAttend Start...");
		System.out.println("HsServiceImpl lecStdAttend atndc_state1 -> " +attend);
		List<Hs_Attend> atndc_state = hsd.lecStdAttend(attend);
		System.out.println("HsServiceImpl lecStdAttend atndc_state2 -> " +atndc_state);
		System.out.println("HsServiceImpl lecStdAttend After...");
		return atndc_state;
	}
	
	// 교수 과제목록 List
	@Override
	public List<Hs_Assignment> assProfList(int lctr_num) {
		System.out.println("HsServiceImpl assProfList Start...");
		List<Hs_Assignment> assProfList = hsd.assProfList(lctr_num);
		System.out.println("HsServiceImpl assProfList assProfList-> "+assProfList);
		
		System.out.println("HsServiceImpl assProfList After...");
		return assProfList;
	}
	
	// 과제 입력 시 기본 정보 불러오기 (교수)
	@Override
	public Hs_Assignment AssignInfoProf(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl AssignInfoProf Start...");
		Hs_Assignment profAssign = hsd.AssignInfoProf(hsAss);
		System.out.println("HsServiceImpl AssignInfoProf -> " +profAssign);
		System.out.println("HsServiceImpl AssignInfoProf After...");
		return profAssign;
	}
	
	
	// 파일그룹 insert
	@Override
	public int gongInsert(Hs_Assignment assign) {
		System.out.println("HsServiceImpl gongInsert Start...");
		int result = hsd.gongInsert(assign);
		System.out.println("HsServiceImpl gongInsert After...");
		return result;
	}

	@Override
	public int ProfAsmtInsert(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl ProfAsmtInsert Start...");
		
		int result = hsd.ProfAsmtInsert(hsAss);
		System.out.println("HsServiceImpl ProfAsmtInsert After...");
		return result;
	}
	
	// 내 강의실 과제 수정 form (교수)
	@Override
	public int asmtUpdate(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl asmtUpdate Start...");
		int updateCount = hsd.asmtUpdate(hsAss);
		
		System.out.println("HsServiceImpl asmtUpdate After...");
		return updateCount;
	}
	
	//학생본인의 과제 제출여부 확인해보기
	@Override
	public boolean checkData(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl checkData Start...");
		boolean checkData = hsd.checkData(hsAss);
		System.out.println("HsServiceImpl checkData After...");
		return checkData;
	}
	
	//과제제출 시 필요한 기본정보 info 불러오기
	@Override
	public Hs_Assignment stdAssignment(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl stdAssignment Start...");
		Hs_Assignment hsAssignWrite = hsd.stdAssignment(hsAss);
		System.out.println("HsServiceImpl stdAssignment After...");
		return null;
	}
	
	
	
	

	

}
