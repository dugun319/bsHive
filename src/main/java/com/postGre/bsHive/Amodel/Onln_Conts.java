package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
// 온라인 컨텐츠
public class Onln_Conts {
	private String 	conts_id;			// 컨텐츠ID
	private String 	conts_nm;			// 컨텐츠명
	private String 	vdo_id;				// 비디오ID
	private String 	play_hr;			// 재생시간
}
