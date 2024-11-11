package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Kh_ScholarshipList {
	private long 	scholarship_num;	//장학금수여번호
	private long 	lctr_num;			//강의번호
	private String	lctr_name;			//강의이름
	private int 	ptcp_type;			//참여유형
	private int		priority_type;		//우대유형
	private long	sprt_cost;			//지원비용
	private String	bank_img;			//통장사본
	private String	priority_img;		//우대첨부파일
	private String	ptcp_img;			//참여첨부파일
	private int		give_stts;			//지급상태
	private String	bank_num;			//계좌번호
	private String	bank_name;			//은행이름
	private long 	unq_num;			//고유번호
	private String	stdnt_nm;			//학생이름
	
	//LCTR_APLY
	private int 	atndc_scr;			//출석점수
	private int 	asmt_scr;			//과제점수
	private String 	fnsh_yn;			//수료여부
	private int 	pace;				//강의진도율
	
	//OFL_LCTR
	private int 	fnsh_cost;			//수강료
	
	private String	search;				//분류
	private String	keyword;			//Search Keyword
	private int		start;				//시작번호
	private int		end;				//끝번호
	
	private String	imgType;			// image load type
	
	private String	currentPage;		//현제 페이지
	
	
}
