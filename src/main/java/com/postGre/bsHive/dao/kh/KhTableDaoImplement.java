package com.postGre.bsHive.dao.kh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.dto.Paper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KhTableDaoImplement implements KhTableDao {
	private final SqlSession session;
	
	public List<Paper> getTestTableList(){
		List<Paper> paperList = null;
		try {
			paperList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPaperTableList");
			System.out.println("KhTableDaoImplement getTestTableList() paperList -> " + paperList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTestTableList() e.getMessage() -> " + e.getMessage());
		}
		
		return paperList;
	}

	@Override
	public List<Paper> getPaperTableList() {
		List<Paper> paperList = null;
		try {
			paperList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPaperTableList");
			System.out.println("KhTableDaoImplement getPaperTableList() paperList -> " + paperList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPaperTableList() e.getMessage() -> " + e.getMessage());
		}
		
		return paperList;
	}

}
