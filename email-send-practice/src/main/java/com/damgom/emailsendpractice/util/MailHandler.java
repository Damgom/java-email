package com.damgom.emailsendpractice.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

public class MailHandler {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper helper;

    public MailHandler(JavaMailSender jSender) throws
            MessagingException {
        this.mailSender = jSender;
        message = jSender.createMimeMessage();
        helper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setFrom(String fromAddress) throws MessagingException {
        helper.setFrom(fromAddress);
    }

    public void setTo(String email) throws MessagingException {
        helper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException {
        helper.setSubject(subject);
    }

    public void setText(String text, boolean useHtml) throws MessagingException {
        helper.setText(text, useHtml);
    }

    public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
        File file = new ClassPathResource(pathToAttachment).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        helper.addAttachment(displayFileName, fsr);
    }

    public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
        File file = new ClassPathResource(pathToInline).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        helper.addInline(contentId, fsr);
    }

    public void send() {
        try {
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
