package com.postGre.bsHive.MhService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.MhDao.MhDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MhServiceImp implements MhService {
	private final MhDao md;
	
	
	@Override
	public int totalGongList() {
		int totalGongList = md.totalGongList();
		
		return totalGongList;
	}

	@Override
	public List<Pst> listGong(Pst pst) {
		List<Pst> listGong = null;
		listGong = md.listGong(pst);
		return listGong;
	}

	@Override
	public List<Pst> GongView(Pst pst) {
		List<Pst> GongView = null;
		GongView = md.GongView(pst);
	
		return GongView;
	}

	@Override
	public int GongDelete(int pst_num) {
		int GongDelete = 0;
		GongDelete = md.GongDelete(pst_num);
		return GongDelete;
	}

<<<<<<< HEAD
=======
	@Override
	public int gongInsert(Pst pst) {
		int gongInsert = 0;
		gongInsert = md.gongInsert(pst);
		return gongInsert;
	}

	@Override
	public int updateGong(Pst pst) {
		int updateGong = 0;
		updateGong = md.updateGong(pst);
		return updateGong;
	}

	@Override
	public int totalYakList() {
		int totalYakList = 0;
		totalYakList = md.totalYakList();
		return totalYakList;
	}

	@Override
	public List<Pst> listYak(Pst pst) {
		List<Pst> listYak = null;
		listYak = md.listYak(pst);
		return listYak;
	}

	@Override
	public List<Pst> yakView(Pst pst) {
		List<Pst> yakView = null;
		yakView = md.yakView(pst);
		return yakView;
	}

	@Override
	public int yakInsert(Pst pst) {
		int yakInsert = 0;
		yakInsert = md.yakInsert(pst);
		return yakInsert;
	}

	@Override
	public int updateYak(Pst pst) {
		int updateYak = 0;
		updateYak = md.updateYak(pst);
		return updateYak;
	}

	@Override
	public int yakDelete(int pst_num) {
		int yakDelete = 0;
		yakDelete = md.yakDelete(pst_num);
		return yakDelete;
	}

	@Override
	public int totalFaqList() {
		int totalFaqList = 0;
		totalFaqList = md.totalFaqList();
		return totalFaqList;
	}

	@Override
	public List<Pst> listFaq(Pst pst) {
		List<Pst>listFaq = null;
		listFaq = md.listFaq(pst);
		return listFaq;
	}

	@Override
	public List<Pst> fnqView(Pst pst) {
		List<Pst> fnqView = null;
		fnqView = md.fnqView(pst);
		return fnqView;
	}

>>>>>>> origin/br_test
}
