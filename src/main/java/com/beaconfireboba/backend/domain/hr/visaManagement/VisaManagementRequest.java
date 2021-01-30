package com.beaconfireboba.backend.domain.hr.visaManagement;

import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.PersonalDocument;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class VisaManagementRequest {
    private int userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

    private int employeeId;
    private String nextStep;
    private String action;
    private String visaStartDate;
    private String visaEndDate;
    private int dayLeft;

    private List<PersonalDocument> personalDocuments;

}
