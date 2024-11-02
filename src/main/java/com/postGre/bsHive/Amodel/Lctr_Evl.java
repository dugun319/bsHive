package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Lctr_Evl {
		private String 	evl_num; 	//평가번호
		private int			 lctr_num;	//강의번호
		private int 			unq_num;	// 고유(학생)번호
		private String 	evl_ques;  	// 문항
		private int		 	evl_total; 	//총점
		private int 			evl_ox;		//강의평가유무
		
}
