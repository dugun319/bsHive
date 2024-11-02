package com.postGre.bsHive.MnDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.postGre.bsHive.Amodel.Crans_Qitem;
=======
import com.postGre.bsHive.Adto.Mn_LctrAplyOflWeek;
import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.Amodel.Pst;
>>>>>>> origin/br_test

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MnDaoImpl implements MnDao {
	private final SqlSession session;
	
	@Override
	public List<Crans_Qitem> selAll() {
		List<Crans_Qitem> critem = null;
		System.out.println("selAll start//////////////");
		try {
<<<<<<< HEAD
			critem = session.selectList("com.postGre.bsHive.Mapper.Mnxml.selAll");
=======
			critem = session.selectList("com.postGre.bsHive.Mapper.mnMapper.selAll");
>>>>>>> origin/br_test
			System.out.println("daoCrit >>>>>>>>>>" + critem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return critem;
	}
<<<<<<< HEAD
=======

	@Override
	public List<Pst> pstAllList() {
		List<Pst> listPst = null;
		System.out.println("MnDaoImpl pstAllList start...");
		
		try {
			listPst = session.selectList("com.postGre.bsHive.Mapper.mnMapper.selPstAllList");
		} catch (Exception e) {
			System.out.println("MnDaoImpl pstAllList e.getMessage() >> " + e.getMessage());
		}
		
		return listPst;
	}

	@Override
	public List<Mn_LctrAplyOflWeek> lctrAplyJoinAllList() {
		List<Mn_LctrAplyOflWeek> mnList = null;
		System.out.println("MnDaoImpl lctrAplyJoinAllList start...");
		
		try {
			mnList = session.selectList("com.postGre.bsHive.Mapper.mnMapper.joinAplyLctrAllList");
		} catch (Exception e) {
			System.out.println("MnDaoImpl lctrAplyJoinAllList e.getMessage() >>>" + e.getMessage());
		}
		
		return mnList;
	}
>>>>>>> origin/br_test
	
}
