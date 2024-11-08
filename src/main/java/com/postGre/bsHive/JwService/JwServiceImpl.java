package com.postGre.bsHive.JwService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Onln_Lctr;
import com.postGre.bsHive.Amodel.Syllabus_Unit;
import com.postGre.bsHive.JwDao.JwDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwServiceImpl implements JwService {
	private final JwDao jd;

// 1. 강의계획서
	// 교수정보 불러오기
	@Override
	public User_Table getUserTable(int unq_num) {
		System.out.println("JwServiceImpl getUserTable Start...");
		
		User_Table user_Table =  jd.getUserTable(unq_num);
		System.out.println("JwServiceImpl getUserTable user_Table->"+user_Table);
		
		return user_Table;
	}

	// 온라인강의 TBL insert 
	@Override
	public int insertLCTR(Onln_Lctr onln_lctr) {
		int Lctrinsert = 0;
		System.out.println("JwServiceImpl insertLCTR Start...");
		
		Lctrinsert = jd.insertLCTR(onln_lctr);
		System.out.println("JwServiceImpl insertLCTR Lctrinsert->"+Lctrinsert);
		
		return Lctrinsert;
	}
	
	// DB에 입력된 강의번호 가져오기
	@Override
	public int getLctrNum() {
		int getLctrNum = 0;
		System.out.println("JwServiceImpl getLctrNum Start...");
		
		getLctrNum = jd.getLctrNum();
		
		return getLctrNum;
	}
	
	// 온라인 콘텐츠 TBL insert
	@Override
	public int insertOnlnCont(Syllabus_Unit syllabus_Unit) {
		int onlnContInsert = 0;
		System.out.println("JwServiceImpl insertOnlnCont Start...");
		
		onlnContInsert = jd.insertOnlnCont(syllabus_Unit);
		System.out.println("JwServiceImpl insertOnlnCont onlnContInsert->"+onlnContInsert);
		
		return onlnContInsert;
	}

	

	
	
	

}
