package com.beaconfireboba.backend.domain.facilityReport;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.entity.FacilityReport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityReportResponse {
    private ServiceStatus serviceStatus;
    private FacilityReport facilityReport;
}
