package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class EmployeeRequest {
    private String title;
    private String startDate;
    private String endDate;
    private String avatar;
    private String car;
    private Map<String, String> visaStatus;
    private String driverLicense;
    private String driverLicenseExpirationDate;
    public int houseId;
}
