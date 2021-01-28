package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.onboarding.OnboardingRequest;
import com.beaconfireboba.backend.domain.onboarding.OnboardingSubmittedResponse;
import com.beaconfireboba.backend.service.employee.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {
    private OnboardingService onboardingService;

    @Autowired
    public void setOnboardingService(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping(value="/submit")
    public OnboardingSubmittedResponse submitOnBoardingRequest(@RequestBody OnboardingRequest onboardingRequest) {
        System.out.println(onboardingRequest);
        OnboardingSubmittedResponse onboardingSubmittedResponse = new OnboardingSubmittedResponse();
        try {
            int userId = onboardingService.saveOnboarding(onboardingRequest.getPersonRequest(), onboardingRequest.getAddressRequest(), onboardingRequest.getEmployeeRequest(), onboardingRequest.getContactRequest(), onboardingRequest.getPersonalDocumentRequests());
            onboardingSubmittedResponse.setUserId(userId);
            prepareOnboardingSubmittedResponse(onboardingSubmittedResponse, true, "");
        } catch (Exception e) {
            prepareOnboardingSubmittedResponse(onboardingSubmittedResponse, false, "Error when submitting on boarding application.");
        }
        return onboardingSubmittedResponse;
    }

    private void prepareOnboardingSubmittedResponse(OnboardingSubmittedResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}