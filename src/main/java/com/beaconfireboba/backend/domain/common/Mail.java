package com.beaconfireboba.backend.domain.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private String to;
    private String from;
    private String message;
    private String subject;

}
