package com.beaconfireboba.backend.security.util;

import com.beaconfireboba.backend.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;


public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailConfig mailConfig;
    private String from = mailConfig.getFrom();
    //private String generalSubject = "Action required";

    HashMap<String, String> subjects = new HashMap<>();
    HashMap<String, String> messages = new HashMap<>();
    messages.put("receipt", "Please upload your OPT receipt");
    messages.put("983", "Please upload your I-983 form");
    messages.put("20", "Please upload your I-20");
    messages.put("EAD", "Please upload your OPT EAD card");
    subjects.put("983", "Action required");
    subjects.put("20", "Action required");
    subjects.put("EAD", "Action required");


    public void sendGeneralMail(String to, String subject, String message){
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

    public void sendOptionMail (String to, String option){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subjects.get(option));
        mailMessage.setText(messages.get(option));
        try{
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent.");
        }catch (Exception e){
            System.out.println("Mail failed to send.");
        }
    }


}
