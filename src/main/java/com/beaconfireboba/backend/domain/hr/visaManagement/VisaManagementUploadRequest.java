package com.beaconfireboba.backend.domain.hr.visaManagement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VisaManagementUploadRequest {
    private int employeeId;
    private String path;
}
