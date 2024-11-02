package com.postGre.bsHive.HbService;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

import com.postGre.bsHive.HbDao.HbTableDao;

=======
import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_Evl;
import com.postGre.bsHive.HbDao.HbTableDao;

import jakarta.transaction.Transactional;
>>>>>>> origin/br_test
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HbTableServiceImpl implements HbTableService {
	private final HbTableDao hbtableDao;

	@Override
<<<<<<< HEAD
	public void addCourseEval() {
		// TODO Auto-generated method stub
		
=======
	@Transactional
    public void addAndUpdateEval(Lctr_Evl courseEval, int evl_total) {
        hbtableDao.submitQuestion(courseEval.getEvl_num());
        hbtableDao.submitQuestion(courseEval.getEvl_ques());
        hbtableDao.addCourseEval(courseEval);
		System.out.println("i'm Service start...");

    }
	
	@Override
	public List<String> getEvalQuestion() {
		return hbtableDao.getEvalQuestion();
	}

	@Override
	public List<Onln_Lctr_List> getStntNum(Onln_Lctr_List onln_Lctr_List) {
		System.out.println("startLecTest service");
		return hbtableDao.getStntNum(onln_Lctr_List);
>>>>>>> origin/br_test
	}
}
