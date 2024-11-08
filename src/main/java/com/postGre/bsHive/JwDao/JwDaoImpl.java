package com.postGre.bsHive.JwDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.Amodel.Onln_Lctr;
import com.postGre.bsHive.Amodel.Syllabus_Unit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JwDaoImpl implements JwDao {
	private final SqlSession session;

// 1. 강의계획서 작성
	// 교수정보 불러오기
	@Override
	public User_Table getUserTable(int unq_num) {
		System.out.println("JwDaoImpl detail start..");
		
		User_Table user_Table = new User_Table();
		
		try {
			user_Table = session.selectOne("selectUserTable",    unq_num);
			System.out.println("JwDaoImpl getUserTable user_Table->"+user_Table);
		
		} catch (Exception e) {
			System.out.println("JwDaoImpl getUserTable Exception->"+e.getMessage());
		}
		
		return user_Table;
	}

	// 온라인강의 TBL insert 
	@Override
	public int insertLCTR(Onln_Lctr onln_lctr) {
		int onlnLctrResult = 0;
		System.out.println("JwDaoImpl insertLCTR Start...");
		
		try {
			onlnLctrResult = session.insert("insertOnln_Lctr", onln_lctr);
			System.out.println("JwDaoImpl insertLCTR insertResult->"+onlnLctrResult);
			
		} catch (Exception e) {
			System.out.println("JwDaoImpl insertLCTR Exception->"+e.getMessage());
		}
		
		return onlnLctrResult;
	}

	// DB에 입력된 강의번호 가져오기
	@Override
	public int getLctrNum() {
		int selectLctrNum = 0;
		System.out.println("JwDaoImpl selectLctrNum Start...");
		
		try {
			selectLctrNum = session.selectOne("selLctrNum",selectLctrNum);
			System.out.println("JwDaoImpl getLctrNum selectLctrNum->"+selectLctrNum);
			
		} catch (Exception e) {
			System.out.println("JwDaoImpl getLctrNum Exception->"+e.getMessage());
		}
		
		return selectLctrNum;
	}
	
	// 온라인 콘텐츠 TBL insert
	@Override
	public int insertOnlnCont(Syllabus_Unit syllabus_Unit) {
		int contResult = 0;
		System.out.println("JwDaoImpl insertOnlnCont Start...");
		
		try {
			contResult = session.insert("insertCont", syllabus_Unit);
			System.out.println("JwDaoImpl insertOnlnCont contResult->"+contResult);
			
		} catch (Exception e) {
			System.out.println("JwDaoImpl insertOnlnCont Exception->"+e.getMessage());
		}
		
		return contResult;
	}

	
}
