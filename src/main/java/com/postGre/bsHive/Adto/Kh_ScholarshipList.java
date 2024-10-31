package com.postGre.bsHive.Adto;

import lombok.Data;

@Data
public class Kh_ScholarshipList {
	private long 	scholarship_num;	//장학금수여번호
	private long 	lctr_num;			//강의번호
	private int 	ptcp_type;			//참여유형
	private int		priority_type;		//우대유형
	private long	sprt_cost;			//지원비용
	private String	bank_img;			//통장사본
	private String	priority_img;		//우대첨부파일
	private String	ptcp_img;			//참여첨부파일
	private int		give_stts;			//지급상태
	private String	bank_num;			//계좌번호
	private String	bank_name;			//은행이름
	private long 	unq_num;			//강의번호
}
