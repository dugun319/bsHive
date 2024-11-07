package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
// 게시판
public class Kh_pstList {
	private int 	pst_num;			// 게시판번호
	private int 	unq_num;			// 고유번호
	private String 	pst_clsf;			// 게시판분류
	private String 	pst_ttl;			// 제목
	private String 	pst_cn;				// 내용
	private String 	wrt_ymd;			// 작성일
	private String 	ans;				// 답변
	private String 	answr;				// 답변자
	private String 	answr_ymd;			// 답변일자
	private String 	ans_yn;				// 답변처리여부
	private String 	del_yn;				// 삭제여부
	private String  file_group;    		// 파일그룹
	
	private String  empName;			// 작성자이름
	private String  studentName;		// 작성자이름
	private String  empTelNo;			// 작성자전화번호
	private String  studentTelNo;		// 작성자전화번호
	
	
	private String	search;				//분류
	private String	keyword;			//Search Keyword
	private int		start;				//시작번호
	private int		end;				//끝번호
	
	private String	currentPage;		//현제 페이지
}
