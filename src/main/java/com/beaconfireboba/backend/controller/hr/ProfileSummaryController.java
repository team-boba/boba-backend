package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryResponse;
import com.beaconfireboba.backend.service.hr.ProfileSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr/profile")
public class ProfileSummaryController {
    private ProfileSummaryService profileSummaryService;

    @Autowired
    public void setProfileSummaryService(ProfileSummaryService profileSummaryService) {
        this.profileSummaryService = profileSummaryService;
    }

    @GetMapping(value="/")
    public ProfileSummaryResponse getAllEmployeeProfiles(HttpServletRequest httpRequest) {
        ProfileSummaryResponse profileSummaryResponse = new ProfileSummaryResponse();
        List<ProfileSummaryRequest> profileSummaryRequests = this.profileSummaryService.getAllPersonWithEmployeeAndVisa();

        if (profileSummaryRequests != null) {
            profileSummaryResponse.setProfileSummaryRequests(profileSummaryRequests);
            prepareProfileSummaryResponse(profileSummaryResponse, true, "");
        } else {
            prepareProfileSummaryResponse(profileSummaryResponse, false, "No application workflow found.");
        }

        return profileSummaryResponse;
    }

    private void prepareProfileSummaryResponse(ProfileSummaryResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
