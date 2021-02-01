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
    private Integer numberOfPerson;
    private Integer numberOfBeds;
    private Integer numberOfMattresses;
    private Integer numberOfTables;
    private Integer numberOfChairs;
    private List<EmployeeInfo> employeeInfos;
}
