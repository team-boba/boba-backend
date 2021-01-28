package com.beaconfireboba.backend.controller.hr;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
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

    @GetMapping(value="/application-review")
    public ApplicationWorkflowResponse getAllApplications(HttpServletRequest httpRequest) {
        ApplicationWorkflowResponse applicationWorkflowResponse = new ApplicationWorkflowResponse();
        List<ApplicationWorkflow> allApplicationWorkflows= this.hireService.getAllApplicationWorkflows();
        if (allApplicationWorkflows != null) {
            applicationWorkflowResponse.setApplicationWorkflows(allApplicationWorkflows);
            prepareResponse(applicationWorkflowResponse, true, "");
        } else {
            prepareResponse(applicationWorkflowResponse, false, "No application workflow found.");
        }

        return applicationWorkflowResponse;
    }

    @PostMapping(value = "/application-review/update")
    public boolean updateApplication(@RequestParam("id") String id, @RequestParam("comment") String comment, @RequestParam("isApproved") String isApproved) {
        Integer applicationId = Integer.valueOf(id);
        ApplicationWorkflow applicationWorkflow = hireService.getApplicationWorkflowById(applicationId);
        applicationWorkflow.setComments(comment);
        if (isApproved.equals("true")) {
            applicationWorkflow.setStatus("Completed");
        } else {
            applicationWorkflow.setStatus("Rejected");
        }
        hireService.setApplicationWorkflow(applicationWorkflow);
        return true;
    }

    @GetMapping(value="/application-review/{applicationId}")
    public PersonResponse getApplicationDetail(@PathVariable String applicationId) {
        System.out.println(applicationId);
        PersonResponse personResponse = new PersonResponse();
//        Person person = this.personService.getPersonByUserId(Integer.parseInt(userId));
//        if (person != null) {
//            personResponse.setPerson(person);
//            prepareResponse(personResponse, true, "");
//        } else {
//            prepareResponse(personResponse, false, "No person found.");
//        }

        return personResponse;
    }

    private void prepareResponse(ApplicationWorkflowResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    @Autowired
    public void setHireService(HireService hireService) {
        this.hireService = hireService;
    }
}
