package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
// 온라인강의
public class Onln_Lctr {
	private int 	lctr_num;			// 강의번호
	private String 	lctr_expln;			// 강의설명
	private String 	lctr_data;			// 강의자료
	private String 	bgng_ymd;			// 시작일
	private String 	end_ymd;			// 종료일
	private int 	rcrt_nope;			// 모집인원수
	private String 	fnsh_crtr;			// 수료기준
}
