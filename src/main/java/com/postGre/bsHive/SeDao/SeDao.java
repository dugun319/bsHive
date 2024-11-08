package com.postGre.bsHive.SeDao;

import java.util.List;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_View;

public interface SeDao {

	int onlnTotal();
	List<Onln_Lctr_List> onlnList(Onln_Lctr_List onln_Lctr_List);
	
	Onln_Lctr_List onlnDtl(Integer lctr_Num);
	List<Onln_Lctr_List> lctrviewList(Integer unq_Num, Integer lctr_num);
	int updateLastDtl(int lastDtl, int maxDtl, int unqNum, int unitNum, int lctrNum, int lctrPace);
	int updateMaxDtl(int lastDtl, int unqNum, int unitNum, int lctrNum);
	List<Onln_Lctr_List> chapterList(String vdoId);
	int getMaxDtl(Lctr_View lctr_View);
	








	
}
