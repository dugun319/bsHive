package com.postGre.bsHive.dto;

import lombok.Data;

@Data
public class Paper {			
	private int		aply_num;		//신청번호
	private long 	lctr_num;		//강의번호
	private long 	unq_num;		//고유번호
	private int 	prdoc_type;		//증명서타입
	private String 	aply_ymd;		//신청일
	private String 	issu_ymd;		//발급일
	private int 	issu_stts;		//발급상태		
	
}
