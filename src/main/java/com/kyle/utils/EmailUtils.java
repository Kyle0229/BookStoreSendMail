package com.kyle.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;

/**
 * Created by 54110 on 2019-09-19.
 */
@Component
public class EmailUtils {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javamailSender;
    //sendTo：用户注册时输入的邮箱
    public String sendMail(String sendTo,String uid){
        try{
        MimeMessage mimeMailMessage = javamailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
            mimeMessageHelper.setSubject("注册验证");
        //String code = randomCode();
            mimeMessageHelper.setText("<a href='http://127.0.0.1:8030/bookstoreregister/updateStatus?uid="+uid+"'>点击链接验证账户</a>",true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(sendTo);
            javamailSender.send(mimeMailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

//    public static String randomCode(){
//        Random random = new Random();
//        StringBuffer str = new StringBuffer();
//
//        for (int i =0 ;i< 6;i++){
//            int i1 = random.nextInt(9);
//            str.append(i1);
//        }
//        return str.toString();
//    }
}
