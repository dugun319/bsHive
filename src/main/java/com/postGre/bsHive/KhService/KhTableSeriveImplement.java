package com.postGre.bsHive.KhService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Kh_EmpList;
import com.postGre.bsHive.Adto.Kh_LctrList;
import com.postGre.bsHive.Adto.Kh_Lctrm;
import com.postGre.bsHive.Adto.Kh_PrdocList;
import com.postGre.bsHive.Adto.Kh_ScholarshipList;
import com.postGre.bsHive.Adto.Kh_SortCode;
import com.postGre.bsHive.Adto.Kh_StdntList;
import com.postGre.bsHive.Adto.Kh_pstList;
import com.postGre.bsHive.Amodel.Lgn;
import com.postGre.bsHive.KhDao.KhTableDao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KhTableSeriveImplement implements KhTableSerive {
	
	private final KhTableDao khTableDao;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public List<Kh_PrdocList> getTestTableList() {
		List<Kh_PrdocList> paperList = khTableDao.getTestTableList();
		return paperList;
	}

	@Override
	public List<Kh_PrdocList> getPrdocList(Kh_PrdocList prList) {
		List<Kh_PrdocList> prdocList = khTableDao.getPrdocList(prList);
		return prdocList;
	}

	@Override
	public int getTotPrdocList(Kh_PrdocList prList) {
		int totPrdocList	= khTableDao.getTotPrdocList(prList);
		return totPrdocList;
	}

	@Override
	public int getTotStdntList(Kh_StdntList stList) {
		int totStdntList	= khTableDao.getTotStdntList(stList);
		return totStdntList;
	}

	@Override
	public List<Kh_StdntList> getStdntList(Kh_StdntList stList) {
		List<Kh_StdntList> stdntList = khTableDao.getStdntList(stList);
		return stdntList;
	}

	@Override
	public int updateLgnDelYn(Lgn lgn) {
		int result = khTableDao.updateLgnDelYn(lgn);
		return result;
	}

	@Override
	public int getTotEmpList(Kh_EmpList eList) {
		int totProfList	= khTableDao.getTotEmpList(eList);
		return totProfList;
	}

	@Override
	public List<Kh_EmpList> getEmpList(Kh_EmpList eList) {
		List<Kh_EmpList> empList = khTableDao.getEmpList(eList);
		return empList;
	}

	@Override
	public int getTotLctrList(Kh_LctrList lcList) {
		int totLctrList	= khTableDao.getTotLctrList(lcList);
		return totLctrList;
	}

	@Override
	public List<Kh_LctrList> getLctrList(Kh_LctrList lcList) {
		List<Kh_LctrList> lctrList = khTableDao.getLctrList(lcList);
		return lctrList;
	}

	@Override
	public Kh_LctrList getLctrDetail(Kh_LctrList lcList) {
		Kh_LctrList lctrDetail	= khTableDao.getLctrDetail(lcList);
		return lctrDetail;
	}

	@Override
	public void sendRequest(Kh_LctrList lctrDetail) {
		SimpleMailMessage emailBody = new SimpleMailMessage();
		String mailAddress	  		=	"dugun319@naver.com";
		// String mailAddress		= lctrDetail.getEml();
		String mailStr		  		= lctrDetail.getEmlContent();
		
		
		emailBody.setTo(mailAddress);									// 받는 사람 이메일
		emailBody.setSubject("안녕하세요 교수님, BSHive 학사지원처입니다");		// 이메일 제목
		emailBody.setText(mailStr);									 	// 이메일 내용
        
        try {
            // 메일 보내기
            this.mailSender.send(emailBody);
            System.out.println("KhTableSeriveImplement sendRequest is completed!");
        } catch (MailException e) {
            throw e;
        }

	}

	@Override
	public void updateAplyType(Kh_LctrList lcList) {
		khTableDao.updateAplyType(lcList);
	}
	
	@Transactional
	@Override
	public void openLecture(Kh_LctrList lcList) {
		khTableDao.openLecture(lcList);
		khTableDao.insertAtdncType(lcList);	
		khTableDao.updateOflLctr(lcList);
	}

	@Override
	public int getTotBoardList(Kh_pstList pList) {
		int totBoardList	= khTableDao.getTotBoardList(pList);
		return totBoardList;
	}

	@Override
	public List<Kh_pstList> getBoardList(Kh_pstList pList) {
		List<Kh_pstList> pstList	= khTableDao.getBoardList(pList);
				
		return pstList;
	}

	@Override
	public void updateDelYnPst(Kh_pstList pList) {
		khTableDao.updateDelYnPst(pList);
	}

	@Override
	public int getTotSchList(Kh_ScholarshipList sList) {
		int totSchList	= khTableDao.getTotSchList(sList);
		return totSchList;
	}

	@Override
	public Kh_ScholarshipList getSchDetail(Kh_ScholarshipList sList) {
		Kh_ScholarshipList schDetail	= khTableDao.getSchDetail(sList);
		return schDetail;
	}

	@Override
	public void insertSchDetail(Kh_ScholarshipList schDetail) {
		khTableDao.insertSchDetail(schDetail);		
	}

	@Override
	public List<Kh_ScholarshipList> getSchList(Kh_ScholarshipList sList) {
		List<Kh_ScholarshipList> schList	= khTableDao.getSchList(sList);
		
		return schList;
	}

	@Override
	public List<Kh_Lctrm> getLctrmList(String yearAndSemester) {
		List<Kh_Lctrm> lctrmList	= khTableDao.getLctrmList(yearAndSemester);
		
		return lctrmList;
	}

	@Override
	public String getScholarahipImgPath(Kh_ScholarshipList schList) {
		String filePath = khTableDao.getScholarahipImgPath(schList);
		
		return filePath;
	}

	@Override
	public void updateGiveStss(Kh_ScholarshipList schList) {
		khTableDao.updateGiveStss(schList);
	}

	@Override
	public Kh_PrdocList getPrdocDetail(Kh_PrdocList prList) {
		Kh_PrdocList prDetail = khTableDao.getPrdocDetail(prList);
		return prDetail;
	}

	@Override
	public String getSortContent(Kh_SortCode sCode) {
		String sortContent	= khTableDao.getSortContent(sCode);
		
		return sortContent;
	}

	@Override
	public void updateIsuueDate(Kh_PrdocList prList) {
		khTableDao.updateIsuueDate(prList);
	}

}
