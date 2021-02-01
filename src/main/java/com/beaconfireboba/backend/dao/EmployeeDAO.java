package com.beaconfireboba.backend.dao;

import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.VisaStatus;

import java.util.List;


public interface EmployeeDAO {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee setEmployee(Employee employee);

    public List<ProfileSummaryRequest> getAllEmployeeWithPersonAndVisa();

    public List<VisaManagementRequest> getAllOPTEmployeeWithPersonalDocument(VisaStatus visaStatus);
}