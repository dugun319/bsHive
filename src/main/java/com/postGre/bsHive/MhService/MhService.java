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

	int gongInsert(Pst pst);

	int updateGong(Pst pst);

	int totalYakList();

	List<Pst> listYak(Pst pst);

	List<Pst> yakView(Pst pst);

	int yakInsert(Pst pst);

	int updateYak(Pst pst);

	int yakDelete(int pst_num);

	int totalFaqList();

	List<Pst> listFaq(Pst pst);

	List<Pst> fnqView(Pst pst);

}
