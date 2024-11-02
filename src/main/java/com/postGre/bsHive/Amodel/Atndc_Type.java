package com.postGre.bsHive.Amodel;

import lombok.Data;

//출석기준 TBL

@Data
public class Atndc_Type {
	private int 	sn;			//일련번호
	private int 	lctr_num;	//강의번호 (수강과목 TBL과 조인)
	private String 	crtr_cnt;	//기준명
	private int		atndc_sct;	//점수
}
