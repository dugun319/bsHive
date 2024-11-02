package com.postGre.bsHive.SeDao;

<<<<<<< HEAD

public interface SeDao {

=======
import java.util.List;

import com.postGre.bsHive.Adto.Onln_Lctr_List;

public interface SeDao {

	int onlnTotal();
	List<Onln_Lctr_List> onlnList(Onln_Lctr_List onln_Lctr_List);
	
	Onln_Lctr_List onlnDtl(Integer lctr_Num);
	List<Onln_Lctr_List> lctrviewList(Integer unq_Num, Integer lctr_num);

>>>>>>> origin/br_test







	
}
