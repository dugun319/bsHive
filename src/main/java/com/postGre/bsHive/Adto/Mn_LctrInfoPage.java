package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Mn_LctrInfoPage {
	private int 	lctr_num;	// 강의번호
	private int 	unq_num;	// 고유번호(교직원) (교원정보 TBL과 조인)
	private String 	aply_type;	// 강의상태
	private String 	aply_ydm;	// 개설일
	private int 	pscp_nope;	// 정원인원수
	private String 	lctr_name;	// 강의명
	private int 	unq_num2;	// 강사고유번호
	
	// 교원정보 TBL
	private String 	emp_nm; 	//이름
	private int 	emp_telno; 	//전화번호
	private String 	emp_photo; 	//프로필사진
	private String 	emp_addr; 	//주소
	private String 	emp_daddr; 	//상세주소
	private String 	emp_zip; 	//우편번호
	
	// 오프라인 강의 TBL
	private String 	bgng_ymd;	// 시작일
	private String 	end_ymd;	// 종료일
	private int 	fnsh_cost;	// 수강료
	private String 	fnsh_scr;	// 수료기준
	
	// 강의주차별 TBL
	private String 	lctr_weeks;	// 주차
	private String 	lctr_ymd;	// 강의일자
	private String 	lctr_plan;	// 강의계획
	private String 	lctr_data;	// 수업자료
	
	// 강의실 TBL
	private int 	lctrm_num;	//강의실번호
	private String 	bgng_tm;    //시작시간
	private String 	end_tm;     //종료시간
	private String 	dow_day;	//강의요일
	private String 	lctrm_rmrk; //강의실비고
	
	// 오프라인 강의계획서 TBL
	private String 	lctr_otln;		// 강의개요
	private String 	edu_prps;		// 교육목적
	private String 	edu_cn;			// 교육내용
	private String 	evl_mthd;		// 평가방법
	private String 	ref_data_cn;	// 참고자료내용
	private String 	excptn_mttr;	// 특이사항
}
