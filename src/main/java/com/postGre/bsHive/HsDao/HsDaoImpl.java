package com.postGre.bsHive.HsDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.Lctr;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HsDaoImpl implements HsDao {
	@Autowired
	private final SqlSession session;
	
	// 강의번호/ 강의명 불러오기
	@Override
	public Lctr callLctr_num(int lctr_num) {
		System.out.println("HsDaoImpl callLctr_num Start...");
		Lctr lctr = new Lctr();
		
		try {
			lctr = session.selectOne("hsLctr_num", lctr_num);
		} catch (Exception e) {
			System.out.println("HsDaoImpl callLctr_num e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return lctr;
	}
	
	// 강의계획서 조회
	@Override
	public List<Hs_Lec> lecTotSche(int lctr_num) {
		System.out.println("HsDaoImpl lecTotSche Start...");
		List<Hs_Lec> lec_TotSche = null;
		
		try {
			lec_TotSche = session.selectList("hsLec_TotSche", lctr_num);
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
	
	// 학생 주차별 출결상태 불러오기
	@Override
	public List<Hs_Attend> lecStdAttend(Hs_Attend attend) {
		System.out.println("HsDaoImpl lecStdAttend Start...");
		List<Hs_Attend> atndc_state = null;
		
		try {
			atndc_state = session.selectList("hsAttendStd", attend);
		} catch (Exception e) {
			System.out.println("HsDaoImpl lecStdAttend e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return atndc_state;
	}
	
	// 교수 과제목록 List
	@Override
	public List<Hs_Assignment> assProfList(int lctr_num) {
		System.out.println("HsDaoImpl assProfList Start...");
		List<Hs_Assignment> assProfList = null;
		try {
			assProfList = session.selectList("hsProfListAsmt", lctr_num);
		} catch (Exception e) {
			System.out.println("HsDaoImpl assProfList e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		
		return assProfList;
	}
	
	// 과제 입력 시 기본 정보 불러오기 (교수)
	@Override
	public Hs_Assignment AssignInfoProf(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl AssignInfoProf Start...");
		Hs_Assignment profAssign = new Hs_Assignment();
		System.out.println("HsDaoImpl AssignInfoProf hsAss-> "+ hsAss);
		try {
			profAssign = session.selectOne("hsProfAsmtInfo", hsAss);
		} catch (Exception e) {
			System.out.println("HsDaoImpl AssignInfoProf e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return profAssign;
	}
	
	// 파일그룹 insert
	@Override
	public int gongInsert(Hs_Assignment assign) {
		System.out.println("HsDaoImpl gongInsert Start...");
		int result = 0;
		
		try {
			result = session.insert("hsFileUp", assign);
		} catch (Exception e) {
			System.out.println("HsDaoImpl gongInsert e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int ProfAsmtInsert(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl ProfAsmtInsert Start...");
		int result = 0;
		try {
			result = session.insert("hsProfAsmt", hsAss);
		} catch (Exception e) {
			System.out.println("HsDaoImpl ProfAsmtInsert e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	// 내 강의실 과제 수정 form (교수)
	@Override
	public int asmtUpdate(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl asmtUpdate Start...");
		int updateCount = 0;
		try {
			updateCount = session.update("hsProfAsmtUpd", hsAss);
			System.out.println("HsDaoImpl asmtUpdate updateCount -> "+updateCount);
		} catch (Exception e) {
			System.out.println("HsDaoImpl asmtUpdate e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return updateCount;
	}
	
	//학생본인의 과제 제출여부 확인해보기
	@Override
	public boolean checkData(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl checkData Start...");
		boolean checkData = false;
		try {
			checkData = session.selectOne("hsCheckDataAssign", hsAss);
		} catch (Exception e) {
			System.out.println("HsDaoImpl checkData e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return checkData;
	}
	
	//과제제출 시 필요한 기본정보 info 불러오기
	@Override
	public Hs_Assignment stdAssignment(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl stdAssignment Start...");
		Hs_Assignment hsAssignWrite = new Hs_Assignment();
		try {
			hsAssignWrite = session.selectOne("hsAsmtStdntInfo", hsAss);
			System.out.println("HsDaoImpl stdAssignment hsAssignWrite-> "+hsAssignWrite);
		} catch (Exception e) {
			System.out.println("HsDaoImpl stdAssignment e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return hsAssignWrite;
	}
	
	
	
	
	
}
