package com.postGre.bsHive.KhDao;

import java.util.List;

import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Amodel.Lgn;


public interface KhTableDao {

	List<Kh_PrdocList>		getTestTableList();
	List<Kh_PrdocList> 		getPrdocList(Kh_PrdocList prList);
	int 					getTotPrdocList(Kh_PrdocList prList);
	int 					getTotStdntList(Kh_StdntList stList);
	List<Kh_StdntList> 		getStdntList(Kh_StdntList stList);
	int 					updateLgnDelYn(Lgn lgn);			
}
