package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.VisaStatusDAO;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.entity.VisaStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository("visaStatusDao")
public class VisaStatusDAOImpl extends AbstractHibernateDAO<VisaStatus> implements VisaStatusDAO {
    public VisaStatusDAOImpl() { setClazz(VisaStatus.class); }

    @Override
    public VisaStatus getVisaStatusById(int id) {
        return findById(id);
    }

    @Override
    public VisaStatus getVisaStatusByName(String name) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<VisaStatus> cq = cb.createQuery(VisaStatus.class);
        Root<VisaStatus> root = cq.from(VisaStatus.class);
        cq.where(cb.equal(root.get("visaType"), name));

        VisaStatus visaStatus = session.createQuery(cq).uniqueResult();
        return visaStatus;
    }

    @Override
    public VisaStatus setVisaStatus(VisaStatus visaStatus) {
        return save(visaStatus);
    }
}