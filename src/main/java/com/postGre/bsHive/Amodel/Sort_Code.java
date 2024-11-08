package com.postGre.bsHive.Amodel;

import lombok.Data;

@Data
public class Sort_Code {
	private int 	bcode;			//대분류
	private int 	mcode;			//중분
	private String 	content;		//코드
}
