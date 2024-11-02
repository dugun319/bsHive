package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Test_Submit {
	//시험제출
	private int lctr_num;		//강의번호
	private int unq_num2;		//고유(학생)번호
	private String qitem_no;	//문항번호
	private String crans_yn;	//정답여부
	private String crans_cd;	//정답코드
	private String crans_cnt;	//주관식입력
	private int crans_scr;   	//점수
}
