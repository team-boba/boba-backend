package com.beaconfireboba.backend.security.util;

import com.beaconfireboba.backend.domain.common.Mail;
import com.beaconfireboba.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


public class MailUtil {
    public static Mail messageWithToken (String token, String to){
        Mail mail = new Mail();
        String subject = "Register to start your application at BOBA!";
        String message = "Click this link to register: "+token;
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setTo(to);
        return mail;
    }

    public static Mail messageForReceipt (String to) {
        Mail mail = new Mail();
        String subject = "Action required";
        String message = "Please upload your OPT receipt";
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setTo(to);
        return mail;
    }

    public static Mail messageFor983 (String to) {
        Mail mail = new Mail();
        String subject = "Action required";
        String message = "Please upload your I-983 form";
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setTo(to);
        return mail;
    }

    public static Mail messageFor20 (String to) {
        Mail mail = new Mail();
        String subject = "Action required";
        String message = "Please upload your I-20";
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setTo(to);
        return mail;
    }

    public static Mail messageForEAD (String to) {
        Mail mail = new Mail();
        String subject = "Action required";
        String message = "Please upload your OPT EAD card";
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setTo(to);
        return mail;
    }
}
