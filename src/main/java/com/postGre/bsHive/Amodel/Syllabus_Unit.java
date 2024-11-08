package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Syllabus_Unit {
	// 온라인강의차시
	
	private int unit_num;	//차시번호
	private int lctr_num;		//강의번호
	private String vdo_id;		//비디오 ID
	private String conts_nm;	//콘텐츠명
	private String play_hr;		//재생시간
}
