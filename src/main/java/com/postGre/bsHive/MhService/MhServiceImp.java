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

}
