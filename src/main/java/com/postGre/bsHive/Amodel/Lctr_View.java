package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Lctr_View {
	// 강의시청
	private int unq_num;		// 고유(학생)번호
	private int lctr_num;		// 강의번호
	private String unit_num;	// 차시번호
	private String max_dtl;		// 최대시간
	private String last_dtl;	// 최종시간			
	private int lctr_pace;		// 차시진도율
	private String conts_type;	// 컨텐츠구분

}
