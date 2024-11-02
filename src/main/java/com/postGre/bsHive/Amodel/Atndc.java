package com.postGre.bsHive.Amodel;

import lombok.Data;

// 출석 TBL

@Data
public class Atndc {
	private int 	lctr_num;	//강의번호 (수강신청 TBL과 조인)
	private int 	unq_num2;	//고유(학생)번호 (수강신청 TBL과 조인)
	private String 	lctr_weeks;	//주차 (강의주차별 TBL과 조인)
	private String	atndc_type;	//출석구분
}
