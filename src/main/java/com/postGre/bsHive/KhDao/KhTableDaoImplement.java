package com.postGre.bsHive.KhDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_Lctrm;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_ScholarshipList;
import com.postGre.bsHive.Adto.Kh_SortCode;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Adto.Kh_pstList;
import com.postGre.bsHive.Amodel.Lgn;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KhTableDaoImplement implements KhTableDao {
	private final SqlSession session;
	
	
	//
	//삭제여부 변경
	//
		
	@Override
	public int updateLgnDelYn(Lgn lgn) {
		int result = 0;
		
		try {
			result = session.update("com.postGre.bsHive.kh_TableMapper.updateLgnDelYn", lgn);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateLgnDelYn() e.getMessage() -> " + e.getMessage());
		}
		
		return result;
	}
	
		
	
	//
	//stdnt
	//
	
	
	@Override
	public int getTotStdntList(Kh_StdntList stList) {
		int totStdntList = 0;
		
		try {
			totStdntList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotStdntList", stList);
			System.out.println("KhTableDaoImplement getTotStdntList() totStdntList -> " + totStdntList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotStdntList() e.getMessage() -> " + e.getMessage());
		}
		
		return totStdntList;
	}

	@Override
	public List<Kh_StdntList> getStdntList(Kh_StdntList stList) {
		List<Kh_StdntList> stdntList = null;
		try {
			stdntList = session.selectList("com.postGre.bsHive.kh_TableMapper.getStdntList", stList);
			System.out.println("KhTableDaoImplement getStdntList() stdntLists -> " + stdntList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getStdntList() e.getMessage() -> " + e.getMessage());
		}
		
		return stdntList;
	}

	//
	// EMP List
	//

	@Override
	public int getTotEmpList(Kh_EmpList eList) {
		int totEmpList = 0;
		
		try {
			totEmpList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotEmpList", eList);
			System.out.println("KhTableDaoImplement getTotEmpList() totStdntList -> " + totEmpList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotEmpList() e.getMessage() -> " + e.getMessage());
		}
		
		return totEmpList;
	}

	@Override
	public List<Kh_EmpList> getEmpList(Kh_EmpList eList) {
		List<Kh_EmpList> empList = null;
		try {
			empList = session.selectList("com.postGre.bsHive.kh_TableMapper.getEmpList", eList);
			System.out.println("KhTableDaoImplement getEmpList() empList -> " + empList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getEmpList() e.getMessage() -> " + e.getMessage());
		}
		
		return empList;
	}

	

	//
	// Lecture List
	//
	
	@Override
	public int getTotLctrList(Kh_LctrList lcList) {
		int totLctrList = 0;
		
		try {
			totLctrList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotLctrList", lcList);
			System.out.println("KhTableDaoImplement getTotLctrList() totLctrList -> " + totLctrList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotLctrList() e.getMessage() -> " + e.getMessage());
		}
		
		return totLctrList;
	}

	@Override
	public List<Kh_LctrList> getLctrList(Kh_LctrList lcList) {
		List<Kh_LctrList> lctrList = null;
		try {
			lctrList = session.selectList("com.postGre.bsHive.kh_TableMapper.getLctrList", lcList);
			System.out.println("KhTableDaoImplement getLctrList() lctrList -> " + lctrList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getLctrList() e.getMessage() -> " + e.getMessage());
		}
		
		return lctrList;
	}

	@Override
	public Kh_LctrList getLctrDetail(Kh_LctrList lcList) {
		Kh_LctrList lctrDetail 	= null;
		String str_lctrNum 		= "" +  lcList.getLctr_num();
		
		String year 			= "20" + str_lctrNum.substring(0, 2) + " 년도";
		String semester			= str_lctrNum.substring(2, 3) + " 학기";
		String division			= "3" + str_lctrNum.substring(3, 4) + "0";
		String department		= str_lctrNum.substring(3, 5);
		lcList.setBcode(division);
		lcList.setMcode(department);
		
		try {
			lctrDetail = session.selectOne("com.postGre.bsHive.kh_TableMapper.getLctrDetail", lcList);

			lctrDetail.setYear(year);
			lctrDetail.setSemester(semester);
			
			System.out.println("KhTableDaoImplement getLctrDetail() lctrDetail -> " + lctrDetail);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getLctrDetail() e.getMessage() -> " + e.getMessage());
		}
		
		return lctrDetail;
	}

	@Override
	public void updateAplyType(Kh_LctrList lcList) {
		try {
			session.update("com.postGre.bsHive.kh_TableMapper.updateAplyType", lcList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateAplyType() e.getMessage() -> " + e.getMessage());
		}		
	}

	@Override
	public void openLecture(Kh_LctrList lcList) {
		try {
			session.update("com.postGre.bsHive.kh_TableMapper.openLecture", lcList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement openLecture() e.getMessage() -> " + e.getMessage());
		}
		
	}

	@Override
	public void insertAtdncType(Kh_LctrList lcList) {
		try {
			session.insert("com.postGre.bsHive.kh_TableMapper.insertAtdncType", lcList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement insertAtdncType() e.getMessage() -> " + e.getMessage());
		}
		
	}

	@Override
	public void updateOflLctr(Kh_LctrList lcList) {
		try {
			session.update("com.postGre.bsHive.kh_TableMapper.updateOflLctr", lcList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateOflLctr() e.getMessage() -> " + e.getMessage());
		}
		
	}

	
	
	//LCTRM

	@Override
	public List<Kh_Lctrm> getLctrmList(String yearAndSemester) {
		List<Kh_Lctrm> lctrmList = null;
		try {
			lctrmList = session.selectList("com.postGre.bsHive.kh_TableMapper.getLctrmList", yearAndSemester);
			System.out.println("KhTableDaoImplement getLctrmList() lctrmList -> " + lctrmList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getLctrmList() e.getMessage() -> " + e.getMessage());
		}
		
		return lctrmList;
	}
	

	
	
	
	//Scholarship

	@Override
	public int getTotSchList(Kh_ScholarshipList sList) {
		int totSchList = 0;
		
		try {
			totSchList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotSchList", sList);
			System.out.println("KhTableDaoImplement getTotSchList() totPrdocList -> " + totSchList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotSchList() e.getMessage() -> " + e.getMessage());
		}
		
		return totSchList;
	}

	@Override
	public Kh_ScholarshipList getSchDetail(Kh_ScholarshipList sList) {
		Kh_ScholarshipList schDetail	= null;
		
		try {
			schDetail = session.selectOne("com.postGre.bsHive.kh_TableMapper.getSchDetail", sList);
			System.out.println("KhTableDaoImplement getSchDetail() schDetail -> " + schDetail);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getSchDetail() e.getMessage() -> " + e.getMessage());
		}
		
		return schDetail;
	}

	

	@Override
	public void insertSchDetail(Kh_ScholarshipList schDetail) {
		try {
			session.insert("com.postGre.bsHive.kh_TableMapper.insertSchDetail", schDetail);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement insertSchDetail() e.getMessage() -> " + e.getMessage());
		}
	}
	
	@Override
	public List<Kh_ScholarshipList> getSchList(Kh_ScholarshipList sList) {
		List<Kh_ScholarshipList> schList = null;
		try {
			schList = session.selectList("com.postGre.bsHive.kh_TableMapper.getSchList", sList);
			System.out.println("KhTableDaoImplement getSchList() schList -> " + schList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getSchList() e.getMessage() -> " + e.getMessage());
		}
		
		return schList;
	}


	@Override
	public String getScholarahipImgPath(Kh_ScholarshipList schList) {
		String filePath	= null;
		
		try {
			filePath = session.selectOne("com.postGre.bsHive.kh_TableMapper.getScholarahipImgPath", schList);
			System.out.println("KhTableDaoImplement getScholarahipImgPath() filePath -> " + filePath);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getScholarahipImgPath() e.getMessage() -> " + e.getMessage());
		}
		
		return filePath;
	}


	@Override
	public void updateGiveStss(Kh_ScholarshipList schList) {
		try {
			System.out.println("com.postGre.bsHive.kh_TableMapper.updateGiveStss -> " + schList);
			session.update("com.postGre.bsHive.kh_TableMapper.updateGiveStss", schList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateGiveStss() e.getMessage() -> " + e.getMessage());
		}
		
		
	}


	
	
	
	
	//PRDOC
	
	public List<Kh_PrdocList> getTestTableList(){
		List<Kh_PrdocList> paperList = null;
		try {
			paperList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPaperTableList");
			System.out.println("KhTableDaoImplement getTestTableList() paperList -> " + paperList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTestTableList() e.getMessage() -> " + e.getMessage());
		}
		
		return paperList;
	}

	@Override
	public List<Kh_PrdocList> getPrdocList(Kh_PrdocList prList) {
		List<Kh_PrdocList> prdocList = null;
		try {
			prdocList = session.selectList("com.postGre.bsHive.kh_TableMapper.getPrdocList", prList);
			System.out.println("KhTableDaoImplement getPrdocList() prdocList -> " + prdocList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return prdocList;
	}

	@Override
	public int getTotPrdocList(Kh_PrdocList prList) {
		int totPrdocList = 0;
		
		try {
			totPrdocList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotPrdocList", prList);
			System.out.println("KhTableDaoImplement getTotPrdocList() totPrdocList -> " + totPrdocList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotPrdocList() e.getMessage() -> " + e.getMessage());
		}
		
		return totPrdocList;
	}


	@Override
	public Kh_PrdocList getPrdocDetail(Kh_PrdocList prList) {
		Kh_PrdocList prDetail 	= null;
		
		try {
			prDetail = session.selectOne("com.postGre.bsHive.kh_TableMapper.getPrdocDetail", prList);
			System.out.println("KhTableDaoImplement getPrdocDetail() prDetail -> " + prDetail);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getPrdocDetail() e.getMessage() -> " + e.getMessage());
		}
		
		return prDetail;
	}
	
	
	
	//
	// Board
	//
	
	@Override
	public int getTotBoardList(Kh_pstList pList) {
		int totBoardList = 0;
		
		try {
			totBoardList = session.selectOne("com.postGre.bsHive.kh_TableMapper.getTotBoardList", pList);
			System.out.println("KhTableDaoImplement getTotBoardList() totBoardList -> " + totBoardList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getTotBoardList() e.getMessage() -> " + e.getMessage());
		}
		
		return totBoardList;
	}

	@Override
	public List<Kh_pstList> getBoardList(Kh_pstList pList) {
		List<Kh_pstList> pstList	= null;
		
		try {
			pstList = session.selectList("com.postGre.bsHive.kh_TableMapper.getBoardList", pList);
			System.out.println("KhTableDaoImplement getBoardList() pstList -> " + pstList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getBoardList() e.getMessage() -> " + e.getMessage());
		}
		
		return pstList;
	}

	@Override
	public void updateDelYnPst(Kh_pstList pList) {
		try {
			session.update("com.postGre.bsHive.kh_TableMapper.updateDelYnPst", pList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateDelYnPst() e.getMessage() -> " + e.getMessage());
		}
		
	}



	@Override
	public String getSortContent(Kh_SortCode sCode) {
		String sortContent = null;
		
		try {
			sortContent = session.selectOne("com.postGre.bsHive.kh_TableMapper.getSortContent", sCode);
			System.out.println("KhTableDaoImplement getSortContent() sortContent -> " + sortContent);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement getSortContent() e.getMessage() -> " + e.getMessage());
		}
		
		return sortContent;
	}



	@Override
	public void updateIsuueDate(Kh_PrdocList prList) {
		try {
			session.update("com.postGre.bsHive.kh_TableMapper.updateIsuueDate", prList);
		} catch (Exception e) {
			System.out.println("KhTableDaoImplement updateIsuueDate() e.getMessage() -> " + e.getMessage());
		}
	}




}
