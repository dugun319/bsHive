package com.postGre.bsHive.Amodel;

import lombok.Data;

//과제제출 TBL

@Data
public class Asmt_Sbmsn {
	private String	cycl;		//차수 (과제 TBL과 조인)
	private int		lctr_num;	//강의번호 (과제, 수강신청 TBL과 조인)
	private int		unq_num2;	//고유(학생)번호 (수강신청 TBL과 조인)
	private String	crans_cnt;	//제출내용
	private int		cycl_scr;	//학생점수
	private int 	file_group;	//파일그룹 (첨부파일 TBL과 조인)
}
