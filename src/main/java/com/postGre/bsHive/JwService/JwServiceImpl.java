package com.postGre.bsHive.JwService;

import java.util.List;

import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.postGre.bsHive.Adto.All_Lctr;
=======
import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;
>>>>>>> origin/br_test
import com.postGre.bsHive.JwDao.JwDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwServiceImpl implements JwService {
	private final JwDao jd;

<<<<<<< HEAD
	// 온라인 강의정보 불러오기 
	@Override
	public List<All_Lctr> listOnlnLct(All_Lctr al) {
		List<All_Lctr> AllLctrList = null;
		System.out.println("JwServiceImpl listOnlnLct Start...");

		AllLctrList = jd.listAllLctr(al);
		System.out.println("JwServiceImpl listOnlnLct AllLctrList.size->"+AllLctrList.size());
		
		return AllLctrList;
	}
	
=======
	// 강의계획서 작성 정보 insert 
	@Override
	public int insertLCTR(LgnEmp lgnEmp2) {
		int result = 0;
		System.out.println("JwServiceImpl insertLCTR Start...");

		result = jd.insertLctr(lgnEmp2);
		System.out.println("JwServiceImpl insertLCTR result->"+result);
		
		return result;
	}
	
	// 온라인 강의정보 불러오기 
	@Override
	public List<Onln_Lctr_List> listOnlnLct(Onln_Lctr_List ol) {
		List<Onln_Lctr_List> OnlnLctrList = null;
		System.out.println("JwServiceImpl listOnlnLct Start...");

		OnlnLctrList = jd.listOnlnLctr(ol);
		System.out.println("JwServiceImpl listOnlnLct OnlnLctrList.size->"+OnlnLctrList.size());
		
		return OnlnLctrList;
	}

	// 온라인강의 차시정보 불러오기
	@Override
	public List<Onln_Lctr_List> UnitOnlnList(Onln_Lctr_List ol) {
		List<Onln_Lctr_List> OnlnUnitList = null;
		System.out.println("JwServiceImpl listOnlnUnit Start...");
		
		OnlnUnitList = jd.listOnlnUnit(ol);
		System.out.println("JwServiceImpl listOnlnUnit OnlnUnitList.size()->"+OnlnUnitList.size());
		
		return OnlnUnitList;
	}

	
	
>>>>>>> origin/br_test
}
