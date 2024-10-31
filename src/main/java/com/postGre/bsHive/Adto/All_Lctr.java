package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class All_Lctr {
	
	// 수강과목 LCTR TBL
	private int lctr_num;		//강의번호   PK
	private int unq_num;		//고유번호(교직원) (교원정보 TBL과 조인)
	private String aply_type;	//강의상태
	private String aply_ydm;	//개설일
	private int pscp_nope;		//정원인원수
	private String sbjct_nm;	//과목명
	private String lctr_name;	//강의명
	private int unq_num2;		// 강사고유번호
	
	// 온라인강의 Onln_Lctr TBL
	private String 	lctr_expln;			// 강의설명
	private String 	lctr_data;			// 강의자료
	private String 	bgng_ymd;			// 시작일
	private String 	end_ymd;			// 종료일
	private int 	rcrt_nope;			// 모집인원수
	private String 	fnsh_crtr;			// 수료기준
	
}
