package com.beaconfireboba.backend.service.employee;


import com.beaconfireboba.backend.dao.EmployeeDAO;
import com.beaconfireboba.backend.dao.PersonalDocumentDAO;
import com.beaconfireboba.backend.domain.ducoment.UploadDocumentRequest;
import com.beaconfireboba.backend.entity.Employee;
import com.beaconfireboba.backend.entity.PersonalDocument;
import com.beaconfireboba.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class VisaStatusService {
    private PersonalDocumentDAO personalDocumentDAO;
    private EmployeeDAO employeeDAO;
    private DateUtil dateUtil;

    @Transactional
    public UploadDocumentRequest adduploadPersonalDocument(UploadDocumentRequest uploadDocumentRequest){
        PersonalDocument preparedDocument = new PersonalDocument();
        preparedDocument.setComment(uploadDocumentRequest.getComment());
        preparedDocument.setCreateBy(uploadDocumentRequest.getCreateBy());
        preparedDocument.setTitle(uploadDocumentRequest.getTitle());
        preparedDocument.setPath(uploadDocumentRequest.getPath());
        preparedDocument.setCreatedDate(dateUtil.getCurrentDate());

        Employee employee = employeeDAO.getEmployeeById(uploadDocumentRequest.getEmployeeID());

        preparedDocument.setEmployee(employee);

        PersonalDocument newDocument = personalDocumentDAO.setPersonalDocument(preparedDocument);

        if(newDocument != null){
            return uploadDocumentRequest;
        }

        return null;
    }

    @Autowired
    public void setDataUtil(DateUtil dateUtil){this.dateUtil = dateUtil;}

    @Autowired
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO){this.personalDocumentDAO = personalDocumentDAO;}

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO){this.employeeDAO = employeeDAO;}




}
