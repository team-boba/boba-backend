package com.beaconfireboba.backend.domain.housing;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.entity.House;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseManagementResponse {
    private ServiceStatus serviceStatus;
    private List<House> houses;

}
