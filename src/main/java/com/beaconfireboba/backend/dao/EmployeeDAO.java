package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.entity.Employee;

import java.util.List;


public interface EmployeeDAO {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee setEmployee(Employee employee);
}