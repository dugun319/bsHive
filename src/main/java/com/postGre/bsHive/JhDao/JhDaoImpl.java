package com.postGre.bsHive.JhDao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Adto.Jh_myClassroomList;
import com.postGre.bsHive.Adto.User_Table;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JhDaoImpl implements JhDao {
	
	private final SqlSession session;
	
	@Override
	public User_Table login(String eml, int mbr_se) {
		User_Table user_table = new User_Table();
		try {
			user_table.setEml(eml);
			user_table.setMbr_se(mbr_se);

			user_table = session.selectOne("com.postGre.bsHive.jh_Mapper.login", user_table);
			System.out.println("로그인 이메일 정보 = " + user_table.getEml());
		} catch (Exception e) {
			System.out.println("dao 로그인 오류" + e.getMessage());
		}
		return user_table;
	}

	@Override
	public User_Table realPwChk(String eml) {
		User_Table user = new User_Table();
		try {
			user.setEml(eml);
			System.out.println("dao유저정보"+ user);
			user = session.selectOne("com.postGre.bsHive.jh_Mapper.realPwChk", user);
		} catch (Exception e) {
			System.out.println("dao 유저정보 불러오기 오류" + e.getMessage());
		}
		return user;
	}

	@Override
	public void updateMyInfor(User_Table user) {
		int result = 0;
		System.out.println("@@@@@@@@@@"+user);
		try {
			result = session.update("com.postGre.bsHive.jh_Mapper.updateMyInfor", user);
			System.out.println("변경완료 = " + result);
		} catch (Exception e) {
			System.out.println("dao 회원정보 변경 오류" + e.getMessage());
		}
	
	}
	
	@Override
	public List<Map<String, Object>> classRegistrationStatus(int unq_num) {
		System.out.println("@@@@@unq_num = " + unq_num);
	    List<Map<String, Object>> classRegistrationStatus = null;
	    try {
	        classRegistrationStatus = session.selectList("com.postGre.bsHive.jh_Mapper.classRegistrationStatus", unq_num);
	        System.out.println("dao 수강신청현황 갯수 = " + classRegistrationStatus.size());
	    } catch (Exception e) {
	        System.out.println("dao 수강신청현황 오류: " + e.getMessage());
	    }
	    return classRegistrationStatus;
	}

	@Override
	public List<Map<String, Object>> lectureRegistrationStatus(int unq_num) {
		System.out.println("@@@@@unq_num = " + unq_num);
	    List<Map<String, Object>> lectureRegistrationStatus = null;
	    try {
	    	lectureRegistrationStatus = session.selectList("com.postGre.bsHive.jh_Mapper.lectureRegistrationStatus", unq_num);
	        System.out.println("dao 수강신청현황 갯수 = " + lectureRegistrationStatus.size());
	    } catch (Exception e) {
	        System.out.println("dao 수강신청현황 오류: " + e.getMessage());
	    }
	    return lectureRegistrationStatus;
	}

	@Override
	public List<Jh_myClassroomList> myOnlineClass(int unq_num) {
		List<Jh_myClassroomList> myClass = null;
		try {
			myClass = session.selectList("com.postGre.bsHive.jh_Mapper.myOnlineClass",unq_num);
		} catch (Exception e) {
			System.out.println("dao 나의 강의실 오류"+e.getMessage());
		}
		return myClass;
	}

	@Override
	public List<Jh_myClassroomList> myOfflineClass(int unq_num) {
		List<Jh_myClassroomList> myClass = null;
		try {
			myClass = session.selectList("com.postGre.bsHive.jh_Mapper.myOfflineClass",unq_num);
		} catch (Exception e) {
			System.out.println("dao 나의 강의실 오류"+e.getMessage());
		}
		return myClass;
	}

	@Override
	public List<Jh_myClassroomList> myProfOnlineClass(int unq_num) {
		List<Jh_myClassroomList> myClass = null;
		try {
			myClass = session.selectList("com.postGre.bsHive.jh_Mapper.myProfOnlineClass",unq_num);
			System.out.println("dao 나의 강의실 목록"+myClass);
		} catch (Exception e) {
			System.out.println("dao 나의 강의실 오류"+e.getMessage());
		}
		return myClass;
	}

	@Override
	public List<Jh_myClassroomList> myProfOfflineClass(int unq_num) {
		List<Jh_myClassroomList> myClass = null;
		try {
			myClass = session.selectList("com.postGre.bsHive.jh_Mapper.myProfOfflineClass",unq_num);
			System.out.println("dao 나의 강의실 목록"+myClass);
		} catch (Exception e) {
			System.out.println("dao 나의 강의실 오류"+e.getMessage());
		}
		return myClass;
	}
	
	@Override
	public int join(User_Table user) {
		int join = session.insert("com.postGre.bsHive.jh_Mapper.join", user);
		return join;
	}

	 @Override
	 public boolean checkEmailDuplicate(String email) {
        int count = session.selectOne("com.postGre.bsHive.jh_Mapper.checkEmailDuplicate", email);
        return count > 0;
	}


	@Override
	public void realDeleteAccount(String eml) {
		int result = 0;
		System.out.println("탈퇴 이메일 = " + eml);
		try {
			result = session.update("com.postGre.bsHive.jh_Mapper.realDeleteAccount", eml);
		} catch (Exception e) {
			System.out.println("dao 회원정보 변경 오류" + e.getMessage());
		}
	
	}

	@Override
	public void realchangePassword(String eml, String new_pswd1) {
	    int result = 0;
	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("eml", eml);
	        params.put("new_pswd", new_pswd1);
	        result = session.update("com.postGre.bsHive.jh_Mapper.realchangePassword", params);
	        System.out.println("비번 변경 result = "+result);
	    } catch (Exception e) {
	        System.out.println("dao 회원정보 변경 오류: " + e.getMessage());
	    }
	}

	@Override
	public int findAccount(User_Table user) {
		int result = session.selectOne("com.postGre.bsHive.jh_Mapper.findAccount", user);
		return result;
	}

	@Override
	public void updateTempPswd(String eml, String hashedTempPswd) {
		User_Table user = new User_Table();
		user.setEml(eml);
		user.setPswd(hashedTempPswd);
		System.out.println("db에 업데이트 되는 임시비밀번호 = " + hashedTempPswd);
		int updateTempPswd = session.update("com.postGre.bsHive.jh_Mapper.updateTempPswd", user);
		if (updateTempPswd == 1) {
			System.out.println("임시비밀번호 db에 업데이트 성공 = " + updateTempPswd);
		} else {
			System.out.println("임시비밀번호 db에 업데이트 실패 = " + updateTempPswd);
		}
	}
	
	
	
}
