package com.postGre.bsHive.MnService;

import java.util.List;

import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.Pst;


public interface MnService {

	List<Crans_Qitem> selAll();

	List<Pst> pstList();

	List<Mn_LctrAplyOflWeek> joinLctrAplyAllList();

}
