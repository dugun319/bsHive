package com.postGre.bsHive.Amodel;

import lombok.Data;

//게시판 이미지
@Data
public class Pst_Img {
	private int 	dtl_num; 		//상세번호
	private int 	pst_num;		//게시판번호
	private String 	file_name;		//파일명
	private String 	url;			//URL
}
