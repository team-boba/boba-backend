package com.beaconfireboba.backend.service;

import com.beaconfireboba.backend.domain.common.Mail;
import org.springframework.stereotype.Service;

@Service("mailService")
public interface MailService {
    void sendMail(Mail mail);
}
