package com.damgom.emailsendpractice.dto;

import lombok.Data;

@Data
public class MailDto {
    private String address;
    private String title;
    private String message;
}
