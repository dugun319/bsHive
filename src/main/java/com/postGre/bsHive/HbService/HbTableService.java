package com.postGre.bsHive.HbService;

import java.util.List;
import java.util.Map;

import com.postGre.bsHive.Adto.Hb_Evl;

public interface HbTableService {
	List<Hb_Evl> getEvalQuestion(Hb_Evl hb_Evl);
	//Hb_Evl addAndUpdateEval(Hb_Evl hb_Evl);
	
	// 학생용 평가 확인 페이지
	List<Hb_Evl> getAllStudentsList();
	Hb_Evl addAndUpdateEval(Hb_Evl hb_Evl, Map<String, Integer> evaluationScores);

	List<Hb_Evl> getAllClass(Hb_Evl hb_Evl);
	//강의 번호, 강의 이름
	String getClassName();

	Hb_Evl getDetailGrade(int unqNum, int lctrNum);

}
