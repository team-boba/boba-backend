package com.beaconfireboba.backend.domain.hr.profile;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileSummaryResponse {
    private ServiceStatus serviceStatus;

    private List<ProfileSummaryRequest> profileSummaryRequests;
}
