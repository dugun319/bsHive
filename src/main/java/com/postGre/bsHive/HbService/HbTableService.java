package com.postGre.bsHive.HbService;

<<<<<<< HEAD
public interface HbTableService {

	void addCourseEval();
=======
import java.util.List;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_Evl;

public interface HbTableService {
	List<String> getEvalQuestion();
	void addAndUpdateEval(Lctr_Evl courseEval, int evl_total);
	List<Onln_Lctr_List> getStntNum(Onln_Lctr_List onln_Lctr_List);
>>>>>>> origin/br_test

}
