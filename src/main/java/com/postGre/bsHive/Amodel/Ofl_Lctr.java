package com.postGre.bsHive.Amodel;

import lombok.Data;

// 오프라인 강의 TBL

@Data
public class Ofl_Lctr {
	private int 	lctr_num;	// 강의번호
	private String 	lctr_expln;	// 강의설명
	private String 	bgng_ymd;	// 시작일
	private String 	end_ymd;	// 종료일
	private int 	rcrt_nope;	// 모집정원 
	private int 	fnsh_cost;	// 수강료
	private String 	fnsh_crtr;	// 수료기준
	private String 	lctr_date;	// 개설일자
}
