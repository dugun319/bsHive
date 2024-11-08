package com.postGre.bsHive.Amodel;

import lombok.Data;

//과제 TBL

@Data
public class Asmt {
	private String  cycl;			//차수 (과제제출 TBL과 조인)
	private int		lctr_num;		//강의번호 (수강과목, 과제제출 TBL과 조인)
	private String  asmt_tpc;		//과제주제
	private String  asmt_dtl_cn;	//상세내용
	private String  asmt_ddln;		//제출마감
	private int 	file_group;		//파일그룹 (첨부파일 TBL과 조인)
}
