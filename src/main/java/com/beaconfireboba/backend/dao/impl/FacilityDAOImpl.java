package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.FacilityDAO;
import com.beaconfireboba.backend.dao.HouseDAO;
import com.beaconfireboba.backend.entity.Facility;
import com.beaconfireboba.backend.entity.House;
import org.springframework.stereotype.Repository;

@Repository("failityDao")
public class FacilityDAOImpl extends AbstractHibernateDAO<Facility> implements FacilityDAO {
    public FacilityDAOImpl() { setClazz(Facility.class); }

    @Override
    public Facility getFacilityById(int id) {
        return findById(id);
    }

    @Override
    public Facility setFacility(Facility facility) {
        return save(facility);
    }
}
