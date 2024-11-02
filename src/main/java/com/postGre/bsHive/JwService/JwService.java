package com.postGre.bsHive.JwService;

import java.util.List;

<<<<<<< HEAD
import com.postGre.bsHive.Adto.All_Lctr;

public interface JwService {

	// 온라인 강의정보 불러오기 
	List<All_Lctr> listOnlnLct(All_Lctr al);
=======
import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;

public interface JwService {

	// 강의계획서 작성 정보 insert 
	int 					insertLCTR(LgnEmp lgnEmp2);
	
	// 온라인 강의정보 불러오기 
	List<Onln_Lctr_List> 	listOnlnLct(Onln_Lctr_List ol);

	// 온라인강의 차시정보 불러오기
	List<Onln_Lctr_List> 	UnitOnlnList(Onln_Lctr_List ol);



	
>>>>>>> origin/br_test
}
