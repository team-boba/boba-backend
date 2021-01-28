package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.ApplicationWorkflowDAO;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("applicationWorkflowDao")
public class ApplicationWorkflowDAOImpl extends AbstractHibernateDAO<ApplicationWorkflow> implements ApplicationWorkflowDAO {
    public ApplicationWorkflowDAOImpl() { setClazz(ApplicationWorkflow.class); }

    @Override
    public ApplicationWorkflow getApplicationWorkflowById(int id) {
        return findById(id);
    }

    @Override
    public List<ApplicationWorkflow> getAllApplicationWorkflows() {
        return findAll();
    }

    @Override
    public ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow) {
        return save(applicationWorkflow);
    }
}
