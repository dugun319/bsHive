package com.postGre.bsHive.HbDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Hb_Evl;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HbTableDaoImpl implements HbTableDao {
	
	private final SqlSession session;

	@Override
	public List<Hb_Evl> getCourseEval(Hb_Evl hb_Evl) {
		return session.selectList("getCourseEval",hb_Evl);
	}
	
	@Override
	public void addCourseEval(int courseEval) {
	    System.out.println("HbCourseEval Start... ");
	    try {
	        int result = session.insert("addCourseEval", courseEval);
	        if (result > 0) {
	            System.out.println("Insert successful.");
	        } else {
	            System.out.println("Insert failed.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
	    }
	}

	@Override
	public List<Hb_Evl> getstudentClassList() {
		System.out.println("HbGetStudentClass");
		return session.selectList("studentClassList");
	}

	@Override
	public List<Hb_Evl> getAllClass(Hb_Evl hb_Evl) {
		System.out.println("HbGetStudentClass getAllClass");
		return session.selectList("getstudentClassList",hb_Evl);
	}

	@Override
	public String getClassName() {
		System.out.println("HbGetStudentClass getClassName");
		String className = session.selectOne("getClassName");
		return className;
	}

	@Override
	public Hb_Evl getDetailGrade(int unqNum, int lctrNum) {
		System.out.println("Hb getdetailgrade start...");
		Hb_Evl hb_evl = session.selectOne("getDetailGrade");
		return hb_evl;
	}


}
