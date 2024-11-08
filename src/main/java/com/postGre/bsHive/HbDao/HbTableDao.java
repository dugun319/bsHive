package com.postGre.bsHive.HbDao;

import java.util.List;

import com.postGre.bsHive.Adto.Hb_Evl;


public interface HbTableDao {
	void addCourseEval(int detnum);
	List<Hb_Evl> getCourseEval(Hb_Evl hb_Evl);
	
	// 학생의 성적 확인
	List<Hb_Evl> getstudentClassList();
	List<Hb_Evl> getAllClass(Hb_Evl hb_Evl);
	Hb_Evl getDetailGrade(int unqNum, int lctrNum);
	
	// 강의 정보 확인
	String getClassName();

}
