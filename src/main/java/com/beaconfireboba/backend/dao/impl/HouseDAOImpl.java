package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.HouseDAO;
import com.beaconfireboba.backend.entity.House;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("houseDao")
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {
    public HouseDAOImpl() { setClazz(House.class); }

    @Override
    public House getHouseById(int id) {
        return findById(id);
    }

    @Override
    public House setHouse(House house) {
        return save(house);
    }

    @Override
    public List<House> getAllHouses() {return findAll();}
}
