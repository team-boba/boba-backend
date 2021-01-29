package com.beaconfireboba.backend.service.hr;

import com.beaconfireboba.backend.dao.ApplicationWorkflowDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.dao.VisaStatusDAO;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class VisaManagementService {
    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    @Autowired
    public void setEmployeeDao(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDao(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

//    @Transactional
//    public ApplicationWorkflow getApplicationWorkflowById(Integer id) {
//        return applicationWorkflowDAO.getApplicationWorkflowById(id);
//    }
//
//    @Transactional
//    public List<ApplicationWorkflow> getAllApplicationWorkflows() {
//        return applicationWorkflowDAO.getAllApplicationWorkflows();
//    }

    @Transactional
    public List<VisaManagementRequest> getAllOPTEmployeeWithPersonalDocument(VisaStatus visaStatus) {
        return employeeDAO.getAllOPTEmployeeWithPersonalDocument(visaStatus);
    }

    @Transactional
    public VisaStatus getVisaStatusByName(String name){
        return visaStatusDAO.getVisaStatusByName(name);
    }

//    @Transactional
//    public ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow) {
//        return applicationWorkflowDAO.setApplicationWorkflow(applicationWorkflow);
//    }
//
//    @Transactional
//    public ApplicationWorkflow updateApplicationWorkflowStatus(ApplicationWorkflowRequest applicationWorkFlowRequest) {
//        Integer applicationId = applicationWorkFlowRequest.getId();
//        ApplicationWorkflow applicationWorkflow = getApplicationWorkflowById(applicationId);
//
//        applicationWorkflow.setComments(applicationWorkFlowRequest.getComments());
//        applicationWorkflow.setStatus(applicationWorkFlowRequest.getStatus());
//        applicationWorkflow.setModificationDate(getCurrentDateTime());
//
//        setApplicationWorkflow(applicationWorkflow);
//        return applicationWorkflow;
//    }


    public String getCurrentDateTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
