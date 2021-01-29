package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import com.beaconfireboba.backend.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {
    public EmployeeDAOImpl() { setClazz(Employee.class); }

    @Override
    public Employee getEmployeeById(int id) {
        return findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return findAll();
    }

    public List<ProfileSummaryRequest> getAllEmployeeWithPersonAndVisa() {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        List<Employee> employeeList = session.createQuery(cq).getResultList();

        if (employeeList == null) return null;

        List<ProfileSummaryRequest> res = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            ProfileSummaryRequest tmp = new ProfileSummaryRequest();
            tmp.setPersonId(employeeList.get(i).getPerson().getId());
            tmp.setFirstName(employeeList.get(i).getPerson().getFirstName());
            tmp.setLastName(employeeList.get(i).getPerson().getLastName());
            tmp.setMiddleName(employeeList.get(i).getPerson().getMiddleName());
            tmp.setSsn(employeeList.get(i).getPerson().getSSN());
            tmp.setUserId(employeeList.get(i).getPerson().getUserId());

            tmp.setStartDate(employeeList.get(i).getStartDate());
            tmp.setEmployeeId(employeeList.get(i).getId());

            tmp.setVisaType(employeeList.get(i).getVisaStatus().getVisaType());

            res.add(tmp);
        }

        return res;
    }

    @Override
    public Employee setEmployee(Employee employee) {
        return save(employee);
    }
}
