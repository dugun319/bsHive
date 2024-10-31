package com.postGre.bsHive.JwService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.All_Lctr;
import com.postGre.bsHive.JwDao.JwDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwServiceImpl implements JwService {
	private final JwDao jd;

	// 온라인 강의정보 불러오기 
	@Override
	public List<All_Lctr> listOnlnLct(All_Lctr al) {
		List<All_Lctr> AllLctrList = null;
		System.out.println("JwServiceImpl listOnlnLct Start...");

		AllLctrList = jd.listAllLctr(al);
		System.out.println("JwServiceImpl listOnlnLct AllLctrList.size->"+AllLctrList.size());
		
		return AllLctrList;
	}
	
}
