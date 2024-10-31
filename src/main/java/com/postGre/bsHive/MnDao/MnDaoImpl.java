package com.postGre.bsHive.MnDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Amodel.Crans_Qitem;

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
			critem = session.selectList("com.postGre.bsHive.Mapper.Mnxml.selAll");
			System.out.println("daoCrit >>>>>>>>>>" + critem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return critem;
	}
	
}
