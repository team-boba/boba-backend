package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.domain.employee.visaStatus.EmployeeVisaRequest;
import com.beaconfireboba.backend.domain.employee.visaStatus.EmployeeVisaResponse;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.service.employee.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/employee")
public class VisaStatusManagementController {
    private VisaStatusService visaStatusService;


//    @GetMapping(value="/vsm/{employeeID}")
//    public EmployeeVisaResponse getEmployee(@PathVariable String employeeID) {
//        EmployeeVisaRequest employeeVisaRequest = new EmployeeVisaRequest();
//        EmployeeVisaResponse employeeVisaResponse = new EmployeeVisaResponse();
//
//        Employee employee =  visaStatusService.getEmployeeByID(Integer.parseInt(employeeID));
//
//        employeeVisaRequest.setVisaStatus(employee.getVisaStatus().getVisaType());
//        employeeVisaRequest.setUserID(employee.getId());
//
//        employeeVisaResponse.setEmplyeeVisaRequest(employeeVisaRequest);
//        prepareResponse(employeeVisaResponse, true, "Get employee visa");
//        return employeeVisaResponse;
//    }


    @PostMapping(value="/vsm/{employeeID}/update/{visaStatus}")
    public EmployeeVisaResponse upDateEmployeeVisaStatus(@PathVariable String employeeID, @PathVariable String visaStatus) {
        EmployeeVisaResponse employeeVisaResponse = new EmployeeVisaResponse();
        EmployeeVisaRequest employeeVisaRequest = new EmployeeVisaRequest();

        employeeVisaRequest.setUserID(Integer.parseInt(employeeID));
        employeeVisaRequest.setVisaStatus(visaStatus);

        Employee employeeWithNewStatus = visaStatusService.updateEmployeeVisaStatus(employeeVisaRequest);
        if(employeeWithNewStatus == null){

            prepareResponse(employeeVisaResponse, false, "Could not find employee or visaStatus");
        }
        else{
            employeeVisaRequest.setVisaStatus(employeeWithNewStatus.getVisaStatus().getVisaType());
            employeeVisaResponse.setEmplyeeVisaRequest(employeeVisaRequest);
            prepareResponse(employeeVisaResponse, true, "Successful updated");
        }
        return employeeVisaResponse;
    }


    private void prepareResponse(EmployeeVisaResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService){this.visaStatusService = visaStatusService;}



}
