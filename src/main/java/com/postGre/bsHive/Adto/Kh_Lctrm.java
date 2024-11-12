package com.postGre.bsHive.Adto;

import lombok.Data;

// 강의실 TBL

@Data
public class Kh_Lctrm {
	private int 	lctrm_num;	//강의실번호
	private int 	lctr_num;	//강의번호
	private String 	lctr_name;	//강의명
	private String 	bgng_tm;    //시작시간
	private String 	end_tm;     //종료시간
	private String 	dow_day;	//강의요일
}
