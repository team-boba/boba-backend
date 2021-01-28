package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowResponse;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.service.employee.person.PersonService;
import com.beaconfireboba.backend.service.hr.HireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/hr/hire")
public class HireController {
    private HireService hireService;
    private PersonService personService;

    @Autowired
    public void setHireService(HireService hireService) {
        this.hireService = hireService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
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

    @GetMapping(value="/application-review/{applicationId}")
    public PersonResponse getApplicationDetail(@PathVariable String applicationId) {
        PersonResponse personResponse = new PersonResponse();

        // get application
        Integer appId = Integer.valueOf(applicationId);
        ApplicationWorkflow applicationWorkflow = hireService.getApplicationWorkflowById(appId);

        // get person
        int userId = applicationWorkflow.getEmployee().getPerson().getUserId();
        Person person = personService.getPersonByUserId(userId);

        if (applicationWorkflow != null && person != null) {
            personResponse.setPerson(person);
            preparePersonResponse(personResponse, true, "");
        } else {
            preparePersonResponse(personResponse, false, "No application or person found.");
        }

        return personResponse;
    }

    private void prepareApplicationWorkflowResponse(ApplicationWorkflowResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    private void preparePersonResponse(PersonResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
