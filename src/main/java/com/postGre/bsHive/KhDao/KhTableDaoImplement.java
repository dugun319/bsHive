package com.postGre.bsHive.KhDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Amodel.Lgn;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KhTableDaoImplement implements KhTableDao {
	private final SqlSession session;
	
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

}
