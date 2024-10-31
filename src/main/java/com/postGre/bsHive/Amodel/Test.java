package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Test {
	//시험
	private String cycl; 	//차수
	private int lctr_num;	//강의번호
	private String prblm; 	//시험명
	private String atch; 	//마감일시
}
