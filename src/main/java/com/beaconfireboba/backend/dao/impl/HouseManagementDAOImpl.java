package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.HouseManagementDAO;
import com.beaconfireboba.backend.dao.PersonDAO;
import com.beaconfireboba.backend.domain.housing.EmployeeInfo;
import com.beaconfireboba.backend.domain.housing.HouseManagementRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.Facility;
import com.beaconfireboba.backend.entity.House;
import com.beaconfireboba.backend.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("houseManagementDAO")
public class HouseManagementDAOImpl extends AbstractHibernateDAO implements HouseManagementDAO {
    public HouseManagementDAOImpl() {setClazz(House.class);}
    @Override
    public List<HouseManagementRequest> getAllHouseInfo(){
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<House> cq = cb.createQuery(House.class);
        Root<House> root = cq.from(House.class);
        List<House> houses = session.createQuery(cq).getResultList();
        PersonDAO personDAO = new PersonDAOImpl();

        if (houses == null) return null;

        List<HouseManagementRequest> res = new ArrayList<>();
        for (int i = 0; i < houses.size(); i++){
            HouseManagementRequest request = new HouseManagementRequest();
            request.setHouseId(houses.get(i).getId());
            request.setAddress(houses.get(i).getAddress());
            System.out.println(request.getAddress());

            request.setLandlord(houses.get(i).getContact().getTitle());
            request.setLandlordPhone(houses.get(i).getContact().getPerson().getCellphone());
            request.setLandlordEmail(houses.get(i).getContact().getPerson().getEmail());
            request.setNumberOfPerson(houses.get(i).getNumberOfPerson());

            request.setNumberOfBeds(houses.get(i).getFacilities().get(0).getQuantity());
            request.setNumberOfMattresses(houses.get(i).getFacilities().get(1).getQuantity());
            request.setNumberOfTables(houses.get(i).getFacilities().get(2).getQuantity());
            request.setNumberOfChairs(houses.get(i).getFacilities().get(3).getQuantity());

            List<EmployeeInfo> employeeList = new ArrayList<>();
            int size = request.getNumberOfPerson();
            for (int j = 0; j < size; j++){
                EmployeeInfo info = new EmployeeInfo();
                int id = houses.get(i).getEmployees().get(j).getId();
                info.setName(personDAO.getPersonByPersonId(id).getFirstName());
                info.setPhone(personDAO.getPersonByPersonId(id).getCellphone());
                info.setEmail(personDAO.getPersonByPersonId(id).getEmail());
                info.setCar(houses.get(i).getEmployees().get(j).getCar());
                employeeList.add(info);
            }
            request.setEmployees(employeeList);
            res.add(request);
        }
        return res;
    }

}
