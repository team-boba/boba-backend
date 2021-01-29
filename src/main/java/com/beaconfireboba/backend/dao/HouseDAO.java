package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.House;

public interface HouseDAO {
    House getHouseById(int id);

    House setHouse(House house);
}