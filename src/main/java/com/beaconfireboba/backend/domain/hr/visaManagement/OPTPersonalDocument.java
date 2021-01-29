package com.beaconfireboba.backend.domain.hr.visaManagement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OPTPersonalDocument {
    private int id;
    private String title;
    private String path;
    private String comments;
    private String visaType;
    private String visaStartDate;
    private String visaEndDate;
    private String CreatedDate;
    private String CreatedBy;
}
