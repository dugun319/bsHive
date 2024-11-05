package com.postGre.bsHive.JhService;

import java.util.List;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr_Aply;

public interface JhService {

	User_Table login(String eml, String pswd, String mbr_se);

	String sendAuthCode(String user_email);

	List<Lctr_Aply> myClass(int unq_num);

	int join(User_Table user);
	
	

}
