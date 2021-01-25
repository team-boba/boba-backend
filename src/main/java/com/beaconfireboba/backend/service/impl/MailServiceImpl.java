package com.beaconfireboba.backend.service.impl;


import com.beaconfireboba.backend.domain.common.Mail;
import com.beaconfireboba.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(String to, String from, String message, String subject){
        Mail mail = new Mail();
        mail.setMessage(message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        try{
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent.");
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }
}
