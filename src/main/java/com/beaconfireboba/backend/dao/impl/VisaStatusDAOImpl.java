package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.VisaStatusDAO;
import com.beaconfireboba.backend.entity.VisaStatus;
import org.springframework.stereotype.Repository;

@Repository("visaStatusDao")
public class VisaStatusDAOImpl extends AbstractHibernateDAO<VisaStatus> implements VisaStatusDAO {
    public VisaStatusDAOImpl() { setClazz(VisaStatus.class); }

    @Override
    public VisaStatus getVisaStatusById(int id) {
        return findById(id);
    }

    @Override
    public VisaStatus setVisaStatus(VisaStatus visaStatus) {
        return save(visaStatus);
    }
}
