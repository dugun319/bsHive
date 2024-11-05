package com.postGre.bsHive.JhService;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.postGre.bsHive.Adto.User_Table;
import com.postGre.bsHive.Amodel.Lctr_Aply;
import com.postGre.bsHive.JhDao.JhDao;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JhServiceImpl implements JhService{

	private final JhDao jd;
	private final JavaMailSender mailSender;

	@Override
	public User_Table login(String eml, String pswd, String mbr_se) {
		User_Table user_table = null;
		user_table = jd.login(eml, pswd, mbr_se);
		
		return user_table;
	}
	
    @Override
    public String sendAuthCode(String user_email) {
        String setFrom = "fantamasitta@gmail.com";
        String title = "JmAuto 인증번호";
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

	@Override
	public List<Lctr_Aply> myClass(int unq_num) {
		return jd.myClass(unq_num);
	}

	@Override
	public int join(User_Table user) {
		int join = jd.join(user);
		return join;
	}


    

}
