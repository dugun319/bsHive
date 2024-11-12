package com.postGre.bsHive.HsDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Hs_Assignment;
import com.postGre.bsHive.Adto.Hs_Attend;
import com.postGre.bsHive.Adto.Hs_Lec;
import com.postGre.bsHive.Amodel.File;
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
			System.out.println("HsDaoImpl assProfList lctr_num-> "+lctr_num);
			assProfList = session.selectList("hsProfListAsmt", lctr_num);
			System.out.println("HsDaoImpl assProfList assProfList-> "+assProfList);
		} catch (Exception e) {
			System.out.println("HsDaoImpl assProfList e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		
		return assProfList;
	}
	
	// 과제 입력 시 기본 정보 불러오기 (교수)
	@Override
	public Hs_Assignment AssignWriteProf(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl AssignWriteProf Start...");
		Hs_Assignment profAssign = new Hs_Assignment();
		
		try {
			
			profAssign = session.selectOne("hsProfAsmtWriteInfo", hsAss);
			String cycl = MaxCycl(hsAss.getLctr_num());
			System.out.println("cycl=> "+cycl);
			profAssign.setCycl(cycl);
			System.out.println("HsDaoImpl AssignWriteProf profAssign-> "+profAssign);
		} catch (Exception e) {
			System.out.println("HsDaoImpl AssignWriteProf e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return profAssign;
	}
	
	//과제 상세보기 시 차수, 강의번호로 select
	@Override
	public Hs_Assignment AssignInfoProf(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl AssignInfoProf Start...");
		Hs_Assignment profAssign = new Hs_Assignment();
		System.out.println("HsDaoImpl AssignInfoProf hsAss-> "+ hsAss);
		try {
			profAssign = session.selectOne("hsProfAsmtInfo", hsAss);
			System.out.println("HsDaoImpl AssignInfoProf profAssign-> "+profAssign);
		} catch (Exception e) {
			System.out.println("HsDaoImpl AssignInfoProf e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return profAssign;
	}
	
	// 교수가 과제 첨부파일 insert
	@Override
	public int ProfAsmtInsert(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsDaoImpl ProfAsmtInsert Start...");
		System.out.println("HsDaoImpl ProfAsmtInsert fileList -> "+fileList);
		int result = 0;
		
		try {
			// 파일 정보가 있다면 저장
	        if (fileList != null && !fileList.isEmpty()) {
	        	// 파일 그룹 ID 생성
	            int fileGroupId = CreateNewFileGroupId();
	            System.out.println("HsDaoImpl ProfAsmtInsert fileGroupId->" +fileGroupId);
	            
	            // 1. 과제 ASMT_Sbmsn TABLE에 먼저 저장
	            hsAss.setFile_group(fileGroupId);
	            System.out.println("HsDaoImpl ProfAsmtInsert hsAss-> "+hsAss);
	            result = session.insert("hsProfAsmt", hsAss);
	            
	            for (File fileUpload : fileList) {
	            	//Filegroup 객체를 순회
	            	int fileSeq = CreateNewFileSeq(fileGroupId);
	            	fileUpload.setFile_group(fileGroupId);	//파일 그룹 ID 설정
	            	fileUpload.setFile_no(fileSeq);			//파일 시퀀스 저장
	            	System.out.println("HsDaoImpl ProfAsmtInsert fileUpload-> "+fileUpload);
	            	
	            	// 2. 첨부파일 TABLE에 저장
	            	int fileResult = session.insert("hsFileUpload", fileUpload);
	            	System.out.println("HsDaoImpl ProfAsmtInsert fileResult-> "+fileResult);
	            	
	            	//파일 업로드 실패 시
	            	if (fileResult <= 0) {
	            		System.out.println("HsDaoImpl ProfAsmtInsert 업로드 실패");
	            	}
	            }
	        } else {
	        	System.out.println("HsDaoImpl ProfAsmtInsert 첨부파일 없음");
	        	result = session.insert("hsProfAsmt", hsAss);
	        }
		} catch (Exception e) {
			System.out.println("HsDaoImpl ProfAsmtInsert e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	// 내 강의실 과제 수정 form (교수)
	@Override
	public int asmtUpdate(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsDaoImpl asmtUpdate Start...");
		int updateCount = 0;		
		try {
			
			// 1. 파일 그룹 ID 확인: 만약 file_group이 null이면 새로 생성
		    if (hsAss.getFile_group() == 0) {
		        // file_group이 null인 경우 새로운 file_group 생성
		        int fileGroupId = CreateNewFileGroupId();  // 새 file_group 생성
		        hsAss.setFile_group(fileGroupId);           // 과제에 file_group 설정
		        System.out.println("HsDaoImpl asmtUpdate 새로운 fileGroupId 생성 -> " + fileGroupId);
		    } else {
		        System.out.println("HsDaoImpl asmtUpdate 기존 file_group -> " + hsAss.getFile_group());
		    }
		    
            // 2. 과제 ASMT TABLE에 먼저 update
			System.out.println("HsDaoImpl asmtUpdate hsAss-> "+hsAss);
			updateCount = session.update("hsProfAsmtUpd", hsAss);
			
			// 3. 파일 정보가 있다면 저장
	        if (fileList != null && !fileList.isEmpty()) {
	            for (File fileUpload : fileList) {
	            	// Filegroup 객체를 순회
	            	int fileGroupId = hsAss.getFile_group();
	            	int fileSeq = CreateNewFileSeq(hsAss.getFile_group());
	            	fileUpload.setFile_group(fileGroupId);	//파일 그룹 ID 설정
	            	fileUpload.setFile_no(fileSeq);			//파일 시퀀스 저장
	            	
	            	System.out.println("HsDaoImpl asmtUpdate fileUpload-> "+fileUpload);
	            	
	            	// 4. 첨부파일 TABLE에 저장
	            	int fileResult = session.insert("hsFileUpload", fileUpload);
	            	System.out.println("HsDaoImpl asmtUpdate fileResult-> "+fileResult);
	            	
	            	//파일 업로드 실패 시
	            	if (fileResult <= 0) {
	            		System.out.println("HsDaoImpl asmtUpdate 업로드 실패");
	            	}
	            }
	        } else {
	        	System.out.println("HsDaoImpl asmtUpdate 첨부파일 없음");
	        }
		} catch (Exception e) {
			System.out.println("HsDaoImpl asmtUpdate e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return updateCount;
	}
	
	
	
	// update form에서 파일 삭제 ajax
	@Override
	public int deleteFile(Hs_Assignment assign) {
		System.out.println("HsDaoImpl deleteFile ajax Start...");
		int deleteUpdFile = 0;
		try {
			deleteUpdFile = session.delete("hsFileDelUpd", assign);
			System.out.println("HsDaoImpl deleteFile deleteUpdFile->"+deleteUpdFile);
			
		} catch (Exception e) {
			System.out.println("HsDaoImpl deleteFile e.getMessage()-> "+e.getMessage());
			e.printStackTrace();
		}
		return deleteUpdFile;
	}
	
	//학생본인의 과제 제출여부 확인해보기
	@Override
	public boolean checkData(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl checkData Start...");
		boolean checkData = true;
		try {
			checkData = session.selectOne("hsCheckDataAssign", hsAss);
			System.out.println("HsDaoImpl checkData checkData-> "+checkData);
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
	
	// 학생 과제제출 파일그룹 insert
	@Override
	public int StdntAsmtInsert(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsDaoImpl StdntAsmtInsert Start...");
		System.out.println("HsDaoImpl StdntAsmtInsert fileList -> "+fileList);
		int result = 0;
		
		try {
			// 파일 정보가 있다면 저장
	        if (fileList != null && !fileList.isEmpty()) {
	        	// 파일 그룹 ID 생성
	            int fileGroupId = CreateNewFileGroupId();
	            System.out.println("HsDaoImpl StdntAsmtInsert fileGroupId->" +fileGroupId);
	            
	            // 1. 과제 ASMT TABLE에 먼저 저장
	            hsAss.setFile_group(fileGroupId);
	            System.out.println("HsDaoImpl StdntAsmtInsert hsAss-> "+hsAss);
	            result = session.insert("hsStdntAsmt", hsAss);
	            
	            for (File fileUpload : fileList) {
	            	//Filegroup 객체를 순회
	            	int fileSeq = CreateNewFileSeq(fileGroupId);
	            	fileUpload.setFile_group(fileGroupId);	//파일 그룹 ID 설정
	            	fileUpload.setFile_no(fileSeq);			//파일 시퀀스 저장
	            	System.out.println("HsDaoImpl StdntAsmtInsert fileUpload-> "+fileUpload);
	            	
	            	// 2. 첨부파일 TABLE에 저장
	            	int fileResult = session.insert("hsFileUpload", fileUpload);
	            	System.out.println("HsDaoImpl StdntAsmtInsert fileResult-> "+fileResult);
	            	
	            	//파일 업로드 실패 시
	            	if (fileResult <= 0) {
	            		System.out.println("HsDaoImpl StdntAsmtInsert 업로드 실패");
	            	}
	            }
	        } else {
	        	System.out.println("HsDaoImpl StdntAsmtInsert 첨부파일 없음");
	        	result = session.insert("hsStdntAsmt", hsAss);
	        }
		} catch (Exception e) {
			System.out.println("HsDaoImpl StdntAsmtInsert e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	//강의번호, 차수로 파일그룹 불러오기
	@Override
	public int CallFileGroup(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl CallFileGroup Start...");
		int fileGroup = 0;
		try {
			fileGroup = session.selectOne("HsFileGroup", hsAss);
			System.out.println("HsDaoImpl CallFileGroup fileGroup-> "+fileGroup);
		} catch (Exception e) {
			System.out.println("HsDaoImpl CallFileGroup e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return fileGroup;
	}
	
	
	//파일그룹으로 파일 TBL 정보 불러오기
	@Override
	public List<File> filePath(int file_group) {
		System.out.println("HsDaoImpl filePath Start...");
		List<File> filePath = null;
		try {
			System.out.println("file_group-> "+file_group);
			filePath = session.selectList("hsfilePath", file_group);
			System.out.println("HsDaoImpl filePath 실행 뒤-> "+filePath);
		} catch (Exception e) {
			System.out.println("HsDaoImpl filePath e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return filePath;
	}
	
	// 학생 과제제출 수정 정보
	@Override
	public Hs_Assignment stdAssignmentUpd(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl stdAssignmentUpd Start...");
		Hs_Assignment hsStdAssUpd = new Hs_Assignment();
		try {
			hsStdAssUpd = session.selectOne("hsStdAssUpd", hsAss);
			System.out.println("HsDaoImpl stdAssignmentUpd hsStdAssUpd-> "+hsStdAssUpd);
		} catch (Exception e) {
			System.out.println("HsDaoImpl stdAssignmentUp e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return hsStdAssUpd;
	}
	
	//강의번호, 차수, 학번으로 파일그룹 불러오기 (학생)
	@Override
	public int CallFileGroupStd(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl CallFileGroupStd Start...");
		int fileGroup = 0;
		try {
			fileGroup = session.selectOne("HsFileGroupStd", hsAss);
			System.out.println("HsDaoImpl CallFileGroupStd fileGroup-> "+fileGroup);
		} catch (Exception e) {
			System.out.println("HsDaoImpl CallFileGroupStd e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return fileGroup;
	}
	
	
	// 파일 그룹 ID 생성
	private int CreateNewFileGroupId() {
		System.out.println("HsDaoImpl createNewFileGroupId Start...(파일그룹 ID 생성)");
		int NewFileGroupId = session.selectOne("hsGetNextFileGroupId"); // 새로운 파일 그룹 ID 생성
		System.out.println("createNewFileGroupId ->" +NewFileGroupId);
		System.out.println("HsDaoImpl createNewFileGroupId End...(파일그룹 ID 생성)");
		return NewFileGroupId;

	}
	
	//파일 TBL 파일번호 불러오기
	private int CreateNewFileSeq(int fileGroupId) {
		// file_seq는 파일 그룹에 대해 최대값을 가져오는 방법
		Integer maxFileSeq = session.selectOne("hsGetMaxFileSeq", fileGroupId);
		System.out.println("maxFileSeq->" +maxFileSeq);
		return (maxFileSeq == null) ? 1 : maxFileSeq + 1; // maxFileSeq가 null이면 1을 반환
	}

	// 교수 차시번호 max값 불러오기
	private String MaxCycl(int lctrNum) {
		String maxCyclNum = session.selectOne("hsGetMaxCycl",lctrNum);
		System.out.println("maxCyclNum-> "+maxCyclNum);
		return maxCyclNum;
	}
	
	// 내 강의실 과제 수정 form (학생)
	@Override
	public int asmtSbmsnUpdate(Hs_Assignment hsAss, List<File> fileList) {
		System.out.println("HsDaoImpl asmtSbmsnUpdate Start...");
		int updateCount = 0;		
		try {
			System.out.println("hsAss.getFile_group()-> "+hsAss.getFile_group());
			// 1. 파일 그룹 ID 확인: 만약 file_group이 null이면 새로 생성
		    if (hsAss.getFile_group() == 0) {
		        // file_group이 null인 경우 새로운 file_group 생성
		        int fileGroupId = CreateNewFileGroupId();  // 새 file_group 생성
		        hsAss.setFile_group(fileGroupId);           // 과제에 file_group 설정
		        System.out.println("HsDaoImpl asmtSbmsnUpdate 새로운 fileGroupId 생성 -> " + fileGroupId);
		    } else {
		        System.out.println("HsDaoImpl asmtSbmsnUpdate 기존 file_group -> " + hsAss.getFile_group());
		    }
		    
            // 2. 과제 ASMT TABLE에 먼저 update
			System.out.println("HsDaoImpl asmtSbmsnUpdate hsAss-> "+hsAss);
			updateCount = session.update("hsStdntAsmtUpd", hsAss);
			
			// 3. 파일 정보가 있다면 저장
	        if (fileList != null && !fileList.isEmpty()) {
	            for (File fileUpload : fileList) {
	            	// Filegroup 객체를 순회
	            	int fileGroupId = hsAss.getFile_group();
	            	int fileSeq = CreateNewFileSeq(hsAss.getFile_group());
	            	fileUpload.setFile_group(fileGroupId);	//파일 그룹 ID 설정
	            	fileUpload.setFile_no(fileSeq);			//파일 시퀀스 저장
	            	
	            	System.out.println("HsDaoImpl asmtSbmsnUpdate fileUpload-> "+fileUpload);
	            	
	            	// 4. 첨부파일 TABLE에 저장
	            	int fileResult = session.insert("hsFileUpload", fileUpload);
	            	System.out.println("HsDaoImpl asmtSbmsnUpdate fileResult-> "+fileResult);
	            	
	            	//파일 업로드 실패 시
	            	if (fileResult <= 0) {
	            		System.out.println("HsDaoImpl asmtSbmsnUpdate 업로드 실패");
	            	}
	            }
	        } else {
	        	System.out.println("HsDaoImpl asmtSbmsnUpdate 첨부파일 없음");
	        }
		} catch (Exception e) {
			System.out.println("HsDaoImpl asmtSbmsnUpdate e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return updateCount;
	}
	
	//해당 강의의 과제 차수 list 불러오기
	@Override
	public List<Hs_Assignment> assCycl(int lctr_num) {
		System.out.println("HsDaoImpl assCycl Start... 과제 차수");
		List<Hs_Assignment> assCycl = null;
		try {
			// 학생 목록 조회
			assCycl = session.selectList("hsCyclList", lctr_num);
			System.out.println("HsDaoImpl assCycl ->" +assCycl);
		} catch (Exception e) {
			System.out.println("HsDaoImpl assCycl e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return assCycl;
	}
	
	// 학생과제제출물 list 불러오기
	@Override
	public List<Hs_Assignment> assignSubmitList(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl assignSubmitList Start...");
		List<Hs_Assignment> assignSubmitList = null;
		try {
			// 학생들 목록 list
			assignSubmitList = session.selectList("hsSubmitList", hsAss);
			// 차시 당 제출학생 수
			int count = session.selectOne("hsSubmitCount", hsAss);
			System.out.println("HsDaoImpl assignSubmitList assignSubmitList-> "+assignSubmitList);
		} catch (Exception e) {
			System.out.println("HsDaoImpl assignSubmitList e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return assignSubmitList;
	}
	
	// 제출 학생 수 조회 count
	@Override
	public int getSubmitCount(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl getSubmitCount Start...");
		int count = 0;
		try {
			// 차시 당 제출학생 수
			count = session.selectOne("hsSubmitCount", hsAss);
			System.out.println("HsDaoImpl getSubmitCount count-> "+count);
		} catch (Exception e) {
			System.out.println("HsDaoImpl getSubmitCount e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	
	//과제 점수부여 update form (교수 -> 학생)
	@Override
	public void asmtScoreUpd(Hs_Assignment hsAss) {
		System.out.println("HsDaoImpl asmtScoreUpd Start...");
		try {
			session.update("hsScoreUpd",hsAss);
		} catch (Exception e) {
			System.out.println("HsDaoImpl asmtScoreUpd e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//출석 table 자동 insert (procedure)
	@Override
	public void atndcTblInsert(int lctr_num) {
		System.out.println("HsDaoImpl atndcTblInsert Start...");
		try {
			session.update("hsAtndcIns",lctr_num);
		} catch (Exception e) {
			System.out.println("HsDaoImpl atndcTblInsert e.getMessage() ->"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
}
