package com.beaconfireboba.backend.domain.housing;

import com.beaconfireboba.backend.entity.House;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseManagementRequest {
    private Integer houseId;
    private String address;
    private String landlord;
    private String landlordPhone;
    private String landlordEmail;
    private Integer numberOfPerson;

    private Integer numberOfBeds;
    private Integer numberOfMattresses;
    private Integer numberOfTables;
    private Integer numberOfChairs;

    private String title;
    private String date;
    private String status;
    private String description;
    private String createdBy;

    private String name;
    private String phone;
    private String email;
    private String car;
}
