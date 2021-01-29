package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.HouseManagementDAO;
import com.beaconfireboba.backend.domain.housing.HouseManagementRequest;
import com.beaconfireboba.backend.entity.House;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HouseManagementDAOImpl extends AbstractHibernateDAO implements HouseManagementDAO {
    public List<HouseManagementRequest> getAllHouseInfo(){
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<House> cq = cb.createQuery(House.class);
        Root<House> root = cq.from(House.class);
        List<House> houses = session.createQuery(cq).getResultList();

        if (houses == null) return null;

        List<HouseManagementRequest> res = new ArrayList<>();
        for (int i = 0; i < houses.size(); i++){
            HouseManagementRequest request = new HouseManagementRequest();
            request.setHouseId(houses.get(i).getId());
            request.setAddress(houses.get(i).getAddress());
            request.setLandlord(houses.get(i).getContact().getTitle());
            request.setLandlordPhone(houses.get(i).getContact().getPerson().getCellphone());
            request.setLandlordEmail(houses.get(i).getContact().getPerson().getEmail());
            request.setNumberOfPerson(houses.get(i).getNumberOfPerson());

            int houseId = request.getHouseId();
            String hql = "select f.quantity from Facility as f where f.houseId = ? and f.type = ?";
            request.setNumberOfBeds(houses.get(i).getFacility().);
        }
    }

}
