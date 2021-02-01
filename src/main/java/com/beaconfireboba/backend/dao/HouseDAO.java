package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.House;

import java.util.List;

public interface HouseDAO {
    House getHouseById(int id);

    House setHouse(House house);

    List<House> getAllHouses();
}