package com.postGre.bsHive.HsService;

import java.util.List;

import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;

public interface HsService {
	// 강의계획서 조회
	Hs_Lec lecTotSche(int lctr_num);
	// 주차별 강의 계획서 List
	List<Hs_Lec> lecWeekSchedule(int lctr_num);
	
	//강의 주차 option 불러오는 list
	List<Hs_Attend> WeekList(int lctr_num);
	//강의 주차 별 학생 출석입력 list
	List<Hs_Attend> lecWeekProf(Hs_Attend attend);
	//교수가 강의 출결 등록
	int updateAttProf(Hs_Attend attendL);
	
	

}
