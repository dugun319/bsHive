package com.postGre.bsHive.MhDao;

import java.util.List;

import com.postGre.bsHive.Amodel.Pst;

public interface MhDao {

	int totalGongList();

	List<Pst> listGong(Pst pst);

	List<Pst> GongView(Pst pst);

	int GongDelete(int pst_num);

<<<<<<< HEAD
=======
	int gongInsert(Pst pst);

	int updateGong(Pst pst);

	int totalYakList();

	List<Pst> listYak(Pst pst);

	List<Pst> yakView(Pst pst);

	int yakInsert(Pst pst);

	int updateYak(Pst pst);

	int yakDelete(int pst_num);

	List<Pst> listFaq(Pst pst);

	int totalFaqList();

	List<Pst> fnqView(Pst pst);

>>>>>>> origin/br_test
}
