package com.postGre.bsHive.MhService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.MhDao.MhDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MhServiceImp implements MhService {
	private final MhDao md;
	
	
	@Override
	public int totalGongList() {
		int totalGongList = md.totalGongList();
		
		return totalGongList;
	}

	@Override
	public List<Pst> listGong(Pst pst) {
		List<Pst> listGong = null;
		listGong = md.listGong(pst);
		return listGong;
	}

	@Override
	public List<Pst> GongView(Pst pst) {
		List<Pst> GongView = null;
		GongView = md.GongView(pst);
	
		return GongView;
	}

	@Override
	public int GongDelete(int pst_num) {
		int GongDelete = 0;
		GongDelete = md.GongDelete(pst_num);
		return GongDelete;
	}

	@Override
	public int gongInsert(Pst pst) {
		int gongInsert = 0;
		gongInsert = md.gongInsert(pst);
		return gongInsert;
	}

	@Override
	public int updateGong(Pst pst) {
		int updateGong = 0;
		updateGong = md.updateGong(pst);
		return updateGong;
	}

	@Override
	public int totalYakList() {
		int totalYakList = 0;
		totalYakList = md.totalYakList();
		return totalYakList;
	}

	@Override
	public List<Pst> listYak(Pst pst) {
		List<Pst> listYak = null;
		listYak = md.listYak(pst);
		return listYak;
	}

	@Override
	public List<Pst> yakView(Pst pst) {
		List<Pst> yakView = null;
		yakView = md.yakView(pst);
		return yakView;
	}

	@Override
	public int yakInsert(Pst pst) {
		int yakInsert = 0;
		yakInsert = md.yakInsert(pst);
		return yakInsert;
	}

	@Override
	public int updateYak(Pst pst) {
		int updateYak = 0;
		updateYak = md.updateYak(pst);
		return updateYak;
	}

	@Override
	public int yakDelete(int pst_num) {
		int yakDelete = 0;
		yakDelete = md.yakDelete(pst_num);
		return yakDelete;
	}

	@Override
	public int totalFaqList() {
		int totalFaqList = 0;
		totalFaqList = md.totalFaqList();
		return totalFaqList;
	}

	@Override
	public List<Pst> listFaq(Pst pst) {
		List<Pst>listFaq = null;
		listFaq = md.listFaq(pst);
		return listFaq;
	}

	@Override
	public List<Pst> fnqView(Pst pst) {
		System.out.println("fnqView service pst->" +pst);
		List<Pst> fnqView = null;
		fnqView = md.fnqView(pst);
		System.out.println("fnqView service:" +fnqView);
		return fnqView;
	}

	@Override
	public int faqInsert(Pst pst) {
		int faqInsert = 0;
		faqInsert = md.faqInsert(pst);
		return faqInsert;
	}

	@Override
	public int faqDelete(int pst_num) {
		int faqDelete = 0;
		faqDelete = md.faqDelete(pst_num);
		return faqDelete;
	}

	@Override
	public int updatefaq(Pst pst) {
		int updatefaq =0;
		updatefaq = md.updatefaq(pst);
		return updatefaq;
	}

	@Override
	public int totaloneList() {
		int totaloneList = 0;
		totaloneList = md.totaloneList();
		return totaloneList;
	}

	@Override
	public List<Pst> listOne(Pst pst) {
		List<Pst> listOne = null;
		listOne = md.listOne(pst);
		return listOne;
	}

	@Override
	public List<Pst> oneView(Pst pst) {
		List<Pst> oneView = null;
		oneView = md.oneView(pst);
		return oneView;
	}

	@Override
	public int oneDelete(int pst_num) {
		int oneDelete = 0;
		oneDelete = md.oneDelete(pst_num);
		return oneDelete;
	}

	@Override
	public int oneInsert(Pst pst) {
		int oneInsert = 0;
		oneInsert = md.oneInsert(pst);
		return oneInsert;
	}

	@Override
	public int updateone(Pst pst) {
		int updateone = 0;
		updateone = md.updateone(pst);
		return updateone;
	}

	@Override
	public int noticeCreate(Pst pst, List<File> fileList) {
		int noticeCreate = 0;
		noticeCreate = md.noticeCreate(pst, fileList);
		return noticeCreate;
	}

}
