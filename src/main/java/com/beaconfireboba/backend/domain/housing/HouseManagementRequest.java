package com.beaconfireboba.backend.domain.housing;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseManagementRequest {
    private Integer houseId;
    private String address;
    //private String landlord;
    //private String landlordPhone;
    //private String landlordEmail;
    private Integer numberOfPerson;

    private Integer numberOfBeds;
    private Integer numberOfMattresses;
    private Integer numberOfTables;
    private Integer numberOfChairs;

//    private String title;
//    private String date;
//    private String status;
//    private String description;
//    private String createdBy;

    private List<EmployeeInfo> employeeInfos;
}
