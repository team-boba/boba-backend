package com.beaconfireboba.backend.service.impl;


import com.beaconfireboba.backend.domain.common.Mail;
import com.beaconfireboba.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private String from;


    public void sendMail(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        try{
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent.");
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }
}
