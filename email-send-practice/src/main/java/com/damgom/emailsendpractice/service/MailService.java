package com.damgom.emailsendpractice.service;

import com.damgom.emailsendpractice.dto.MailDto;
import com.damgom.emailsendpractice.util.MailHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username")
    private String FROM_ADDRESS;

    public void mailSend(MailDto mailDto) {
        try{
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setFrom(FROM_ADDRESS);
            mailHandler.setSubject(mailDto.getTitle());
            String htmlContent = "<p>" + mailDto.getMessage() +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
//            mailHandler.setAttach("newTest.txt", "static/originTest.txt");
            mailHandler.setInline("sample-img", "static/sample1.jpeg");
            mailHandler.send();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void mailSend(MailDto mailDto) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mailDto.getAddress());
//        mailMessage.setFrom(FROM_ADDRESS);
//        mailMessage.setSubject(mailDto.getTitle());
//        mailMessage.setText(mailDto.getMessage());
//        mailSender.send(mailMessage);
//    }
}
