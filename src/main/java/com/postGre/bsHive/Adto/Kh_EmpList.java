package com.postGre.bsHive.Adto;

import lombok.Data;


// 교원정보 TBL
@Data
public class Kh_EmpList {
	
	private int 	unq_num; 		//고유번호(교직원)
	private String 	emp_nm; 		//이름
	private String 	emp_telno; 		//전화번호
	private String 	emp_photo; 		//프로필사진
	private String 	emp_addr; 		//주소
	private String 	emp_daddr; 		//상세주소
	private String 	emp_zip; 		//우편번호
	
	private String 	eml;			//Lgn 이메일
	private int 	mbr_se;			//회원구분
	
	private String	search;			//분류
	private String	keyword;		//Search Keyword
	private int		start;			//시작번호
	private int		end;			//끝번호
	
	private String	currentPage;	//현제 페이지
}
