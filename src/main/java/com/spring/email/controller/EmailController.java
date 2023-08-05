package com.spring.email.controller;

import com.spring.email.model.Email;
import com.spring.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    private EmailService mailService;

    @PostMapping("/simple")
    public String sendSimpleMail(@RequestBody Email email){
        return mailService.sendSimpleEmail(email);
    }

    @PostMapping("/attachment")
    public String sendMailWithAttachment(@RequestBody Email email){
        return mailService.sendMailWithAttachment(email);
    }

}
