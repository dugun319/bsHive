package com.postGre.bsHive.Amodel;

import lombok.Data;

//첨부파일
@Data
public class File {
	private String file_group; //파일그룹
	private String file_no; //파일번호
	private String uuid; //UUID
	private String dwnld_file_nm; //실제파일명
	private int file_size; //파일사이즈
	private String file_path_nm; //파일경로

}
