package com.beaconfireboba.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    @Value("spring.mail.username")
    String from;



}
