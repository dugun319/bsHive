package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Onln_Lctr_List {
	
	// ============= 강의정보 목록을 위해 만듬 ==============
	
	// 수강과목 LCTR TBL
	private int lctr_num;		// 강의번호   PK  (온라인강의, 온라인강의차시 TBL과 조인)
	private String aply_type;	// 강의상태 
	private String aply_ydm;	// 개설일
	private int pscp_nope;		// 정원인원수
	private String sbjct_nm;	// 과목명
	private String lctr_name;	// 강의명
	private int unq_num2;		// 강사고유번호
	private String end_date;	// 마감일
	
	// 온라인강의 Onln_Lctr TBL
	private String 	lctr_expln;			// 강의설명
	private String 	lctr_data;			// 강의자료
	private String 	bgng_ymd;			// 시작일
	private String 	end_ymd;			// 종료일
	private int 	rcrt_nope;			// 모집인원수
	private String 	fnsh_crtr;			// 수료기준

	
	// 교원정보 EMP TBL
	private int unq_num;		// 교직원 고유번호 (수강과목 TBL과 조인)
	private String emp_nm;		// 교직원 이름
	
	
	// ========== 차시정보 목록을 위해 만듬 ===============
	
	// 온라인콘텐츠 ONLN_CONTS TBL
	private String conts_id;		// 콘텐츠 ID	PK (콘텐츠챕터,온라인강의차시 TBL과 조인)
	private String conts_nm;		// 콘텐츠명
	private String vdo_id;			// 비디오 ID
	private int play_hr;			// 재생시간
	
	// 콘텐츠챕터 CONTS_CH TBL
	private int    ch_num;		// 챕터 ID(PK)
	private String ch_nm;		// 챕터이름
	
	
	// =========== 승은씨의 강의시청 ================
	
	// 강의시청 LCTR_VIEW TBL
	private int unit_num;	// 차시번호
	private int max_dtl;		// 최대시간
	private int last_dtl;	// 최종시간			
	private int lctr_pace;		// 차시진도율
	private String conts_type;	// 컨텐츠구분
	
	
	
	// Page 정보
	private int		start;
	private int		end;
	private String	currentPage;
		
	private String play_start; //시작시간
	
	
	
}
