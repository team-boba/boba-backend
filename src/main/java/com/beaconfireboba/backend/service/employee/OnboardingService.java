package com.beaconfireboba.backend.service.employee;

import com.beaconfireboba.backend.dao.*;
import com.beaconfireboba.backend.domain.onboarding.*;
import com.beaconfireboba.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public int saveOnboarding(PersonRequest personRequest, AddressRequest addressRequest, EmployeeRequest employeeRequest, ContactRequest contactRequest, List<PersonalDocumentRequest> personalDocumentRequests) {
        String currentDate = getCurrentDateTime();

        // save application work flow
        ApplicationWorkflow applicationWorkflow = new ApplicationWorkflow();
        applicationWorkflow.setCreatedDate(currentDate);
        applicationWorkflow.setModificationDate(currentDate);
        applicationWorkflow.setStatus("pending");
        applicationWorkflow.setType("onboarding");

        // save employee
        Employee employee = new Employee();
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusByName(employeeRequest.getVisaStatus().get("visaType"));
        House house = houseDAO.getHouseById(employeeRequest.getHouseId());

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
        person.setUserId(personRequest.getUserId());

        person = personDAO.setPerson(person);

        address.setPerson(person);
        address = addressDAO.setAddress(address);

        employee.setPerson(person);
        employee = employeeDAO.setEmployee(employee);

        applicationWorkflow.setEmployee(employee);
        applicationWorkflow = applicationWorkflowDAO.setApplicationWorkflow(applicationWorkflow);

        // save personal document
        for (int i = 0; i < personalDocumentRequests.size(); i++) {
            PersonalDocumentRequest personalDocumentRequest = personalDocumentRequests.get(i);
            PersonalDocument personalDocument = new PersonalDocument();
            personalDocument.setTitle(personalDocumentRequest.getTitle());
            personalDocument.setPath(personalDocumentRequest.getPath());
            personalDocument.setCreatedDate(currentDate);
            personalDocument.setCreateBy(person.getFirstName());
            personalDocument.setEmployee(employee);
            personalDocumentDAO.setPersonalDocument(personalDocument);
        }

        // save contact
        Contact reference = new Contact();
        reference.setTitle(contactRequest.getReference().get("title"));
        reference.setRelationship(contactRequest.getReference().get("relationship"));
        reference.setReference(true);
        reference.setPerson(person);
        contactDAO.setContact(reference);

        Contact emergency = new Contact();
        emergency.setTitle(contactRequest.getEmergency().get("title"));
        emergency.setRelationship(contactRequest.getEmergency().get("relationship"));
        emergency.setEmergency(true);
        emergency.setPerson(person);
        contactDAO.setContact(emergency);

        return person.getUserId();
    }

    public String getCurrentDateTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-h-mm");
        String strDate = dateFormat.format(date);
        return strDate;
    }


}
