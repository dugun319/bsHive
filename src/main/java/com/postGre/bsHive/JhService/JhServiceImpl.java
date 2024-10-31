package com.postGre.bsHive.JhService;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.JhDao.JhDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JhServiceImpl implements JhService{

	private final JhDao jd;
}
