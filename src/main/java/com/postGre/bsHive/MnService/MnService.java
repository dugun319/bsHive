package com.postGre.bsHive.MnService;

import java.util.List;

import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Adto.Mn_LctrInfoPage;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Lctr_Week;
import com.postGre.bsHive.Amodel.Lctrm;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.Amodel.Sort_Code;
import com.postGre.bsHive.Amodel.Stdnt;


public interface MnService {

	List<Crans_Qitem> selAll();

	List<Pst> pstList();

	List<Mn_LctrAplyOflWeek> joinLctrAplyAllList(int startIndex, int endIndex);

	List<Mn_LctrAplyOflWeek> searchLctrAplyList(String keyword);

	List<Lctrm> lctrmListAll();

	List<Sort_Code> selectLctrm();

	List<Lctrm> getLcrtmRoomNumber(String roomNumber);

	int subminPageLctr(Mn_LctrAplyOflWeek lctrAplyOflWeek, List<Lctr_Week> lctrWeeks);

	Stdnt getUserTable(int userSess);

	int lctrListCount();

	List<Mn_LctrInfoPage> selGetLctrList(String lctr_num);

	List<Lctr_Week> selWeekList(String lctr_num);

	List<File> imgInsertList(List<File> fileList);

}
