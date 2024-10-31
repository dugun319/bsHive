package com.postGre.bsHive.Amodel;

import lombok.Data;

// 강의실 TBL

@Data
public class Lctrm {
	private int 	lctrm_num;	//강의실번호
	private String 	bgng_tm;    //시작시간
	private String 	end_tm;     //종료시간
	private String 	dow;		//강의요일
	private String 	lctrm_rmrk; //강의실비고
}
