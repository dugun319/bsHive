package com.postGre.bsHive.Adto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Hs_Assignment {
	//과제 TBL
	private String  cycl;			//차수 (과제제출 TBL과 조인)
	private int		lctr_num;		//강의번호 (수강과목, 과제제출 TBL과 조인)
	private String  asmt_tpc;		//과제주제
	private String  asmt_dtl_cn;	//상세내용
	private String  asmt_ddln;		//제출마감
	private int		file_group;		//파일그룹 (첨부파일 TBL과 조인)
	
	private boolean dataPresent;	//학생과제 제출여부 확인하기
	
	//과제제출 TBL
	private int		unq_num;		//고유(학생)번호 (수강신청 TBL과 조인)
	private String	crans_cnt;		//제출내용
	private int		cycl_scr;		//학생점수
	private int		file_group2;	//파일그룹 (첨부파일 TBL과 조인)
	
	// 과제제출인원
	private int		asmtSubCount;	//과제제출 총 인원
	private List<Hs_Assignment> studentList;  // 학생 목록
	
	//첨부파일 TBL
	private int 	file_no; 		//파일번호
	private String 	uuid; 			//UUID
	private String 	dwnld_file_nm; 	//실제파일명
	private int 	file_size; 		//파일사이즈
	private String 	file_path_nm; 	//파일경로
	
	private List<MultipartFile>	file;			// 파일 정보 이동시키는 dto
	
	// 수강과목 TBL
	private String 	aply_type;	//강의상태
	private String 	aply_ydm;	//개설일
	private int 	pscp_nope;	//정원인원수
	private String 	sbjct_nm;	//과목명
	private String 	lctr_name;	//강의명
	private int 	unq_num2;	// 강사고유번호
	
	// 수강신청 TBL
	private String 	aply_stts;	// 신청상태
	private String 	aply_ymd;	// 신청일
	private int 	atndc_scr;	// 출석점수
	private int 	asmt_scr;	// 과제점수
	private String 	fnsh_yn;	// 수료여부
	private int 	evl_total;	// 강의평가 총점
	
	// 학생정보 TBL
	private String	stdnt_nm;		//이름
	private int 	stdnt_telno;	//연락처
	private String	stdnt_photo;	//프로필사진
	private String	stdnt_addr;		//주소
	private String	stdnt_daddr;	//상세주소
	private String	stdnt_zip;		//우편번호
	
	// 교직원정보 TBL
	private String 	emp_nm; 	//이름
	private int 	emp_telno; 	//전화번호
	private String 	emp_photo; 	//프로필사진
	private String 	emp_addr; 	//주소
	private String 	emp_daddr; 	//상세주소
	private String 	emp_zip; 	//우편번호
	
	// 공통분류코드
	private int 	bcode;			//대분류
	private int 	mcode;			//중분
	private String 	content;		//코드
}
