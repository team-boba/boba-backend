package com.beaconfireboba.backend.domain.employee.person;

import com.beaconfireboba.backend.entity.Person;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonRequest {
    private Person person;
}
