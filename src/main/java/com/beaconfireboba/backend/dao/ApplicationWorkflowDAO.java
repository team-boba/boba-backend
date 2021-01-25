package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.ApplicationWorkflow;


public interface ApplicationWorkflowDAO {
    ApplicationWorkflow getApplicationWorkflowById(int id);

    ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow);
}