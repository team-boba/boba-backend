package com.beaconfireboba.backend.domain.hr.hire;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.beaconfireboba.backend.entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
public class ApplicationWorkFlowRequest {
    private int id;
    private String createdDate;
    private String modificationDate;
    private String status;
    private String comments;
    private String type;
}
