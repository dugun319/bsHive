package com.postGre.bsHive.Adto;

import lombok.Data;

// 수강과목 TBL

@Data
public class Kh_LctrList {
	private int 	lctr_num;		//강의번호
	private int 	unq_num;		//고유번호(교직원) (교원정보 TBL과 조인)
	private int 	unq_num2;		//강사고유번호
	private String 	aply_type;		//강의상태
	private String 	aply_ydm;		//개설일
	private String  end_date;		//마감일
	private int 	pscp_nope;		//정원인원수
	private String 	lctr_name;		//강의명
	
	private String 	emp_nm; 		//이름
	private String 	emp_telno; 		//전화번호
	private String 	eml;			//이메일
	private String 	emlContent;		//이메일 내용
	
	private String	crtr_cnt;		//출석기준 
	
	private int		fnsh_cost;		//수강료	FNSH_COST
	
	private String 	year; 			//개설년도
	private String 	semester; 		//개설학기
	private String 	div_name; 		//학부명
	private String 	dept_name;		//학과명
	private String	bcode;			//B코드
	private String	mcode;			//M코드
	
	private String	search;			//분류
	private String	keyword;		//Search Keyword
	private int		start;			//시작번호
	private int		end;			//끝번호
  	
	private String	currentPage;	//현제 페이지
	
	private	int		lectureType;	//강의타입(online or offline)
	private	int		lectureTypeEnd;	//강의타입(online or offline) + 4
}
