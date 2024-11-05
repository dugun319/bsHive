package com.postGre.bsHive.MnDao;

import java.util.List;

import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.Pst;

public interface MnDao {

	List<Crans_Qitem> selAll();

	List<Pst> pstAllList();

	List<Mn_LctrAplyOflWeek> lctrAplyJoinAllList();
	
}
