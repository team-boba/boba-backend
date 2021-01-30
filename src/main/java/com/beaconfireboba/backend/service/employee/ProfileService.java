package com.beaconfireboba.backend.service.employee;

import com.beaconfireboba.backend.dao.*;
import com.beaconfireboba.backend.domain.onboarding.*;
import com.beaconfireboba.backend.entity.*;
import com.beaconfireboba.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {
    PersonDAO personDAO;
    VisaStatusDAO visaStatusDAO;
    EmployeeDAO employeeDAO;
    AddressDAO addressDAO;
    ContactDAO contactDAO;
    DateUtil dateUtil;
    PersonalDocumentDAO personalDocumentDAO;

    @Transactional
    public void updatePersonByUserId(int userId, PersonRequest personRequest) {
        Person person = this.personDAO.getPersonByUserId(userId);
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setMiddleName(personRequest.getMiddleName());
        person.setEmail(personRequest.getEmail());
        person.setCellphone(personRequest.getCellPhone());
        person.setAlternatePhone(personRequest.getAlternatePhone());
        person.setGender(personRequest.getGender());
        person.setSSN(personRequest.getSsn());
        person.setDOB(personRequest.getDob());
        personDAO.setPerson(person);
    }

    @Transactional
    public void updateEmployeeByUserId(int userId, EmployeeRequest employeeRequest) {
        Person person = this.personDAO.getPersonByUserId(userId);
        Employee employee = person.getEmployee();
        employee.setTitle(employeeRequest.getTitle());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setEndDate(employeeRequest.getEndDate());
        employee.setAvatar(employeeRequest.getAvatar());
        employee.setCar(employeeRequest.getCar());
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusByName(employeeRequest.getVisaStatus().get("visaType"));
        employee.setVisaStatus(visaStatus);
        employee.setVisaStartDate(employeeRequest.getVisaStatus().get("visaStartDate"));
        employee.setVisaEndDate(employeeRequest.getVisaStatus().get("visaEndDate"));
        employee.setDriverLicense(employeeRequest.getDriverLicense());
        employee.setDriverLicenseExpirationDate(employeeRequest.getDriverLicenseExpirationDate());
        employeeDAO.setEmployee(employee);
    }

    @Transactional
    public void updateAddressByUserId(int userId, AddressRequest addressRequest) {
        Person person = this.personDAO.getPersonByUserId(userId);
        Address address = person.getAddress();
        address.setAddressLineOne(addressRequest.getAddressLineOne());
        address.setAddressLineTwo(addressRequest.getAddressLineTwo());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        address.setStateName(addressRequest.getStateName());
        address.setStateAbbr(addressRequest.getStateAbbr());
        addressDAO.setAddress(address);
    }

    @Transactional
    public void updateContactsByUserId(int userId, ContactRequest contactRequest) {
        Person person = this.personDAO.getPersonByUserId(userId);
        List<Contact> contacts = person.getContacts();
        for (Contact c : contacts) {
            if (c.isReference()) {
                c.setTitle(contactRequest.getReference().get("title"));
                c.setRelationship(contactRequest.getReference().get("relationship"));
            }
            if (c.isEmergency()) {
                c.setTitle(contactRequest.getEmergency().get("title"));
                c.setRelationship(contactRequest.getEmergency().get("relationship"));
            }
            contactDAO.setContact(c);
        }
    }

    @Transactional
    public void updatePersonalDocumentsByUserId(int userId, List<PersonalDocumentRequest> personalDocumentRequests) {
        Person person = this.personDAO.getPersonByUserId(userId);
        Employee employee = person.getEmployee();
        List<PersonalDocument> oldPersonalDocuments = employee.getPersonalDocuments();

        for (int i = 0; i < personalDocumentRequests.size(); i++) {
            PersonalDocumentRequest personalDocumentRequest = personalDocumentRequests.get(i);
            for (PersonalDocument oldP : oldPersonalDocuments) {
                if (oldP.getTitle().equals(personalDocumentRequest.getTitle())) {
                    oldP.setPath(personalDocumentRequest.getPath());
                    personalDocumentDAO.setPersonalDocument(oldP);
                    break;
                }
            }
        }
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Autowired
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }
}
