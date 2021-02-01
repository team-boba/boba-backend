package com.beaconfireboba.backend.domain.hr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class HrEmailSendRequest {
    private String email;
    private String subject;
    private String message;
}
