package com.beaconfireboba.backend.service.housing;

import com.beaconfireboba.backend.dao.HouseDAO;
import com.beaconfireboba.backend.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HousingService {
    private HouseDAO houseDAO;

    @Autowired
    public void setHouseDAO(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Transactional
    public House getHouseById (Integer id){
        return houseDAO.getHouseById(id);
    }

    @Transactional
    public House setHouse (House house){
        return houseDAO.setHouse(house);
    }
}
