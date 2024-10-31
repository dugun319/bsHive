package com.postGre.bsHive.JwService;

import java.util.List;

import com.postGre.bsHive.Adto.All_Lctr;

public interface JwService {

	// 온라인 강의정보 불러오기 
	List<All_Lctr> listOnlnLct(All_Lctr al);
}
