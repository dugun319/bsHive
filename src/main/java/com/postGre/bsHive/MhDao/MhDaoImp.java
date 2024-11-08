package com.postGre.bsHive.MhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postGre.bsHive.Amodel.File;
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
			System.out.println("gongInsert error" + e);
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
			System.out.println(e);
		}System.out.println("MhDaoImp pst->" +pst);
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
		System.out.println("dao" +pst.getPst_num());
		try {
			fnqView = session.selectList("fnqView", pst);
			System.out.println("fnqView dao:" +fnqView);

		} catch (Exception e) {
			System.out.println(e);
		}
		return fnqView;
	}

	@Override
	public int faqInsert(Pst pst) {
		int faqInsert = 0;
		try {
			faqInsert = session.insert("faqInsert",pst);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return faqInsert;
	}

	@Override
	public int faqDelete(int pst_num) {
		int faqDelete = 0;
		try {
			faqDelete = session.update("faqDelete", pst_num);
		} catch (Exception e) {
		}
		return faqDelete;
	}

	@Override
	public int updatefaq(Pst pst) {
		int updatefaq = 0;
		try {
			updatefaq = session.update("updatefaq", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return updatefaq;
	}

	@Override
	public int totaloneList() {
		int totaloneList = 0;
		try {
			totaloneList = session.selectOne("totaloneList");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return totaloneList;
	}

	@Override
	public List<Pst> listOne(Pst pst) {
		List<Pst> listOne = null;
		System.out.println("pst daoimp->" +pst);
		try {
			listOne = session.selectList("listOne", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOne;
	}

	@Override
	public List<Pst> oneView(Pst pst) {
		List<Pst> oneView = null;
		try {
			oneView = session.selectList("oneView", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return oneView;
	}

	@Override
	public int oneDelete(int pst_num) {
		int oneDelete = 0;
		try {
			oneDelete = session.update("oneDelete", pst_num);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return oneDelete;
	}

	@Override
	public int oneInsert(Pst pst) {
		int oneInsert =0;
		try {
			oneInsert = session.insert("oneInsert", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return oneInsert;
	}

	@Override
	public int updateone(Pst pst) {
		int updateone = 0;
		try {
			updateone = session.update("updateone", pst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return updateone;
	}

	@Override
    public int noticeCreate(Pst pst, List<File> fileList) {
        System.out.println("공지사항 작성");
        System.out.println("fileList ->" +fileList);

        // 파일 그룹 ID 생성
        int fileGroupId = createNewFileGroupId();
        System.out.println("fileGroupId->" +fileGroupId);
        // 공지사항 먼저 저장

        // 파일 정보가 있다면 저장
        if (fileList != null && !fileList.isEmpty()) {
        	
            for (File fileUpload : fileList) { // Filegroup 객체를 순회
            	int fileSeq = createNewFileSeq(fileGroupId);
                fileUpload.setFile_group(fileGroupId); // 파일 그룹 ID 설정
                fileUpload.setFile_no(fileSeq); // 파일 시퀀스 설정
                System.out.println("filegroup ------ > " + fileUpload);
                int fileResult = session.insert("FileUpload", fileUpload);
                System.out.println("파일 업로드 임 ㅋ");
             
                if (fileResult <= 0) {
                    // 파일 업로드 실패 처리
                    System.out.println("파일 업로드 실패");
                }
            }
        } 
        
        pst.setFile_group(fileGroupId);
        int result = session.insert("noticeCreate", pst);


        System.out.println("공지사항 업로드 result ---> " + result);
        return result;

    }

	private int createNewFileGroupId() {
	  int createNewFileGroupId = session.selectOne("getNextFileGroupId"); // 새로운 파일 그룹 ID 생성
	  System.out.println("createNewFileGroupId ->" +createNewFileGroupId);
	  return createNewFileGroupId;
	
	}

	private int createNewFileSeq(int fileGroupId) {
	    // file_seq는 파일 그룹에 대해 최대값을 가져오는 방법
	    Integer maxFileSeq = session.selectOne("getMaxFileSeq", fileGroupId);
	    System.out.println("maxFileSeq->" +maxFileSeq);
	    return (maxFileSeq == null) ? 1 : maxFileSeq + 1; // maxFileSeq가 null이면 1을 반환
	}

}
