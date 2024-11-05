package com.postGre.bsHive.MhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Amodel.Pst;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MhDaoImp implements MhDao {
	
	@Autowired
	private final SqlSession session;
	
	@Override
	public int totalGongList() {
		int totalGongList = 0;
		try {
			totalGongList = session.selectOne("totalGongList");
		} catch (Exception e) {
			System.out.println("totalGongList: " +e);
		}
		return totalGongList;
	}

	@Override
	public List<Pst> listGong(Pst pst) {
		List<Pst> listGong = null;
		try {
			listGong = session.selectList("listGong", pst);
		} catch (Exception e) {
			System.out.println("listGong: " +e);
		}
		
		return listGong;
	}

	@Override
	public List<Pst> GongView(Pst pst) {
		List<Pst> GongView = null;
		try {
			GongView = session.selectList("GongView", pst);
		} catch (Exception e) {
			System.out.println("GongView" + e.getMessage());
		}
		
		return GongView;
	}

	@Override
	public int GongDelete(int pst_num) {
		int GongDelete = 0;
		try {
			GongDelete = session.update("GongDelete", pst_num);
		} catch (Exception e) {
		}
		return GongDelete;
	}

	@Override
	public int gongInsert(Pst pst) {
		int gongInsert = 0;
		try {
			gongInsert = session.insert("gongInsert", pst);
		} catch (Exception e) {
			System.out.println("gongInsert error" + e.getMessage());
		}
		return gongInsert;
	}

	@Override
	public int updateGong(Pst pst) {
		int updateGong = 0;
		try {
			updateGong = session.update("updateGong", pst);
		} catch (Exception e) {
			System.out.println("updateGong : " + e);
		}
		return updateGong;
	}

	@Override
	public int totalYakList() {
		int totalYakList = 0;
		try {
			totalYakList = session.selectOne("totalYakList");
		} catch (Exception e) {
		}
		return totalYakList;
	}

	@Override
	public List<Pst> listYak(Pst pst) {
		List<Pst> listYak = null;
		try {
			listYak = session.selectList("listYak", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listYak;
	}

	@Override
	public List<Pst> yakView(Pst pst) {
		List<Pst> yakView = null;
		try {
			yakView = session.selectList("yakView", pst);
		} catch (Exception e) {
		}
		return yakView;
	}

	@Override
	public int yakInsert(Pst pst) {
		int yakInsert =0;
		try {
			yakInsert = session.insert("yakInsert", pst);
		} catch (Exception e) {
		}
		return yakInsert;
	}

	@Override
	public int updateYak(Pst pst) {
		int updateYak = 0;
		try {
			updateYak = session.update("updateYak", pst);
		} catch (Exception e) {
		}
		return updateYak;
	}

	@Override
	public int yakDelete(int pst_num) {
		int yakDelete = 0;
		try {
			yakDelete = session.update("yakDelete", pst_num);
		} catch (Exception e) {
		}
		return yakDelete;
	}

	@Override
	public List<Pst> listFaq(Pst pst) {
		List<Pst> listFaq = null;
		try {
			listFaq = session.selectList("listFaq", pst);
		} catch (Exception e) {
		}
		return listFaq;
	}

	@Override
	public int totalFaqList() {
		int totalFaqList = 0;
		try {
			totalFaqList = session.selectOne("totalFaqList", totalFaqList);
		} catch (Exception e) {
		}
		
		return totalFaqList;
	}

	@Override
	public List<Pst> fnqView(Pst pst) {
		List<Pst> fnqView = null;
		try {
			fnqView = session.selectList("fnqView", fnqView);
		} catch (Exception e) {
		}
		return fnqView;
	}

}
