package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class LgnEmp {
	
	// 로그인정보 TBL
		private int unq_num;	// 고유번호 (교원정보 TBL의 PK이기도 함)
		private String eml;		// 이메일
		private String pswd;	// 비밀번호
		private String del_yn;	// 삭제여부
		private String mbr_se;	// 회원구분
		
	// 교원정보 TBL
	private String emp_nm; //이름
	private int emp_telno; //전화번호
	private String emp_photo; //프로필사진
	private String emp_addr; //주소
	private String emp_daddr; //상세주소
	private String emp_zip; //우편번호
	

}
