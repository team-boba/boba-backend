package com.beaconfireboba.backend.domain.onboarding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OnboardingRequest {
    private EmployeeRequest employeeRequest;
    private PersonRequest personRequest;
    private AddressRequest addressRequest;
    private ContactRequest contactRequest;
    private List<PersonalDocumentRequest> personalDocumentRequests;
}
