package com.postGre.bsHive.JhDao;

<<<<<<< HEAD
public interface JhDao {

=======
import java.util.List;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr_Aply;

public interface JhDao {

	User_Table login(String eml, String pswd, String mbr_se);

	List<Lctr_Aply> myClass(int unq_num);

	int join(User_Table user);

>>>>>>> origin/br_test
}
