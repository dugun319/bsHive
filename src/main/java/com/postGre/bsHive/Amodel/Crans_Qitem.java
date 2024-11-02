package com.postGre.bsHive.Amodel;

import lombok.Data;

//정답문항
@Data
public class Crans_Qitem {
	private String crans_cd; //정답코드
	private String qitem_no; //문항번호
	private String crans; //정답
	private String crans_no; //객관식정답

}
