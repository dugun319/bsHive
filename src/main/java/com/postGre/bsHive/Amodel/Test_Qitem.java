package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Test_Qitem {
	//시험문항
	private String qitem_md; 	//문항번호
	private String qitem_nm;	//문항명
	private String qitem_type;	//문항종류
	private int qitem_scr;		//점수
	private String cycl; 		//차수
	private int lctr_num;		//강의번호
}
