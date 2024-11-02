package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class User_Table {
	
	private int 	unq_num;	// 고유번호 
	private String  eml;		// 이메일
	private String  pswd;		// 비밀번호
	private int 	del_yn;		// 삭제여부
	private String  mbr_se;		// 회원구분
	
	private String  nm; 		// 이름
	private String 	telno; 		// 연락처
	private String  zip; 		// 우편번호
	private String  addr; 		// 주소
	private String  daddr; 		// 상세주소

	
	public String getMbr_se() {
	    return mbr_se;
	}
	
}
