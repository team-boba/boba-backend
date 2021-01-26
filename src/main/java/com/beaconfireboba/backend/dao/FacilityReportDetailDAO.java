package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.FacilityReportDetail;

public interface FacilityReportDetailDAO {
    FacilityReportDetail getFacilityReportDetailById(int id);

    FacilityReportDetail setFacilityReportDetail(FacilityReportDetail facilityReportDetail);
}