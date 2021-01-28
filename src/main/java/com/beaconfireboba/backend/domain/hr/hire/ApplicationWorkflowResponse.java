package com.beaconfireboba.backend.domain.hr.hire;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationWorkflowResponse {
    private ServiceStatus serviceStatus;

    private List<ApplicationWorkflow> applicationWorkflows;
}
