package com.beaconfireboba.backend.domain.onboarding;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OnboardingSubmittedResponse {
    private ServiceStatus serviceStatus;
    private int userId;
}
