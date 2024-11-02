package com.postGre.bsHive.MnService;

import java.util.List;

import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.postGre.bsHive.Amodel.Crans_Qitem;
=======
import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.Pst;
>>>>>>> origin/br_test
import com.postGre.bsHive.MnDao.MnDao;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MnServiceImpl implements MnService {
	
	private final MnDao md;
	
	@Override
	public List<Crans_Qitem> selAll() {
		System.out.println("selAll start...");
		return md.selAll();
	}
<<<<<<< HEAD
=======

	@Override
	public List<Pst> pstList() {
		System.out.println("MnServiceImpl pstList start...");
		return md.pstAllList();
	}

	@Override
	public List<Mn_LctrAplyOflWeek> joinLctrAplyAllList() {
		System.out.println("MnServiceImpl joinLctrAplyAllList start...");
		return md.lctrAplyJoinAllList();
	}
>>>>>>> origin/br_test
	
}
