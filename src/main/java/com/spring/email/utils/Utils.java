package com.spring.email.utils;

import com.spring.email.model.Email;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.io.File;

public class Utils {

    /**
     * setting email in case: send mail with no attachment
     *
     * @param mailMessage: object for sending email with no attachment
     * @param mail:        object input
     */
    public static void setSimpleMail(SimpleMailMessage mailMessage, Email mail) {
        mailMessage.setTo(mail.getTo());
        mailMessage.setText(mail.getMessage());
        mailMessage.setSubject(mail.getSubject());
    }

    /**
     * setting email in case: send mail with attachment
     *
     * @param sender:
     * @param mailMessageHelper: object for sending email with attachment
     * @param email:             object input
     */
    public static void setMimeMessage(JavaMailSender sender, MimeMessageHelper mailMessageHelper, Email email) {
        try {
            mailMessageHelper.setTo(email.getTo());
            mailMessageHelper.setText(email.getMessage());
            mailMessageHelper.setSubject(email.getSubject());

            FileSystemResource file = new FileSystemResource(
                    new File(email.getAttachment())
            );
            mailMessageHelper.addAttachment(file.getFilename(), file);
        } catch ( MessagingException m) {
            m.notify();
        }
    }

}
