package com.beaconfireboba.backend.service.hr;

import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.domain.hr.profile.ProfileSummaryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProfileSummaryService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<ProfileSummaryRequest> getAllPersonWithEmployeeAndVisa() {
        return employeeDAO.getAllEmployeeWithPersonAndVisa();
    }
}
