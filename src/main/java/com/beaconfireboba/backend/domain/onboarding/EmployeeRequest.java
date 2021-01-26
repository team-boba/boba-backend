package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class EmployeeRequest {
    private String title;
    private String startDate;
    private String endDate;
//    private MultipartFile avatar;
    private String car;
    private String visaType;
    private String visaStartDate;
    private String visaEndDate;
    private String driverLicense;
    private String driverLicenseExpirationDate;
}
