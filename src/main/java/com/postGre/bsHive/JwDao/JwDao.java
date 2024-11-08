package com.postGre.bsHive.JwDao;

import java.util.List;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Onln_Lctr;
import com.postGre.bsHive.Amodel.Syllabus_Unit;

public interface JwDao {
	
	// 교수정보 불러오기
	User_Table              getUserTable(int unq_num);
	
	// 온라인강의 TBL insert 
	int 					insertLCTR(Onln_Lctr onln_lctr);
	
	// DB에 입력된 강의번호 가져오기
	int 					getLctrNum();
	
	// 온라인 콘텐츠 TBL insert
	int 					insertOnlnCont(Syllabus_Unit syllabus_Unit);



}
