package com.postGre.bsHive.SeService;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.SeDao.SeDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeServiceImpl implements SeService {
	
	private final SeDao sd;
	

}
