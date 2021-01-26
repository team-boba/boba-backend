package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.FacilityReportDetailDAO;
import com.beaconfireboba.backend.dao.VisaStatusDAO;
import com.beaconfireboba.backend.entity.FacilityReportDetail;
import com.beaconfireboba.backend.entity.VisaStatus;
import org.springframework.stereotype.Repository;

@Repository("facilityReportDetailDao")
public class FacilityReportDetailDAOImpl extends AbstractHibernateDAO<FacilityReportDetail> implements FacilityReportDetailDAO {
    public FacilityReportDetailDAOImpl() { setClazz(FacilityReportDetail.class); }

    @Override
    public FacilityReportDetail getFacilityReportDetailById(int id) {
        return findById(id);
    }

    @Override
    public FacilityReportDetail setFacilityReportDetail(FacilityReportDetail facilityReportDetail) {
        return save(facilityReportDetail);
    }
}
