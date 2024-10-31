package com.postGre.bsHive.MhDao;

import java.util.List;

import com.postGre.bsHive.Amodel.Pst;

public interface MhDao {

	int totalGongList();

	List<Pst> listGong(Pst pst);

	List<Pst> GongView(Pst pst);

	int GongDelete(int pst_num);

}
