package com.postGre.bsHive.MhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Amodel.Pst;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MhDaoImp implements MhDao {
	
	@Autowired
	private final SqlSession session;
	
	@Override
	public int totalGongList() {
		int totalGongList = 0;
		try {
			totalGongList = session.selectOne("totalGongList");
		} catch (Exception e) {
			System.out.println("totalGongList: " +e.getMessage());
		}
		return totalGongList;
	}

	@Override
	public List<Pst> listGong(Pst pst) {
		List<Pst> listGong = null;
		try {
			listGong = session.selectList("listGong", pst);
		} catch (Exception e) {
			System.out.println("listGong: " +e.getMessage());
		}
		
		return listGong;
	}

	@Override
	public List<Pst> GongView(Pst pst) {
		List<Pst> GongView = null;
		try {
			GongView = session.selectList("GongView", pst);
		} catch (Exception e) {
			System.out.println("GongView" + e.getMessage());
		}
		
		return GongView;
	}

	@Override
	public int GongDelete(int pst_num) {
		int GongDelete = 0;
		try {
			GongDelete = session.delete("GongDelete", pst_num);
		} catch (Exception e) {
		}
		return GongDelete;
	}

}
