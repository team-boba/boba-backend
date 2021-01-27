package com.beaconfireboba.backend.domain.housing;

import com.beaconfireboba.backend.entity.House;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HousingRequest {
    private House house;
}
