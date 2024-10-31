package com.postGre.bsHive.MhService;

import java.util.List;

import com.postGre.bsHive.Amodel.Pst;

import jakarta.transaction.Transactional;

@Transactional
public interface MhService {

	int totalGongList();

	List<Pst> listGong(Pst pst);


	List<Pst> GongView(Pst pst);

	int GongDelete(int pst_num);

}
