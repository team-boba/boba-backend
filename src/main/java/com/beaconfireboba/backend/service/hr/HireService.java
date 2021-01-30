package com.beaconfireboba.backend.service.hr;

import com.beaconfireboba.backend.dao.*;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.entity.*;
import com.beaconfireboba.backend.util.DateUtil;
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
    private DateUtil dateUtil;

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
    public List<ApplicationWorkflowRequest> getAllApplicationWorkflowsWithUserId() {
        return applicationWorkflowDAO.getAllApplicationWorkflowsWithUserId();
    }

    @Transactional
    public ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow) {
        return applicationWorkflowDAO.setApplicationWorkflow(applicationWorkflow);
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Transactional
    public ApplicationWorkflow updateApplicationWorkflowStatus(ApplicationWorkflowRequest applicationWorkFlowRequest) {
        String currentDate = dateUtil.getCurrentDate();
        Integer applicationId = applicationWorkFlowRequest.getId();
        ApplicationWorkflow applicationWorkflow = getApplicationWorkflowById(applicationId);

        applicationWorkflow.setComments(applicationWorkFlowRequest.getComments());
        applicationWorkflow.setStatus(applicationWorkFlowRequest.getStatus());
        applicationWorkflow.setModificationDate(currentDate);

        setApplicationWorkflow(applicationWorkflow);
        return applicationWorkflow;
    }
}
