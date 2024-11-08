package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Jh_myClassroomList {
	
	private int lctr_num;			//강의번호
	private String lctr_name;		//강의명
	private String emp_nm;			//강사명
	private String bgng_ymd;		//시작일
	private String end_ymd;			//종료일
	private int pace;				//강의진도율
	private String aply_type;		//강의상태
	private int fnsh_yn;			//수료여부
	private int unq_num;			//고유번호(학생)
	
	
	
	private String aply_ydm;        // 개설일
	private String end_date;        // 마감일
	private int pscp_nope;          // 정원인원수
	private int rcrt_nope;          // 모집인원수
	private int unq_num2;        // 고유번호(강사)
	
}
