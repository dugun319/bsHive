package com.postGre.bsHive.Amodel;

import lombok.Data;


// 교원정보 TBL
@Data
public class Emp {
	
	private int unq_num; //고유번호(교직원)
	private String emp_nm; //이름
	private int emp_telno; //전화번호
	private String emp_photo; //프로필사진
	private String emp_addr; //주소
	private String emp_daddr; //상세주소
	private String emp_zip; //우편번호

}
