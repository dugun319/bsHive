package com.postGre.bsHive.HsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.Asmt_Sbmsn;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Lctr;
import com.postGre.bsHive.HsDao.HsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HsServiceImpl implements HsService {
	
	@Autowired
	private final HsDao hsd;
	
	// 강의번호/ 강의명 불러오기
	@Override
	public Lctr callLctr_num(int lctr_num) {
		System.out.println("HsServiceImpl callLctr_num Start...");
		Lctr lctr = hsd.callLctr_num(lctr_num);
		System.out.println("HsServiceImpl callLctr_num lctr -> " +lctr);
		System.out.println("HsServiceImpl callLctr_num After...");
		return lctr;
	}
	
	// 강의계획서 조회
	@Override
	public List<Hs_Lec> lecTotSche(int lctr_num) {
		System.out.println("HsServiceImpl lecTotSche Start...");
		List<Hs_Lec> lec_TotSche = hsd.lecTotSche(lctr_num);
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
	
	// 학생 주차별 출결상태 불러오기
	@Override
	public List<Hs_Attend> lecStdAttend(Hs_Attend attend) {
		System.out.println("HsServiceImpl lecStdAttend Start...");
		System.out.println("HsServiceImpl lecStdAttend atndc_state1 -> " +attend);
		List<Hs_Attend> atndc_state = hsd.lecStdAttend(attend);
		System.out.println("HsServiceImpl lecStdAttend atndc_state2 -> " +atndc_state);
		System.out.println("HsServiceImpl lecStdAttend After...");
		return atndc_state;
	}
	
	// 교수 과제목록 List
	@Override
	public List<Hs_Assignment> assProfList(int lctr_num) {
		System.out.println("HsServiceImpl assProfList Start...");
		List<Hs_Assignment> assProfList = hsd.assProfList(lctr_num);
		System.out.println("HsServiceImpl assProfList assProfList-> "+assProfList);
		
		System.out.println("HsServiceImpl assProfList After...");
		return assProfList;
	}
	
	// 과제 입력 시 기본 정보 불러오기 (교수)
	@Override
	public Hs_Assignment AssignWriteProf(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl AssignWriteProf Start...");
		Hs_Assignment profAssign = hsd.AssignWriteProf(hsAss);
		System.out.println("HsServiceImpl AssignWriteProf -> " +profAssign);
		System.out.println("HsServiceImpl AssignWriteProf After...");
		return profAssign;
	}
	
	//과제 상세보기 시 차수, 강의번호로 select
	@Override
	public Hs_Assignment AssignInfoProf(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl AssignInfoProf Start...");
		Hs_Assignment profAssign = hsd.AssignInfoProf(hsAss);
		System.out.println("HsServiceImpl AssignInfoProf -> " +profAssign);
		System.out.println("HsServiceImpl AssignInfoProf After...");
		return profAssign;
	}

	
	// 교수가 과제 첨부파일
	@Override
	public int ProfAsmtInsert(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsServiceImpl ProfAsmtInsert Start...");
		
		int result = hsd.ProfAsmtInsert(hsAss, fileList);
		System.out.println("HsServiceImpl ProfAsmtInsert After...");
		return result;
	}
	
	// 내 강의실 과제 수정 form (교수)
	@Override
	public int asmtUpdate(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsServiceImpl asmtUpdate Start...");
		int updateCount = hsd.asmtUpdate(hsAss, fileList);
		
		System.out.println("HsServiceImpl asmtUpdate After...");
		return updateCount;
	}
	
	// update form에서 파일 삭제 ajax
	@Override
	public int deleteFile(Hs_Assignment assign) {
		System.out.println("HsServiceImpl deleteFile Start...");
		int deleteFile = hsd.deleteFile(assign);
		System.out.println("HsServiceImpl boolean deleteFile ->"+deleteFile);
		System.out.println("HsServiceImpl asmtUpdate After...");
		return deleteFile;
	}
	
	//학생본인의 과제 제출여부 확인해보기
	@Override
	public boolean checkData(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl checkData Start...");
		boolean checkData = hsd.checkData(hsAss);
		System.out.println("HsServiceImpl checkData After...");
		return checkData;
	}
	
	//과제제출 시 필요한 기본정보 info 불러오기
	@Override
	public Hs_Assignment stdAssignment(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl stdAssignment Start...");
		Hs_Assignment hsAssignWrite = hsd.stdAssignment(hsAss);
		System.out.println("HsServiceImpl stdAssignment After...");
		return hsAssignWrite;
	}
	
	// 학생 과제제출 파일그룹 insert
	@Override
	public int StdntAsmtInsert(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsServiceImpl StdntAsmtInsert Start...");
		int result = hsd.StdntAsmtInsert(hsAss, fileList);
		System.out.println("HsServiceImpl StdntAsmtInsert After...");
		return result;
	}
	
	//강의번호, 차수로 파일그룹 불러오기
	@Override
	public int CallFileGroup(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl CallFileGroup Start...");
		int fileGroup = hsd.CallFileGroup(hsAss);
		System.out.println("HsServiceImpl CallFileGroup After...");
		return fileGroup;
	}
	
	//파일그룹으로 파일 TBL 정보 불러오기
	@Override
	public List<File> filePath(int file_group) {
		System.out.println("HsServiceImpl filePath Start...");
		List<File> filePath = hsd.filePath(file_group);
		System.out.println("HsServiceImpl filePath After...");
		return filePath;
	}
	
	// 학생 과제제출 수정 정보
	@Override
	public Hs_Assignment stdAssignmentUpd(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl stdAssignmentUpd Start...");
		Hs_Assignment stdAssUpd = hsd.stdAssignmentUpd(hsAss);
		System.out.println("HsServiceImpl stdAssignmentUpd After...");
		return stdAssUpd;
	}
	
	//강의번호, 차수, 학번으로 파일그룹 불러오기 (학생)
	@Override
	public int CallFileGroupStd(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl CallFileGroupStd Start...");
		int fileGroup = hsd.CallFileGroupStd(hsAss);
		System.out.println("HsServiceImpl CallFileGroupStd After...");
		return fileGroup;
	}
	
	// 내 강의실 과제 수정 form (학생)
	@Override
	public int asmtSbmsnUpdate(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsServiceImpl asmtSbmsnUpdate Start...");
		int updateCount = hsd.asmtSbmsnUpdate(hsAss, fileList);
		
		System.out.println("HsServiceImpl asmtSbmsnUpdate After...");
		return updateCount;
	}
	
	// 학생과제제출물 list 불러오기
	@Override
	public List<Hs_Assignment> assignSubmitList(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl assignSubmitList Start...");
		List<Hs_Assignment> assignSubmitList = hsd.assignSubmitList(hsAss);
		System.out.println("HsServiceImpl assignSubmitList After...");
		return assignSubmitList;
	}
	
	//해당 강의의 과제 차수 list 불러오기
	@Override
	public List<Hs_Assignment> assCycl(int lctr_num) {
		System.out.println("HsServiceImpl assCycl Start...");
		List<Hs_Assignment> assCycl = hsd.assCycl(lctr_num);
		System.out.println("HsServiceImpl assCycl After...");
		return assCycl;
	}
	
	// 제출 학생 수 조회 count
	@Override
	public int getSubmitCount(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl getSubmitCount Start...");
		int count = hsd.getSubmitCount(hsAss);
		System.out.println("HsServiceImpl getSubmitCount After...");
		return count;
	}
	
	//과제 점수부여 update form (교수 -> 학생)
	@Override
	public void asmtScoreUpd(Hs_Assignment hsAss) {
		System.out.println("HsServiceImpl asmtScoreUpd Start...");
		hsd.asmtScoreUpd(hsAss);
		System.out.println("HsServiceImpl asmtScoreUpd After...");
	}
	
	//출석 table 자동 insert (procedure)
	@Override
	public void atndcTblInsert(int lctr_num) {
		System.out.println("HsServiceImpl atndcTblInsert Start...");
		hsd.atndcTblInsert(lctr_num);
		System.out.println("HsServiceImpl atndcTblInsert After...");
	}
	
	

	
	
	
	

	

}
