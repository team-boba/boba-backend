package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.dao.FacilityReportDAO;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.FacilityReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("facilityReportDao")
public class FacilityReportDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDAO {
    public FacilityReportDAOImpl() { setClazz(FacilityReport.class); }

    @Override
    public FacilityReport getFacilityReportById(int id) {
        return findById(id);
    }

    @Override
    public List<FacilityReport> getAllFacilityReports() {
        return findAll();
    }

    @Override
    public FacilityReport setFacilityReport(FacilityReport facilityReport) {
        return save(facilityReport);
    }
}
