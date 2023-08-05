package com.spring.email.service.impl;

import com.spring.email.model.Email;
import com.spring.email.service.EmailService;
import com.spring.email.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMail;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendSimpleEmail(Email email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            Utils.setSimpleMail(mailMessage, email);
            mailMessage.setFrom(from);
            log.info("Starting send email");
            javaMail.send(mailMessage);
            log.info("Email was sent successfully!");
            return "Email was sent to: ".concat(email.getTo());
        } catch (Exception m) {
            return m.getLocalizedMessage();
        }
    }

    @Override
    public String sendMailWithAttachment(Email email) {

        MimeMessage mailMessage = javaMail.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mailMessage, true);
            mimeMessageHelper.setFrom(from);
            Utils.setMimeMessage(javaMail, mimeMessageHelper, email);
            javaMail.send(mailMessage);
            return "Email was sent to: ".concat(email.getTo());

        } catch (MessagingException m) {
            return m.getLocalizedMessage();
        }
    }
}
