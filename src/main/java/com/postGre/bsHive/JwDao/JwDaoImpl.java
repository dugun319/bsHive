package com.postGre.bsHive.JwDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.All_Lctr;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JwDaoImpl implements JwDao {
	private final SqlSession session;

	// 온라인 강의정보 불러오기 
	@Override
	public List<All_Lctr> listAllLctr(All_Lctr al) {
		List<All_Lctr> listResult = null;
		System.out.println("JwDaoImpl listAllLctr Start...");
		
		try {
			listResult = session.selectList("selectAllLctr", al);
			System.out.println("JwDaoImpl listAllLctr listResult.size()->"+listResult.size());
					
		} catch (Exception e) {
			System.out.println("JwDaoImpl listAllLctr Exception->"+e.getMessage());
		}
		
		return listResult;
	}

	
}
