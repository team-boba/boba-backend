package com.beaconfireboba.backend.service;

public interface MailService {
    void sendMail(String to, String from, String message, String subject);
}
