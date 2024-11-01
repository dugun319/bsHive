package com.postGre.bsHive.Adto;

import lombok.Data;

//학생정보
@Data
public class Kh_StdntList {
	private int 	unq_num;		//고유(학생)번호
	private String	stdnt_nm;		//이름
	private String 	stdnt_telno;	//연락처
	private String	stdnt_photo;	//프로필사진
	private String	stdnt_addr;		//주소
	private String	stdnt_daddr;	//상세주소
	private String	stdnt_zip;		//우편번호
	
	private String 	eml;			//Lgn 이메일
	
	private String	search;			//분류
	private String	keyword;		//Search Keyword
	private int		start;			//시작번호
	private int		end;			//끝번호
	
	private String	currentPage;	//현제 페이지
}
