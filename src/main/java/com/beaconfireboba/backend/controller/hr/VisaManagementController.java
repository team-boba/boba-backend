package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowResponse;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementResponse;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.beaconfireboba.backend.entity.VisaStatus;
import com.beaconfireboba.backend.service.hr.HireService;
import com.beaconfireboba.backend.service.hr.VisaManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr/visa-management")
public class VisaManagementController {
    private VisaManagementService visaManagementService;

    @Autowired
    public void setVisaManagementService(VisaManagementService visaManagementService) {
        this.visaManagementService = visaManagementService;
    }

    @GetMapping(value="/")
    public VisaManagementResponse getAllOPTEmployeeVisaInfo(HttpServletRequest httpRequest) {
        VisaManagementResponse visaManagementResponse = new VisaManagementResponse();
        VisaStatus visaStatus = this.visaManagementService.getVisaStatusByName("F1(CPT/OPT)");
        List<VisaManagementRequest> visaManagementRequests = this.visaManagementService.getAllOPTEmployeeWithPersonalDocument(visaStatus);

        if (visaManagementRequests != null) {
            visaManagementResponse.setVisaManagementRequests(visaManagementRequests);
            prepareResponse(visaManagementResponse, true, "");
        } else {
            prepareResponse(visaManagementResponse, false, "No application workflow found.");
        }

        return visaManagementResponse;
    }

//    @PostMapping(value = "/application-review/update")
//    public ApplicationWorkflow updateApplication(@RequestBody ApplicationWorkflowRequest applicationWorkFlowRequest) {
//        return hireService.updateApplicationWorkflowStatus(applicationWorkFlowRequest);
//    }

    private void prepareResponse(VisaManagementResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
