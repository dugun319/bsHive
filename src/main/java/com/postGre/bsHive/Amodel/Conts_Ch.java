package com.postGre.bsHive.Amodel;

import lombok.Data;

//콘텐츠챕터
@Data
public class Conts_Ch {
	private String ch_id; //챕터ID
	private String conts_idl; //콘텐츠ID
	private String play_start; //시작시간
	private String ch_nm; //챕터이름

}
