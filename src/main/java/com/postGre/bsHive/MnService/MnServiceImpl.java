package com.postGre.bsHive.MnService;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Amodel.Crans_Qitem;
import com.postGre.bsHive.MnDao.MnDao;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MnServiceImpl implements MnService {
	
	private final MnDao md;
	
	@Override
	public List<Crans_Qitem> selAll() {
		System.out.println("selAll start...");
		return md.selAll();
	}
	
}
