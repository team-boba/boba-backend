package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class PersonRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String cellPhone;
    private String alternatePhone;
    private String gender;
    private String ssn;
    private String dob;
}
