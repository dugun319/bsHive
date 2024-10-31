package com.postGre.bsHive.dao.kh;

import java.util.List;

import com.postGre.bsHive.dto.Paper;

public interface KhTableDao {

	List<Paper>			getTestTableList();
	List<Paper> 		getPaperTableList();	
}
