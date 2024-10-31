package com.postGre.bsHive.HbService;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.HbDao.HbTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HbTableServiceImpl implements HbTableService {
	private final HbTableDao hbtableDao;

	@Override
	public void addCourseEval() {
		// TODO Auto-generated method stub
		
	}
}
