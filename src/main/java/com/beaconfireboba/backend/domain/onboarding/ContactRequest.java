package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ContactRequest {
    private Map<String, String> reference;
    private Map<String, String> emergency;
}
