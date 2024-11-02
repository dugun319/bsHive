package com.postGre.bsHive.HsDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HsDaoImpl implements HsDao {
	@Autowired
	private final SqlSession session;
}
