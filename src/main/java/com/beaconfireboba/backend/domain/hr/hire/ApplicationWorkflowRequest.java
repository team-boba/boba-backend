package com.beaconfireboba.backend.domain.hr.hire;

import lombok.*;


@Setter
@Getter
@ToString
public class ApplicationWorkflowRequest {
    private int id;
    private String createdDate;
    private String modificationDate;
    private String status;
    private String comments;
    private String type;

    private int userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
}
