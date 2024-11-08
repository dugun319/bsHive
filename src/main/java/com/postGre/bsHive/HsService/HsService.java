package com.postGre.bsHive.HsService;

import java.util.List;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.Asmt_Sbmsn;
import com.postGre.bsHive.Amodel.Lctr;

public interface HsService {
	// 강의번호/ 강의명 불러오기
	Lctr callLctr_num(int lctr_num);
	
	// 강의계획서 조회
	List<Hs_Lec> lecTotSche(int lctr_num);
	// 주차별 강의 계획서 List
	List<Hs_Lec> lecWeekSchedule(int lctr_num);
	
	//강의 주차 option 불러오는 list
	List<Hs_Attend> WeekList(int lctr_num);
	//강의 주차 별 학생 출석입력 list
	List<Hs_Attend> lecWeekProf(Hs_Attend attend);
	//교수가 강의 출결 등록
	int updateAttProf(Hs_Attend attendL);
	// 학생 주차별 출결상태 불러오기
	List<Hs_Attend> lecStdAttend(Hs_Attend attend);
	
	// 교수 과제목록 List
	List<Hs_Assignment> assProfList(int lctr_num);
	// 과제 입력 시 기본 정보 불러오기 (교수)
	Hs_Assignment AssignInfoProf(Hs_Assignment hsAss);
	
	// 파일그룹 insert
	int gongInsert(Hs_Assignment assign);
	// 파일그룹 insert
	int ProfAsmtInsert(Hs_Assignment hsAss);
	
	// 내 강의실 과제 수정 form (교수)
	int asmtUpdate(Hs_Assignment hsAss);
	//학생본인의 과제 제출여부 확인해보기
	boolean checkData(Hs_Assignment hsAss);
	//과제제출 시 필요한 기본정보 info 불러오기
	Hs_Assignment stdAssignment(Hs_Assignment hsAss);
	

	
	
	

}
