package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Lgn {
	// 로그인정보
	private int unq_num;	// 고유번호 
	private String eml;		// 이메일
	private String pswd;	// 비밀번호
	private int del_yn;	// 삭제여부
	private int mbr_se;	// 회원구분
}
