package com.postGre.bsHive.JwDao;

import java.util.List;

<<<<<<< HEAD
import com.postGre.bsHive.Adto.All_Lctr;

public interface JwDao {

	List<All_Lctr> 			listAllLctr(All_Lctr al);

=======
import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;

public interface JwDao {
	
	// 강의계획서 작성 정보 insert
	int 					insertLctr(LgnEmp lgnEmp2);
	
	// 온라인 강의정보 불러오기 
	List<Onln_Lctr_List> 	listOnlnLctr(Onln_Lctr_List ol);
	
	// 온라인강의 차시정보 불러오기
	List<Onln_Lctr_List> 	listOnlnUnit(Onln_Lctr_List ol);

	


	
>>>>>>> origin/br_test
}
