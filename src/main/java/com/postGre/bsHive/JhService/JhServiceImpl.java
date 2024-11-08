package com.postGre.bsHive.JhService;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.Jh_myClassroomList;
import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.JhDao.JhDao;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JhServiceImpl implements JhService{

	private final JhDao jd;
	private final JavaMailSender mailSender;
	private final BCryptPasswordEncoder passwordEncoder;

	// 로그인 로직
	@Override
	public User_Table login(String eml, String pswd, int mbr_se) {
		User_Table user_table  = jd.login(eml, mbr_se);
		// 일치하는 아이디가 없음
		if (user_table == null) {
			return null; 
		}
	
		// DB에 저장된 암호화된 비번과 사용자가 입력한 비번과 비교
		boolean passwordMatch = passwordEncoder.matches(pswd, user_table.getPswd());
	
		// 비번이 다름
		if (!passwordMatch) {
			return null; 
		}
		return user_table;
	}

	// 이메일로 인증번호 보내는 로직
    @Override
    public String sendAuthCode(String user_email) {
        String setFrom = "busanhive@gmail.com";
        String title = "busanHive 인증번호";
        String authCode = String.valueOf((int) (Math.random() * 900000) + 100000);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(setFrom);
            messageHelper.setTo(user_email);
            messageHelper.setSubject(title);
            messageHelper.setText("인증번호는 " + authCode + " 입니다.");

            mailSender.send(message);
            return authCode; // 인증번호를 반환
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }

    //(학생)나의 온라인 강의 목록 조회
	@Override
	public List<Jh_myClassroomList> myOnlineClass(int unq_num) {
		return jd.myOnlineClass(unq_num);
	}
	
    //(학생)나의 오프라인 강의 목록 조회	
	@Override
	public List<Jh_myClassroomList> myOfflineClass(int unq_num) {
		return jd.myOfflineClass(unq_num);
	}
	
	//(교수)나의 온라인 강의 목록 조회
	@Override
	public List<Jh_myClassroomList> myProfOnlineClass(int unq_num) {
		return jd.myProfOnlineClass(unq_num);
	}
	
	//(교수)나의 온라인 강의 목록 조회
	@Override
	public List<Jh_myClassroomList> myProfOfflineClass(int unq_num) {
		return jd.myProfOfflineClass(unq_num);
	}

	//회원가입 로직
	@Override
	public int join(User_Table user) {
		String encodePassword = passwordEncoder.encode(user.getPswd());
		user.setPswd(encodePassword);
		int join = jd.join(user);
		return join;
	}
	
	//회원가입시 이메일 중복체크 
	@Override
	public boolean checkEmailDuplicate(String email) {
		   return jd.checkEmailDuplicate(email);
    }
	
	//비밀번호 체크 로직
	@Override
	public User_Table realPwChk(String eml, String pswd) {
		User_Table user = new User_Table();
		user = jd.realPwChk(eml);
		boolean passwordMatch = passwordEncoder.matches(pswd, user.getPswd());
		
		// 비번이 다름
		if (!passwordMatch) {
			return null; 
		}
		return user;
	}

	//회원정보 변경하는 로직
	@Override
	public void updateMyInfor(User_Table user) {
		jd.updateMyInfor(user);
	}

	//회원탈퇴하는 로직
	@Override
	public void realDeleteAccount(String eml) {
		jd.realDeleteAccount(eml);
	}

	//비밀번호 변경하는 로직
	@Override
	public void realchangePassword(String eml, String new_pswd) {
		String new_pswd1 = passwordEncoder.encode(new_pswd);
		jd.realchangePassword(eml, new_pswd1);
	}

	//(학생)수강신청현황 조회 로직
	@Override
	public List<Map<String, Object>> classRegistrationStatus(int unq_num) {
		List<Map<String, Object>> classRegistrationStatus = null;
		classRegistrationStatus = jd.classRegistrationStatus(unq_num);
		return classRegistrationStatus;
	}
	
	//(교수)강의신청현황 조회 로직
	@Override
	public List<Map<String, Object>> lectureRegistrationStatus(int unq_num) {
		List<Map<String, Object>> lectureRegistrationStatus = null;
		lectureRegistrationStatus = jd.lectureRegistrationStatus(unq_num);
		return lectureRegistrationStatus;
	}

	//계정 유무 조회하는 로직
	@Override
	public int findAccount(User_Table user) {
		int result = jd.findAccount(user);
		return result;
	}
	
	//임시 비밀번호 랜덤 생성하고 db에 업데이트하는 로직
	@Override
	public String createtempPswd(String eml) {
		String tempPswd = createRandomPw();
		String hashedTempPswd = passwordEncoder.encode(tempPswd);
		jd.updateTempPswd(eml, hashedTempPswd);
		return tempPswd;
	}
	
	// 임시 비밀번호 랜덤 생성하는 규칙
	private String createRandomPw() {
		int length = 6;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder pswd = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			pswd.append(chars.charAt(random.nextInt(chars.length())));
		}
		return pswd.toString();
	}

	//이메일로 임시 비밀번호 보내는 로직
	@Override
	public int sendTempPswd(String eml, String tempPswd) {
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("busanhive@gmail.com");
    	message.setTo(eml);
    	message.setSubject("BusanHive 임시 비밀번호 발급");
    	message.setText("귀하의 임시 비밀번호는 " + tempPswd + " 입니다.");
    	
    	int result = 0;
        try {
            mailSender.send(message); // 이메일 전송
            System.out.println("임시 비밀번호가 발송되었습니다.");
            result = 1;
            
        } catch (Exception e) {
            System.err.println("이메일 전송 실패: " + e.getMessage());
            result = 0;
        }
        return result;
	}
	
		
	
}


