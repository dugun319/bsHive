package com.postGre.bsHive.JhService;

import java.util.List;
import java.util.Map;

import com.postGre.bsHive.Adto.Jh_myClassroomList;
import com.postGre.bsHive.Adto.User_Table;

public interface JhService {

	User_Table login(String eml, String pswd, int mbr_se);

	String sendAuthCode(String user_email);

	List<Jh_myClassroomList> myOnlineClass(int unq_num);
	
	List<Jh_myClassroomList> myOfflineClass(int unq_num);

	int join(User_Table user);

	List<Jh_myClassroomList> myProfOnlineClass(int unq_num);

	List<Jh_myClassroomList> myProfOfflineClass(int unq_num);

	boolean checkEmailDuplicate(String email);

	User_Table realPwChk(String eml, String pswd);

	void updateMyInfor(User_Table user);

	void realDeleteAccount(String eml);

	void realchangePassword(String eml, String new_pswd);

	List<Map<String, Object>> classRegistrationStatus(int unq_num);

	List<Map<String, Object>> lectureRegistrationStatus(int unq_num);

	int findAccount(User_Table user);

	String createtempPswd(String eml);

	int sendTempPswd(String eml, String tempPswd);





	
	
	

}
