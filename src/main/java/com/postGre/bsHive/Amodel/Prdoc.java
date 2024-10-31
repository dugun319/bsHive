package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
// 증명서
public class Prdoc {
	private int 	aply_num;			// 신청번호
	private int 	unq_num;			// 고유(학생)번호
	private int 	lctr_num;			// 강의번호
	private String 	prdoc_type;			// 증명서타입
	private String 	aply_ymd;			// 신청일
	private String 	issu_ymd;			// 발급일
	private String 	issu_stts;			// 발급상태
}
