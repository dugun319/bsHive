package com.postGre.bsHive.HsDao;

import java.util.List;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Lctr;

public interface HsDao {
	// 강의번호/ 강의명 불러오기
	Lctr callLctr_num(int lctr_num);
		
	// 강의계획서 조회
	List<Hs_Lec> lecTotSche(int lctr_num);
	// 주차별 강의 계획서 List
	List<Hs_Lec> lecWeekSchedule(int lctr_num);
	
	//강의 주차 option 불러오는 list
	List<Hs_Attend> WeekList(int lctr_num);
	//강의 주차 별 학생 출석입력 list
	List<Hs_Attend> lecWeekProf(Hs_Attend attend);
	//교수가 강의 출결 등록
	int updateAttProf(Hs_Attend attendL);
	// 학생 주차별 출결상태 불러오기
	List<Hs_Attend> lecStdAttend(Hs_Attend attend);
	
	// 교수 과제목록 List
	List<Hs_Assignment> assProfList(int lctr_num);
	// 과제 입력 시 기본 정보 불러오기 (교수)
	Hs_Assignment AssignWriteProf(Hs_Assignment hsAss);
	//과제 상세보기 시 차수, 강의번호로 select
	Hs_Assignment AssignInfoProf(Hs_Assignment hsAss);

	// 학생 과제제출 파일그룹 insert
	int StdntAsmtInsert(Hs_Assignment hsAss, List<File> fileList);
	// 교수가 과제 첨부파일
	int ProfAsmtInsert(Hs_Assignment hsAss, List<File> fileList);
	// 내 강의실 과제 수정 form (교수)
	int asmtUpdate(Hs_Assignment hsAss, List<File> fileList);
	// update form에서 파일 삭제 ajax
	int deleteFile(Hs_Assignment assign);
	//학생본인의 과제 제출여부 확인해보기
	boolean checkData(Hs_Assignment hsAss);
	//과제제출 시 필요한 기본정보 info 불러오기
	Hs_Assignment stdAssignment(Hs_Assignment hsAss);
	
	//강의번호, 차수로 파일그룹 불러오기
	int CallFileGroup(Hs_Assignment hsAss);
	
	//파일그룹으로 파일 TBL 정보 불러오기
	List<File> filePath(int file_group);
	// 학생 과제제출 수정 정보
	Hs_Assignment stdAssignmentUpd(Hs_Assignment hsAss);
	//강의번호, 차수, 학번으로 파일그룹 불러오기 (학생)
	int CallFileGroupStd(Hs_Assignment hsAss);
	// 내 강의실 과제 수정 form (학생)
	int asmtSbmsnUpdate(Hs_Assignment hsAss, List<File> fileList);
	// 학생과제제출물 list 불러오기
	List<Hs_Assignment> assignSubmitList(Hs_Assignment hsAss);
	//해당 강의의 과제 차수 list 불러오기
	List<Hs_Assignment> assCycl(int lctr_num);
	// 제출 학생 수 조회 count
	int getSubmitCount(Hs_Assignment hsAss);
	//과제 점수부여 update form (교수 -> 학생)
	void asmtScoreUpd(Hs_Assignment hsAss);
	//출석 table 자동 insert (procedure)
	void atndcTblInsert(int lctr_num);

	

	
	

	
	
	

}
