package com.postGre.bsHive.HsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.HsDao.HsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HsServiceImpl implements HsService {
	
	@Autowired
	private final HsDao hsd;
	
	// 강의계획서 조회
	@Override
	public Hs_Lec lecTotSche(int lctr_num) {
		System.out.println("HsServiceImpl lecTotSche Start...");
		Hs_Lec lec_TotSche = hsd.lecTotSche(lctr_num);
		System.out.println("HsServiceImpl lecTotSche lec_TotSche -> " +lec_TotSche);
		System.out.println("HsServiceImpl lecTotSche After...");
		return lec_TotSche;
	}
	
	// 주차별 강의 계획서 List
	@Override
	public List<Hs_Lec> lecWeekSchedule(int lctr_num) {
		System.out.println("HsServiceImpl lecWeekSchedule Start...");
		List<Hs_Lec> lctrWeek = hsd.lecWeekSchedule(lctr_num);
		System.out.println("HsServiceImpl lecWeekSchedule lctrWeek -> " +lctrWeek);
		System.out.println("HsServiceImpl lecWeekSchedule After...");
		return lctrWeek;
	}
	
	//강의 주차 option 불러오는 list
	@Override
	public List<Hs_Attend> WeekList(int lctr_num) {
		System.out.println("HsServiceImpl WeekList Start...");
		List<Hs_Attend> weekList = hsd.WeekList(lctr_num);
		System.out.println("HsServiceImpl WeekList weekList -> " +weekList);
		System.out.println("HsServiceImpl WeekList After...");
		return weekList;
	}
	
	//강의 주차 별 학생 출석입력 list
	@Override
	public List<Hs_Attend> lecWeekProf(Hs_Attend attend) {
		System.out.println("HsServiceImpl lecWeekProf Start...");
		List<Hs_Attend> weekList = hsd.lecWeekProf(attend);
		System.out.println("HsServiceImpl lecWeekProf weekList -> " +weekList);
		System.out.println("HsServiceImpl lecWeekProf After...");
		return weekList;
	}
	
	//교수가 강의 출결 등록
	@Override
	public int updateAttProf(Hs_Attend attendL) {
		System.out.println("HsServiceImpl updateAttProf Start...");
		int updateCount = hsd.updateAttProf(attendL);
		System.out.println("HsServiceImpl updateAttProf attendL -> " +attendL);
		System.out.println("HsServiceImpl updateAttProf After...");
		return updateCount;
	}

}
