package com.beaconfireboba.backend.domain.employee.person;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.entity.Person;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonResponse {
    private ServiceStatus serviceStatus;

    private Person person;
}
