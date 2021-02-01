package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowResponse;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.beaconfireboba.backend.service.hr.HireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr/hire")
public class HireController {
    private HireService hireService;

    @Autowired
    public void setHireService(HireService hireService) {
        this.hireService = hireService;
    }

    @GetMapping(value="/application-review")
    public ApplicationWorkflowResponse getAllApplications(HttpServletRequest httpRequest) {
        ApplicationWorkflowResponse applicationWorkflowResponse = new ApplicationWorkflowResponse();
        List<ApplicationWorkflowRequest> applicationWorkflowRequests = this.hireService.getAllApplicationWorkflowsWithUserId();

        if (applicationWorkflowRequests != null) {
            applicationWorkflowResponse.setApplicationWorkflowRequests(applicationWorkflowRequests);
            prepareApplicationWorkflowResponse(applicationWorkflowResponse, true, "");
        } else {
            prepareApplicationWorkflowResponse(applicationWorkflowResponse, false, "No application workflow found.");
        }

        return applicationWorkflowResponse;
    }

    @PostMapping(value = "/application-review/update")
    public ApplicationWorkflow updateApplication(@RequestBody ApplicationWorkflowRequest applicationWorkFlowRequest) {
        return hireService.updateApplicationWorkflowStatus(applicationWorkFlowRequest);
    }

    private void prepareApplicationWorkflowResponse(ApplicationWorkflowResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
