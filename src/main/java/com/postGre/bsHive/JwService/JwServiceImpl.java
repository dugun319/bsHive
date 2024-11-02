package com.postGre.bsHive.JwService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.LgnEmp;
import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.JwDao.JwDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwServiceImpl implements JwService {
	private final JwDao jd;

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

	
	
}
