package com.beaconfireboba.backend.dao.impl;

import com.beaconfireboba.backend.dao.AbstractHibernateDAO;
import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.entity.Employee;
import org.springframework.stereotype.Repository;

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

    @Override
    public Employee setEmployee(Employee employee) {
        return save(employee);
    }
}
