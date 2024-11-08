package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Hb_Evl {
	//강의 평가용 DTO

	// 학생 정보 TABLE
	private int 	unq_num;		//고유(학생)번호
	private String	stdnt_nm;		//이름

	// 오프라인 강의  
	private String 	end_ymd;	// 종료일
	private int 	rcrt_nope;	// 모집정원 
	private int 	fnsh_cost;	// 수강료
	private String 	fnsh_crtr;	// 수료기준
	private String 	lctr_date;	// 개설일자
	
	// 수강 신청 TABLE
	private String aply_stts;	// 신청상태
	private String aply_ymd;	// 신청일
	private int atndc_scr;		// 출석점수
	private int asmt_scr;		// 과제점수
	private String fnsh_yn;		// 수료여부
	private int pace;			// 강의진도율
	private int evl_total; //강의평가
	
	//설문 문항 TABLE
	private int evl_detnum; 	//문항번호
	private String evl_detail;	//문항내용
	
	// 설문 제출 TABLE
	private int 		evl_ox; 	//강의평가유무
	private int 		lctr_num;	// 강의번호
	private int		 evl_num; 	//선택숫자
		
	//강의명 table
	private String aply_type;	//강의상태
	private String aply_ydm;	//개설일
	private int pscp_nope;		//정원인원수
	private String sbjct_nm;	//과목명
	private String lctr_name;	//강의명
	private int unq_num2;		// 강사고유번호
}
