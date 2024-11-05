package com.postGre.bsHive.HsDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HsDaoImpl implements HsDao {
	@Autowired
	private final SqlSession session;
	
	// 강의계획서 조회
	@Override
	public Hs_Lec lecTotSche(int lctr_num) {
		System.out.println("HsDaoImpl lecTotSche Start...");
		Hs_Lec lec_TotSche = new Hs_Lec();
		
		try {
			lec_TotSche = session.selectOne("hsLec_TotSche", lctr_num);
			System.out.println("HsDaoImpl lecTotSche lec_TotSche -> " +lec_TotSche);
		} catch (Exception e) {
			System.out.println("HsDaoImpl lecTotSche e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		
		return lec_TotSche;
	}
	
	// 주차별 강의 계획서 List
	@Override
	public List<Hs_Lec> lecWeekSchedule(int lctr_num) {
		System.out.println("HsDaoImpl lecWeekSchedule Start...");
		List<Hs_Lec> lctrWeek = null;
		
		try {
			lctrWeek = session.selectList("hsLctrWeek", lctr_num);
			System.out.println("HsDaoImpl lecWeekSchedule lctrWeek -> "+lctrWeek);
		} catch (Exception e) {
			System.out.println("HsDaoImpl lecWeekSchedule e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return lctrWeek;
	}
	
	//강의 주차 option 불러오는 list
	@Override
	public List<Hs_Attend> WeekList(int lctr_num) {
		System.out.println("HsDaoImpl WeekList Start...");
		List<Hs_Attend> weekList = null;
		
		try {
			weekList = session.selectList("hsWeekList", lctr_num);
			System.out.println("HsDaoImpl WeekList weekList -> "+weekList);
		} catch (Exception e) {
			System.out.println("HsDaoImpl WeekList e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return weekList;
	}
	
	//강의 주차 별 학생 출석입력 list
	@Override
	public List<Hs_Attend> lecWeekProf(Hs_Attend attend) {
		System.out.println("HsDaoImpl lecWeekProf Start...");
		List<Hs_Attend> lecWeekProf = null;
		
		try {
			lecWeekProf = session.selectList("hsWeekListProf", attend);
			System.out.println("HsDaoImpl lecWeekProf lecWeekProf -> "+lecWeekProf);
		} catch (Exception e) {
			System.out.println("HsDaoImpl lecWeekProf e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return lecWeekProf;
	}
	
	//교수가 강의 출결 등록
	@Override
	public int updateAttProf(Hs_Attend attendL) {
		System.out.println("HsDaoImpl updateAttProf Start...");
		int updateCount = 0;
		
		try {
			updateCount = session.update("hsUpdAttendProf", attendL);
		} catch (Exception e) {
			System.out.println("HsDaoImpl updateAttProf e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return updateCount;
	}
}
