 package com.postGre.bsHive.SeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Onln_Lctr_List;
import com.postGre.bsHive.Amodel.Lctr_View;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeDaoImpl implements SeDao {
	
	private final SqlSession session;
	
	private final DataSource dataSource;
	
	@Override
	public int onlnTotal() {
		int onlnTotal = 0;
		System.out.println("SeDaoImpl onlnTotal Start");
		
		try {
			onlnTotal = session.selectOne("com.postGre.bsHive.Mapper.seMapper.onlnTotal");
			System.out.println("SeDaoImpl onlnTotal ok "+onlnTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlnTotal;
	}
	
	@Override
	public List<Onln_Lctr_List> onlnList(Onln_Lctr_List onln_Lctr_List) {
		List<Onln_Lctr_List> onlnList = null;
		System.out.println("SeDaoImpl onlnList Start");
		
		try {
			onlnList = session.selectList("onlnList", onln_Lctr_List);
			System.out.println("SeDaoImpl onlnList " + onlnList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlnList;
	}

	@Override
	public Onln_Lctr_List onlnDtl(Integer lctrNum) {
	    Onln_Lctr_List result = null;
	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("LCTR_NUM", lctrNum);
	        result = session.selectOne("onlnDtl", params);
	        System.out.println("SeDaoImpl onlnList " + result);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public List<Onln_Lctr_List> lctrviewList(Integer unq_Num, Integer lctr_num) {
	    System.out.println("SeDaoImpl lctrviewList start");
	    List<Onln_Lctr_List> onln_Lctr_List = new ArrayList<>();

	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("unq_Num", unq_Num);
	        params.put("lctr_num", lctr_num);
	        
	        onln_Lctr_List = session.selectList("lctrviewList", params);
	        System.out.println("SeDaoImpl lctrviewList " + onln_Lctr_List);
	    } catch (Exception e) {
	        e.printStackTrace(); // 로깅 등을 추가할 수 있습니다.
	    }
	    return onln_Lctr_List;
	}

	public int updateLastDtl(int maxDtl, int lastDtl, int unqNum, int unitNum, int lctrNum, int lctrPace) {

	    String sql = "UPDATE \"LCTR_VIEW\" "
	               + "SET \"MAX_DTL\" = CASE WHEN ? > \"MAX_DTL\" THEN ? ELSE \"MAX_DTL\" END, "
	               + "\"LCTR_PACE\" = CASE WHEN ? > \"LCTR_PACE\" THEN ? ELSE \"LCTR_PACE\" END, "
	               + "\"CONTS_TYPE\" = CASE WHEN ? = 100 THEN '1' ELSE \"CONTS_TYPE\" END "
	               + "WHERE \"UNQ_NUM\" = ? AND \"UNIT_NUM\" = ? AND \"LCTR_NUM\" = ?";

	    try (Connection conn = dataSource.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, maxDtl);     // maxDtl (비교를 위해 사용)
	        pstmt.setInt(2, maxDtl);     // maxDtl (실제 업데이트에 사용)
	        pstmt.setInt(3, lctrPace);   // lctrPace (비교를 위해 사용)
	        pstmt.setInt(4, lctrPace);   // lctrPace (실제 업데이트에 사용)
	        pstmt.setInt(5, lctrPace);   // lctrPace (100일 때 CONTS_TYPE을 1로 설정하기 위한 조건)
	        pstmt.setInt(6, unqNum);     // unqNum 조건
	        pstmt.setInt(7, unitNum);    // unitNum 조건
	        pstmt.setInt(8, lctrNum);    // lctrNum 조건

	        int rowsAffected = pstmt.executeUpdate(); // 업데이트된 행 수 반환
	        return rowsAffected;

	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return 0;
	}



	@Override
	public int updateMaxDtl(int lastDtl, int unqNum, int unitNum, int lctrNum) {
	    String sql = "UPDATE \"LCTR_VIEW\" "
	               + "SET \"LAST_DTL\" = ? "
	               + "WHERE \"UNQ_NUM\" = ? AND \"UNIT_NUM\" = ? AND \"LCTR_NUM\" = ?";

	    // PreparedStatement를 사용하여 SQL 쿼리 실행
	    try (Connection conn = dataSource.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, lastDtl);   // MAX_DTL 업데이트
	        pstmt.setInt(2, unqNum);      // UNQ_NUM 조건
	        pstmt.setInt(3, unitNum);  // UNIT_NUM 조건
	        pstmt.setInt(4, lctrNum);     // LCTR_NUM 조건
	        
	        int rowsAffected = pstmt.executeUpdate(); // 업데이트된 행 수 반환

	        return rowsAffected; // 업데이트된 행 수 반환
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 예외 처리
	    }
	    return 0; // 업데이트가 이루어지지 않은 경우
	}

	@Override
	public List<Onln_Lctr_List> chapterList(String vdoId) {
		List<Onln_Lctr_List> chapterList = null;
		System.out.println("SeDaoImpl onlnList Start");
		
		try {
			chapterList = session.selectList("chapterList", vdoId);
			System.out.println("SeDaoImpl chapterList " + chapterList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chapterList;
	}

	@Override
	public int getMaxDtl(Lctr_View lctr_View) {
		int getMaxDtl = 0;
		System.out.println("SeDaoImpl getMaxDtl Start");
		
		try {
			getMaxDtl = session.selectOne("getMaxDtl",lctr_View);
			System.out.println("SeDaoImpl getMaxDtl ok "+getMaxDtl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMaxDtl;
	}






		
		

}
