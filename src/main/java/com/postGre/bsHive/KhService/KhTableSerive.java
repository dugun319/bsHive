package com.postGre.bsHive.KhService;

import java.util.List;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_Lctrm;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_ScholarshipList;
import com.postGre.bsHive.Adto.Kh_SortCode;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Adto.Kh_pstList;
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
	
	
	// LCTRM
	
	List<Kh_Lctrm> 			getLctrmList(String yearAndSemester);
	

	// PRDOC
	List<Kh_PrdocList>		getPrdocList(Kh_PrdocList prList);
	int 					getTotPrdocList(Kh_PrdocList prList);
	Kh_PrdocList 			getPrdocDetail(Kh_PrdocList prList);
	void 					updateIsuueDate(Kh_PrdocList prList);
	
	//Scholarship
	int 					getTotSchList(Kh_ScholarshipList sList);
	Kh_ScholarshipList 		getSchDetail(Kh_ScholarshipList sList);
	void 					insertSchDetail(Kh_ScholarshipList schDetail);
	List<Kh_ScholarshipList> getSchList(Kh_ScholarshipList sList);
	String 					getScholarahipImgPath(Kh_ScholarshipList schList);
	void 					updateGiveStss(Kh_ScholarshipList schList);
	
	// Lecture
	
	int 					getTotLctrList(Kh_LctrList lcList);
	List<Kh_LctrList> 		getLctrList(Kh_LctrList lcList);
	Kh_LctrList 			getLctrDetail(Kh_LctrList lcList);
	void					sendRequest(Kh_LctrList lctrDetail);
	void 					updateAplyType(Kh_LctrList lcList);
	void 					openLecture(Kh_LctrList lcList);

	// Board
	int 					getTotBoardList(Kh_pstList pList);
	List<Kh_pstList> 		getBoardList(Kh_pstList pList);
	void 					updateDelYnPst(Kh_pstList pList);

	String 					getSortContent(Kh_SortCode sCode);

	
	
}
