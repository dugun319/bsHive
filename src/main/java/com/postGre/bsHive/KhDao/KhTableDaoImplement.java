package com.postGre.bsHive.KhDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Amodel.Lgn;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KhTableDaoImplement implements KhTableDao {
	private final SqlSession session;
	
	public List<Kh_PrdocList> getTestTableList(){
		List<Kh_PrdocList> paperList = null;
		try {
			paperList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPaperTableList");
			System.out.println("KhTableDaoImplement getTestTableList() paperList -> " + paperList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTestTableList() e.getMessage() -> " + e.getMessage());
		}
		
		return paperList;
	}

	@Override
	public List<Kh_PrdocList> getPrdocList(Kh_PrdocList prList) {
		List<Kh_PrdocList> prdocList = null;
		try {
			prdocList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPrdocList", prList);
			System.out.println("KhTableDaoImplement getPrdocList() prdocList -> " + prdocList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return prdocList;
	}

	@Override
	public int getTotPrdocList(Kh_PrdocList prList) {
		int totPrdocList = 0;
		
		try {
			totPrdocList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotPrdocList", prList);
			System.out.println("KhTableDaoImplement getTotPrdocList() totPrdocList -> " + totPrdocList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return totPrdocList;
	}

	
	
	//
	//stdnt
	//
	
	
	@Override
	public int getTotStdntList(Kh_StdntList stList) {
		int totStdntList = 0;
		
		try {
			totStdntList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotStdntList", stList);
			System.out.println("KhTableDaoImplement getTotPrdocList() totStdntList -> " + totStdntList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return totStdntList;
	}

	@Override
	public List<Kh_StdntList> getStdntList(Kh_StdntList stList) {
		List<Kh_StdntList> stdntLists = null;
		try {
			stdntLists = session.selectList("com.postGre.bsHive.kh_TableMapper.getStdntList", stList);
			System.out.println("KhTableDaoImplement getPrdocList() stdntLists -> " + stdntLists);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return stdntLists;
	}

	@Override
	public int updateLgnDelYn(Lgn lgn) {
		int result = 0;
		
		try {
			result = session.update("com.postGre.bsHive.kh_TableMapper.updateLgnDelYn", lgn);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return result;
	}

}
