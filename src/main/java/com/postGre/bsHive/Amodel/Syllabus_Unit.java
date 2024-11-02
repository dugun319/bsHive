package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Syllabus_Unit {
	// 온라인강의차시
	
	private String unit_num;	//차시번호
	private int lctr_num;		//강의번호
	private String conts_id;	//콘텐츠ID
}
