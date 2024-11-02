package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Mn_LctrAplyOflWeek {
	private int lctr_num;		//강의번호
	private int unq_num;		//고유번호(교직원) (교원정보 TBL과 조인)
	private String aply_type;	//강의상태
	private String aply_ydm;	//개설일
	private int pscp_nope;		//정원인원수
	private String sbjct_nm;	//과목명
	private String lctr_name;	//강의명
	private int unq_num2;		// 강사고유번호
	private String end_date;	//마감일자
	
	// 수강신청
	private String aply_stts;	// 신청상태
	private String aply_ymd;	// 신청일
	private int atndc_scr;		// 출석점수
	private int asmt_scr;		// 과제점수
	private String fnsh_yn;		// 수료여부
	private int pace;			// 강의진도율
	
	//오프라인 강의
	private String 	lctr_expln;	// 강의설명
	private String 	bgng_ymd;	// 시작일
	private String 	end_ymd;	// 종료일
	private int 	rcrt_nope;	// 모집정원 
	private int 	fnsh_cost;	// 수강료
	private String 	fnsh_crtr;	// 수료기준
	private String 	lctr_date;	// 개설일자
	
	// 오프라인 강의계획서
	private String lctr_otln;	// 강의개요
	private String edu_prps;	// 교육목적
	private String edu_cn;		// 교육내용
	private String evl_mthd;	// 평가방법
	private String ref_data_cn;	// 참고자료내용
	private String excptn_mttr;	// 오프라인 강의계획서
	
	// 강의주차별 TBL
	private String 	lctr_weeks;	// 주차
	private String 	lctr_ymd;	//강의일자
	private String 	lctr_plan;	// 강의계획
	private String 	lctr_data;	// 수업자료
	private int 	lctrm_num;	// 강의실번호
}
