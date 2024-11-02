package com.postGre.bsHive.JwDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.postGre.bsHive.Adto.All_Lctr;
=======
import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;
>>>>>>> origin/br_test

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JwDaoImpl implements JwDao {
	private final SqlSession session;

<<<<<<< HEAD
	// 온라인 강의정보 불러오기 
	@Override
	public List<All_Lctr> listAllLctr(All_Lctr al) {
		List<All_Lctr> listResult = null;
		System.out.println("JwDaoImpl listAllLctr Start...");
		
		try {
			listResult = session.selectList("selectAllLctr", al);
=======
	// 강의계획서 작성 정보 insert
	@Override
	public int insertLctr(LgnEmp lgnEmp2) {
		int result = 0;
		System.out.println("JwDaoImpl insertLctr Start...");
		
		try {
			System.out.println("JwDaoImpl insertLctr lgnEmp2->"+lgnEmp2);
			result = session.insert("insertLctr", lgnEmp2);
			
		} catch (Exception e) {
			System.out.println("JwDaoImpl insertLctr Exception->"+e.getMessage());
		}
		
		return result;
	}
	
	// 온라인 강의정보 불러오기 
	@Override
	public List<Onln_Lctr_List> listOnlnLctr(Onln_Lctr_List ol) {
		List<Onln_Lctr_List> listResult = null;
		System.out.println("JwDaoImpl listAllLctr Start...");
		
		try {
			listResult = session.selectList("selectOnlnLctr", ol);
>>>>>>> origin/br_test
			System.out.println("JwDaoImpl listAllLctr listResult.size()->"+listResult.size());
					
		} catch (Exception e) {
			System.out.println("JwDaoImpl listAllLctr Exception->"+e.getMessage());
		}
		
		return listResult;
	}

<<<<<<< HEAD
	
=======
	// 온라인강의 차시정보 불러오기
	@Override
	public List<Onln_Lctr_List> listOnlnUnit(Onln_Lctr_List ol) {
		List<Onln_Lctr_List> unitResult = null;
		System.out.println("JwDaoImpl listOnlnUnit Start...");
		
		try {
			unitResult = session.selectList("selectOnlnUnit", ol);
			System.out.println("JwDaoImpl listOnlnUnit unitResult.size()->"+unitResult.size());
			
		} catch (Exception e) {
			System.out.println("JwDaoImpl listOnlnUnit Exception->"+e.getMessage());
		}
		
		return unitResult;
	}

		
>>>>>>> origin/br_test
}
