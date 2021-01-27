package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonalDocumentRequest {
    private String title;
    private String path;
}
