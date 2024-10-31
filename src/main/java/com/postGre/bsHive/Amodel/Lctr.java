package com.postGre.bsHive.Amodel;

import lombok.Data;

// 수강과목 TBL

@Data
public class Lctr {
	private int lctr_num;		//강의번호
	private int unq_num;		//고유번호(교직원) (교원정보 TBL과 조인)
	private String aply_type;	//강의상태
	private String aply_ydm;	//개설일
	private int pscp_nope;		//정원인원수
	private String sbjct_nm;	//과목명
	private String lctr_name;	//강의명
	private int unq_num2;		// 강사고유번호
}
