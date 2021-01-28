package com.beaconfireboba.backend.service.hr;

import com.beaconfireboba.backend.dao.*;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkFlowRequest;
import com.beaconfireboba.backend.domain.onboarding.*;
import com.beaconfireboba.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class HireService {
    private ApplicationWorkflowDAO applicationWorkflowDAO;

    @Autowired
    public void setApplicationWorkflowDao(ApplicationWorkflowDAO applicationWorkflowDAO) {
        this.applicationWorkflowDAO = applicationWorkflowDAO;
    }

    @Transactional
    public ApplicationWorkflow getApplicationWorkflowById(Integer id) {
        return applicationWorkflowDAO.getApplicationWorkflowById(id);
    }

    @Transactional
    public List<ApplicationWorkflow> getAllApplicationWorkflows() {
        return applicationWorkflowDAO.getAllApplicationWorkflows();
    }

    @Transactional
    public ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow) {
        return applicationWorkflowDAO.setApplicationWorkflow(applicationWorkflow);
    }

    @Transactional
    public ApplicationWorkflow updateApplicationWorkflowStatus(ApplicationWorkFlowRequest applicationWorkFlowRequest) {
        Integer applicationId = applicationWorkFlowRequest.getId();
        ApplicationWorkflow applicationWorkflow = getApplicationWorkflowById(applicationId);

        applicationWorkflow.setComments(applicationWorkFlowRequest.getComments());
        applicationWorkflow.setStatus(applicationWorkFlowRequest.getStatus());
        applicationWorkflow.setModificationDate(getCurrentDateTime());

        setApplicationWorkflow(applicationWorkflow);
        return applicationWorkflow;
    }


    public String getCurrentDateTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
