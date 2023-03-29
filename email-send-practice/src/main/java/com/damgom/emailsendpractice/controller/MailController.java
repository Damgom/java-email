package com.damgom.emailsendpractice.controller;

import com.damgom.emailsendpractice.dto.MailDto;
import com.damgom.emailsendpractice.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void sendMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
    }
}
