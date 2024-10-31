package com.postGre.bsHive.HbDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HbTableDaoImpl implements HbTableDao {
	
	private final SqlSession session;

}
