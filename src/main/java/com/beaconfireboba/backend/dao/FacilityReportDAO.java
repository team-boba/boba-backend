package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.FacilityReport;

import java.util.List;


public interface FacilityReportDAO {
    List<FacilityReport> getAllFacilityReports();

    FacilityReport getFacilityReportById(int id);

    FacilityReport setFacilityReport(FacilityReport facilityReport);
}