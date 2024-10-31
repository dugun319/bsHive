package com.postGre.bsHive.service.kh;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.dao.kh.KhTableDao;
import com.postGre.bsHive.dto.Paper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KhTableSeriveImplement implements KhTableSerive {
	
	private final KhTableDao khTableDao;

	@Override
	public List<Paper> getTestTableList() {
		List<Paper> paperList = khTableDao.getTestTableList();
		return paperList;
	}

	@Override
	public List<Paper> getPaperTableList() {
		List<Paper> paperList = khTableDao.getPaperTableList();
		return paperList;
	}

}
