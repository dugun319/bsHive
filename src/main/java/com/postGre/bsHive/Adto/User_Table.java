package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class User_Table {
	
	private int 	unq_num;	// 고유번호 
	private String  eml;		// 이메일
	private String  pswd;		// 비밀번호
	private int 	del_yn;		// 삭제여부
	private int  	mbr_se;		// 회원구분
	
	private String  nm; 		// 이름
	private String 	telno; 		// 연락처
	private String  zip; 		// 우편번호
	private String  addr; 		// 주소
	private String  daddr; 		// 상세주소
	
	// 교원정보 EMP TBL
	private String  emp_nm; 		// 교원이름
	private String 	emp_telno; 		// 연락처
	private String 	emp_photo; 		// 연락처
	private String  emp_addr; 		// 주소
	private String  emp_daddr; 		// 상세주소
	private String  emp_zip; 		// 우편번호

	// 학생정보 STDNT TBL
	private String stdnt_nm;		// 학생이름
	private String stdnt_telno;		// 연락처
	private String stdnt_photo;		// 사진
	private String stdnt_addr;		// 주소
	private String stdnt_daddr;		// 상세주소
	private String stdnt_zip;		// 우편번호
		
}
