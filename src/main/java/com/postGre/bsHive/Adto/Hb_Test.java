package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Hb_Test {
	
	// join 컬럼
	private String cycl; 	//차수
	private int lctr_num;	//강의번호
	
	//강의명
	private String aply_type;	//강의상태
	private String sbjct_nm;	//과목명
	private String lctr_name;	//강의명
	
	// 시험 테이블
	private String prblm; 	//시험명
	private String atch; 	//마감일시
	
	//시험 문항 테이블
	private String qitem_md; 	//문항번호
	private String qitem_nm;	//문항명
	private String qitem_type;	//문항종류
	private int qitem_scr;		//점수

	// 시험 제출 테이블
	private int unq_num2;		//고유(학생)번호
	private String qitem_no;	//문항번호
	private String crans_yn;	//정답여부
	private String crans_cd;	//정답코드
	private String crans_cnt;	//주관식입력
	private int crans_scr;   	//점수
}
