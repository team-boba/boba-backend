package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.ApplicationWorkflow;

import java.util.List;


public interface ApplicationWorkflowDAO {
    ApplicationWorkflow getApplicationWorkflowById(int id);

    List<ApplicationWorkflow> getAllApplicationWorkflows();

    ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow);
}