package com.postGre.bsHive.HbDao;

<<<<<<< HEAD
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

=======
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_Evl;

>>>>>>> origin/br_test
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HbTableDaoImpl implements HbTableDao {
	
	private final SqlSession session;

<<<<<<< HEAD
=======
	@Override
	public void addCourseEval(Lctr_Evl courseEval) {
		session.insert("addCourseEval", courseEval);
		System.out.println("i'm Insert start...");
	}
	   @Override
	    public void submitQuestion(String evl_num) {
	        session.update("submitQuestion", evl_num);
	        System.out.println("i'm update start...");
	    }
	   
	@Override
	public List<String> getEvalQuestion() {
		System.out.println("start getEvalQuesting");
		return  session.selectList("getEvalQuestion");
	}
	@Override
	public List<Onln_Lctr_List> getStntNum(Onln_Lctr_List onln_Lctr_List) {
		System.out.println("service" + onln_Lctr_List.getUnq_num());
		return session.selectList("getStntNum",onln_Lctr_List);
	}

>>>>>>> origin/br_test
}
