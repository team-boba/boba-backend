package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListPersonalDocumentsRequest {
    List<PersonalDocumentRequest> personalDocumentRequests;
}
