package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;

import java.util.List;


public interface ApplicationWorkflowDAO {
    ApplicationWorkflow getApplicationWorkflowById(int id);

    List<ApplicationWorkflow> getAllApplicationWorkflows();

    List<ApplicationWorkflowRequest> getAllApplicationWorkflowsWithUserId();

    ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow);
}