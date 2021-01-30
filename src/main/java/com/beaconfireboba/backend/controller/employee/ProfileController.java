package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.domain.onboarding.*;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.service.employee.ProfileService;
import com.beaconfireboba.backend.service.employee.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/employee")
public class ProfileController {
    private PersonService personService;
    private ProfileService profileService;

    @GetMapping(value="/profile/{userId}")
    public PersonResponse getProfile(@PathVariable String userId) {
        PersonResponse personResponse = new PersonResponse();
        Person person = this.personService.getPersonByUserId(Integer.parseInt(userId));
        if (person != null) {
            personResponse.setPerson(person);
            prepareResponse(personResponse, true, "");
        } else {
            prepareResponse(personResponse, false, "No person found.");
        }

        return personResponse;
    }

    @PostMapping(value="/profile/update-person/{userId}")
    public void updatePerson(@RequestBody PersonRequest personRequest, @PathVariable String userId) {
        this.profileService.updatePersonByUserId(Integer.parseInt(userId), personRequest);
    }

    @PostMapping(value="/profile/update-employee/{userId}")
    public void updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable String userId) {
        this.profileService.updateEmployeeByUserId(Integer.parseInt(userId), employeeRequest);
    }

    @PostMapping(value="/profile/update-address/{userId}")
    public void updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable String userId) {
        this.profileService.updateAddressByUserId(Integer.parseInt(userId), addressRequest);
    }

    @PostMapping(value="/profile/update-contact/{userId}")
    public void updateContacts(@RequestBody ContactRequest contactRequest, @PathVariable String userId) {
        this.profileService.updateContactsByUserId(Integer.parseInt(userId), contactRequest);
    }

    @PostMapping(value="/profile/update-personal-documents/{userId}")
    public void updatePersonDocuments(@RequestBody ListPersonalDocumentsRequest listPersonalDocumentsRequest, @PathVariable String userId) {
        this.profileService.updatePersonalDocumentsByUserId(Integer.parseInt(userId), listPersonalDocumentsRequest.getPersonalDocumentRequests());
    }

    private void prepareResponse(PersonResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
