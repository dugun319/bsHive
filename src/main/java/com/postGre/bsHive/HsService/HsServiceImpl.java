package com.postGre.bsHive.HsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.HsDao.HsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HsServiceImpl implements HsService {
	@Autowired
	private final HsDao hd;

}
