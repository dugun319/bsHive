package com.postGre.bsHive.KhService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Amodel.Lgn;
import com.postGre.bsHive.KhDao.KhTableDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KhTableSeriveImplement implements KhTableSerive {
	
	private final KhTableDao khTableDao;

	@Override
	public List<Kh_PrdocList> getTestTableList() {
		List<Kh_PrdocList> paperList = khTableDao.getTestTableList();
		return paperList;
	}

	@Override
	public List<Kh_PrdocList> getPrdocList(Kh_PrdocList prList) {
		List<Kh_PrdocList> prdocList = khTableDao.getPrdocList(prList);
		return prdocList;
	}

	@Override
	public int getTotPrdocList(Kh_PrdocList prList) {
		int totPrdocList	= khTableDao.getTotPrdocList(prList);
		return totPrdocList;
	}

	@Override
	public int getTotStdntList(Kh_StdntList stList) {
		int totStdntList	= khTableDao.getTotStdntList(stList);
		return totStdntList;
	}

	@Override
	public List<Kh_StdntList> getStdntList(Kh_StdntList stList) {
		List<Kh_StdntList> stdntList = khTableDao.getStdntList(stList);
		return stdntList;
	}

	@Override
	public int updateLgnDelYn(Lgn lgn) {
		int result = khTableDao.updateLgnDelYn(lgn);
		return result;
	}

	@Override
	public int getTotEmpList(Kh_EmpList eList) {
		int totProfList	= khTableDao.getTotEmpList(eList);
		return totProfList;
	}

	@Override
	public List<Kh_EmpList> getEmpList(Kh_EmpList eList) {
		List<Kh_EmpList> empList = khTableDao.getEmpList(eList);
		return empList;
	}

	@Override
	public int getTotLctrList(Kh_LctrList lcList) {
		int totLctrList	= khTableDao.getTotLctrList(lcList);
		return totLctrList;
	}

	@Override
	public List<Kh_LctrList> getLctrList(Kh_LctrList lcList) {
		List<Kh_LctrList> lctrList = khTableDao.getLctrList(lcList);
		return lctrList;
	}

}
