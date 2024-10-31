package com.postGre.bsHive.Amodel;

import lombok.Data;

//장학금
@Data
public class Scholarship {
	private int 	scholarship_num;	//장학금수여번호
	private int 	lctr_num;			//강의번호
	private String 	ptcp_type;			//참여유형
	private String 	priority_type;		//우대유형
	private int 	sprt_cost;			//지원비용
	private String 	back_img;			//통장사본
	private String 	priority_img;		//우대첨부파일
	private String 	ptcp_img;			//참여첨부파일
	private String 	give_stts;			//지급상태
	private String 	bank_num;			//계좌번호
	private String 	bank_name;			//은행명
	private int 	unq_num;			//고유(학생)번호
	private String 	give_date;			//지급일자
}
