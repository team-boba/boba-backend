package com.beaconfireboba.backend.service.hr;

import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.dao.PersonalDocumentDAO;
import com.beaconfireboba.backend.dao.VisaStatusDAO;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementRequest;
import com.beaconfireboba.backend.domain.hr.visaManagement.VisaManagementUploadRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.PersonalDocument;
import com.beaconfireboba.backend.entity.VisaStatus;
import com.beaconfireboba.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class VisaManagementService {
    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    private PersonalDocumentDAO personalDocumentDAO;

    private DateUtil dateUtil;

    @Autowired
    public void setEmployeeDao(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDao(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setPersonalDocumentDao(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Transactional
    public List<VisaManagementRequest> getAllOPTEmployeeWithPersonalDocument(VisaStatus visaStatus) {
        return employeeDAO.getAllOPTEmployeeWithPersonalDocument(visaStatus);
    }

    @Transactional
    public VisaStatus getVisaStatusByName(String name){
        return visaStatusDAO.getVisaStatusByName(name);
    }

    @Transactional
    public PersonalDocument addUploadPersonalDocument(VisaManagementUploadRequest visaManagementUploadRequest){
        String currentDate = dateUtil.getCurrentDate();

        PersonalDocument personalDocument = new PersonalDocument();
        Employee employee = employeeDAO.getEmployeeById(visaManagementUploadRequest.getEmployeeId());
        if (employee == null) {
            System.out.println("cannot find the employee");
            return personalDocument;
        }
        personalDocument.setEmployee(employee);
        personalDocument.setPath(visaManagementUploadRequest.getPath());
        personalDocument.setTitle("Signed I-983");
        personalDocument.setCreatedDate(currentDate);
        personalDocument.setCreateBy("hr");

        personalDocumentDAO.setPersonalDocument(personalDocument);
        return personalDocument;
    }
}
