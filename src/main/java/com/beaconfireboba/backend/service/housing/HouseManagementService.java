package com.beaconfireboba.backend.service.housing;

import com.beaconfireboba.backend.dao.HouseDAO;
import com.beaconfireboba.backend.dao.HouseManagementDAO;
import com.beaconfireboba.backend.domain.housing.HouseManagementRequest;
import com.beaconfireboba.backend.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class HouseManagementService {
    private HouseManagementDAO houseManagementDAO;

    @Autowired
    public void setHouseManagementDAODAO(HouseManagementDAO houseManagementDAO) {
        this.houseManagementDAO = houseManagementDAO;
    }

    @Transactional
    public List<HouseManagementRequest> getAllHouses() {return houseManagementDAO.getAllHouseInfo();}
}
