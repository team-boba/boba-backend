package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.domain.housing.HouseManagementRequest;
import com.beaconfireboba.backend.entity.House;
import org.hibernate.Session;

import java.util.List;

public interface HouseManagementDAO {
    List<HouseManagementRequest> getAllHouseInfo();
}
