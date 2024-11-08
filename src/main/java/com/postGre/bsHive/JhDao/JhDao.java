package com.postGre.bsHive.JhDao;

import java.util.List;
import java.util.Map;

import com.postGre.bsHive.Adto.Jh_myClassroomList;
import com.postGre.bsHive.Adto.User_Table;

public interface JhDao {

	User_Table login(String eml, int mbr_se);

	List<Jh_myClassroomList> myOnlineClass(int unq_num);
	
	List<Jh_myClassroomList> myOfflineClass(int unq_num);

	int join(User_Table user);

	List<Jh_myClassroomList> myProfOnlineClass(int unq_num);

	List<Jh_myClassroomList> myProfOfflineClass(int unq_num);

	boolean checkEmailDuplicate(String email);

	User_Table realPwChk(String eml);

	void updateMyInfor(User_Table user);

	void realDeleteAccount(String eml);

	void realchangePassword(String eml, String new_pswd1);

	List<Map<String, Object>> classRegistrationStatus(int unq_num);

	List<Map<String, Object>> lectureRegistrationStatus(int unq_num);

	int findAccount(User_Table user);

	void updateTempPswd(String eml, String hashedTempPswd);




}
