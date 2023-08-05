package com.spring.email.service;

import com.spring.email.model.Email;

public interface EmailService {

    String sendSimpleEmail(Email email);

    String sendMailWithAttachment(Email email);
}
