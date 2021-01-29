package com.beaconfireboba.backend.controller.employee;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.domain.employee.person.PersonResponse;
import com.beaconfireboba.backend.entity.Person;
import com.beaconfireboba.backend.service.employee.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/employee")
public class ProfileController {
    private PersonService personService;

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

    private void prepareResponse(PersonResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
