package com.postGre.bsHive.Amodel;

import lombok.Data;

//학생정보
@Data
public class Stdnt {
	private int 	unq_num;		//고유(학생)번호
	private String	stdnt_nm;		//이름
	private int 	stdnt_telno;	//연락처
	private String	stdnt_photo;	//프로필사진
	private String	stdnt_addr;		//주소
	private String	stdnt_daddr;	//상세주소
	private String	stdnt_zip;		//우편번호
}
