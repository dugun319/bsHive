package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Ofl_Syllabus {
	// 오프라인 강의계획서
	private int lctr_num;		// 강의번호
	private String lctr_otln;	// 강의개요
	private String edu_prps;	// 교육목적
	private String edu_cn;		// 교육내용
	private String evl_mthd;	// 평가방법
	private String ref_data_cn;	// 참고자료내용
	private String excptn_mttr;	// 오프라인 강의계획서

}
