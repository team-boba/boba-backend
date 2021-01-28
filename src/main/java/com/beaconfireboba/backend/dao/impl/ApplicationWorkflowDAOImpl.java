package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.ApplicationWorkflowDAO;
import com.beaconfireboba.backend.domain.hr.hire.ApplicationWorkflowRequest;
import com.beaconfireboba.backend.entity.ApplicationWorkflow;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<ApplicationWorkflowRequest> getAllApplicationWorkflowsWithUserId() {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ApplicationWorkflow> cq = cb.createQuery(ApplicationWorkflow.class);
        Root<ApplicationWorkflow> root = cq.from(ApplicationWorkflow.class);
        List<ApplicationWorkflow> applicationWorkflows = session.createQuery(cq).getResultList();

        if (applicationWorkflows == null) return null;

        List<ApplicationWorkflowRequest> res = new ArrayList<>();
        for (int i = 0; i < applicationWorkflows.size(); i++) {
            ApplicationWorkflowRequest tmp = new ApplicationWorkflowRequest();
            tmp.setUserId(applicationWorkflows.get(i).getEmployee().getPerson().getUserId());
            tmp.setFirstName(applicationWorkflows.get(i).getEmployee().getPerson().getFirstName());
            tmp.setLastName(applicationWorkflows.get(i).getEmployee().getPerson().getLastName());
            tmp.setMiddleName(applicationWorkflows.get(i).getEmployee().getPerson().getMiddleName());
            tmp.setEmail(applicationWorkflows.get(i).getEmployee().getPerson().getEmail());

            tmp.setId(applicationWorkflows.get(i).getId());
            tmp.setCreatedDate(applicationWorkflows.get(i).getCreatedDate());
            tmp.setModificationDate(applicationWorkflows.get(i).getModificationDate());
            tmp.setStatus(applicationWorkflows.get(i).getStatus());
            tmp.setComments(applicationWorkflows.get(i).getComments());
            tmp.setType(applicationWorkflows.get(i).getType());
            res.add(tmp);
        }

        return res;
    }

    @Override
    public ApplicationWorkflow setApplicationWorkflow(ApplicationWorkflow applicationWorkflow) {
        return save(applicationWorkflow);
    }
}
