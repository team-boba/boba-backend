package com.beaconfireboba.backend.service.employee;

import com.beaconfireboba.backend.dao.*;
import com.beaconfireboba.backend.domain.onboarding.AddressRequest;
import com.beaconfireboba.backend.domain.onboarding.EmployeeRequest;
import com.beaconfireboba.backend.domain.onboarding.PersonRequest;
import com.beaconfireboba.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OnboardingService {

    private PersonDAO personDAO;
    private AddressDAO addressDAO;
    private EmployeeDAO employeeDAO;
    private ContactDAO contactDAO;
    private PersonalDocumentDAO personalDocumentDAO;
    private VisaStatusDAO visaStatusDAO;
    private ApplicationWorkflowDAO applicationWorkflowDAO;
    private HouseDAO houseDAO;

    @Autowired
    public void setPersonDao(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setAddressDao(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setContactDao(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setPersonalDocumentDao(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    @Autowired
    public void setVisaStatusDao(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setApplicationWorkflowDao(ApplicationWorkflowDAO applicationWorkflowDAO) {
        this.applicationWorkflowDAO = applicationWorkflowDAO;
    }

    @Autowired
    public void setHouseDao(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Transactional
    public boolean saveOnboarding(PersonRequest personRequest, AddressRequest addressRequest, EmployeeRequest employeeRequest) {
        // save application work flow
        ApplicationWorkflow applicationWorkflow = new ApplicationWorkflow();
        applicationWorkflow.setCreatedDate("2021-01-26-00-00");
        applicationWorkflow.setModificationDate("2021-01-26-00-00");
        applicationWorkflow.setStatus("pending");
        applicationWorkflow.setType("onboarding");

        // save employee
        Employee employee = new Employee();
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(1);
        House house = houseDAO.getHouseById(1);

        employee.setTitle(employeeRequest.getTitle());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setEndDate(employeeRequest.getEndDate());
        employee.setAvatar(employeeRequest.getAvatar());
        employee.setCar(employeeRequest.getCar());
        employee.setVisaStartDate(employeeRequest.getVisaStatus().get("visaStartDate"));
        employee.setVisaEndDate(employeeRequest.getVisaStatus().get("visaEndDate"));
        employee.setDriverLicense(employeeRequest.getDriverLicense());
        employee.setDriverLicenseExpirationDate(employeeRequest.getDriverLicenseExpirationDate());
        employee.setVisaStatus(visaStatus);
        employee.setHouse(house);

        // save address
        Address address = new Address();
        address.setAddressLineOne(addressRequest.getAddressLineOne());
        address.setAddressLineTwo(addressRequest.getAddressLineTwo());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        address.setStateName(addressRequest.getStateName());
        address.setStateAbbr(addressRequest.getStateAbbr());

        // save person
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setMiddleName(personRequest.getMiddleName());
        person.setEmail(personRequest.getEmail());
        person.setCellphone(personRequest.getCellPhone());
        person.setAlternatePhone(personRequest.getAlternatePhone());
        person.setGender(personRequest.getGender());
        person.setSSN(personRequest.getSsn());
        person.setDOB(personRequest.getDob());

        person = personDAO.setPerson(person);

        address.setPerson(person);
        address = addressDAO.setAddress(address);

        employee.setPerson(person);
        employee = employeeDAO.setEmployee(employee);

        applicationWorkflow.setEmployee(employee);
        applicationWorkflow = applicationWorkflowDAO.setApplicationWorkflow(applicationWorkflow);

        return true;
    }
}
