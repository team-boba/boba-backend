package com.beaconfireboba.backend.service.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class VisaStatusService {

//
//    @Transactional
//    public Employee updateEmployeeVisaStatus(EmployeeVisaRequest employeeVisaRequest){
//        VisaStatus visaStatus = visaStatusDAO.getVisaStatusByName(employeeVisaRequest.getVisaStatus());
//        Employee employeeWithOldStatus = employeeDAO.getEmployeeById(employeeVisaRequest.getUserID());
//
//        if(visaStatus != null && employeeWithOldStatus != null){
//            employeeWithOldStatus.setVisaStatus(visaStatus);
//
//            Employee employeeWithNewStatus = employeeDAO.setEmployee(employeeWithOldStatus);
//
//            return employeeWithNewStatus;
//        }
//        else{
//            return null;
//        }
//
//    }


}
