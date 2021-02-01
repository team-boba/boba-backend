package com.beaconfireboba.backend.domain.hr.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileSummaryRequest {
    private int personId;
    private int employeeId;
    private int userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String ssn;
    private String startDate;
    private String visaType;
}
