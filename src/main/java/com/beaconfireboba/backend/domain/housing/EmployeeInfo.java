package com.beaconfireboba.backend.domain.housing;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeInfo {
    private Integer employeeId;
    private String name;
    private String phone;
    private String email;
    private String car;
}
