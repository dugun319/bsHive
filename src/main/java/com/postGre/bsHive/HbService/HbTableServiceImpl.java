package com.postGre.bsHive.HbService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.postGre.bsHive.Adto.Hb_Evl;
import com.postGre.bsHive.HbDao.HbTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HbTableServiceImpl implements HbTableService {
	private final HbTableDao hbtableDao;

	@Override
	public List<Hb_Evl> getEvalQuestion(Hb_Evl hb_Evl) {
		return hbtableDao.getCourseEval(hb_Evl);
	}

	@Override
	public Hb_Evl addAndUpdateEval(Hb_Evl hb_Evl, @RequestParam Map<String,Integer> evaluationScores) {
		System.out.println("Start .. addAndEval");
		 for (Map.Entry<String, Integer> entry : evaluationScores.entrySet()) {
		        String key = entry.getKey();
		        if (key.startsWith("scores[")) {
		            int detnum = Integer.parseInt(key.substring(7, key.length() - 1)); 
		            System.out.println(detnum);
		             hbtableDao.addCourseEval(detnum);
		        }
		 }
		 return  hb_Evl;
	
	}

	@Override
	public List<Hb_Evl> getAllStudentsList() {
		return hbtableDao.getstudentClassList();
	}

	@Override
	public List<Hb_Evl> getAllClass(Hb_Evl hb_Evl) {
		System.out.println(hb_Evl.getEvl_detail());
		return hbtableDao.getAllClass(hb_Evl);
	}

	@Override
	public String getClassName() {
		String className = hbtableDao.getClassName();
		return className;
	}

	@Override
	public Hb_Evl getDetailGrade(int unqNum, int lctrNum) {
		return hbtableDao.getDetailGrade(unqNum,lctrNum);
	}

    }
	

