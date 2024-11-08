package com.postGre.bsHive.Amodel;

import lombok.Data;

//첨부파일
@Data
public class File {
	private int file_group; //파일그룹
	private int file_no; //파일번호
	private String file_unq; //UUID
	private String dwnld_file_nm; //실제파일명
	private int file_size; //파일사이즈
	private String file_path_nm; //파일경로

}
