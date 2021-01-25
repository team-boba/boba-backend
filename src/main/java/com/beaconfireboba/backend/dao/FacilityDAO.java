package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Facility;

public interface FacilityDAO {
    Facility getFacilityById(int id);

    Facility setFacility(Facility facility);
}