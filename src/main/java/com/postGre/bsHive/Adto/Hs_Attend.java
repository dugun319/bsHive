package com.postGre.bsHive.Adto;

import java.util.List;

import lombok.Data;

@Data
public class Hs_Attend {
	// 학생정보TBL
	private int 	unq_num;		//학생번호
	private String	stdnt_nm;		//이름
	private int 	stdnt_telno;	//연락처
	private String	stdnt_photo;	//프로필사진
	private String	stdnt_addr;		//주소
	private String	stdnt_daddr;	//상세주소
	private String	stdnt_zip;		//우편번호
	
	// 출석 TBL
	private int 	lctr_num;		//강의번호 (수강신청 TBL과 조인)
	private int 	unq_num2;		//고유(학생)번호 (수강신청 TBL과 조인)
	private String 	lctr_weeks;		//주차 (강의주차별 TBL과 조인)
	private String	atndc_type;		//출석구분
	
	// 강의주차별 TBL
	private String 	lctr_ymd;	// 강의일자
	private String 	lctr_plan;	// 강의계획
	private String 	lctr_data;	// 수업자료
	
	// 출석 list로 update위해 만든 배열함수
	private List<Hs_Attend> attendanceData; // 여러 학생의 출석 정보를 담는 리스트
	
	// 공통분류코드
	private int 	bcode;			//대분류
	private int 	mcode;			//중분
	private String 	content;		//코드
	
	//첨부파일 TBL
	private int		file_group;		//파일그룹 (첨부파일 TBL과 조인)
	private int 	file_no; 		//파일번호
	private String 	uuid; 			//UUID
	private String 	dwnld_file_nm; 	//실제파일명
	private int 	file_size; 		//파일사이즈
	private String 	file_path_nm; 	//파일경로
}
