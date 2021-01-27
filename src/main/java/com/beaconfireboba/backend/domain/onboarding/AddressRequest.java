package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class AddressRequest {
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String zipcode;
    private String stateName;
    private String stateAbbr;
}
