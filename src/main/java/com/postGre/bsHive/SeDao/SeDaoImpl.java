 package com.postGre.bsHive.SeDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Onln_Lctr_List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeDaoImpl implements SeDao {
	
	private final SqlSession session;
	
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

		

}
