package com.postGre.bsHive.Acontroller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.File;
import com.postGre.bsHive.Amodel.Pst;
import com.postGre.bsHive.JhService.JhService;
import com.postGre.bsHive.MhService.MhService;
import com.postGre.bsHive.SeService.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value="/mh")
public class MhController {
	@Value("${spring.file.upload.path}")
    private String UPLOAD_DIRECTORY;
	
	@Autowired
	private final MhService ms;
	@Autowired
	private final JhService js;
	
	@GetMapping("/gongList")
	public String GongGiList(Pst pst, Model model, HttpSession session) {
		int totalGongList = ms.totalGongList();
		Paging page = new Paging(totalGongList, pst.getCurrentPage());
		pst.setStart(page.getStart());
		pst.setEnd(page.getEnd());
		List<Pst> listGong = ms.listGong(pst);
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int admin = user_Table1.getMbr_se();
		int writer = user_Table1.getUnq_num();
        System.out.println("JwController writeFormOnlnLctr user_Table->"+user_Table1);
        System.out.println("JwController writeFormOnlnLctr admin->"+admin);
		
		model.addAttribute("totalGongList", totalGongList);
		model.addAttribute("listGong", listGong);
		model.addAttribute("page", page);
		model.addAttribute("admin", admin);
		model.addAttribute("writer", writer);
		
		return "mh/gongList";
	}
	
	@GetMapping("/gongView")
	public String GongView(Pst pst, Model model, HttpSession session) {
		 List<Pst> GongView = ms.GongView(pst);
		 User_Table user = (User_Table) session.getAttribute("user"); 
	        if (user == null) { 
	        	 return "redirect:/jh/loginForm";
	        }
			User_Table user_Table1 = (User_Table) session.getAttribute("user");
			int writer = user_Table1.getUnq_num();
			model.addAttribute("writer", writer);
		 model.addAttribute("GongView",GongView);
		return "mh/gongView";
	}
	
	@GetMapping("/gongDelete")
	public String GongDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.GongDelete(pst_num);
		return "redirect:gongList";	
	}
	
	@GetMapping("/gongWrite")
	public String Gongwrite(Model model, HttpSession session) {
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int writer = user_Table1.getUnq_num();
		System.out.println("writer"+writer);
		model.addAttribute("writer", writer);
		

		return "mh/gongWrite";	
	}
	
	@PostMapping("/gongInsert")
	public String gongInsert(@RequestParam("file") List<MultipartFile> files, Pst pst, Model model, 
			@RequestParam("writer") int writer, HttpServletRequest request) {
		
		
		List<File> fileList = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        String uuid = UUID.randomUUID().toString();
                        String originalFileName = file.getOriginalFilename();
                        int fileSize = (int) file.getSize();
                        String uniqueFileName = uuid + "_" + originalFileName;
                        Path filePath = Paths.get("C:/upload/" + uniqueFileName);
                        Files.createDirectories(filePath.getParent());
                        file.transferTo(filePath.toFile());

                        // File 객체에 파일 정보 설정
                        File fileRecord = new File();
                        fileRecord.setFile_group(0); // 필요시 그룹 이름 설정
                        fileRecord.setFile_no(0); // 파일 번호 설정
                        fileRecord.setFile_unq(uuid);
                        fileRecord.setDwnld_file_nm(originalFileName);
                        fileRecord.setFile_size(fileSize);
                        fileRecord.setFile_path_nm("C:/upload/" + uniqueFileName);

                        fileList.add(fileRecord);
                        System.out.println("fileList controller->" +fileList);
                        
                      
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("파일이 없습니다.");
            
        }
        int noticeCreate = ms.noticeCreate(pst, fileList) ;
		return "redirect:/mh/gongList";
	}
	 @GetMapping("/download")
	    public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) {
		 	System.out.println(filePath);
	        try {
	            Path fullPath = Paths.get(filePath).toAbsolutePath();
	            Resource resource = new UrlResource(fullPath.toUri());
	   		 System.out.println(fullPath);
			 System.out.println(resource);

	            if (resource.exists() && resource.isReadable()) {
	                String fileName = fullPath.getFileName().toString();
	                return ResponseEntity.ok()
	                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                        .body(resource);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().build();
	        }
	    }
		
	@GetMapping("/gongModify")
	public String gongModify (Pst pst, Model model) {
		 List<Pst> GongView = ms.GongView(pst);
		 
		 model.addAttribute("GongView",GongView);
		return "mh/gongModify";
	}
	
	@PostMapping("/gongModifyDB")
	public String gongModifyDB (Pst pst) {
		int updateCount = ms.updateGong(pst);
		System.out.println("updateCount->" +updateCount);
		return "redirect:gongList";
	}
	
	@GetMapping("/yakList")
	public String yakGiList(Pst pst, Model model, HttpSession session) {
		int totalYakList = ms.totalYakList();
		Paging page = new Paging(totalYakList, pst.getCurrentPage());
		pst.setStart(page.getStart());
		pst.setEnd(page.getEnd());
		List<Pst> listYak = ms.listYak(pst);
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int admin = user_Table1.getMbr_se();
		System.out.println("listYak" +listYak);
		model.addAttribute("totalYakList", totalYakList);
		model.addAttribute("listYak", listYak);
		model.addAttribute("page", page);
		model.addAttribute("admin", admin);
		
		return "mh/yakList";
	}
	
	@GetMapping("/yakView")
	public String yakView(Pst pst, Model model, HttpSession session) {
		 List<Pst> yakView = ms.yakView(pst);
		 User_Table user = (User_Table) session.getAttribute("user"); 
	        if (user == null) { 
	        	 return "redirect:/jh/loginForm";
	        }
			User_Table user_Table1 = (User_Table) session.getAttribute("user");
			int writer = user_Table1.getUnq_num();
			model.addAttribute("writer", writer);
			
		 
		 model.addAttribute("yakView", yakView);
		 System.out.println("yakView->" +yakView);
		
		return "mh/yakView";
	}
	@GetMapping("/yakWrite")
	public String yakwrite(Model model, HttpSession session) {
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int writer = user_Table1.getUnq_num();
		System.out.println("writer"+writer);
		model.addAttribute("writer", writer);
		
		return "mh/yakWrite";	
	}
	
	@PostMapping("/yakInsert")
	public String yakInsert (@RequestParam("file") MultipartFile[] files, Pst pst, Model model, @RequestParam("writer") int writer) {
		StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성
	    System.out.println("writer oneinsert" +writer);

	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            try {
	                // 고유 파일 이름 생성 (UUID 사용)
	                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	                Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueFileName);

	                // 파일 저장
	                Files.createDirectories(filePath.getParent());  // 디렉토리 생성
	                file.transferTo(filePath.toFile());

	                fileGroupPaths.append("C:/upload/").append(uniqueFileName).append(",");
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // 모든 파일 경로 설정 후 pst 객체에 저장
	    if (fileGroupPaths.length() > 0) {
	        fileGroupPaths.setLength(fileGroupPaths.length() - 1); // 마지막 콤마 제거
	    }
	    pst.setFile_group(Integer.parseInt(fileGroupPaths.toString())); // pst 객체에 file_group 경로 설정
	    pst.setUnq_num(writer);
	    System.out.println("pst controller"+pst);
	    // 데이터베이스에 pst 객체 저장
	    int insertResult = ms.yakInsert(pst); 
	    if (insertResult <= 0) {
	        model.addAttribute("message", "데이터베이스 저장 중 오류가 발생했습니다.");
	        return "error"; // 오류 페이지로 이동
	    }

	    return "redirect:yakList"; // 업로드 후 목록 페이지로 이동
	}
	
	@GetMapping("/yakModify")
	public String yakModify (Pst pst, Model model) {
		 List<Pst> yakView = ms.yakView(pst);
		 
		 model.addAttribute("yakView",yakView);
		return "mh/yakModify";
	}
	
	@PostMapping("/yakModifyDB")
	public String yakModifyDB (Pst pst) {
		int updateCount = ms.updateYak(pst);
		return "redirect:yakList";
	}
	
	@GetMapping("/yakDelete")
	public String yakDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.yakDelete(pst_num);
		return "redirect:yakList";	
	}
	
	@GetMapping("/fnqList")
	public String faqList(Pst pst, Model model, HttpSession session) {
		
		int totalFaqList = ms.totalFaqList();
		Paging page = new Paging(totalFaqList, pst.getCurrentPage());
		pst.setStart(page.getStart());
		pst.setEnd(page.getEnd());
		List<Pst> listFaq = ms.listFaq(pst);
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int admin = user_Table1.getMbr_se();
		model.addAttribute("totalFaqList", totalFaqList);
		model.addAttribute("listFaq", listFaq);
		model.addAttribute("page", page);
		model.addAttribute("admin", admin);
		
		return "mh/fnqList";
	}
	
	@GetMapping("/fnqView")
	public String fnqView(Pst pst, Model model, HttpSession session) {
		 System.out.println("fnqView controller pst->" + pst);
		
		 List<Pst> fnqView = ms.fnqView(pst);
		 User_Table user = (User_Table) session.getAttribute("user"); 
	        if (user == null) { 
	        	 return "redirect:/jh/loginForm";
	        }
			User_Table user_Table1 = (User_Table) session.getAttribute("user");
			int writer = user_Table1.getUnq_num();
			model.addAttribute("writer", writer);
		 model.addAttribute("fnqView",fnqView);
		 System.out.println("fnqView controller:" + fnqView);
		 System.out.println(pst.getPst_num());
		return "mh/fnqView";
	}
	@GetMapping("/fnqWrite")
	public String faqwrite(Model model, HttpSession session) {
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int writer = user_Table1.getUnq_num();
		System.out.println("writer"+writer);
		model.addAttribute("writer", writer);
		
		return "mh/fnqWrite";	
	}
	
	@PostMapping("/faqInsert")
	public String faqInsert (@RequestParam("file") MultipartFile[] files, Pst pst, Model model, @RequestParam("writer") int writer) {
		StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성
	    System.out.println("writer oneinsert" +writer);

	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            try {
	                // 고유 파일 이름 생성 (UUID 사용)
	                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	                Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueFileName);

	                // 파일 저장
	                Files.createDirectories(filePath.getParent());  // 디렉토리 생성
	                file.transferTo(filePath.toFile());

	                fileGroupPaths.append("C:/upload/").append(uniqueFileName).append(",");
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // 모든 파일 경로 설정 후 pst 객체에 저장
	    if (fileGroupPaths.length() > 0) {
	        fileGroupPaths.setLength(fileGroupPaths.length() - 1); // 마지막 콤마 제거
	    }
	    pst.setFile_group(Integer.parseInt(fileGroupPaths.toString())); // pst 객체에 file_group 경로 설정
	    pst.setUnq_num(writer);
	    System.out.println("pst controller"+pst);
	    // 데이터베이스에 pst 객체 저장
	    int insertResult = ms.faqInsert(pst); 
	    if (insertResult <= 0) {
	        model.addAttribute("message", "데이터베이스 저장 중 오류가 발생했습니다.");
	        return "error"; // 오류 페이지로 이동
	    }

	    return "redirect:faqList"; // 업로드 후 목록 페이지로 이동
	}
	
	@GetMapping("/faqDelete")
	public String faqDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.faqDelete(pst_num);
		return "redirect:fnqList";	
	}
	
	@GetMapping("/fnqModify")
	public String fnqModify (Pst pst, Model model) {
		 List<Pst> fnqView = ms.fnqView(pst);
		 
		 model.addAttribute("fnqView",fnqView);
		return "mh/fnqModify";
	}
	
	@PostMapping("/faqModifyDB")
	public String faqModifyDB (Pst pst) {
		int updateCount = ms.updatefaq(pst);
		return "redirect:fnqList";
	}
	
	@GetMapping("/oneList")
	public String oneList(Pst pst, Model model, HttpSession session) {
		int totaloneList = ms.totaloneList();
		Paging page = new Paging(totaloneList, pst.getCurrentPage());
		pst.setStart(page.getStart());
		pst.setEnd(page.getEnd());
		
		List<Pst> listOne = ms.listOne(pst);
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int admin = user_Table1.getMbr_se();
		int writer = user_Table1.getUnq_num();
		pst.setUnq_num(writer);
		System.out.println("writer" +writer);
		System.out.println("pst" +pst);
		System.out.println("listOne+"+listOne);
		System.out.println("totaloneList+"+totaloneList);
		model.addAttribute("totaloneList", totaloneList);
		model.addAttribute("listOne", listOne);
		model.addAttribute("page", page);
		model.addAttribute("admin", admin);
		model.addAttribute("writer", writer);
		
		
		return "mh/oneList";
	}
	

	@GetMapping("/oneView")
	public String oneView(Pst pst, Model model, HttpSession session) {
		 List<Pst> oneView = ms.oneView(pst);
		 User_Table user = (User_Table) session.getAttribute("user"); 
	        if (user == null) { 
	        	 return "redirect:/jh/loginForm";
	        }
			User_Table user_Table1 = (User_Table) session.getAttribute("user");
			int writer = user_Table1.getUnq_num();
			model.addAttribute("writer", writer);
		 model.addAttribute("oneView",oneView);
		
		return "mh/oneView";
	}
	
	@GetMapping("/oneDelete")
	public String oneDelete(@RequestParam("pst_num") int pst_num) {
		int result = ms.oneDelete(pst_num);
		return "redirect:oneList";	
	}
	
	@GetMapping("/oneWrite")
	public String onewrite(Model model, HttpSession session) {
		User_Table user = (User_Table) session.getAttribute("user"); 
        if (user == null) { 
        	 return "redirect:/jh/loginForm";
        }
		User_Table user_Table1 = (User_Table) session.getAttribute("user");
		int writer = user_Table1.getUnq_num();
		System.out.println("writer"+writer);
		model.addAttribute("writer", writer);
		return "mh/oneWrite";	
	}
	
	@PostMapping("/oneInsert")
	public String oneInsert(@RequestParam("file") MultipartFile[] files,@RequestParam("writer") int writer,Pst pst, Model model) {
	    StringBuilder fileGroupPaths = new StringBuilder(); // 여러 파일 경로를 저장할 StringBuilder 생성
	    System.out.println("writer oneinsert" +writer);

	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            try {
	                // 고유 파일 이름 생성 (UUID 사용)
	                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	                Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueFileName);

	                // 파일 저장
	                Files.createDirectories(filePath.getParent());  // 디렉토리 생성
	                file.transferTo(filePath.toFile());

	                fileGroupPaths.append("C:/upload/").append(uniqueFileName).append(",");
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // 모든 파일 경로 설정 후 pst 객체에 저장
	    if (fileGroupPaths.length() > 0) {
	        fileGroupPaths.setLength(fileGroupPaths.length() - 1); // 마지막 콤마 제거
	    }
	    pst.setFile_group(Integer.parseInt(fileGroupPaths.toString())); // pst 객체에 file_group 경로 설정
	    pst.setUnq_num(writer);
	    System.out.println("pst controller"+pst);
	    // 데이터베이스에 pst 객체 저장
	    int insertResult = ms.oneInsert(pst); 
	    if (insertResult <= 0) {
	        model.addAttribute("message", "데이터베이스 저장 중 오류가 발생했습니다.");
	        return "error"; // 오류 페이지로 이동
	    }

	    return "redirect:oneList"; // 업로드 후 목록 페이지로 이동
	}
	
	
	@GetMapping("/oneModify")
	public String oneModify (Pst pst, Model model) {
		 List<Pst> oneView = ms.oneView(pst);
		 
		 model.addAttribute("oneView",oneView);
		return "mh/oneModify";
	}
	
	@PostMapping("/oneModifyDB")
	public String oneModifyDB (Pst pst) {
		int updateCount = ms.updateone(pst);
		System.out.println("updateCount->" +updateCount);
		return "redirect:oneList";
	}
	
}
