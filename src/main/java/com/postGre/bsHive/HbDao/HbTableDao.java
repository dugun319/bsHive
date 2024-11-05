package com.postGre.bsHive.HbDao;

import java.util.List;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_Evl;


public interface HbTableDao {
	List<String> getEvalQuestion();
	void addCourseEval(Lctr_Evl courseEval);
	void submitQuestion(String evl_num);
	List<Onln_Lctr_List> getStntNum(Onln_Lctr_List onln_Lctr_List);

}
