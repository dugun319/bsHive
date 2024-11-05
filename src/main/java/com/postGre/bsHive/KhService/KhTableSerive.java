package com.postGre.bsHive.KhService;

import java.util.List;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Amodel.Lgn;

public interface KhTableSerive {

	List<Kh_PrdocList> 		getTestTableList();
	
	// 삭제여부 변경
	int 					updateLgnDelYn(Lgn lgn);
	
	// Student
	int 					getTotStdntList(Kh_StdntList stList);
	List<Kh_StdntList> 		getStdntList(Kh_StdntList stList);
		
	// EMP(Professor & employee)
	int 					getTotEmpList(Kh_EmpList eList);
	List<Kh_EmpList> 		getEmpList(Kh_EmpList eList);
	

	// PRDOC
	List<Kh_PrdocList>		getPrdocList(Kh_PrdocList prList);
	int 					getTotPrdocList(Kh_PrdocList prList);
	
	
	// Lecture
	
	int 					getTotLctrList(Kh_LctrList lcList);
	List<Kh_LctrList> 		getLctrList(Kh_LctrList lcList);
	Kh_LctrList 			getLctrDetail(Kh_LctrList lcList);
	void					sendRequest(Kh_LctrList lctrDetail);
	void 					updateAplyType(Kh_LctrList lcList);
	void 					openLecture(Kh_LctrList lcList);
}
